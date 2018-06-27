package ro.microservices.invetory.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.microservices.invetory.entities.Product;
import ro.microservices.invetory.models.ProductModel;
import ro.microservices.invetory.services.ProductService;

import java.util.Optional;


@RestController
@RequestMapping("/products")
public class ProductResource {

    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{code}")
    public ResponseEntity<ProductModel> getProductByCode(@PathVariable(name = "code") String prodCode){
        Optional<ProductModel> prod = productService.getByCode(prodCode);
        return prod.map(p ->
                ResponseEntity.ok(p))
                   .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
            }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody final ProductModel productModel){

    return ResponseEntity.ok(productService.save(productModel));
    }
}
