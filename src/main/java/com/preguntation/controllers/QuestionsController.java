package com.preguntation.controllers;

import com.preguntation.models.answer;
import com.preguntation.models.question;
import com.preguntation.repositories.AnswersRepository;
import com.preguntation.repositories.QuestionsRepository;
import com.preguntation.repositories.RankingRepository;
import com.preguntation.repositories.UsersRepository;
import com.preguntation.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class QuestionsController {

    @Autowired
    public QuestionService questionService;

    @Autowired
    public AnswerService answerService;

    public AnswersRepository answersRepository;
    public QuestionsRepository questionsRepository;
    public UsersRepository usersRepository;
    public RankingRepository rankingRepository;

    public QuestionsController(AnswersRepository answersRepository, QuestionsRepository questionsRepository, UsersRepository usersRepository, RankingRepository rankingRepository) {
        this.answersRepository = answersRepository;
        this.questionsRepository = questionsRepository;
        this.usersRepository = usersRepository;
        this.rankingRepository = rankingRepository;
    }

    // using the serviceRepo instead.
    @GetMapping("/triviaAllQuestions")
    public Iterable<question> getQuestions(){
        return questionService.findAll();
    }

    @GetMapping("/triviaEachQuestion")
    public question getQuestion(){
        long count = questionsRepository.count();
        long random = new Random().nextInt(((int)count - 1))+1;
        return questionService.findQuestion(random);
    }

    @GetMapping("/triviaByQuestion/{id}")
    public question getQuestionById(@PathVariable long id) {
        return questionService.findQuestion(id);
    }

    @GetMapping("/answersByQuestionID/{id}")
    public Iterable getAnswers(@PathVariable long id){
        return answerService.findAnswers(id);
    }

    @GetMapping("/triviaQuestionsByLanguage/{id}")
    public Iterable<question> getQuestionsByLanguage(@PathVariable long id) {
        return questionService.findQuestionByLanguage(id);
    }

    @PostMapping("/triviaSubmit")
    public String getAnswer(Model model,
                                            @RequestParam(value = "id") String answer_id,
                                            @RequestParam(value = "correct_answer_id") String correct_answer_id) {

        System.out.println("The user's answer is: " + answer_id);
        System.out.println("The correct answer is: " + correct_answer_id);

        if(answer_id.equals(correct_answer_id)){
            System.out.println("That is the correct answer!");
            String message = "Yes, that is the correct answer!";
            model.addAttribute("correct_answer", message);
            return "redirect:/trivia";
        }
        return "redirect:/trivia";
    }
}

// SAMPLE Controller for the question controller to implement ajax calls.
