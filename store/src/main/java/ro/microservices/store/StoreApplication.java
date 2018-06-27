package ro.microservices.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.stereotype.Component;
import ro.microservices.store.config.KafkaChannels;
import ro.microservices.store.entity.Category;
import ro.microservices.store.entity.Product;
import ro.microservices.store.repositories.CategoryRepository;
import ro.microservices.store.repositories.ProductRepository;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
@EnableBinding(KafkaChannels.class)
public class StoreApplication {
    public static void main(String[] args) {SpringApplication.run(StoreApplication.class, args);
    }

}
    @Component
    class DummyData implements CommandLineRunner {

        @Autowired
        private ProductRepository productRepository;

        @Autowired
        private CategoryRepository categoryRepository;

        @Override
        public void run(final String... args) throws Exception {
            final Category category = (Category) categoryRepository.save(
                    Category.builder()
                            .name("Test Categ")
                            .build()
            );
            productRepository.save(
                    Product.builder()
                            .code("prod1")
                    .category(category)
                    .build()
            );
        }
    }
