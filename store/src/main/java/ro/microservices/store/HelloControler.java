package ro.microservices.store;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControler {



    @GetMapping(value = "/hello")
    public String helloWorld(){

        return "Hello World";


    }



}
