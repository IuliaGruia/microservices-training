package ro.microservices.store.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.microservices.store.entity.Product;
import ro.microservices.store.models.ProductModel;
import ro.microservices.store.services.ProductService;

import java.util.Collection;
import java.util.Objects;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/products")
public class ProductResource {

    private final ProductService productService;


    @Autowired
    public ProductResource(ProductService productService) {
        this.productService = Objects.requireNonNull(productService,"Product service shoud not be null");
    }

    @GetMapping("/list/{category}")
    public Collection<ProductModel> getcategoryProducts(@PathVariable("category") final long categId){
        return productService.getByCategoryId(categId);
    }

    @GetMapping
    public Collection<ProductModel> getByCode(@PathVariable("code") final String code){
       return productService.getByCode(code);

    }

}
