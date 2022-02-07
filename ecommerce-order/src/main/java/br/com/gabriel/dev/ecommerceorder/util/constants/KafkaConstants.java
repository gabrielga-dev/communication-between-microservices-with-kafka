package br.com.gabriel.dev.ecommerceorder.util.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class holds every needed kafka constants
 *
 * @author Gabriel Guimarães de Almeida
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class KafkaConstants {

    public static final String TOPIC_NEW_ORDER = "ECOMMERCE_NEW_ORDER";
    public static final String TOPIC_SEND_EMAIL = "ECOMMERCE_SEND_EMAIL";
}
