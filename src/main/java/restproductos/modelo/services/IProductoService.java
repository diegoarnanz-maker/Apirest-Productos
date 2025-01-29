package restproductos.modelo.services;

import java.util.List;

import restproductos.modelo.entities.Producto;

public interface IProductoService extends IGenericoCRUD<Producto, Integer> {

    List<Producto> buscarPorFamilia(int codigoFamilia);
    List<Producto> buscarPorMarcaYColor(String marca, String color);
    double mediaPrecioProdPorFamilia(int codigoFamilia);
    List<Producto> sbCadenaDeDescripcion(String cadena);

}
