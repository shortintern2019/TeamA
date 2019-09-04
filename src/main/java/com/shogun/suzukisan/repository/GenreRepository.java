package com.shogun.suzukisan.repository;

import com.shogun.suzukisan.entity.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Long> {
}
