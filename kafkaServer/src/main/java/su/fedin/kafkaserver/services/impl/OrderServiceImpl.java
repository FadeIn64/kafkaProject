package su.fedin.kafkaserver.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.fedin.kafkaserver.dtos.OrderDTO;
import su.fedin.kafkaserver.entities.Order;
import su.fedin.kafkaserver.repos.OrderRepo;
import su.fedin.kafkaserver.services.OrderService;
import su.fedin.kafkaserver.mappers.OrderMapper;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrderRepo orderRepo, OrderMapper orderMapper) {
        this.orderRepo = orderRepo;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        orderDTO.setId(0);
        Order order = orderMapper.wrap(orderDTO);
        order = orderRepo.save(order);
        System.out.println(order);
        return orderMapper.wrap(order);
    }

    @Override
    public OrderDTO getOrder(OrderDTO orderDTO) {
        Order order = orderRepo.findById(orderDTO.getId()).orElse(new Order());
        return orderMapper.wrap(order);
    }
}
