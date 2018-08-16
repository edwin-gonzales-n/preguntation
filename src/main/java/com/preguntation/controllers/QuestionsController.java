package com.preguntation.controllers;

import com.preguntation.models.answer;
import com.preguntation.models.question;
import com.preguntation.repositories.AnswersRepository;
import com.preguntation.repositories.QuestionsRepository;
import com.preguntation.repositories.RankingRepository;
import com.preguntation.repositories.UsersRepository;
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
    public String getAnswer(Model model,
                            @ModelAttribute answer ans,
                            @RequestParam(value = "id") String answer_id,
                            @RequestParam(value = "correct_answer_id") String correct_answer_id) {
        System.out.println("The user's answer is: " + answer_id);
        System.out.println("The correct answer is: " + correct_answer_id);

        if(ans.getAnswer().equals(correct_answer_id)){
            System.out.println(ans.getAnswer());
            System.out.println("the model attribute answer answer is equal to the correct answer id.");
        }

        if(answer_id.equals(correct_answer_id)){
            String message = "Yes, that is the correct answer!";
            model.addAttribute("correct_answer", message);
            return "redirect:/trivia";
        } else{
            String wrong = "Nope!";
            model.addAttribute("wrong_answer", wrong);
            return "redirect:/trivia";
        }
    }
}
