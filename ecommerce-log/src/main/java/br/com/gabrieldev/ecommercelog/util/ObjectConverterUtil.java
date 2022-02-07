package br.com.gabrieldev.ecommercelog.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

/**
 * This class can parse java objects into json Strings
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@Getter
public class ObjectConverterUtil {

    @Autowired
    private ObjectMapper objectMapper;

    public String toJson(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Can't parse the objecto into json",
                    e
            );
        }
    }
}
