package su.fedin.kafkaapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import su.fedin.kafkaapi.dtos.Order;

@RestController
public class TestController {

    KafkaTemplate<Integer, Order> orderTemplate;
    UsersControll usersControll;

    @Autowired
    public TestController(KafkaTemplate<Integer, Order> orderTemplate, UsersControll usersControll) {
        this.orderTemplate = orderTemplate;
        this.usersControll = usersControll;
    }

    @GetMapping("/send/{user_id}")
    ResponseEntity send(@PathVariable Integer user_id){
        usersControll.addWait(user_id);
        orderTemplate.send("orders", 1, new Order(1, 1, "sadasdas", 50));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
