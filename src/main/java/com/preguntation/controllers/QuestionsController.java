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

import java.util.List;

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
        long randomId = (long)(Math.random()*(8+1));
        question questions = questionsRepository.findOne(randomId);
        model.addAttribute("questions", questions);
        List<answer> answers = answersRepository.findAllByQuestion_ID(randomId);
        model.addAttribute("answers", answers);
        return "game/trivia";
    }

    @PostMapping("/triviaSubmit")
    public ResponseEntity<String> getAnswer(Model model,
                                            @RequestParam(value = "id") String answer_id,
                                            @RequestParam(value = "correct_answer_id") String correct_answer_id) {

        System.out.println("The user's answer is: " + answer_id);
        System.out.println("The correct answer is: " + correct_answer_id);

        if(answer_id.equals(correct_answer_id)){
            System.out.println("That is the correct answer!");
            String message = "Yes, that is the correct answer!";
            model.addAttribute("correct_answer", message);

            HttpHeaders headers = new HttpHeaders();
            return new ResponseEntity<>("OK", headers, HttpStatus.OK);
//            return "redirect:/trivia";
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers,HttpStatus.FOUND);
    }
}
