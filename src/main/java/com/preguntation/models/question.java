package com.preguntation.models;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity
@Table(name="questions")
public class question {

    @Id
    @GeneratedValue
    private long id;

    @NotBlank(message = "Must have question")
    @Column(nullable = false)
    private String question;

    @NotBlank(message = "Must have correct answer")
    @Column(nullable = false)
    private long correct_answer;

    @NotBlank(message = "Must have language id")
    @Column(nullable = false)
    private long language_id;

    public question(){}

    //insert into db
    public question(String question, long correct_answer, long language_id) {
        this.question = question;
        this.correct_answer = correct_answer;
        this.language_id = language_id;
    }

    //pull from db
    public question(long id,String question, long correct_answer, long language_id) {
        this.id = id;
        this.question = question;
        this.correct_answer = correct_answer;
        this.language_id = language_id;
    }

    //setters and getters

    public long getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(long language_id) {
        this.language_id = language_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public long getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(long correct_answer) {
        this.correct_answer = correct_answer;
    }
}
