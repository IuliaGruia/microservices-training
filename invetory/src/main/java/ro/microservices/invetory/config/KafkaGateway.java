package ro.microservices.invetory.config;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ro.microservices.invetory.models.ProductModel;

@MessagingGateway
public interface KafkaGateway {

    @Gateway(requestChannel = "stockChannel")
    void write (ProductModel productModel);
}
