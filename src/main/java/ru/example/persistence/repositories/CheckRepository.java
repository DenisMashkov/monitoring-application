package ru.example.persistence.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.example.persistence.documents.CheckDocument;

import java.util.Collection;

public interface CheckRepository extends MongoRepository<CheckDocument, String> {
    Collection<CheckDocument> findTop10ByOrderByCheckDateDesc();
    CheckDocument findTopByOrderByCheckDateDesc();
}
