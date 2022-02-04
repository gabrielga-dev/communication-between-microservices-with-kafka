package br.com.gabrieldev.ecommerce;

import br.com.gabrieldev.ecommerce.config.GsonSerializer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.Closeable;
import java.util.Properties;

public class KafkaDispatcher<T> implements Closeable {

    public final KafkaProducer<String, T> producer;
    public final Callback callback;

    public KafkaDispatcher(Callback callback){
        this.callback = callback;
        this.producer = new KafkaProducer<>(generateProperties());
    }

    public void send(String topic, String key, T value) {
        var record = new ProducerRecord<>(topic, key, value);
        producer.send(record, callback);
    }

    private static Properties generateProperties() {
        var props = new Properties();
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, GsonSerializer.class.getName());

        return props;
    }

    @Override
    public void close() {
        producer.close();
    }
}
