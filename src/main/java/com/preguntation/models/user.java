package com.preguntation.models;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity
@Table(name="users")
public class user {

    @Id
    @GeneratedValue
    private long id;

    @NotBlank(message = "Must have user name")
    @Column(nullable = false)
    private String username;

    @NotBlank(message = "Must have score")
    @Column(nullable = false)
    private long score;

    public user(){}

    //pull from db
    public user(long id, String username, long score) {
        this.id = id;
        this.username = username;
        this.score = score;
    }

    //insert into db
    public user(String username, long score) {
        this.username = username;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }
}
