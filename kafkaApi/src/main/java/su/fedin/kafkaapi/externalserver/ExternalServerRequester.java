package su.fedin.kafkaapi.externalserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import su.fedin.kafkaapi.dtos.Order;
import su.fedin.kafkaapi.dtos.User;

import java.util.List;

@Component
public class ExternalServerRequester {
    private ExternalServerProperties externalServerProperties;
    private RestTemplate externalServerTemplate;
    private KafkaTemplate<Integer, Order> orderTemplate;
    private KafkaTemplate<Integer, User> userTemplate;
    @Value("${kafka.topic.order}")
    private String orderTopic;
    @Value("${kafka.topic.user}")
    private String userTopic;


    @Autowired
    public ExternalServerRequester(ExternalServerProperties externalServerProperties,
                                   @Qualifier("ExternalServerTemplate") RestTemplate restTemplate,
                                   @Qualifier("OrderTemplate") KafkaTemplate<Integer, Order> orderTemplate,
                                   @Qualifier("UserTemplate") KafkaTemplate<Integer, User> userTemplate) {
        this.externalServerProperties = externalServerProperties;
        this.externalServerTemplate = restTemplate;
        this.orderTemplate = orderTemplate;
        this.userTemplate = userTemplate;

    }

    public ResponseEntity<Order> getOrderById(int id){
        return externalServerTemplate
                .getForEntity(String.format("%s/orders/%d",externalServerProperties.uri(), id), Order.class);
    }

    public ResponseEntity<String> createOrder(Order order){
        orderTemplate.send(orderTopic, order.getUser_id(), order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<User> getUser(int id){
        return externalServerTemplate.getForEntity(String.format("%s/users/%d",externalServerProperties.uri(), id), User.class);
    }

    public ResponseEntity<List> getTopUsersByCost(){
        return externalServerTemplate.getForEntity(String.format("%s/users/top10bycost",externalServerProperties.uri()), List.class);
    }

    public ResponseEntity<String> createUser(User user){
        userTemplate.send(userTopic, user.getId(), user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
