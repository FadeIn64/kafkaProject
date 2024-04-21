package su.fedin.kafkaserver.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import su.fedin.kafkaserver.entities.User;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    @Query(value = """
    select user_id as id, name, sum(o.cost) as sum
    from users inner join orders o on users.id = o.user_id
    group by user_id, name
    order by sum desc
    limit 10
""", nativeQuery = true)
    List<User> myFindTop10ByAggregateCost();

}
