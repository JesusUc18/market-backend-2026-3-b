package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "compra_producto")

public class CompraProducto {
    @EmbeddedId
    private CompraProductoPK id;

    private Integer cantidad;
    private Double totaL;
    private Boolean estado;
}
