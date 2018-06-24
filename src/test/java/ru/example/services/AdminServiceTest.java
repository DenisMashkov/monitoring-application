package ru.example.services;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.example.persistence.repositories.CheckRepository;
import ru.example.persistence.repositories.SettingsRepository;
import ru.example.services.impl.AdminServiceImpl;

import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static ru.example.services.utils.CheckFactory.createCheckDocument;
import static ru.example.services.utils.SettingsFactory.createSettingsDocument;

public class AdminServiceTest extends AbstractServiceTest {

    @Mock
    private SettingsRepository settingsRepository;

    @Mock
    private CheckRepository checkRepository;

    @InjectMocks
    private AdminServiceImpl adminService;

    @Test
    public void adminServiceTest_getLastCheck() {
        given(checkRepository.findTopByOrderByCheckDateDesc())
                .willReturn(createCheckDocument("1"));

        assertNotNull(adminService.getLastCheck());
    }

    @Test
    public void adminServiceTest_getLastTenChecks() {
        given(checkRepository.findTop10ByOrderByCheckDateDesc())
                .willReturn(
                Arrays.asList(
                        createCheckDocument("1"),
                        createCheckDocument("2")
                )
        );

        assertEquals(adminService.getLastTenChecks().size(), 2);
    }

    @Test
    public void adminServiceTest_getCurrentSettings() {
        given(settingsRepository.findTopByOrderByIdDesc())
                .willReturn(createSettingsDocument("1"));

        assertNotNull(adminService.getCurrentSettings());
    }

    @Test
    public void adminServiceTest_assignNewSettings() {
        given(settingsRepository.findTopByOrderByIdDesc())
                .willReturn(createSettingsDocument("1"));
        given(settingsRepository.save(any()))
                .willReturn(createSettingsDocument("2"));

        adminService.assignNewSettings(createSettingsDocument("2"));
    }
}