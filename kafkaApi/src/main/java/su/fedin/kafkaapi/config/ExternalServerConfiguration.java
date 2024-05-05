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

    @Bean
    @Qualifier("uri")
    public String uri(){
        return String.format("%s://%s:%s", protocol, domain, port);
    }
}
