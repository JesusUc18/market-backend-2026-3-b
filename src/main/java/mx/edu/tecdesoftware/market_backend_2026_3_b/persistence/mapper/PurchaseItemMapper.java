package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.mapper;

import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.PurchaseItem;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.CompraProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PurchaseItemMapper {

    @Mappings({
            @Mapping(source = "id.idCompra", target = "purchaseId"),
            @Mapping(source = "id.idProducto", target = "productId"),
            @Mapping(source = "cantidad", target = "quantity"),
            @Mapping(source = "total", target = "total"),
            @Mapping(source = "estado", target = "active")
    })
    PurchaseItem toPurchaseItem(CompraProducto compraProducto);
    List<PurchaseItem> toPurchaseItems(List<CompraProducto> compraProductos);

    @InheritInverseConfiguration
    //Se ignoran las asociaciones para evitar ciclos (Compra <-> CompraProducto <-> Producto).
    //"compra" se asigna manualmente en el repositorio antes de guardar (integridad referencial).
    @Mapping(target = "compra", ignore = true)
    @Mapping(target = "producto", ignore = true)
    CompraProducto toCompraProducto(PurchaseItem purchaseItem);
    List<CompraProducto> toCompraProductos(List<PurchaseItem> purchaseItems);
}
