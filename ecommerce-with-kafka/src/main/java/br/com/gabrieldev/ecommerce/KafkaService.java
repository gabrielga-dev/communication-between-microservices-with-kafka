package br.com.gabrieldev.ecommerce;

import br.com.gabrieldev.ecommerce.config.GsonDeserializer;
import br.com.gabrieldev.ecommerce.config.GsonSerializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.regex.Pattern;

public class KafkaService<T> {


    private final KafkaConsumer<String, T> consumer;
    private final ConsumerFunction parse;

    public KafkaService(
        String groupId, Pattern topic, ConsumerFunction parse, Class<T> type, Map<String, String> props
    ) {
        this.parse = parse;

        this.consumer = new KafkaConsumer<>(generateProperties(groupId, type, props));
        this.consumer.subscribe(topic);
    }

    public KafkaService(
        String groupId, String topic, ConsumerFunction parse, Class<T> type, Map<String, String> props
    ) {
        this.parse = parse;

        this.consumer = new KafkaConsumer<>(generateProperties(groupId, type, props));
        this.consumer.subscribe(Collections.singletonList(topic));
    }

    public void run(){
        while (true) {

            try {
                Thread.sleep(2500);
            } catch (Exception e) {
                //ignore
            }

            var records = this.consumer.poll(Duration.ofMillis(100));
            if (records.isEmpty()) {
                System.out.println("\nNo records");
                continue;
            }
            records.forEach(parse::consume);
        }
    }

    private Properties generateProperties(String groupId, Class<T> type, Map<String, String> customProps){
        var props = new Properties();
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, GsonDeserializer.class.getName());
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.setProperty(ConsumerConfig.CLIENT_ID_CONFIG, UUID.randomUUID().toString());
        props.setProperty(GsonDeserializer.TYPE_CONFIG, type.getName());

        customProps.forEach(props::setProperty);

        return props;
    }
}
