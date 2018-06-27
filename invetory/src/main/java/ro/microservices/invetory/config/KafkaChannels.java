package ro.microservices.invetory.config;


import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface KafkaChannels {

    @Output
    MessageChannel stockChannel();


}
