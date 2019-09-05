package com.shogun.suzukisan.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@Setter
@Getter
public class ConversationLog {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotNull
    @ManyToOne
    private User mentorId;
    @NotNull
    @ManyToOne
    private User menteeId;
    @NotEmpty
    private String roomName;
    @NotNull
    @Min(0)
    private Integer mentorScore;
    @NotNull
    @Min(0)
    private Integer menteeScore;

    @Autowired
    public ConversationLog(User mentorId, User menteeId, String roomName, Integer mentorScore, Integer menteeScore) {
        this.menteeId = menteeId;
        this.mentorId = mentorId;
        this.roomName = roomName;
        this.mentorScore = mentorScore;
        this.menteeScore = menteeScore;
    }
}
