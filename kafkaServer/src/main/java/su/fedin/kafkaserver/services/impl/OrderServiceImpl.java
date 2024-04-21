package su.fedin.kafkaserver.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.fedin.kafkaserver.dtos.OrderDTO;
import su.fedin.kafkaserver.entities.Order;
import su.fedin.kafkaserver.entities.User;
import su.fedin.kafkaserver.repos.OrderRepo;
import su.fedin.kafkaserver.services.OrderService;
import su.fedin.kafkaserver.utils.mappers.OrderMapper;

import java.util.List;

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
        Order order = orderMapper.mapToEntity(orderDTO);
        order = orderRepo.save(order);
        System.out.println(order);
        return orderMapper.mapToDTO(order);
    }

    @Override
    public OrderDTO getOrder(OrderDTO orderDTO) {
        Order order = orderRepo.findById(orderDTO.getId()).orElse(new Order());
        return orderMapper.mapToDTO(order);
    }

    @Override
    public List<OrderDTO> findAllByUser(int userID) {
        var user = new User(userID, "");
        var list = orderRepo.findAllByUser(user);
        return list.stream().map(orderMapper::mapToDTO).toList();
    }
}
