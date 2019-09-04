package com.shogun.suzukisan.repository;

import com.shogun.suzukisan.entity.Genre;
import com.shogun.suzukisan.entity.Room;
import com.shogun.suzukisan.entity.RoomGenre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomGenreRepository extends CrudRepository<RoomGenre, Long> {
    List<RoomGenre> findByRoomId(Room roomId);
    List<RoomGenre> findByGenreId(Genre genreId);
}
