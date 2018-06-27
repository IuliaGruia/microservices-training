package ro.microservices.store.clients;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@FeignClient(name="inventory-service", fallbackFactory= InventoryClientFallbackFactory.class)
public interface InventoryClient {

    @GetMapping(value = "/products/{prodCode}")
    ResponseEntity<InventoryModel> getProductInventory(@PathVariable("prodCode")final String code);


//
//    private final RestTemplate restTemplate = new RestTemplate();
//
//
//    public InventoryModel getProductPrice(String code) {
//
//        String URL= "/products/"+code;
//        try {
//            return restTemplate.getForEntity("http://localhost:8080/api/inventory"+URL, InventoryModel.class).getBody();
//        } catch (Exception e) {
//            return InventoryModel.builder()
//                    .code("code1")
//                    .price(new BigDecimal(122))
//                    .build();
//        }
//    }
}

