package com.shogun.suzukisan.service;

import com.shogun.suzukisan.entity.Genre;
import com.shogun.suzukisan.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenreService {
    @Autowired
    GenreRepository genreRepository;

    // CREATE
    public Genre create(Genre genre) {
        return genreRepository.save(genre);
    }

    // READ
    public Iterable<Genre> findAll() {
        return genreRepository.findAll();
    }
    public Optional<Genre> findById(Long id) {
        return genreRepository.findById(id);
    }
    public Optional<Genre> findByName(String name) {
        return genreRepository.findByName(name);
    }

    // DELETE
    public void deleteById(Long id) {
        genreRepository.deleteById(id);
    }
}
