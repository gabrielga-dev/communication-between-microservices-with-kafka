package br.com.gabrieldev.ecommercelog.util.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class KafkaKeysConstants {
    public static final Pattern KAFKA_KEY_PATTERN = Pattern.compile("ECOMMERCE.*");
}
