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
//    @Autowired
//45
//    private CommentRepository commentRepository;
//46
//
//47
//    // --------------------------------------------
//48
//    // CRUD OPERATIONS FOR CHILD RECORDS (COMMENTS)
//49
//
//50
//    public Ticket createComment(long ticketId, Comment comment) {
//51
//        Ticket ticket = findTicket(ticketId);
//52
//        comment.setTicket(ticket);
//53
//        ticket.getComments().add(comment);
//54
//
//55
//        return ticketRepository.save(ticket);
//56
//    }
//57
//
//58
//    public List findAllComments(long ticketId) {
//59
//        return findTicket(ticketId).getComments();
//60
//    }
//61
//
//62
//    public Comment findComment(long id) {
//63
//        return commentRepository.findOne(id);
//64
//    }
//65
//
//66
//    public Comment updateComment(long commentId, Comment comment) {
//67
//        Comment savedComment = commentRepository.findOne(commentId);
//68
//        savedComment.setText(comment.getText());
//69
//        commentRepository.save(savedComment);
//70
//        return savedComment;
//71
//    }
//72
//
//73
//    public void deleteCommentById(long id) {
//74
//        commentRepository.delete(id);
//75
//    }

}
