package ru.example.services;

import ru.example.persistence.documents.CheckDocument;
import ru.example.persistence.documents.SettingsDocument;

import java.util.Collection;

public interface AdminService {
    SettingsDocument getCurrentSettings();
    void assignNewSettings(SettingsDocument settingsDocument);

    Collection<CheckDocument> getLastTenChecks();
    CheckDocument getLastCheck();
}
