package br.com.gabrieldev.ecommercelog.domain.entity;

import br.com.gabrieldev.ecommercelog.domain.type.LogType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * This class will represent those logs inside our database
 *
 * @author Gabriel Guimarães de Almeida
 * */
@Entity
@Table(name="log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "topic", nullable = false)
    private String topic;

    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private LogType type;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;
}
