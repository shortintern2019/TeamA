package com.shogun.suzukisan.repository;

import com.shogun.suzukisan.entity.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    Optional<Genre> findByName(String name);
}
