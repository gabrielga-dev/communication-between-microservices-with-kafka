package br.com.gabrieldev.ecommercelog.repository;

import br.com.gabrieldev.ecommercelog.domain.entity.Log;
import br.com.gabrieldev.ecommercelog.domain.type.LogType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * This interface make the interations between the application and database
 *
 * @author Gabriel Guimarães de Almeida
 * */
@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

    @Query("SELECT log FROM Log log WHERE" +
            " ((:topic IS NULL) OR (log.topic = :topic)) AND" +
            " ((:type IS NULL) OR (log.type = :type)) AND" +
            " ((:initialDate IS NULL) OR (log.timestamp >= :initialDate)) AND" +
            " ((:finalDate IS NULL) OR (log.timestamp <= :finalDate))")
    Page<Log> filter(
            @Param(value = "topic") String topic,
            @Param(value = "type") LogType type,
            @Param(value = "initialDate") LocalDateTime initialDate,
            @Param(value = "finalDate") LocalDateTime finalDate,
            Pageable pageable
    );
}
