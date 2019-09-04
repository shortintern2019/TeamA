package com.shogun.suzukisan.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
public class Genre {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @NotEmpty
    private String name;

    @Autowired
    public Genre(String name) {
        this.name = name;
    }
}