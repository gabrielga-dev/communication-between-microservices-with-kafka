package br.com.gabrieldev.ecommercelog.controller.routes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * This class tests every {@link LogRoute}'s method
 *
 * @author Gabriel Guimarães de Almeida
 * */
public class LogRouteTest {

    @Test
    void configure_whenInitiate_thenNoExceptionIsThrown(){
        var router = new LogRoute();
        Assertions.assertDoesNotThrow(router::configure);
    }
}
