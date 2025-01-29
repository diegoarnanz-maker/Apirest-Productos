package restproductos.modelo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import restproductos.modelo.entities.Familia;
import restproductos.repository.IFamiliaRepository;

@Service
public class FamiliaServiceImplMy8 implements IFamiliaService {

    @Autowired
    private IFamiliaRepository familiaRepository;

    @Override
    public List<Familia> findAll() {
        try {
            return familiaRepository.findAll();
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'findAll'");
        }
    }

    @Override
    public Optional<Familia> create(Familia entity) {
        try {
            if (entity == null) {
                throw new IllegalArgumentException("La familia no puede ser null");
            }

            if (entity.getCodigo() != null && familiaRepository.existsById(entity.getCodigo())) {
                throw new IllegalStateException("Familia con ID " + entity.getCodigo() + " ya existe");
            }

            Familia nuevaFamilia = familiaRepository.save(entity);
            return Optional.of(nuevaFamilia);

        } catch (IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la familia", e);
        }
    }

    @Override
    public Optional<Familia> read(Integer id) {
        try {
            return familiaRepository.findById(id);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'read'");
        }
    }

    @Override
    public Familia update(Familia entity) {
        try {
            if (entity == null || entity.getCodigo() == null) {
                throw new IllegalArgumentException("El ID de la familia no puede ser null");
            }

            if (!familiaRepository.existsById(entity.getCodigo())) {
                throw new IllegalStateException(
                        "No se puede actualizar. La familia con ID " + entity.getCodigo() + " no existe");
            }

            return familiaRepository.save(entity);
        } catch (IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la familia", e);
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            if (familiaRepository.existsById(id)) {
                familiaRepository.deleteById(id);
            } else {
                throw new UnsupportedOperationException("Familia no existe");
            }
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'delete'");
        }
    }
}