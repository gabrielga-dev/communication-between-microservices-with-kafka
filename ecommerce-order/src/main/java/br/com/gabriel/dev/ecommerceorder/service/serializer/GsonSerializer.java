package br.com.gabriel.dev.ecommerceorder.service.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;

/**
 * This class is used as a string serializer, it will parse ours java objects into json {@link String} those will be
 * set as kafka message's body
 * @param <T> the type of the current value
 * @author Gabriel Guimarães de Almeida
 */
public class GsonSerializer<T> implements Serializer<T> {

    private final Gson gson = new GsonBuilder().create();

    /**
     * This method transform our data into a byte array
     * @param topic {@link String} topic associated to the data
     * @param data {@link T} the value thats about to be parsed
     * @return byte array resultant
     */
    @Override
    public byte[] serialize(String topic, T data) {
        return gson.toJson(data).getBytes(StandardCharsets.UTF_8);
    }
}
