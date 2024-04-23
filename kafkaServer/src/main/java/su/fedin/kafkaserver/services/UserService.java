package su.fedin.kafkaserver.services;

import su.fedin.kafkaserver.dtos.OrderDTO;
import su.fedin.kafkaserver.dtos.UserDTO;
import su.fedin.kafkaserver.entities.Order;

import java.util.List;

public interface UserService {

    UserDTO getUsers(int id);
    List<OrderDTO> findAllByUser(int userID);

    List<UserDTO> getTopUsersBySumCost();

}
