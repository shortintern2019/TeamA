package com.shogun.suzukisan.repository;

import com.shogun.suzukisan.entity.Genre;
import com.shogun.suzukisan.entity.Mentee;
import com.shogun.suzukisan.entity.MenteeGenre;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MenteeGenreRepository extends CrudRepository<MenteeGenre, Long> {
    List<MenteeGenre> findByMenteeId(Mentee mentee);
    List<MenteeGenre> findByGenreId(Genre genre);
    @Modifying
    @Transactional
    void deleteByMenteeId(Mentee mentee);
    @Modifying
    @Transactional
    void deleteByGenreId(Genre genre);
}
