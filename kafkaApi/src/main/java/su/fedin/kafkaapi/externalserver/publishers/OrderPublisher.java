package su.fedin.kafkaapi.externalserver.publishers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import su.fedin.kafkaapi.dtos.Order;

@Component
public class OrderPublisher {
    private KafkaTemplate<Integer, Order> orderTemplate;
    private String orderTopic;

    @Autowired
    public OrderPublisher(KafkaTemplate<Integer, Order> orderTemplate, String orderTopic) {
        this.orderTemplate = orderTemplate;
        this.orderTopic = orderTopic;
    }

    public ResponseEntity<String> createOrder(Order order){
        orderTemplate.send(orderTopic, order.getUser_id(), order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
