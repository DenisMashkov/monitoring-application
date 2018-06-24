package ru.example.services.utils;

import ru.example.persistence.documents.SettingsDocument;

import java.util.Collections;

public class SettingsFactory {

    private SettingsFactory() {}

    public static SettingsDocument createSettingsDocument(String id) {
        SettingsDocument settingsDocument = new SettingsDocument();
        settingsDocument.setId(id);
        settingsDocument.setInterval(10);
        settingsDocument.setUrls(Collections.singletonList("345"));

        return settingsDocument;
    }
}
