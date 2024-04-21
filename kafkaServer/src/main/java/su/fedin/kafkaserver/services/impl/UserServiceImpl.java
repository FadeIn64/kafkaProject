package su.fedin.kafkaserver.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.fedin.kafkaserver.dtos.UserDTO;
import su.fedin.kafkaserver.entities.User;
import su.fedin.kafkaserver.mappers.UserMapper;
import su.fedin.kafkaserver.repos.UserRepo;
import su.fedin.kafkaserver.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;
    private UserMapper userMapper;


    @Autowired
    public UserServiceImpl(UserRepo userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }


    @Override
    public UserDTO getUsers(int id) {
        var user = userRepo.findById(id);
        return userMapper.wrap(user.orElse(new User()));
    }
}
