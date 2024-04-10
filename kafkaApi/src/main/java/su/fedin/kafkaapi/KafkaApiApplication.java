package su.fedin.kafkaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import su.fedin.kafkaapi.dtos.Order;
import su.fedin.kafkaapi.dtos.User;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class KafkaApiApplication {

    public static void main(String[] args) {
        var context =  SpringApplication.run(KafkaApiApplication.class, args);

    }


}
