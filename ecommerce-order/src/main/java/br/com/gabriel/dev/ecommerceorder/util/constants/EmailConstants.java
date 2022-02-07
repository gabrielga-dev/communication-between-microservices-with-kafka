package br.com.gabriel.dev.ecommerceorder.util.constants;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class holds every needed email constants
 *
 * @author Gabriel Guimarães de Almeida
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EmailConstants {

    public static final String EMAIL_SUBJECT = "New Order!";
    public static final String EMAIL_DEFAULT_BODY = "Your order is being processed.";
}
