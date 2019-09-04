package com.shogun.suzukisan.entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @NotEmpty
    private String name;
    public Genre() {}

    @Autowired
    public Genre(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format(
                "Genre[id=%d, name='%s']",
                id, name);
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

}