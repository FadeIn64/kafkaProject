package su.fedin.kafkaserver.services;

import su.fedin.kafkaserver.dtos.OrderDTO;

public interface OrderService {
    OrderDTO create(OrderDTO orderDTO);
    OrderDTO getOrder(OrderDTO orderDTO);
}
