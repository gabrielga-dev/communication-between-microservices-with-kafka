package br.com.gabrieldev.ecommerce;

import br.com.gabrieldev.ecommerce.domain.Email;
import br.com.gabrieldev.ecommerce.domain.Order;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Map;

public class EmailService {

    private static final String TOPIC_SEND_EMAIL = "ECOMMERCE_SEND_EMAIL";

    public static void main(String[] args) {
        var emailService = new EmailService();
        var service = new KafkaService(
            EmailService.class.getSimpleName(),
            TOPIC_SEND_EMAIL,
            emailService::parse,
            Email.class,
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
