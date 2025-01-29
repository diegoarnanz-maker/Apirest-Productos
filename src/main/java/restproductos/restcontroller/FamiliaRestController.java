package restproductos.restcontroller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import restproductos.modelo.entities.Familia;
import restproductos.modelo.services.IFamiliaService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/familias")
public class FamiliaRestController {

    @Autowired
    private IFamiliaService familiaService;

    @GetMapping
    public ResponseEntity<List<Familia>> listarFamilias() {
        List<Familia> familias = familiaService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(familias);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarFamiliaPorId(@PathVariable int codigo) {
        Optional<Familia> familia = familiaService.read(codigo);
        if (familia.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(familia.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error", "Familia no encontrada: " + codigo));
        }
    }

    @PostMapping
    public ResponseEntity<?> crearFamilia(@RequestBody Familia familia) {
        try {
            Optional<Familia> nuevaFamilia = familiaService.create(familia);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaFamilia.get());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("error", e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Error interno al crear la familia"));
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<?> actualizarFamilia(@RequestBody Familia familia, @PathVariable int codigo) {
        try {
            if (familia.getCodigo() == null || familia.getCodigo() != codigo) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Collections.singletonMap("error", "El ID de la URL y el ID del cuerpo no coinciden"));
            }

            Familia familiaActualizada = familiaService.update(familia);
            return ResponseEntity.ok(familiaActualizada);

        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND) // CAMBIADO: `409 Conflict` -> `404 Not Found`
                    .body(Collections.singletonMap("error", e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Error interno al actualizar la familia"));
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> eliminarFamilia(@PathVariable int codigo) {
        familiaService.delete(codigo);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
