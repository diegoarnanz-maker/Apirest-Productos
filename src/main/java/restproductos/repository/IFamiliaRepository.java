package restproductos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restproductos.modelo.entities.Familia;

public interface IFamiliaRepository extends JpaRepository<Familia, Long> {
    // Familia findByNombre(String nombre);

}
