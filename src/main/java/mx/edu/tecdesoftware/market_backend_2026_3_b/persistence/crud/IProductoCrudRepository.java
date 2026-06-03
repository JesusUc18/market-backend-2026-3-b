package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.crud;

import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IProductoCrudRepository extends CrudRepository<Producto, Integer> {

    //Obtener la lista de productos filtrados por Id de categoria y ordenados ascendentemente por nombre

    List<Producto> findByCantidadOrderByNombreAsc (int idCategoria);

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado (int cantidad, boolean estado);

    boolean estado(Boolean estado);
}
