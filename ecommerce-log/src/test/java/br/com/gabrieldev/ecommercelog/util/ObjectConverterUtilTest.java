package br.com.gabrieldev.ecommercelog.util;

import br.com.gabrieldev.ecommercelog.domain.entity.Log;
import br.com.gabrieldev.ecommercelog.util.constants.Constants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class tests every mehtod at {@link ObjectConverterUtil}
 *
 * @author Gabriel Guimarães de Almeida
 * */
public class ObjectConverterUtilTest {

    private ObjectConverterUtil objectConverterUtil;

    private ObjectMapper objectMapper;

    @BeforeEach
    private void beforeEach() throws JsonProcessingException {

        this.objectConverterUtil = new ObjectConverterUtil();
        this.objectMapper = mock(ObjectMapper.class);
        when(objectMapper.writeValueAsString(any())).thenThrow(new RuntimeException(Constants.TEST));
    }

    private ObjectMapper generateObjectMapper(){
        var objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.findAndRegisterModules();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @Test
    void toJson_whenObjectMapperCantParseTheJavaObjectIntoJson_thenThrowsResponseStatusException() {
        Assertions.assertThrows(
                ResponseStatusException.class,
                () -> objectConverterUtil.toJson(new Log())
        );
    }

    @Test
    void toJson_whenObjectMapperCanParseTheJavaObjectIntoJson_then() {

        this.objectConverterUtil = new ObjectConverterUtil();
        this.objectMapper = generateObjectMapper();

        ReflectionTestUtils.setField(objectConverterUtil, "objectMapper", objectMapper);

        var json = objectConverterUtil.toJson(new Log());

        Assertions.assertEquals(objectConverterUtil.toJson(new Log()), "{}");
    }
}
