package su.fedin.kafkaserver.utils.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import su.fedin.kafkaserver.dtos.OrderDTO;
import su.fedin.kafkaserver.entities.Order;
import su.fedin.kafkaserver.repos.OrderRepo;
import su.fedin.kafkaserver.repos.UserRepo;

@Component
public class OrderMapper {
    private final OrderRepo orderRepo;
    private final UserRepo userRepo;

    @Autowired
    public OrderMapper(OrderRepo orderRepo, UserRepo userRepo) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
    }

    public OrderDTO mapToDTO(Order order){
        return new OrderDTO(order.getId(), order.getUser().getId(), order.getGoods(), order.getCost());
    }

    public Order mapToEntity(OrderDTO orderDTO){
        Order order = orderRepo.findById(orderDTO.getId())
                .orElse(new Order(0, orderDTO.getGoods(), null, orderDTO.getCost()));
        order.setUser(userRepo.findById(orderDTO.getUser_id()).get());
        return order;
    }
}
