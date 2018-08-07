package com.preguntation.models;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity
@Table(name="answers")
public class answer {

    @Id
    @GeneratedValue
    private long id;

    @NotBlank(message = "Must have id_question")
    @Column(nullable = false)
    private long id_question;

    @NotBlank(message = "Must have answer")
    @Column(nullable = false)
    private String answer;

    public answer(){}

    //retrive from db
    public answer(long id, long id_question, String answer){
        this.id = id;
        this.id_question = id_question;
        this.answer = answer;
    }

    //insert into db
    public answer(long id_question, String answer){
        this.id = id;
        this.id_question = id_question;
        this.answer = answer;
    }

    //setters and getters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_question() {
        return id_question;
    }

    public void setId_question(long id_question) {
        this.id_question = id_question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
