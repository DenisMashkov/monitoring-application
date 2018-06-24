package ru.example.persistence.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.example.persistence.documents.SettingsDocument;

public interface SettingsRepository extends MongoRepository<SettingsDocument, String> {
    SettingsDocument findTopByOrderByIdDesc();
}
