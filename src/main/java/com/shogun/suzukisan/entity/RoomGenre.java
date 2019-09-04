package com.shogun.suzukisan.entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class RoomGenre {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotNull
    @ManyToOne
    private Room roomId;
    @NotNull
    @ManyToOne
    private Genre genreId;

    protected RoomGenre() {}

    @Autowired
    public RoomGenre(Room roomId, Genre genreId) {
        this.roomId = roomId;
        this.genreId = genreId;
    }

    @Override
    public String toString() {
        return "RoomGenre{" +
                "id=" + id +
                ", roomId=" + roomId +
                ", genreId=" + genreId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Room getRoomId() {
        return roomId;
    }

    public void setRoomId(Room roomId) {
        this.roomId = roomId;
    }

    public Genre getGenreId() {
        return genreId;
    }

    public void setGenreId(Genre genreId) {
        this.genreId = genreId;
    }
}
