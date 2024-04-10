package su.fedin.kafkaserver.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import su.fedin.kafkaserver.dtos.OrderDTO;
import su.fedin.kafkaserver.dtos.UserDTO;
import su.fedin.kafkaserver.entities.Order;
import su.fedin.kafkaserver.entities.User;
import su.fedin.kafkaserver.mappers.OrderMapper;
import su.fedin.kafkaserver.mappers.UserMapper;
import su.fedin.kafkaserver.services.OrderService;

@Component
public class OrderListener {

    OrderService orderService;
    KafkaTemplate<Integer, UserDTO> userTemplate;
    OrderMapper orderMapper;
    UserMapper userMapper;

    @Autowired
    public OrderListener(OrderService orderService, KafkaTemplate<Integer, UserDTO> userTemplate, OrderMapper orderMapper, UserMapper userMapper) {
        this.orderService = orderService;
        this.userTemplate = userTemplate;
        this.orderMapper = orderMapper;
        this.userMapper = userMapper;
    }


    @KafkaListener(topics = "${kafka.topic.order}", groupId = "group1",
            containerFactory = "orderKafkaListenerContainerFactory")
    void createOrder(OrderDTO orderDTO){
        orderDTO = orderService.create(orderDTO);
        Order order = orderMapper.wrap(orderDTO);
        User user = order.getUser();
        UserDTO userDTO = userMapper.wrap(user);
        System.out.println(user);
        System.out.println(userDTO);
        userTemplate.send("orders-response", userDTO.getId(), userDTO);
    }
}
