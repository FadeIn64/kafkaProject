package su.fedin.kafkaserver.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    int id;
    int user_id;
    String goods;
    int cost;
}
