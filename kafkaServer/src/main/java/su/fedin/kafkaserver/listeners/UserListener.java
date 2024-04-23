package su.fedin.kafkaserver.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import su.fedin.kafkaserver.dtos.UserDTO;
import su.fedin.kafkaserver.services.UserService;

@Component
public class UserListener {

    UserService userService;

    @Autowired
    public UserListener(UserService userService) {
        this.userService = userService;
    }

    @KafkaListener(topics = "${kafka.topic.user}", groupId = "group1",
            containerFactory = "userKafkaListenerContainerFactory")
    void createUser(UserDTO userDTO){
        userService.createUser(userDTO);
    }
}
