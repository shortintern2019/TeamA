package com.shogun.suzukisan.entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class MentorGenre {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotNull
    @ManyToOne
    private Mentor mentorId;
    @NotNull
    @ManyToOne
    private Genre genreId;

    protected MentorGenre() {}

    @Autowired
    public MentorGenre(Mentor mentorId, Genre genreId) {
        this.mentorId = mentorId;
        this.genreId = genreId;
    }

    @Override
    public String toString() {
        return String.format(
                "MentorGenre[id=%d, mentorId='%s', genreId='%s']",
                id, mentorId, genreId);
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

    public Genre getGenreId() {
        return genreId;
    }

    public void setGenreId(Genre genreId) {
        this.genreId = genreId;
    }
}