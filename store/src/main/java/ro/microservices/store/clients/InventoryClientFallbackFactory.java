package ro.microservices.store.clients;

import feign.hystrix.FallbackFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class InventoryClientFallbackFactory implements FallbackFactory<InventoryClient> {

    @Override
    public InventoryClient create(Throwable throwable) {
        return new InventoryClient() {
            @Override
            public ResponseEntity<InventoryModel> getProductInventory(String code) {
                return ResponseEntity.ok(
                        InventoryModel.builder()
                        .code(code)
                        .price(BigDecimal.ZERO)
                        .build()
                );
            }
        };
    }
}
