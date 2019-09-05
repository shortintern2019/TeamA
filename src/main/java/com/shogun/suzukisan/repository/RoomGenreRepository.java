package com.shogun.suzukisan.repository;

import com.shogun.suzukisan.entity.Genre;
import com.shogun.suzukisan.entity.Room;
import com.shogun.suzukisan.entity.RoomGenre;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoomGenreRepository extends CrudRepository<RoomGenre, Long> {
    // TODO: Entityで検索するが、-Idというメソッド名で良いのか？
    List<RoomGenre> findByRoomId(Room room);
    List<RoomGenre> findByGenreId(Genre genre);
    @Modifying
    @Transactional
    void deleteByRoomId(Room room);
    @Modifying
    @Transactional
    void deleteByGenreId(Genre genre);
}
