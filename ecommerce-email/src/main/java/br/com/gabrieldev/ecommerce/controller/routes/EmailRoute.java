package br.com.gabrieldev.ecommerce.controller.routes;

import br.com.gabrieldev.ecommerce.service.processor.EmailProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailRoute extends RouteBuilder {

    @Value("${kafka.email.endpoint}")
    private String kafkaEmailTopicEndpoint;

    @Autowired
    private EmailProcessor emailProcessor;

    @Override
    public void configure() {
        from(kafkaEmailTopicEndpoint)
                .process(emailProcessor)
                .end();
    }
}
