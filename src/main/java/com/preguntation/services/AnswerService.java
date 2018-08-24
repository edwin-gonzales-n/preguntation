package com.preguntation.services;

import com.preguntation.models.answer;
import com.preguntation.repositories.AnswersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    @Autowired
    private AnswersRepository answersRepository;

    public Iterable<answer> findAnswers(long id){
        return answersRepository.findAllByQuestion_ID(id);
    }

}
