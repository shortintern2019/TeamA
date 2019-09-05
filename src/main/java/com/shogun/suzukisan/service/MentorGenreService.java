package com.shogun.suzukisan.service;

import com.shogun.suzukisan.entity.Genre;
import com.shogun.suzukisan.entity.Mentor;
import com.shogun.suzukisan.entity.MentorGenre;
import com.shogun.suzukisan.repository.MentorGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MentorGenreService {
    @Autowired
    MentorGenreRepository mentorGenreRepository;

    // CREATE
    public MentorGenre create(MentorGenre mentorGenre) {
        return mentorGenreRepository.save(mentorGenre);
    }

    // READ
    public Iterable<MentorGenre> findAll() {
        return mentorGenreRepository.findAll();
    }
    public Optional<MentorGenre> findById(Long id) {
        return mentorGenreRepository.findById(id);
    }
    public List<MentorGenre> findByMentorId(Mentor mentor) {
        return mentorGenreRepository.findByMentorId(mentor);
    }
    public List<MentorGenre> findByGenreId(Genre genre) {
        return mentorGenreRepository.findByGenreId(genre);
    }
    public List<MentorGenre> findByGenreName(String name) {
        return mentorGenreRepository.findByGenreName(name);
    }

    // DELETE
    public void deleteById(Long id) {
        mentorGenreRepository.deleteById(id);
    }
    public void deleteByMentorId(Mentor mentor) {
        mentorGenreRepository.deleteByMentorId(mentor);
    }
    public void deleteByGenreId(Genre genre) {
        mentorGenreRepository.deleteByGenreId(genre);
    }
}
