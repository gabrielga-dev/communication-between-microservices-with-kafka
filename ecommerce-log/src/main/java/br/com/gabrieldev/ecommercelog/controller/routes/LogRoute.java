package br.com.gabrieldev.ecommercelog.controller.routes;

import br.com.gabrieldev.ecommercelog.service.processor.LogProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * This class configure all routes for the kafka messages
 *
 * @author Gabriel Guimarães de Almeida
 * */
@Component
public class LogRoute extends RouteBuilder {

    @Value("${kafka.email.endpoint}")
    private String kafkaEmailTopicEndpoint;

    @Value("${kafka.new_order.endpoint}")
    private String kafkaNewOrderTopicEndpoint;

    @Autowired
    private LogProcessor logProcessor;

    @Override
    public void configure() throws Exception {
        from(kafkaEmailTopicEndpoint)
            .process(logProcessor);

        from(kafkaNewOrderTopicEndpoint)
            .process(logProcessor)
                .end();
    }
}
