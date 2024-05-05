package su.fedin.kafkaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import su.fedin.kafkaapi.dtos.Order;

import java.util.UUID;

@SpringBootApplication
public class KafkaApiApplication {

    public static void main(String[] args) {

        var context =  SpringApplication.run(KafkaApiApplication.class, args);
    }

}
