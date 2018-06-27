package ro.microservices.invetory.mapper;

import ro.microservices.invetory.entities.Product;
import ro.microservices.invetory.models.ProductModel;

public class ProductMapper {

public static ProductModel toModel(Product product){
     return ProductModel.builder()
    .code(product.getCode())
    .price(product.getPrice())
    .build();
}
     public static Product toEntity(ProductModel productModel) {
               return Product.builder()
                       .code(productModel.getCode())
                       .price(productModel.getPrice())
                       .stock(productModel.getStock())
                       .build();
     }
}
