package ru.example.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.persistence.documents.CheckDocument;
import ru.example.persistence.documents.SettingsDocument;
import ru.example.persistence.repositories.CheckRepository;
import ru.example.persistence.repositories.SettingsRepository;
import ru.example.services.AdminService;

import java.util.Collection;

@Service
public class AdminServiceImpl implements AdminService {

    private SettingsRepository settingsRepository;
    private CheckRepository checkRepository;

    @Autowired
    public AdminServiceImpl(SettingsRepository settingsRepository, CheckRepository checkRepository) {
        this.settingsRepository = settingsRepository;
        this.checkRepository = checkRepository;
    }

    @Override
    public SettingsDocument getCurrentSettings() {
        return settingsRepository.findTopByOrderByIdDesc();
    }

    @Override
    public void assignNewSettings(SettingsDocument settingsDocument) {
        SettingsDocument oldSettingsDocument = settingsRepository.findTopByOrderByIdDesc();

        if (oldSettingsDocument != null) {
            settingsDocument.setId(oldSettingsDocument.getId());
        }

        settingsRepository.save(settingsDocument);
    }

    @Override
    public Collection<CheckDocument> getLastTenChecks() {
        return checkRepository.findTop10ByOrderByCheckDateDesc();
    }

    @Override
    public CheckDocument getLastCheck() {
        return checkRepository.findTopByOrderByCheckDateDesc();
    }

}

