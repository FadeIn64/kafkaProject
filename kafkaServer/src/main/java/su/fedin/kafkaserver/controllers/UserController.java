package su.fedin.kafkaserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import su.fedin.kafkaserver.dtos.UserDTO;
import su.fedin.kafkaserver.services.UserService;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    ResponseEntity<UserDTO> getUser(@PathVariable int id){
        var user = userService.getUsers(id);
        if (user.getId() == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users/{id}/orders")
    ResponseEntity getUserOrders(@PathVariable int id){
        var list = userService.findAllByUser(id);
        if (list.size() == 0)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/users/top10bycost")
    ResponseEntity getTopUsersByCost(){
        var list = userService.getTopUsersBySumCost();
        if (list.size() == 0)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
