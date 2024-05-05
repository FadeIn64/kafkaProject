package su.fedin.kafkaapi.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExternalServerConfiguration {

    @Value("${backend.server.domain}")
    String domain;
    @Value("${backend.server.port}")
    String port;
    @Value("${backend.server.protocol}")
    String protocol;
    @Value("${kafka.topic.order}")
    private String orderTopic;
    @Value("${kafka.topic.user}")
    private String userTopic;

    @Bean
    @Qualifier("uri")
    public String uri(){
        return String.format("%s://%s:%s", protocol, domain, port);
    }

    @Bean
    public String orderTopic(){return this.orderTopic;}

    @Bean
    public String userTopic(){return this.userTopic;}
}
