package br.com.gabriel.dev.ecommerceorder.service.serializer;

import br.com.gabriel.dev.ecommerceorder.domain.dto.OrderDTO;
import br.com.gabriel.dev.ecommerceorder.util.constants.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * This class tests every mehtod at {@link GsonSerializer}
 *
 * @author Gabriel Guimarães de Almeida
 * */
public class GsonSerializerTest {

    @Test
    void serialize_whenCorrectValuesArePassed_thenCorrectSerializedValueIsReturned(){
        var gsonSerializer = new GsonSerializer<OrderDTO>();
        var orderToBeSerialized = new OrderDTO(
                Constants.TEST,
                Constants.TEST,
                BigDecimal.TEN
        );
        var jsonOrder = new String(gsonSerializer.serialize(Constants.TEST, orderToBeSerialized));

        Assertions.assertNotNull(jsonOrder);
    }
}
