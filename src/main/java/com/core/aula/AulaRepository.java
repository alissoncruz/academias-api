package com.core.aula;

import com.core.model.Aula;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AulaRepository extends MongoRepository<Aula, String> {
}
