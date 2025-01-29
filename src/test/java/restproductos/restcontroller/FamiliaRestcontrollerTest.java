package restproductos.restcontroller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import jakarta.transaction.Transactional;
import restproductos.modelo.entities.Familia;
import restproductos.modelo.services.IFamiliaService;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class FamiliaRestcontrollerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IFamiliaService familiaService;

    @Test
    public void testListarFamilias() throws Exception {
        mockMvc.perform(get("/api/familias"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testReadFamilia() throws Exception {
        mockMvc.perform(get("/api/familias/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigo").value(1));

        mockMvc.perform(get("/api/familias/10000"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateFamilia() throws Exception {

        // Caso exitoso: Crear una nueva familia sin ID ya en la BBDD
        Familia newFamilia = Familia.builder().descripcion("Nueva Familia").build();
        familiaService.create(newFamilia);

        mockMvc.perform(post("/api/familias")
                .contentType("application/json")
                .content("{\"descripcion\": \"Nueva Familia\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.descripcion").value("Nueva Familia"));

        // Caso de error: Intentar crear una familia con un ID ya existente
        Familia familiaExistente = familiaService.create(Familia.builder().descripcion("Familia Existente").build())
                .get();

        mockMvc.perform(post("/api/familias")
                .contentType("application/json")
                .content("{\"codigo\": " + familiaExistente.getCodigo() + ", \"descripcion\": \"Familia Existente\"}"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error").value("Familia con ID " + familiaExistente.getCodigo() + " ya existe"));
    }

    @Test
    public void updateFamilia() throws Exception {
        // Caso exitoso: Modificar una familia existente
        Familia familiaExistente = familiaService.create(Familia.builder().descripcion("Familia Existente").build())
                .get();

        mockMvc.perform(put("/api/familias/{codigo}", familiaExistente.getCodigo())
                .contentType("application/json")
                .content("{\"codigo\": " + familiaExistente.getCodigo() + ", \"descripcion\": \"Familia Modificada\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descripcion").value("Familia Modificada"));

        // Caso de error: Intentar modificar una familia inexistente
        mockMvc.perform(put("/api/familias/{codigo}", 10000)
                .contentType("application/json")
                .content("{\"codigo\": 10000, \"descripcion\": \"Familia Inexistente\"}"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("No se puede actualizar. La familia con ID 10000 no existe"));
    }

    @Test
    public void deleteFamilia() throws Exception {
        // Caso exitoso: Eliminar una familia existente
        Familia familiaExistente = familiaService.create(Familia.builder().descripcion("Familia Existente").build())
                .get();

        mockMvc.perform(get("/api/familias/{codigo}", familiaExistente.getCodigo()))
                .andExpect(status().isOk());

        // Caso de error: Intentar eliminar una familia inexistente
        mockMvc.perform(get("/api/familias/{codigo}", 10000))
                .andExpect(status().isNotFound());
    }

}
