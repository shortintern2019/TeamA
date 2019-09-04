package com.shogun.suzukisan.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
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

    @Autowired
    public Room(String name, Mentor mentorId, Mentee menteeId) {
        this.name = name;
        this.mentorId = mentorId;
        this.menteeId = menteeId;
    }
}
