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
public class Mentor {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotNull
    @ManyToOne
    private User userId;
    @NotEmpty
    @Column(unique=true)
    private String roomName;

    @Autowired
    public Mentor(User userId, String roomName) {
        this.userId = userId;
        this.roomName = roomName;
    }
}