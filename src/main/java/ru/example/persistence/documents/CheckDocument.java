package ru.example.persistence.documents;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.Date;

@Document(collection = "check")
@Data
public class CheckDocument {

    @Id
    private String id;
    @Indexed()
    private Date checkDate;
    private Collection<UrlDocument> urlDocuments;
}
