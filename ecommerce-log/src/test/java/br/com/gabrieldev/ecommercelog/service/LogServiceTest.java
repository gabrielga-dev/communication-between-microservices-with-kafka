package br.com.gabrieldev.ecommercelog.service;


import br.com.gabrieldev.ecommercelog.domain.dto.LogDTO;
import br.com.gabrieldev.ecommercelog.domain.entity.Log;
import br.com.gabrieldev.ecommercelog.domain.mapper.LogMapper;
import br.com.gabrieldev.ecommercelog.domain.type.LogType;
import br.com.gabrieldev.ecommercelog.repository.LogRepository;
import br.com.gabrieldev.ecommercelog.util.constants.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class tests every mehtod at {@link LogService}
 *
 * @author Gabriel Guimarães de Almeida
 * */
public class LogServiceTest {

    @InjectMocks
    private LogService logService;

    @Mock
    private LogMapper logMapper;

    @Mock
    private LogRepository logRepository;

    private static final LogDTO expectedDto = new LogDTO(
            Constants.TEST,
            Constants.TEST,
            LogType.MESSAGE,
            LocalDateTime.now()
    );

    private static final Log expectedDomain = new Log(
            1L,
            Constants.TEST,
            Constants.TEST,
            LogType.MESSAGE,
            LocalDateTime.now()
    );

    @BeforeEach
    private void beforeEach() throws IOException {
        MockitoAnnotations.initMocks(this);

        Mockito.when(logMapper.toDTO(any(Log.class))).thenReturn(expectedDto);
        Mockito.when(logMapper.toEntity(any(LogDTO.class))).thenReturn(expectedDomain);
        Mockito.when(logMapper.toEntity(anyString(), anyString())).thenReturn(expectedDomain);

        Mockito.when(logRepository.save(any(Log.class))).thenReturn(expectedDomain);
        Mockito.when(
                logRepository.filter(
                        anyString(),
                        any(LogType.class),
                        any(LocalDateTime.class),
                        any(LocalDateTime.class),
                        any(Pageable.class)
                )
        ).thenReturn(new PageImpl<>(List.of(expectedDomain)));
    }

    @Test
    void save_whenTopicAndBodyArePassed_thenResturnSavedContent(){
        var savedLog = logService.save(Constants.TEST, Constants.TEST);
        Assertions.assertNotNull(savedLog);
        Assertions.assertEquals(savedLog.getBody(), Constants.TEST);
    }

    @Test
    void save_whenValuesArePassed_thenResturnSavedContent(){
        var savedLog = logService.save(expectedDto);
        Assertions.assertNotNull(savedLog);
        Assertions.assertEquals(savedLog.getBody(), expectedDto.getBody());
    }

    @Test
    void filter_whenValuesToFilterAreSent_thenReturnSavedAndFilteredValues(){
        var mockedPageable = mock(Pageable.class);
        when(mockedPageable.toString()).thenReturn(Constants.TEST);

        var results = logService.filter(
                mockedPageable,
                Constants.TEST,
                LogType.MESSAGE,
                "01/01/2000", "01/01/3000"
        );
        Assertions.assertNotNull(results);
        Assertions.assertEquals(results.getContent().get(0).getBody(), Constants.TEST);
    }
}
