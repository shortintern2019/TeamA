package com.shogun.suzukisan.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class User extends TimeStamp{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @NotEmpty
    private String name;
    @Email
    private String email;
    @NotEmpty
    private String password;
    @NotNull
    @Min(0)
    private Integer mentorScore;
    @NotNull
    @Min(0)
    private Integer menteeScore;
    @NotNull
    @Min(0)
    private Integer mentorCount;
    @NotNull
    @Min(0)
    private Integer menteeCount;

    protected User() {}

    @Autowired
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.mentorScore = 0;
        this.menteeScore = 0;
        this.mentorCount = 0;
        this.menteeCount = 0;
    }

    @Autowired
    public User(String name, String email, String password, Integer mentorScore, Integer menteeScore, Integer mentorCount, Integer menteeCount) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.mentorScore = mentorScore;
        this.menteeScore = menteeScore;
        this.mentorCount = mentorCount;
        this.menteeCount = menteeCount;
    }

//    @Override
//    public String toString() {
//        return String.format(
//                "User[id=%d, name='%s', email='%s', password='%s'," +
//                        "mentorScore='%s', menteeScore='%s', mentorCount='%s', menteeCount='%s']",
//                id, name, email, password, mentorScore, menteeScore, mentorCount, menteeCount);
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getMentorScore() {
        return mentorScore;
    }

    public void setMentorScore(Integer mentorScore) {
        this.mentorScore = mentorScore;
    }

    public Integer getMenteeScore() {
        return menteeScore;
    }

    public void setMenteeScore(Integer menteeScore) {
        this.menteeScore = menteeScore;
    }

    public Integer getMentorCount() {
        return mentorCount;
    }

    public void setMentorCount(Integer mentorCount) {
        this.mentorCount = mentorCount;
    }

    public Integer getMenteeCount() {
        return menteeCount;
    }

    public void setMenteeCount(Integer menteeCount) {
        this.menteeCount = menteeCount;
    }
}