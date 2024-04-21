package su.fedin.kafkaserver.services;

import su.fedin.kafkaserver.dtos.OrderDTO;
import su.fedin.kafkaserver.entities.Order;
import su.fedin.kafkaserver.entities.User;

import java.util.List;

public interface OrderService {
    OrderDTO create(OrderDTO orderDTO);
    OrderDTO getOrder(OrderDTO orderDTO);
    List<OrderDTO> findAllByUser(int userID);
}
