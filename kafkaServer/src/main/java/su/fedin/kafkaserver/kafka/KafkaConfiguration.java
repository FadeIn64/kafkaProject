package su.fedin.kafkaserver.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import su.fedin.kafkaserver.dtos.OrderDTO;
import su.fedin.kafkaserver.dtos.UserDTO;
import su.fedin.kafkaserver.entities.User;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    private ConsumerFactory<Integer, OrderDTO> orderConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new DefaultKafkaConsumerFactory<>(props, new IntegerDeserializer(), jsonDeserializer());
    }

    private JsonDeserializer<OrderDTO> jsonDeserializer(){
        JsonDeserializer<OrderDTO> deserializer = new JsonDeserializer<>(OrderDTO.class);
        deserializer.addTrustedPackages("*");
        deserializer.ignoreTypeHeaders();
        return deserializer;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Integer, OrderDTO> orderKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Integer, OrderDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(orderConsumerFactory());
        return factory;
    }

    public ProducerFactory<Integer, UserDTO> orderProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean("UserTemplate")
    public KafkaTemplate<Integer, UserDTO> userKafkaTemplate() {
        return new KafkaTemplate<>(orderProducerFactory());
    }

    @Bean
    public NewTopic orders() {
        return TopicBuilder.name("orders-response")
                .partitions(2).
                build();
    }
}
