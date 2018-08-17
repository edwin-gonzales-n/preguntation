package com.preguntation.repositories;

import com.preguntation.models.answer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswersRepository extends CrudRepository<answer, Long> {
//    Iterable<answer> findAllById_question(long id_question);
//    Iterable<answer> findAllById_question(long n);
    @Query(nativeQuery =true, value = "SELECT * from answers WHERE id_question = ?1")
    List<answer> findAllByQuestion_ID(long n);


}
