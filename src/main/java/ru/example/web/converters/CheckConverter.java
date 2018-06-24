package ru.example.web.converters;

import ru.example.persistence.documents.CheckDocument;
import ru.example.persistence.documents.UrlDocument;
import ru.example.web.views.CheckView;
import ru.example.web.views.UrlView;

import java.util.stream.Collectors;

public class CheckConverter {

    public static CheckView ofDocument(CheckDocument checkDocument) {
        if (checkDocument == null) {
            return null;
        }

        CheckView checkView = new CheckView();

        checkView.setCheckDate(checkDocument.getCheckDate());
        checkView.setUrls(
                checkDocument
                        .getUrlDocuments()
                        .stream()
                        .map(CheckConverter::ofDocument)
                        .collect(Collectors.toList())
        );

        return checkView;
    }

    private static UrlView ofDocument(UrlDocument urlDocument) {
        if (urlDocument == null) {
            return null;
        }

        UrlView urlView = new UrlView();
        urlView.setActive(urlDocument.getActive());
        urlView.setName(urlDocument.getName());
        urlView.setStatusCode(urlDocument.getStatusCode());

        return urlView;
    }
}
