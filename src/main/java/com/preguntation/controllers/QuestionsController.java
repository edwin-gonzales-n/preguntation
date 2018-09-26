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

//    @GetMapping("/trivia")
//    public String getQuestion(Model model) {
//
//        long count = questionsRepository.count();
//        System.out.println("The table count is: " + count);
//
//        //  creating array list to store each number id of the total count of the questions table.
//        List numbers = new ArrayList();
//        long i = 1;
//        while(i <= count){
//            System.out.println("value of i is: " + i);
//            numbers.add(i);
//            i++;
//        }
//
//        //created this logic to attempt to send to frontend just one question in order.
//        List<question> roles = new ArrayList<>();
//        questionsRepository.findAll().forEach(roles::add);
//        System.out.println("This is the output of roles" + roles);
//        //
//
//        long random = new Random().nextInt(((int)count - 1))+1;
//        System.out.println("randomId = " + random);
//        question questions = questionsRepository.findOne(random);
//        model.addAttribute("questions", questions);
//        List<answer> answers = answersRepository.findAllByQuestion_ID(random);
//        model.addAttribute("answers", answers);
//
//        return "game/trivia";
//    }

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

//@RestController
//18
//public class TicketEndpoint {
//19
//
//20
//    @Autowired
//21
//    private TicketService ticketService;
//22
//
//23
//    // --------------------------------------------
//24
//    // CRUD OPERATIONS FOR PARENT RECORDS (TICKETS)
//25
//
//26
//    @PostMapping("/tickets")
//27
//    public Ticket createTicket(@RequestBody Ticket ticket) {
//28
//        Ticket savedTicket = ticketService.createTicket(ticket);
//29
//        return savedTicket;
//30
//    }
//31
//
//32
//    @GetMapping("/tickets")
//33
//    public List getAllTickets() {
//34
//        return ticketService.findAll();
//35
//    }
//36
//
//37
//    @GetMapping("/tickets/{id}")
//38
//    public Ticket getTicket(@PathVariable long id) {
//39
//        return ticketService.findTicket(id);
//40
//    }
//41
//
//42
//    @PutMapping("/tickets/{id}")
//43
//    public Ticket changeTicket(@PathVariable long id, @RequestBody Ticket ticket) {
//44
//        return ticketService.updateTicket(id, ticket);
//45
//    }
//46
//
//47
//    @DeleteMapping("/tickets/{id}")
//48
//    public String deleteTicket(@PathVariable long id) {
//49
//        ticketService.deleteById(id);
//50
//        return String.format("Ticket id #%d successfully deleted", id);
//51
//    }
//52
//
//53
//    // --------------------------------------------
//54
//    // CRUD OPERATIONS FOR CHILD RECORDS (COMMENTS)
//55
//
//56
//    @PostMapping("/tickets/{id}/comments")
//57
//    public Ticket createComment(@PathVariable long id, @RequestBody Comment comment) {
//58
//        return ticketService.createComment(id, comment);
//59
//    }
//60
//
//61
//    @GetMapping("/tickets/{id}/comments")
//62
//    public List getAllComments(@PathVariable long id) {
//63
//        return ticketService.findAllComments(id);
//64
//    }
//65
//
//66
//    @GetMapping("/tickets/comments/{id}")
//67
//    public Comment getComment(@PathVariable long id) {
//68
//        return ticketService.findComment(id);
//69
//    }
//70
//
//71
//    @PutMapping("/tickets/comments/{id}")
//72
//    public Comment changeComment(@PathVariable long id, @RequestBody Comment comment) {
//73
//        return ticketService.updateComment(id, comment);
//74
//    }
//75
//
//76
//    @DeleteMapping("/tickets/comments/{id}")
//77
//    public String deleteComment(@PathVariable long id) {
//78
//        ticketService.deleteCommentById(id);
//79
//        return String.format("Comment id %d successfully deleted", id);
//80
//    }
//81
//}