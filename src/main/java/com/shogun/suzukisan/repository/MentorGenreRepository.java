package com.shogun.suzukisan.repository;

import com.shogun.suzukisan.entity.Genre;
import com.shogun.suzukisan.entity.Mentor;
import com.shogun.suzukisan.entity.MentorGenre;
import com.shogun.suzukisan.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MentorGenreRepository extends CrudRepository<MentorGenre, Long> {
    List<MentorGenre> findByMentorId(Mentor mentor);
    List<MentorGenre> findByGenreId(Genre genre);
    @Transactional
    @Query("SELECT mg FROM MentorGenre mg WHERE mg.genreId.name in :name")
    List<MentorGenre> findByGenreName(List<String> name);

    @Modifying
    @Transactional
    void deleteByMentorId(Mentor mentor);
    @Modifying
    @Transactional
    void deleteByGenreId(Genre genre);
}
