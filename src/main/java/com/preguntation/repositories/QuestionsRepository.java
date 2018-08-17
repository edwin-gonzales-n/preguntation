package com.preguntation.repositories;

import com.preguntation.models.question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionsRepository extends CrudRepository<question, Long> {
//    Iterable<question> iterator();

    @Query(nativeQuery =true, value = "SELECT count(u) from questions u")
    int findCount();
}
