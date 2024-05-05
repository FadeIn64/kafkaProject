package su.fedin.kafkaapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import su.fedin.kafkaapi.dtos.User;
import su.fedin.kafkaapi.externalserver.clients.UserClient;
import su.fedin.kafkaapi.externalserver.publishers.UserPublisher;

import java.util.List;

@Service
public class UserService {
    private UserClient client;
    private UserPublisher publisher;

    @Autowired
    public UserService(UserClient client, UserPublisher publisher) {
        this.client = client;
        this.publisher = publisher;
    }

    public ResponseEntity<User> getUser(int id){
        return client.getUser(id);
    }

    public ResponseEntity<List> getTopUsersByCost(){
        return client.getTopUsersByCost();
    }

    public ResponseEntity<String> createUser(User user){
        return publisher.createUser(user);
    }
}
