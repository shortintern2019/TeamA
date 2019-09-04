package com.shogun.suzukisan.repository;

import com.shogun.suzukisan.entity.Genre;
import com.shogun.suzukisan.entity.Mentee;
import com.shogun.suzukisan.entity.MenteeGenre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenteeGenreRepository extends CrudRepository<MenteeGenre, Long> {
    List<MenteeGenre> findByMenteeId(Mentee menteeId);
    List<MenteeGenre> findByGenreId(Genre genreId);
}
