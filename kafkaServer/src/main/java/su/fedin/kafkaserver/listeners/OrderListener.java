package su.fedin.kafkaserver.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import su.fedin.kafkaserver.dtos.OrderDTO;
import su.fedin.kafkaserver.services.OrderService;

@Component
public class OrderListener {

    OrderService orderService;

    @Autowired
    public OrderListener(OrderService orderService) {
        this.orderService = orderService;
    }

    @KafkaListener(topics = "${kafka.topic.order}", groupId = "group1",
            containerFactory = "orderKafkaListenerContainerFactory")
    void createOrder(OrderDTO orderDTO){
        orderService.create(orderDTO);
    }
}
