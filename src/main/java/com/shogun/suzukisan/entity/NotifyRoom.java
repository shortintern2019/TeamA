package com.shogun.suzukisan.entity;

import java.io.Serializable;

public class NotifyRoom implements Serializable {

    private static final long serialVersionUID = 1L;

    private String room;
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}