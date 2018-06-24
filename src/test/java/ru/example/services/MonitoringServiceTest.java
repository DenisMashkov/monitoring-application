package ru.example.services;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.example.persistence.repositories.CheckRepository;
import ru.example.services.impl.MonitoringServiceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static ru.example.services.utils.CheckFactory.createCheckDocument;
import static ru.example.services.utils.SettingsFactory.createSettingsDocument;

public class MonitoringServiceTest extends AbstractServiceTest {

    @Mock
    private AdminService adminService;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private CheckRepository checkRepository;

    @InjectMocks
    private MonitoringServiceImpl monitoringService;

    @Test
    public void monitorServiceTest_getLastCheck() {
        ResponseEntity responseEntity = Mockito.mock(ResponseEntity.class);

        given(adminService.getCurrentSettings())
                .willReturn(createSettingsDocument("1"));
        given(restTemplate.getForEntity(any(), any()))
                .willReturn(responseEntity);
        given(responseEntity.getStatusCode())
                .willReturn(HttpStatus.OK);
        given(checkRepository.save(any()))
                .willReturn(createCheckDocument("1"));

        monitoringService.checkUrlsAndSaveResult();
    }

}
