package su.fedin.kafkaapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import su.fedin.kafkaapi.dtos.Order;
import su.fedin.kafkaapi.externalserver.clients.OrderClient;
import su.fedin.kafkaapi.externalserver.publishers.OrderPublisher;

@Service
public class OrderService {
    private OrderClient client;
    private OrderPublisher publisher;

    @Autowired
    public OrderService(OrderClient client, OrderPublisher publisher) {
        this.client = client;
        this.publisher = publisher;
    }

    public ResponseEntity<Order> getOrderById(int id){
        return client.getOrderById(id);
    }

    public ResponseEntity<String> createOrder(Order order){
        return publisher.createOrder(order);
    }
}
