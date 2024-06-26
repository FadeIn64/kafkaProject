package su.fedin.kafkaserver.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import su.fedin.kafkaserver.entities.Order;
import su.fedin.kafkaserver.entities.User;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer>{

    List<Order> findAllByUser(User user);
}
