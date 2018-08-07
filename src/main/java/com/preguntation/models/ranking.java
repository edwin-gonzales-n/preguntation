package com.preguntation.models;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity
@Table(name="ranking")
public class ranking {

    @Id
    @GeneratedValue
    private long id;

    @NotBlank(message = "Must have user name")
    @Column(nullable = false)
    private String user;

    @NotBlank(message = "Must have score")
    @Column(nullable = false)
    private long score;

    public ranking(){}

    //pull from db
    public ranking(long id, String user, long score) {
        this.id = id;
        this.user = user;
        this.score = score;
    }
    //insert into db
    public ranking(String user, long score) {
        this.user = user;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }
}
