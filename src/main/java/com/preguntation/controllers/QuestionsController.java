package com.preguntation.controllers;

import com.preguntation.models.answer;
import com.preguntation.models.question;
import com.preguntation.repositories.AnswersRepository;
import com.preguntation.repositories.QuestionsRepository;
import com.preguntation.repositories.RankingRepository;
import com.preguntation.repositories.UsersRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class QuestionsController {
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

    @GetMapping("/trivia")
    public String getQuestion(Model model) {

        long count = questionsRepository.count();
        System.out.println("The table count is: " + count);

        List numbers = new ArrayList();
        long i = 1;
        while(i <= count){
            System.out.println("value of i is: " + i);
            numbers.add(i);
            i++;
        }

        System.out.println(numbers);

        long random = new Random().nextInt(7)+1;
        System.out.println("randomId = " + random);
        question questions = questionsRepository.findOne(random);
        model.addAttribute("questions", questions);
        List<answer> answers = answersRepository.findAllByQuestion_ID(random);
        model.addAttribute("answers", answers);
        return "game/trivia";
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

//            HttpHeaders headers = new HttpHeaders();
//            return new ResponseEntity<>("OK", headers, HttpStatus.OK);
            return "redirect:/trivia";
        }
//        HttpHeaders headers = new HttpHeaders();
//        return new ResponseEntity<>(headers,HttpStatus.FOUND);
        return "redirect:/trivia";
    }
}
