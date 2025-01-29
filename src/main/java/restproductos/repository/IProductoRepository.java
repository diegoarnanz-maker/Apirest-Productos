package restproductos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import restproductos.modelo.entities.Producto;

public interface IProductoRepository extends JpaRepository<Producto, Integer> {
    
    public List<Producto> findByMarcaAndColor(String marca, String color);
    public List<Producto> findByFamiliaCodigo(int codigoFamilia);
    public List<Producto> findByDescripcionContaining(String subcadena);

}
