package com.shogun.suzukisan.repository;

import com.shogun.suzukisan.entity.Mentee;
import com.shogun.suzukisan.entity.Mentor;
import com.shogun.suzukisan.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface MentorRepository extends CrudRepository<Mentor, Long> {
    Optional<Mentor> findByUserId(User user);
    Optional<Mentor> findByRoomName(String roomName);
    @Modifying
    @Transactional
    void deleteByUserId(User user);
    @Modifying
    @Transactional
    void deleteByRoomName(String roomName);
}
