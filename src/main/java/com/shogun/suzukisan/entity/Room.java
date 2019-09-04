package com.shogun.suzukisan.entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotEmpty
    @Column(unique=true)
    private String name;
    @NotNull
    @ManyToOne
    private Mentor mentorId;
    @NotNull
    @ManyToOne
    private Mentee menteeId;

    protected Room() {}

    @Autowired
    public Room(String name, Mentor mentorId, Mentee menteeId) {
        this.name = name;
        this.mentorId = mentorId;
        this.menteeId = menteeId;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mentorId=" + mentorId +
                ", menteeId=" + menteeId +
                '}';
    }

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

    public Mentor getMentorId() {
        return mentorId;
    }

    public void setMentorId(Mentor mentorId) {
        this.mentorId = mentorId;
    }

    public Mentee getMenteeId() {
        return menteeId;
    }

    public void setMenteeId(Mentee menteeId) {
        this.menteeId = menteeId;
    }
}
