package br.com.gabrieldev.ecommercelog.controller;

import br.com.gabrieldev.ecommercelog.BaseIntegration;
import br.com.gabrieldev.ecommercelog.domain.dto.LogDTO;
import br.com.gabrieldev.ecommercelog.domain.type.LogType;
import br.com.gabrieldev.ecommercelog.service.LogService;
import br.com.gabrieldev.ecommercelog.util.constants.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This class tests every endpoint at {@link LogController}
 *
 * @author Gabriel Guimarães de Almeida
 * */
public class LogControllerIT extends BaseIntegration {

    private static final String URL = "/api/log?topic=TEST&type=MESSAGE&initialDate=TEST&finalDate=TEST";

    @MockBean
    private LogService logService;

    @BeforeEach
    public void setup(){
        Page<LogDTO> expectedPage = new PageImpl<LogDTO>(List.of(
                new LogDTO(
                        Constants.TEST,
                        Constants.TEST,
                        LogType.MESSAGE,
                        LocalDateTime.now()
                )
        ));

        when(logService.filter(any(Pageable.class), anyString(), any(LogType.class), anyString(), anyString()))
                .thenReturn(expectedPage);
    }

    @Test
    void filterLogs_whenWhenCalled_noEcxceptionIsThrown() throws Exception {
        mockMvc.perform(get(URL).contentType(APPLICATION_JSON_UTF8)).andExpect(status().isOk());
    }
}
