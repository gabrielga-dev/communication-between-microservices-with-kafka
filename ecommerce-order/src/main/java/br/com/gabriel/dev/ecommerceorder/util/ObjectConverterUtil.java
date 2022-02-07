package br.com.gabriel.dev.ecommerceorder.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

/**
 * This class can parse java objects into json Strings on our tests
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Component
public class ObjectConverterUtil {

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * This method recieve a java object and parse it into a json string
     *
     * @param value {@link Object} java object to be parsed
     * @return {@link String} string containing the json value
     * */
    public String toJson(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Can't parse the object into json",
                    e
            );
        }
    }
}
