package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.crud;

import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.CompraProducto;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.CompraProductoPK;
import org.springframework.data.repository.CrudRepository;

public interface CompraProductoCrudRepository extends CrudRepository<CompraProducto, CompraProductoPK> {

    void deleteByIdIdProducto(Integer idProducto);
}