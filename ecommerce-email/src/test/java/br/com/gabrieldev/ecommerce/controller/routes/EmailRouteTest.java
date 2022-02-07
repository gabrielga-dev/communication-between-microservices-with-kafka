package br.com.gabrieldev.ecommerce.controller.routes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * This class tests every {@link EmailRoute}'s method
 *
 * @author Gabriel Guimarães de Almeida
 * */
public class EmailRouteTest {

    @Test
    void configure_whenInitiate_thenNoExceptionIsThrown(){
        var router = new EmailRoute();
        Assertions.assertDoesNotThrow(router::configure);
    }
}
