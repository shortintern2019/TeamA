package com.shogun.suzukisan.entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
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

    public Mentor() {}

    @Autowired
    public Mentor(User userId, String roomName) {
        this.userId = userId;
        this.roomName = roomName;
    }

    @Override
    public String toString() {
        return String.format(
                "Mentor[id=%d, userId='%s', roomName='%s']",
                id, userId, roomName);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}