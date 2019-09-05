package com.shogun.suzukisan.service;

import com.shogun.suzukisan.entity.Genre;
import com.shogun.suzukisan.entity.Mentee;
import com.shogun.suzukisan.entity.MenteeGenre;
import com.shogun.suzukisan.entity.MentorGenre;
import com.shogun.suzukisan.repository.MenteeGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenteeGenreService {
    @Autowired
    MenteeGenreRepository menteeGenreRepository;

    // CREATE
    public MenteeGenre create(MenteeGenre menteeGenre) {
        return menteeGenreRepository.save(menteeGenre);
    }

    // READ
    public Iterable<MenteeGenre> findAll() {
        return menteeGenreRepository.findAll();
    }
    public Optional<MenteeGenre> findById(Long id) {
        return menteeGenreRepository.findById(id);
    }
    public List<MenteeGenre> findByMenteeId(Mentee mentee) {
        return menteeGenreRepository.findByMenteeId(mentee);
    }
    public List<MenteeGenre> findByGenreId(Genre genre) {
        return menteeGenreRepository.findByGenreId(genre);
    }
    public List<MentorGenre> findByGenreName(String name) {
        return menteeGenreRepository.findByGenreName(name);
    }


    // DELETE
    public void deleteById(Long id) {
        menteeGenreRepository.deleteById(id);
    }
    public void deleteByMenteeId(Mentee mentee) {
        menteeGenreRepository.deleteByMenteeId(mentee);
    }
    public void deleteByGenreId(Genre genre) {
        menteeGenreRepository.deleteByGenreId(genre);
    }
}
