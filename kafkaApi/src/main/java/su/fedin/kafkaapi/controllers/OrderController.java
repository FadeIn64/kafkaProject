package su.fedin.kafkaapi.controllers;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import su.fedin.kafkaapi.dtos.Order;
import su.fedin.kafkaapi.externalserver.ExternalServerRequester;

@RestController
public class OrderController {

    ExternalServerRequester requester;

    public OrderController(ExternalServerRequester requester) {
        this.requester = requester;
    }

    @GetMapping("/order/{id}")
    ResponseEntity<Order> getOrder(@PathVariable int id){
        try {
            return requester.getOrderById(id);
        }
        catch (HttpClientErrorException e){
            return new ResponseEntity<>(HttpStatusCode.valueOf(e.getStatusCode().value()));
        }
    }

    @PostMapping("/order")
    ResponseEntity<String> createOrder(@RequestBody Order order){
        return requester.createOrder(order);
    }

}
