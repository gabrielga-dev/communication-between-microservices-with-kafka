package br.com.gabriel.dev.ecommerceorder.service.dispatcher;

import br.com.gabriel.dev.ecommerceorder.service.serializer.GsonSerializer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.Closeable;
import java.util.Properties;

/**
 * This class dispatches kafka messages of any given java class
 * @param <T> the type of the bady's value
 * @author Gabriel Guimarães de Almeida
 * */
public class KafkaDispatcher<T> implements Closeable {

    private final KafkaProducer<String, T> producer;

    /**
     * This constructor generates the needed kafka producer for dispatch the needed messages
     * @param kafkaPort {@link String} value of the current kafka server's address
     */
    public KafkaDispatcher(String kafkaPort){
        this.producer = new KafkaProducer<>(generateProperties(kafkaPort));
    }

    /**
     * This method dispatch the given value into a selected topic
     * @param topic {@link String} value to be declared as message's topic
     * @param key {@link String} value to be declared as message's key
     * @param value {@link T} value to be parsed into a json +{@link String} and sent as message's body
     * @param callback {@link Callback} callback funcion that will be executed once the message was dipatched
     */
    public void send(String topic, String key, T value, Callback callback) {
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
