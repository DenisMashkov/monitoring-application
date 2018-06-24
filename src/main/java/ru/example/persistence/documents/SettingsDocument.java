package ru.example.persistence.documents;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Document(collection = "settings")
@Data
public class SettingsDocument {

    @Id
    private String id;
    private Integer interval;
    private Collection<String> urls;

}
