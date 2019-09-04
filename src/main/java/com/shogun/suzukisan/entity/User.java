package com.shogun.suzukisan.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Data
@Getter
@Setter
public class User extends TimeStamp {
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
}