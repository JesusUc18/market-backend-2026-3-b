package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence;

import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.Product;
import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.repository.ProductRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.crud.CompraProductoCrudRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.crud.ProductoCrudRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.Producto;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {

    @Autowired
    private ProductoCrudRepository productoCrudRepository;

    @Autowired
    private CompraProductoCrudRepository compraProductoCrudRepository;

    @Autowired
    private ProductMapper mapper;

    public List<Product> getAll(){
        // Se castea Iterable a lista
        return mapper.toProducts((List<Producto>) productoCrudRepository.findAll());
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos =
                productoCrudRepository.findByIdCategoriaOrderByNombreAsc(
                        Integer.valueOf(categoryId)
                );

        return Optional.of(mapper.toProducts(productos));
    }

    public Optional<List<Product>> getScarceProducts(int quantity){
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true)
                .map(productos -> mapper.toProducts(productos));
    }

    public Optional<Product> getProduct(int productId){
        return productoCrudRepository.findById(productId)
                .map(producto -> mapper.toProduct(producto));
    }

    public Product save(Product product){
        Producto producto = mapper.toProducto(product);
        producto.setIdProducto(null);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    @Transactional
    public void delete(int productId){
        productoCrudRepository.findById(productId)
                .orElseThrow(() -> new java.util.NoSuchElementException("Producto no encontrado"));

        compraProductoCrudRepository.deleteByIdIdProducto(productId);
        productoCrudRepository.deleteById(productId);
    }

}