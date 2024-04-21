package su.fedin.kafkaserver.utils.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import su.fedin.kafkaserver.dtos.UserDTO;
import su.fedin.kafkaserver.entities.User;
import su.fedin.kafkaserver.repos.UserRepo;

@Component
public class UserMapper {
    private UserRepo userRepo;

    @Autowired
    public UserMapper(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserDTO mapToDTO(User user){
        UserDTO userDTO = new UserDTO();

        userDTO.setName(user.getName());
        userDTO.setId(user.getId());

        return userDTO;
    }

    public User mapToEntity(UserDTO user){
        return userRepo.findById(user.getId()).orElse(new User(0, user.getName()));
    }
}
