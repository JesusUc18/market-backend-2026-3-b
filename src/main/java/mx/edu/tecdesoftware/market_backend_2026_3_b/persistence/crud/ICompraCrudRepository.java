package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.crud;

import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.Compra;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ICompraCrudRepository extends CrudRepository<Compra, Integer> {

    //Query Method: obtiene la lista de compras filtradas por el id del cliente
    List<Compra> findByIdCliente(String idCliente);
}
