package br.com.gabriel.dev.ecommerceorder.service.dispatcher;

import br.com.gabriel.dev.ecommerceorder.service.serializer.GsonSerializer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.Closeable;
import java.util.Properties;

public class KafkaDispatcher<T> implements Closeable {

    private final KafkaProducer<String, T> producer;
    private final Callback callback;

    public KafkaDispatcher(String kafkaPort, Callback callback){
        this.callback = callback;
        this.producer = new KafkaProducer<>(generateProperties(kafkaPort));
    }

    public void send(String topic, String key, T value) {
        var record = new ProducerRecord<>(topic, key, value);
        producer.send(record, callback);
    }

    private Properties generateProperties(String kafkaPort) {
        var props = new Properties();
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaPort);
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, GsonSerializer.class.getName());

        return props;
    }

    @Override
    public void close() {
        producer.close();
    }
}
