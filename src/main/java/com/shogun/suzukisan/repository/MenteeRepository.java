package com.shogun.suzukisan.repository;

import com.shogun.suzukisan.entity.Mentee;
import com.shogun.suzukisan.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface MenteeRepository extends CrudRepository<Mentee, Long> {
    Optional<Mentee> findByUserId(User user);
    Optional<Mentee> findByRoomName(String roomName);
    @Modifying
    @Transactional
    void deleteByUserId(User user);
    @Modifying
    @Transactional
    void deleteByRoomName(String roomName);
}
