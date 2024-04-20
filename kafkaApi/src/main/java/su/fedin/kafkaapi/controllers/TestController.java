package su.fedin.kafkaapi.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import su.fedin.kafkaapi.dtos.Order;

@RestController()
public class TestController {

//    @PostMapping("/order")
//    ResponseEntity<Order> testOrder(@RequestBody Order order){
//        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
//    }
}
