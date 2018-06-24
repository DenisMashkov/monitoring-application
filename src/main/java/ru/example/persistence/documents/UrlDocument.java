package ru.example.persistence.documents;

import lombok.Data;

@Data
public class UrlDocument {
    private String name;
    private Boolean active = false;
    private Integer statusCode = -1;
}
