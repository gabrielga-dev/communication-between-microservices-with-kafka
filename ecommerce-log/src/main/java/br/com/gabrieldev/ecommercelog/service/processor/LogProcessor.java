package br.com.gabrieldev.ecommercelog.service.processor;

import br.com.gabrieldev.ecommercelog.service.LogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

/**
 * This class make the processing of each of the messages that com from kafka server
 *
 * @author Gabriel Guimarães de Almeida
 * */
@Slf4j
@Component
@RequiredArgsConstructor
public class LogProcessor implements Processor {

    private final LogService logService;

    private static final String KAFKA_TOPIC_HEADER_KEY = "kafka.TOPIC";

    @Override
    public void process(Exchange exchange) {
        logService.save(
                (String) exchange.getIn().getHeader(KAFKA_TOPIC_HEADER_KEY),
                (String) exchange.getMessage().getBody()
        );
    }
}
