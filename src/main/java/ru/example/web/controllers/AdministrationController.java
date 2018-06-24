package ru.example.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.example.services.AdminService;
import ru.example.web.converters.SettingsConverter;
import ru.example.web.converters.CheckConverter;
import ru.example.web.views.SettingsView;
import ru.example.web.views.CheckView;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/admin")
public class AdministrationController {

    private AdminService adminService;

    @Autowired
    public AdministrationController(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping(
            value = "/settings",
            method = RequestMethod.GET
    )
    public SettingsView getCurrentMonitorSettings() {
        return SettingsConverter.ofDocument(
                adminService.getCurrentSettings()
        );
    }

    @RequestMapping(
            value = "/settings",
            method = RequestMethod.POST
    )
    public void assignNewMonitorSettings(@RequestBody SettingsView settingsView) {
        adminService.assignNewSettings(
                SettingsConverter.toDocument(settingsView)
        );
    }

    @RequestMapping(
            value = "/checks",
            method = RequestMethod.GET
    )
    public Collection<CheckView> getLastTenChecks() {
        return adminService
                .getLastTenChecks()
                .stream()
                .map(CheckConverter::ofDocument)
                .collect(Collectors.toList());
    }

    @RequestMapping(
            value = "/checks/last",
            method = RequestMethod.GET
    )
    public CheckView getLastCheck() {
        return CheckConverter.ofDocument(
                adminService.getLastCheck()
        );
    }

}
