package br.com.gabrieldev.ecommerce;

import br.com.gabrieldev.ecommerce.domain.Order;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Map;

public class FraudDetectorService {

    private static final String TOPIC_NEW_ORDER = "ECOMMERCE_NEW_ORDER";

    public static void main(String[] args) {
        var fraudDetectorService = new FraudDetectorService();
        var service = new KafkaService(
            FraudDetectorService.class.getSimpleName(),
            TOPIC_NEW_ORDER,
            fraudDetectorService::parse,
            Order.class,
            Map.of()
        );
        service.run();
    }

    private void parse(ConsumerRecord<String, String> record){
        System.out.printf(
            "\n--------------\n %s :::parition %s / :::offset %s / :::timestamp %s / :::value %s",
            record.topic(), record.partition(), record.offset(), record.timestamp(), record.value()
        );
    }
}
