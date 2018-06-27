package ro.microservices.store.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.microservices.store.clients.InventoryClient;
import ro.microservices.store.clients.InventoryModel;
import ro.microservices.store.mappers.ProductMapper;
import ro.microservices.store.models.ProductModel;
import ro.microservices.store.repositories.ProductRepository;

import java.util.Collection;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class ProductService {

    private ProductRepository productRepository;

    private InventoryClient inventoryClient;

    @Autowired
    public ProductService(ProductRepository productRepository, InventoryClient client) {
        this.productRepository = productRepository;
        this.inventoryClient = client;

    }
    public Collection<ProductModel> getByCategoryId(final long id){
        return productRepository.findByCategoryId(id).stream()
                .map(p-> {InventoryModel inventoryModel = inventoryClient.getProductInventory(p.getCode()).getBody();
                         return ProductMapper.toModel(p, inventoryModel);}
                                                )
                .collect(toList());
        }

    public Collection<ProductModel> getByCode(String code) {
        return productRepository.findByCode(code).stream()
                .map(ProductMapper::toModel)
                .collect(Collectors.toList());
    }
}
