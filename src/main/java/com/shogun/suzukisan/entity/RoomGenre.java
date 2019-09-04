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

    @Autowired
    public RoomGenre(Room roomId, Genre genreId) {
        this.roomId = roomId;
        this.genreId = genreId;
    }
}
