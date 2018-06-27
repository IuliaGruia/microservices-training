package ro.microservices.store.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import ro.microservices.store.clients.InventoryModel;
import ro.microservices.store.entity.Product;
import ro.microservices.store.repositories.ProductRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryReceiver {

    private final ProductRepository productRepository;

    @Autowired
    public InventoryReceiver(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @StreamListener("stockChannel")
    public void onReceiver(final Message<InventoryModel> message){
        InventoryModel inventoryModel = message.getPayload();

        Collection<Product> products =productRepository.findByCode(inventoryModel.getCode()).stream()
                            .map(p-> {
                                p.setIsPublished(inventoryModel.getStock()>0);
                                return p;
                            })
        .collect(Collectors.toList());

         productRepository.saveAll(products);
    }

}
