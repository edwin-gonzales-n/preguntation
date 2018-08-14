package com.preguntation.repositories;

import com.preguntation.models.answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswersRepository extends CrudRepository<answer, Long> {

}
