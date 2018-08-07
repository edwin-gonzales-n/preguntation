package com.preguntation.repositories;

import com.preguntation.models.question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionsRepository extends CrudRepository<question, Long> {
}
