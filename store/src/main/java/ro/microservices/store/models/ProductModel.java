package ro.microservices.store.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.microservices.store.entity.Category;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class ProductModel {
    private String code;
    private String name;
    private Category category;
    private BigDecimal price;
    private Boolean isPublished;
}
