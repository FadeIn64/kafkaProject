package su.fedin.kafkaapi.externalserver.publishers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import su.fedin.kafkaapi.dtos.User;

@Component
public class UserPublisher {
    private KafkaTemplate<Integer, User> userTemplate;
    private String userTopic;

    @Autowired
    public UserPublisher(KafkaTemplate<Integer, User> userTemplate, String userTopic) {
        this.userTemplate = userTemplate;
        this.userTopic = userTopic;
    }

    public ResponseEntity<String> createUser(User user){
        userTemplate.send(userTopic, user.getId(), user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
