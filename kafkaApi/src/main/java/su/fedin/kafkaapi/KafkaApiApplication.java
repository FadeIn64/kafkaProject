package su.fedin.kafkaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import su.fedin.kafkaapi.dtos.Order;

import java.util.UUID;

@SpringBootApplication
public class KafkaApiApplication {

    public static void main(String[] args) {

        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);

        //var context =  SpringApplication.run(KafkaApiApplication.class, args);

        //var template = context.getBean("OrderTemplate", KafkaTemplate.class);
        //template.send("orders", 1, new Order(1, 1, "sadasdas", 50));
    }

}
