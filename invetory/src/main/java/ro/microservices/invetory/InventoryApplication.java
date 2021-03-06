package ro.microservices.invetory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.stereotype.Component;
import ro.microservices.invetory.config.KafkaChannels;
import ro.microservices.invetory.entities.Product;
import ro.microservices.invetory.repositories.ProductRepository;

import java.math.BigDecimal;

@IntegrationComponentScan(basePackages = "ro.microservices.invetory")
@EnableEurekaClient
@SpringBootApplication
@EnableBinding(KafkaChannels.class)
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class InventoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(InventoryApplication.class, args);
    }
}

@Component
class DummyData implements CommandLineRunner {


    @Autowired
    private ProductRepository productRepository;


    @Override
    public void run(String... args) throws Exception {
        productRepository.save(
                Product.builder()
                .code("prod1")
                .price(new BigDecimal(8086))
                .build()
        )      ;


    }
}