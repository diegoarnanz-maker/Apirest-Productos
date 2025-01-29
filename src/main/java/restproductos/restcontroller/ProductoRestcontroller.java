package restproductos.restcontroller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import restproductos.modelo.entities.Producto;
import restproductos.modelo.services.IProductoService;
import restproductos.repository.IFamiliaRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/productos")
public class ProductoRestcontroller {

    @Autowired
    private IProductoService productoService;

    // @Autowired
    // private IFamiliaRepository familiaRepository;

    @GetMapping
    public ResponseEntity<?> listarProductos() {
        productoService.findAll();
        return ResponseEntity.status(200).body(productoService.findAll());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarProductoPorCodigo(@PathVariable int codigo) {
        Optional<Producto> producto = productoService.read(codigo);

        if (producto.isPresent()) {
            return ResponseEntity.status(200).body(producto.get());
        } else {
            return ResponseEntity.status(404)
                    .body(Collections.singletonMap("error", "Producto no encontrado" + codigo));
        }
    }

    @PostMapping
    public ResponseEntity<?> crearProducto(@RequestBody Producto producto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
        }
        productoService.create(producto);
        return ResponseEntity.status(201).body(producto);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<?> actualizarProducto(@RequestBody Producto producto, @PathVariable int codigo) {
        productoService.update(producto);
        return ResponseEntity.status(201).body(producto);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> eliminarProducto(@PathVariable int codigo) {
        productoService.delete(codigo);
        return ResponseEntity.status(204).build();
    }

}
