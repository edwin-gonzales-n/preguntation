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

        //  creating array list to store each number id of the total count of the questions table.
        List numbers = new ArrayList();
        long i = 1;
        while(i <= count){
            System.out.println("value of i is: " + i);
            numbers.add(i);
            i++;
        }

        //created this logic to attempt to send to frontend just one question in order.
        List<question> roles = new ArrayList<>();
        questionsRepository.findAll().forEach(roles::add);
        System.out.println("This is the output of roles" + roles);
        //

        long random = new Random().nextInt(((int)count - 1))+1;
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
            return "redirect:/trivia";
        }
        return "redirect:/trivia";
    }
}
