package br.com.gabrieldev.ecommercelog.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

import static java.time.LocalDateTime.of;

public class DateUtil {

    public static boolean isDatePatternValid(String value){
        value = Objects.isNull(value) ? "" : value;
        return value.matches("^\\d{4}[\\-](0?[1-9]|1[012])[\\-](0?[1-9]|[12][0-9]|3[01])$");
    }

    public static LocalDateTime parseDateFromString(String input){
        if(Objects.isNull(input) || input.isBlank()) return null;
        return DateUtil.isDatePatternValid(input)
                ? of(LocalDate.parse(input), LocalTime.MIN)
                : of(LocalDate.EPOCH, LocalTime.MIN);
    }
}
