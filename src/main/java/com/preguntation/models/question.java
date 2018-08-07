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

    public question(){}

    //insert into db
    public question(String question, long correct_answer) {
        this.question = question;
        this.correct_answer = correct_answer;
    }

    //pull from db
    public question(long id,String question, long correct_answer) {
        this.id = id;
        this.question = question;
        this.correct_answer = correct_answer;
    }
}
