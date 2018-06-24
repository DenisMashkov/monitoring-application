package ru.example.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.example.persistence.documents.CheckDocument;
import ru.example.persistence.documents.SettingsDocument;
import ru.example.persistence.documents.UrlDocument;
import ru.example.persistence.repositories.CheckRepository;
import ru.example.services.AdminService;
import ru.example.services.MonitoringService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MonitoringServiceImpl implements MonitoringService {

    private RestTemplate restTemplate;
    private CheckRepository checkRepository;
    private AdminService adminService;

    @Autowired
    public MonitoringServiceImpl(RestTemplate restTemplate, CheckRepository checkRepository, AdminService adminService) {
        this.restTemplate = restTemplate;
        this.checkRepository = checkRepository;
        this.adminService = adminService;
    }

    @Override
    public void checkUrlsAndSaveResult() {
        CheckDocument checkDocument = new CheckDocument();
        checkDocument.setCheckDate(new Date());

        List<UrlDocument> urlDocumentList = new ArrayList<>();
        checkDocument.setUrlDocuments(urlDocumentList);

        SettingsDocument settingsDocument = adminService.getCurrentSettings();

        if (settingsDocument == null) {
            // add message to log (settings not found)
            return;
        }

        settingsDocument.getUrls()
                .forEach((url) -> {
                    ResponseEntity entity;

                    UrlDocument urlDocument = new UrlDocument();
                    urlDocument.setName(url);

                    try {
                        entity = restTemplate.getForEntity(url, Void.class);

                        urlDocument.setStatusCode(entity.getStatusCodeValue());
                        urlDocument.setActive(true);
                    } catch (RuntimeException e) {
                        // add message to log
                    }

                    urlDocumentList.add(urlDocument);
                });

        checkRepository.save(checkDocument);
    }
}
