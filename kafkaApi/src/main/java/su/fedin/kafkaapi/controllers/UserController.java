package su.fedin.kafkaapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import su.fedin.kafkaapi.dtos.User;
import su.fedin.kafkaapi.services.UserService;

@RestController
public class UserController {
    UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users/{id}")
    ResponseEntity getUser(@PathVariable int id){
        try {
            return service.getUser(id);
        }
        catch (HttpClientErrorException e){
            return new ResponseEntity<>(HttpStatusCode.valueOf(e.getStatusCode().value()));
        }
    }

    @GetMapping("/users/top10bycost")
    ResponseEntity getTopUsersByCost(){
        try {
            return service.getTopUsersByCost();
        }catch (HttpClientErrorException e){
            return new ResponseEntity<>(HttpStatusCode.valueOf(e.getStatusCode().value()));
        }
    }

    @PostMapping("/users")

    ResponseEntity<String> createOrder(@RequestBody User user){
        return service.createUser(user);
    }
}
