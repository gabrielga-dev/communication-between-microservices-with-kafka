package br.com.gabrieldev.ecommerce;

import br.com.gabrieldev.ecommerce.domain.Email;
import br.com.gabrieldev.ecommerce.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Slf4j
public class NewOrderMain {

    private static final String TOPIC_NEW_ORDER = "ECOMMERCE_NEW_ORDER";
    private static final String TOPIC_SEND_EMAIL = "ECOMMERCE_SEND_EMAIL";

    public static void main(String[] args) {
        try(var orderDispatcher = new KafkaDispatcher<Order>(generateCallback())){
            try(var emailDispatcher = new KafkaDispatcher<Email>(generateCallback())){
                for (int i = 0; i < 10; i++) {

                    var userId = UUID.randomUUID().toString();
                    var orderId = UUID.randomUUID().toString();
                    var amount = new BigDecimal((Math.random() * 5000) + 1);

                    var order = new Order(userId, orderId, amount);
                    var email = new Email(
                        "New Order!",
                        "Wellcome, " + userId + "! We're processing your order! Thanks for the preference!"
                    );

                    orderDispatcher.send(TOPIC_NEW_ORDER, userId, order);
                    emailDispatcher.send(TOPIC_SEND_EMAIL, userId, email);
                }
            }
        }
    }

    private static Callback generateCallback(){
        return (data, ex) -> {
            if (Objects.nonNull(ex)){
                ex.printStackTrace();
                return;
            }
            System.out.printf(
                    "%s :::parition %s / :::offset %s / :::timestamp %s",
                    data.topic(), data.partition(), data.offset(), data.timestamp()
            );
        };
    }
}
