package ro.microservices.store.mappers;

import ro.microservices.store.clients.InventoryModel;
import ro.microservices.store.entity.Product;
import ro.microservices.store.models.ProductModel;

import java.math.BigDecimal;

public class ProductMapper {

    public static ProductModel toModel(final Product product, InventoryModel inventoryModel){
        return ProductModel.builder()
                .code(product.getCode())
                .name(product.getName())
                .category(product.getCategory())
                .price(inventoryModel.getPrice())
                .isPublished(product.getIsPublished())
                .build();

    }
    public static ProductModel toModel(final Product product){
        return ProductModel.builder()
                .code(product.getCode())
                .name(product.getName())
                .category(product.getCategory())
                .price(BigDecimal.ZERO)
                .build();

    }

}
