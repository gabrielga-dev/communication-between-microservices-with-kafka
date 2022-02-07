package br.com.gabriel.dev.ecommerceorder.configuration.beans;

import br.com.gabriel.dev.ecommerceorder.domain.dto.EmailDTO;
import br.com.gabriel.dev.ecommerceorder.domain.dto.OrderDTO;
import br.com.gabriel.dev.ecommerceorder.service.dispatcher.KafkaDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This configuration class creates the beans of kafka dispatchers for orders and emails
 *
 * @author Gabriel Guimarães de Almeida
 * */
@Slf4j
@Configuration
public class KafkaDispatcherBean {

    @Value("${kafka.port}")
    private String kafkaPort;

    @Bean
    public KafkaDispatcher<OrderDTO> getOrderKafkaDispatcher(){
        return new KafkaDispatcher<>(kafkaPort);
    }

    @Bean
    public KafkaDispatcher<EmailDTO> getEmailKafkaDispatcher(){
        return new KafkaDispatcher<>(kafkaPort);
    }
}
