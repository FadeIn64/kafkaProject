package su.fedin.kafkaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import su.fedin.kafkaserver.repos.OrderRepo;
import su.fedin.kafkaserver.repos.UserRepo;

@SpringBootApplication
@EnableJpaRepositories
public class KafkaServerApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(KafkaServerApplication.class, args);

        UserRepo repo = context.getBean(UserRepo.class);
        OrderRepo orderRepo = context.getBean(OrderRepo.class);
        var users = repo.myFindTop10ByAggregateCost();
        System.out.println(users);

    }

}
