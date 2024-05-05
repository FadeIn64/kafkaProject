package su.fedin.kafkaapi.externalserver.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import su.fedin.kafkaapi.dtos.Order;

@Component
public class OrderClient {

    private RestTemplate externalServerTemplate;
    private String uri;

    @Autowired
    public OrderClient(RestTemplate externalServerTemplate, String uri) {
        this.externalServerTemplate = externalServerTemplate;
        this.uri = uri;
    }

    public ResponseEntity<Order> getOrderById(int id){
        return externalServerTemplate
                .getForEntity(String.format("%s/orders/%d",this.uri, id), Order.class);
    }

}
