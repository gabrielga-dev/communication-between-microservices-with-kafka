package br.com.gabriel.dev.ecommerceorder.domain.mapper;

import br.com.gabriel.dev.ecommerceorder.domain.dto.OrderDTO;
import br.com.gabriel.dev.ecommerceorder.util.constants.Constants;
import br.com.gabriel.dev.ecommerceorder.util.constants.EmailConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

/**
 * This class tests every mehtod at {@link OrderMapper}
 *
 * @author Gabriel Guimarães de Almeida
 * */
public class OrderMapperTest {

    @InjectMocks
    private OrderMapper orderMapper;

    private static final OrderDTO orderToBeParsed = new OrderDTO(
            Constants.TEST,
            Constants.TEST,
            BigDecimal.TEN
    );

    @BeforeEach
    private void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void toDomain_whenCorrectValuesArePassed_thenCorrectParsedValuesAreReturned() {
        var parsedValue = orderMapper.toDomain(orderToBeParsed);

        Assertions.assertNotNull(parsedValue);
        Assertions.assertEquals(orderToBeParsed.getAmount(), parsedValue.getAmount());
        Assertions.assertEquals(orderToBeParsed.getUserId(), parsedValue.getUserId());
        Assertions.assertEquals(orderToBeParsed.getUserEmailAddress(), parsedValue.getUserEmailAddress());
    }

    @Test
    void toEmail_whenCorrectValuesArePassed_thenCorrectParsedValuesAreReturned() {
        var parsedValue = orderMapper.toEmail(orderToBeParsed);

        Assertions.assertNotNull(parsedValue);
        Assertions.assertEquals(orderToBeParsed.getUserEmailAddress(), parsedValue.getReciever());
        Assertions.assertEquals(EmailConstants.EMAIL_SUBJECT, parsedValue.getSubject());
        Assertions.assertEquals(EmailConstants.EMAIL_DEFAULT_BODY, parsedValue.getBody());
    }

}
