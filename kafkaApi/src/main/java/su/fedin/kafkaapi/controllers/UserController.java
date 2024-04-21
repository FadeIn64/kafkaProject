package su.fedin.kafkaapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import su.fedin.kafkaapi.dtos.User;
import su.fedin.kafkaapi.externalserver.ExternalServerRequester;

@RestController
public class UserController {
    ExternalServerRequester requester;

    @Autowired
    public UserController(ExternalServerRequester requester) {
        this.requester = requester;
    }

    @GetMapping("/users/{id}")
    ResponseEntity getUser(@PathVariable int id){
        try {
            return requester.getUser(id);
        }
        catch (HttpClientErrorException e){
            return new ResponseEntity<>(HttpStatusCode.valueOf(e.getStatusCode().value()));
        }
    }
}
