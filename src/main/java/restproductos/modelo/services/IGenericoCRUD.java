package restproductos.modelo.services;

import java.util.List;
import java.util.Optional;

public interface IGenericoCRUD<E, ID> {
    
    List<E> findAll();

    E create(E entity);

    Optional<E> read(ID id);

    E update(E entity);

    void delete(ID id);

}
