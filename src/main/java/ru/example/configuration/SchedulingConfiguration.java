package ru.example.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import ru.example.persistence.documents.SettingsDocument;
import ru.example.services.AdminService;
import ru.example.services.MonitoringService;

import java.util.Calendar;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableScheduling
public class SchedulingConfiguration implements SchedulingConfigurer {

    private MonitoringService monitoringService;
    private AdminService adminService;

    @Autowired
    public SchedulingConfiguration(MonitoringService monitoringService, AdminService adminService) {
        this.monitoringService = monitoringService;
        this.adminService = adminService;
    }

    @Bean
    public Executor taskExecutor() {
        return Executors.newSingleThreadScheduledExecutor();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());

        Trigger trigger = triggerContext -> {
            SettingsDocument currentSettings = adminService.getCurrentSettings();

            // todo move default value to config
            int interval = currentSettings != null ? currentSettings.getInterval() : 10;

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.SECOND, interval);

            return calendar.getTime();
        };

        Runnable task = () ->
             monitoringService.checkUrlsAndSaveResult();

        taskRegistrar.addTriggerTask(task, trigger);
    }

}
