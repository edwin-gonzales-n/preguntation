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

    @NotBlank(message = "Must have email")
    @Column(nullable = false)
    private String email;

    @NotBlank(message = "Must have Password")
    @Column(nullable = false)
    private String password;

    private long score;

    public user(){}

    public user(user copy){
        id = copy.id;
        username = copy.username;
        score = copy.score;
        email = copy.email;
        password = copy.password;
    }

    //pull from db
    public user(long id, String username, long score, String email, String password) {
        this.id = id;
        this.username = username;
        this.score = score;
        this.email = email;
        this.password = password;
    }

    //insert into db
    public user(String username, long score, String email, String password) {
        this.username = username;
        this.score = score;
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
