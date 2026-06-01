package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "compra_producto")

public class CompraProducto {
    @EmbeddedId
    private CompraProductoPK id;

    //Saber productos que hay en una compra
    //Unir una tabla de compras
    @ManyToOne
    @JoinColumn(name = "id_compra", insertable = false, updatable = false)
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "ide_producto", insertable = false, updatable = false)
    private Producto producto;

    private Integer cantidad;
    private Double totaL;
    private Boolean estado;
}
