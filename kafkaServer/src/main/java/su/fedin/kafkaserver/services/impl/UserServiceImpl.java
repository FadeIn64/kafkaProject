package su.fedin.kafkaserver.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import su.fedin.kafkaserver.dtos.OrderDTO;
import su.fedin.kafkaserver.dtos.UserDTO;
import su.fedin.kafkaserver.entities.Order;
import su.fedin.kafkaserver.entities.User;
import su.fedin.kafkaserver.services.OrderService;
import su.fedin.kafkaserver.utils.mappers.UserMapper;
import su.fedin.kafkaserver.repos.UserRepo;
import su.fedin.kafkaserver.services.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;
    private UserMapper userMapper;
    private OrderService orderService;


    @Autowired
    public UserServiceImpl(UserRepo userRepo, UserMapper userMapper, @Lazy OrderService orderService) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.orderService = orderService;
    }


    @Override
    public UserDTO getUsers(int id) {
        var user = userRepo.findById(id);
        return userMapper.mapToDTO(user.orElse(new User()));
    }

    @Override
    public List<OrderDTO> findAllByUser(int userID) {
        return orderService.findAllByUser(userID);
    }
}
