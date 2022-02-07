package br.com.gabrieldev.ecommercelog;

import br.com.gabrieldev.ecommercelog.util.ObjectConverterUtil;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Base class for testing integration features with @SpringBootTest
 *
 * @author Gabriel Guimarães de Almeida
 */
@SpringBootTest
@AutoConfigureMockMvc
public abstract class BaseIntegration {

    protected MockMvc mockMvc;
    @Autowired
    protected ObjectConverterUtil objectConverterUtil;

    @Autowired
    protected MappingJackson2HttpMessageConverter converter;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    public final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            StandardCharsets.UTF_8
    );

    @BeforeEach
    public void setupMock() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    /**
     * Converts an object into an array of bytes
     *
     * @param object to be converted
     * @return an array of bytes
     * @throws IOException when can't read the object
     */
    public byte[] convertObjectToJsonBytes(Object object) throws IOException {
        return objectConverterUtil.getObjectMapper().writeValueAsBytes(object);
    }
}
