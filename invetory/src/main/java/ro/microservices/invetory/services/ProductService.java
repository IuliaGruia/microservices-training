package ro.microservices.invetory.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.microservices.invetory.config.KafkaGateway;
import ro.microservices.invetory.entities.Product;
import ro.microservices.invetory.mapper.ProductMapper;
import ro.microservices.invetory.models.ProductModel;
import ro.microservices.invetory.repositories.ProductRepository;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final KafkaGateway kafkaGateway;

    @Autowired
    public ProductService(ProductRepository productRepository, KafkaGateway kafkaGateway) {
        this.productRepository = productRepository;
        this.kafkaGateway = kafkaGateway;
    }

    public Optional<ProductModel> getByCode(final String prodCode){

        return productRepository.findByCode(prodCode).stream()
                .findFirst()
                .map(p -> ProductMapper.toModel(p));
            }

    public ProductModel save(ProductModel productModel) {
        Product prod = productRepository.findByCode(productModel.getCode()).stream()
                .findFirst()
                .map(p-> {
                    Integer initStoc = p.getStock();
                    p.setStock(productModel.getStock());
                    p.setPrice(productModel.getPrice());
                    if(initStoc != productModel.getStock()){
                        kafkaGateway.write(productModel);
                    };
                    return p;
                })
                    .orElseGet(()-> ProductMapper.toEntity(productModel));
        return ProductMapper.toModel(productRepository.save(prod));


        }
}
