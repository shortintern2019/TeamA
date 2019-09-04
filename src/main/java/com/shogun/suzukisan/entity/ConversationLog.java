package com.shogun.suzukisan.entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class ConversationLog {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotNull
    @ManyToOne
    private Mentor mentorId;
    @NotNull
    @ManyToOne
    private Mentee menteeId;
    @NotEmpty
    private String roomName;
    @NotNull
    @Min(0)
    private Integer mentorScore;
    @NotNull
    @Min(0)
    private Integer menteeScore;

    protected ConversationLog() {}

    @Autowired
    public ConversationLog(Mentor mentorId, Mentee menteeId, String roomName, Integer mentorScore, Integer menteeScore) {
        this.menteeId = menteeId;
        this.mentorId = mentorId;
        this.mentorScore = mentorScore;
        this.menteeScore = menteeScore;
    }

    @Override
    public String toString() {
        return "ConversationLog{" +
                "id=" + id +
                ", mentorId=" + mentorId +
                ", menteeId=" + menteeId +
                ", roomName='" + roomName + '\'' +
                ", mentorScore=" + mentorScore +
                ", menteeScore=" + menteeScore +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
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
}
