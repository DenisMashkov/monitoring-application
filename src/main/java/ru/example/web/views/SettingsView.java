package ru.example.web.views;

import lombok.Data;

import java.util.Collection;

@Data
public class SettingsView {
    private Integer interval;
    private Collection<String> urls;
}
