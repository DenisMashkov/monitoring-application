package ru.example.web.converters;

import ru.example.persistence.documents.SettingsDocument;
import ru.example.web.views.SettingsView;

public class SettingsConverter {

    private SettingsConverter() {}

    public static SettingsView ofDocument(SettingsDocument settingsDocument) {
        if (settingsDocument == null) {
            return null;
        }

        SettingsView settingsView = new SettingsView();

        settingsView.setInterval(settingsDocument.getInterval());
        settingsView.setUrls(settingsDocument.getUrls());

        return settingsView;
    }

    public static SettingsDocument toDocument(SettingsView settingsView) {
        if (settingsView == null) {
            return null;
        }

        SettingsDocument settingsDocument = new SettingsDocument();
        settingsDocument.setInterval(settingsView.getInterval());
        settingsDocument.setUrls(settingsView.getUrls());

        return settingsDocument;
    }

}
