package su.fedin.kafkaapi.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonSerializer;
import su.fedin.kafkaapi.dtos.Order;
import su.fedin.kafkaapi.dtos.User;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${kafka.topic.order}")
    private String orderTopic;

    @Value("${kafka.topic.user}")
    private String userTopic;


    public ProducerFactory<Integer, Order> orderProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean("OrderTemplate")
    public KafkaTemplate<Integer, Order> orderKafkaTemplate() {
        return new KafkaTemplate<>(orderProducerFactory());
    }

    public ProducerFactory<Integer, User> userProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean("UserTemplate")
    public KafkaTemplate<Integer, User> userKafkaTemplate() {
        return new KafkaTemplate<>(userProducerFactory());
    }

    @Bean
    public NewTopic orders() {
        return TopicBuilder.name(orderTopic)
                .partitions(1).
                build();
    }

    @Bean
    public NewTopic users() {
        return TopicBuilder.name(userTopic)
                .partitions(1).
                build();
    }
}
