package com.shogun.suzukisan.entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class MenteeGenre {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotNull
    @ManyToOne
    private Mentee menteeId;
    @NotNull
    @ManyToOne
    private Genre genreId;
    protected MenteeGenre() {}

    @Autowired
    public MenteeGenre(Mentee menteeId, Genre genreId) {
        this.menteeId = menteeId;
        this.genreId = genreId;
    }

    @Override
    public String toString() {
        return String.format(
                "MentorGenre[id=%d, menteeId='%s', genreId='%s']",
                id, menteeId, genreId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mentee getMenteeId() {
        return menteeId;
    }

    public void setMenteeId(Mentee menteeId) {
        this.menteeId = menteeId;
    }

    public Genre getGenreId() {
        return genreId;
    }

    public void setGenreId(Genre genreId) {
        this.genreId = genreId;
    }
}
