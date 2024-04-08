package su.fedin.kafkaserver;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.support.serializer.ToStringSerializer;
import su.fedin.kafkaserver.entities.Order;
import su.fedin.kafkaserver.entities.User;
import su.fedin.kafkaserver.repos.OrderRepo;
import su.fedin.kafkaserver.repos.UserRepo;

@SpringBootApplication
@EnableJpaRepositories
public class KafkaServerApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(KafkaServerApplication.class, args);

        UserRepo repo = context.getBean(UserRepo.class);
        OrderRepo orderRepo = context.getBean(OrderRepo.class);
        var user = new User();
        user.setName("Andrey");
        user = repo.save(user);
        orderRepo.save(new Order(0, "Sdsd", user, 50));
        var order = orderRepo.findById(1).get();
        System.out.println(order);

    }

}
