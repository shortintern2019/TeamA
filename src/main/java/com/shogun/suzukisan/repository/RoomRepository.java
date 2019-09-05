package com.shogun.suzukisan.repository;

import com.shogun.suzukisan.entity.Mentee;
import com.shogun.suzukisan.entity.Mentor;
import com.shogun.suzukisan.entity.Room;
import com.shogun.suzukisan.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface RoomRepository extends CrudRepository<Room, Long> {
    Optional<Room> findByName(String name);
    // TODO: Entityで検索するが、-Idというメソッド名で良いのか？
    Optional<Room> findByMentorId(User mentor);
    Optional<Room> findByMenteeId(User mentee);
    @Modifying
    @Transactional
    void deleteByName(String name);
    @Modifying
    @Transactional
    void deleteByMentorId(User mentor);
    @Modifying
    @Transactional
    void deleteByMenteeId(User mentee);
}
