package su.fedin.kafkaapi;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import su.fedin.kafkaapi.dtos.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsersControll {
    public static List<Integer> users = new ArrayList();

    public void addWait(int user_id){
        users.add(user_id);
    }

    @KafkaListener(topics = "orders-response", groupId = "group2",
            containerFactory = "userKafkaListenerContainerFactory")
    void createOrder(User user){
        if (!users.contains(user.getId()))
            throw new RuntimeException("User didn't send order");
        users.remove(Integer.valueOf(user.getId()));
        System.out.println("Order accept for :\t" + user);
    }
}
