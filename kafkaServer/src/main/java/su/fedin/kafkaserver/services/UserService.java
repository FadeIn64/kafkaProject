package su.fedin.kafkaserver.services;

import su.fedin.kafkaserver.dtos.UserDTO;

public interface UserService {

    UserDTO getUsers(int id);

}
