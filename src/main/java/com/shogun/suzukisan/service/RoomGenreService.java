package com.shogun.suzukisan.service;

import com.shogun.suzukisan.entity.Genre;
import com.shogun.suzukisan.entity.Room;
import com.shogun.suzukisan.entity.RoomGenre;
import com.shogun.suzukisan.repository.RoomGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomGenreService {
    @Autowired
    RoomGenreRepository roomGenreRepository;

    // CREATE
    public RoomGenre create(RoomGenre roomGenre) {
        return roomGenreRepository.save(roomGenre);
    }

    // READ
    public Iterable<RoomGenre> findAll() {
        return roomGenreRepository.findAll();
    };
    public Optional<RoomGenre> findById(Long id) {
        return roomGenreRepository.findById(id);
    }
    public List<RoomGenre> findByRoomId(Room room) {
        return roomGenreRepository.findByRoomId(room);
    }
    public List<RoomGenre> findByGenreId(Genre genre) {
        return roomGenreRepository.findByGenreId(genre);
    }

    // DELETE
    public void deleteById(Long id) {
        roomGenreRepository.deleteById(id);
    }
    public void deleteByRoomId(Room room) {
        roomGenreRepository.deleteByRoomId(room);
    }
    public void deleteByGenreId(Genre genre) {
        roomGenreRepository.deleteByGenreId(genre);
    }
}
