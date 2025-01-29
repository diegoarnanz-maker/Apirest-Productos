package restproductos.modelo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restproductos.modelo.entities.Producto;
import restproductos.repository.IProductoRepository;

@Service
public class ProductoServiceImplMy8 implements IProductoService {

    @Autowired
    private IProductoRepository productoRepository;

    @Override
    public List<Producto> findAll() {
        try {
            return productoRepository.findAll();
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'findAll'");
        }
    }

    @Override
    public Optional<Producto> create(Producto entity) {
        try {
            if(productoRepository.existsById(entity.getCodigo())) {
                throw new UnsupportedOperationException("Producto ya existe");
            }
            return Optional.of(productoRepository.save(entity));
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'create'");
        }
    }

    @Override
    public Optional<Producto> read(Integer id) {
        try {
            return productoRepository.findById(id);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'read'");
        }
    }

    @Override
    public Producto update(Producto entity) {
        try {
            if(productoRepository.existsById(entity.getCodigo())) {
                return productoRepository.save(entity);
            } else {
                throw new UnsupportedOperationException("Producto no existe");
            }
        }
        catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'update'");
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            if (productoRepository.existsById(id)) {
                productoRepository.deleteById(id);
            } else {
                throw new UnsupportedOperationException("Producto no existe");
            }
        }
        catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'delete'");
        }
    }

    @Override
    public List<Producto> buscarPorFamilia(int codigoFamilia) {
        try {
            return productoRepository.findByFamiliaCodigo(codigoFamilia);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'buscarPorFamilia'");
        }
    }

    @Override
    public List<Producto> buscarPorMarcaYColor(String marca, String color) {
        try {
            return productoRepository.findByMarcaAndColor(marca, color);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'buscarPorMarcaYColor'");
        }
    }

    @Override
    public double mediaPrecioProdPorFamilia(int codigoFamilia) {
        try{
            List<Producto> productosPorFamilia = productoRepository.findByFamiliaCodigo(codigoFamilia);
            double suma = 0;
            for (Producto producto : productosPorFamilia) {
                suma += producto.getPrecioUnitario();
            }
            return suma / productosPorFamilia.size();
        }
        catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'mediaPrecioProdPorFamilia'");
        }
    }

    @Override
    public List<Producto> sbCadenaDeDescripcion(String cadena) {
        try {
            return productoRepository.findByDescripcionContaining(cadena);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'sbCadenaDeDescripcion'");
        }
    }

}
