package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence;

import java.util.List;

import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.Product;
import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.repository.ProductRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.crud.IProductoCrudRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.Producto;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {

    @Autowired
    private IProductoCrudRepository productoCrudRepository;

    @Autowired
    private ProductMapper productMapper;

    public List<Product> getAll() {
        //Se "castea" Iterable a la lista
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return productMapper.toProducts(productos);
    }

    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByCantidadOrderByNombreAsc(categoryId);
        return Optional.of(productMapper.toProducts(productos));
    }

    public Optional<List<Product>> getScarceProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return Optional.of(productMapper.toProducts(productos.get()));
    }

    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(producto -> productMapper.toProduct(producto));
    }

    public Product save(Product product){
        Producto producto = productMapper.toProducto(product);
        return productMapper.toProduct(productoCrudRepository.save(producto));
    }

    public void delete(int productId) {
        productoCrudRepository.deleteById(productId);
    }
}
