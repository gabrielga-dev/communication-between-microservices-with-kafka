package br.com.gabrieldev.ecommercelog.domain.mapper;

import br.com.gabrieldev.ecommercelog.domain.dto.LogDTO;
import br.com.gabrieldev.ecommercelog.domain.entity.Log;
import br.com.gabrieldev.ecommercelog.domain.type.LogType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * This class has the methods to parse, and build, logs
 *
 * @author Gabriel Guimarães de Almeida
 * */
@Component
public class LogMapper {

    /**
     * This method parse a {@link LogDTO} object into a {@link Log} object
     *
     * @param logDTO {@link LogDTO} object that will be parsed
     *
     * @author Gabriel Guimarães de Almeida
     * */
    public Log toEntity(LogDTO logDTO){
        var toReturn = new Log(
            null,
            logDTO.getTopic(),
            logDTO.getBody(),
            logDTO.getType(),
            logDTO.getTimestamp()
        );

        return toReturn;
    }

    /**
     * This method parse a {@link Log} object into a {@link LogDTO} object
     *
     * @param log {@link Log} object that will be parsed
     *
     * @author Gabriel Guimarães de Almeida
     * */
    public LogDTO toDTO(Log log) {
        var toReturn = new LogDTO(
                log.getTopic(),
                log.getBody(),
                log.getType(),
                log.getTimestamp()
        );

        return toReturn;
    }

    /**
     * This method parse two strings, message's topic and body, into a {@link Log} object
     *
     * @param topic {@link String} message's topic
     * @param message {@link String} message's body
     *
     * @author Gabriel Guimarães de Almeida
     * */
    public Log toEntity(String topic, String message){
        var toReturn = new Log(
                null,
                topic,
                message,
                LogType.MESSAGE,
                LocalDateTime.now()
        );

        return toReturn;
    }
}
