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

@Component
public class ExternalServerRequester {
    private ExternalServerProperties externalServerProperties;
    private RestTemplate externalServerTemplate;
    private KafkaTemplate<Integer, Order> orderTemplate;
    @Value("${kafka.topic.order}")
    private String orderTopic;

    @Autowired
    public ExternalServerRequester(ExternalServerProperties externalServerProperties,
                                   @Qualifier("ExternalServerTemplate") RestTemplate restTemplate,
                                   @Qualifier("OrderTemplate") KafkaTemplate<Integer, Order> orderTemplate) {
        this.externalServerProperties = externalServerProperties;
        this.externalServerTemplate = restTemplate;
        this.orderTemplate = orderTemplate;

    }

    public ResponseEntity<Order> getOrderById(int id){
        return externalServerTemplate
                .getForEntity(String.format("%s/orders/%d",externalServerProperties.uri(), id), Order.class);
    }

    public ResponseEntity<String> createOrder(Order order){
        var future =  orderTemplate.send(orderTopic, order.getUser_id(), order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
