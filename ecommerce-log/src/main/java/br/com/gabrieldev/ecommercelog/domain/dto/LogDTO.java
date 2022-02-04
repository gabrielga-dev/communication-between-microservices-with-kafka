package br.com.gabrieldev.ecommercelog.domain.dto;

import br.com.gabrieldev.ecommercelog.domain.type.LogType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * This class holds the log information that will be sent to the users
 *
 * @author Gabriel Guimarães de Almeida
 * */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LogDTO {

    private String topic;

    private String body;

    private LogType type;

    private LocalDateTime timestamp;
}
