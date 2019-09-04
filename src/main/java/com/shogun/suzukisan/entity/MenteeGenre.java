package com.shogun.suzukisan.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
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

    @Autowired
    public MenteeGenre(Mentee menteeId, Genre genreId) {
        this.menteeId = menteeId;
        this.genreId = genreId;
    }
}
