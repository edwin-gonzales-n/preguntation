package com.preguntation.services;

import com.preguntation.models.question;
import com.preguntation.repositories.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionsRepository questionsRepository;

    public question createQuestion(question question){
        return questionsRepository.save(question);
    }

    public Iterable<question> findAll(){
        return questionsRepository.findAll();
    }

    public question findQuestion(long id){
        return questionsRepository.findOne(id);
    }

    public Iterable<question> findQuestionByLanguage (long languageID){
        return questionsRepository.findAllByLanguage(languageID);
    }

    public question updateQuestion(long id, question question){
        question updateQuestion = findQuestion(id);
        if(!question.getQuestion().equals(updateQuestion.getQuestion())){
            updateQuestion.setQuestion(question.getQuestion());
            return questionsRepository.save(updateQuestion);
        } else
            return null;
    }

    public void deleteById(long id){
        questionsRepository.delete(id);
    }

}
