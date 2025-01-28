package restproductos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import restproductos.modelo.entities.Producto;

public interface IProductoRepository extends JpaRepository<Producto, Long> {
    // Producto findByNombre(String nombre);

    // Producto findByFamilia(Familia familia);

}
