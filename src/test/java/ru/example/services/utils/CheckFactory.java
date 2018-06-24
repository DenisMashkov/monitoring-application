package ru.example.services.utils;

import ru.example.persistence.documents.CheckDocument;
import ru.example.persistence.documents.UrlDocument;

import java.util.Collections;
import java.util.Date;

public class CheckFactory {

    private CheckFactory() {}

    public static CheckDocument createCheckDocument(String id) {
        CheckDocument checkDocument = new CheckDocument();
        checkDocument.setId(id);
        checkDocument.setCheckDate(new Date());
        checkDocument.setUrlDocuments(Collections.singletonList(new UrlDocument()));

        return checkDocument;
    }

}
