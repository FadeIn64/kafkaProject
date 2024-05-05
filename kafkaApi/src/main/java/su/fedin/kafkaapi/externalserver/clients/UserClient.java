package su.fedin.kafkaapi.externalserver.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import su.fedin.kafkaapi.dtos.User;

import java.util.List;

@Component
public class UserClient {
    private RestTemplate externalServerTemplate;
    private String uri;

    @Autowired
    public UserClient(RestTemplate externalServerTemplate, String uri) {
        this.externalServerTemplate = externalServerTemplate;
        this.uri = uri;
    }

    public ResponseEntity<User> getUser(int id){
        return externalServerTemplate.getForEntity(String.format("%s/users/%d",this.uri, id), User.class);
    }

    public ResponseEntity<List> getTopUsersByCost(){
        return externalServerTemplate.getForEntity(String.format("%s/users/top10bycost",this.uri), List.class);
    }
}
