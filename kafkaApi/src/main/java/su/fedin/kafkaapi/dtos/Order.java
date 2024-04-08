package su.fedin.kafkaapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    int id;
    int user_id;
    String goods;
    int cost;
}
