package com.shogun.suzukisan.repository;

import com.shogun.suzukisan.entity.Genre;
import com.shogun.suzukisan.entity.Mentor;
import com.shogun.suzukisan.entity.MentorGenre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MentorGenreRepository extends CrudRepository<MentorGenre, Long> {
    List<MentorGenre> findByMentorId(Mentor mentorId);
    List<MentorGenre> findByGenreId(Genre genreId);
}
