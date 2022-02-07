package br.com.gabrieldev.ecommercelog.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * This class tests every mehtod at {@link DateUtil}
 *
 * @author Gabriel Guimarães de Almeida
 * */
public class DateUtilTest {

    private static final String CORRECT_DATE_PATTERN_STRING = "2000-01-01";
    private static final String INCORRECT_DATE_PATTERN_STRING = "101/01/2000";

    @Test
    void isDatePatternValid_whenInputDoesMatchToThePattern_thenReturnTrue(){

        Assertions.assertTrue(DateUtil.isDatePatternValid(CORRECT_DATE_PATTERN_STRING));
    }

    @Test
    void isDatePatternValid_whenInputDoesNotMatchToThePattern_thenReturnFalse(){

        Assertions.assertFalse(DateUtil.isDatePatternValid(INCORRECT_DATE_PATTERN_STRING));
    }

    @Test
    void isDatePatternValid_whenInputIsNull_thenReturnFalse(){
        Assertions.assertFalse(DateUtil.isDatePatternValid(null));
    }

    @Test
    void parseDateFromString_whenInputIsNull_thenReturnNull(){
        Assertions.assertNull(DateUtil.parseDateFromString(null));
    }

    @Test
    void parseDateFromString_whenInputIsEmpty_thenReturnNull(){
        Assertions.assertNull(DateUtil.parseDateFromString(""));
    }

    @Test
    void parseDateFromString_whenInputDoesMatchToThePattern_thenReturnCorrectDate(){
        var parsedDate = DateUtil.parseDateFromString(CORRECT_DATE_PATTERN_STRING);
        Assertions.assertNotNull(parsedDate);
        Assertions.assertEquals(parsedDate.getYear(), 2000);
    }

    @Test
    void parseDateFromString_whenInputDoesNotMatchToThePattern_thenReturnDateOnPast(){
        var parsedDate = DateUtil.parseDateFromString(INCORRECT_DATE_PATTERN_STRING);
        Assertions.assertNotNull(parsedDate);
        Assertions.assertEquals(parsedDate.getYear(), 1970);
    }
}
