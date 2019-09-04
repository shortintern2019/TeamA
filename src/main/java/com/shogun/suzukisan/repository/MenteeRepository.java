package com.shogun.suzukisan.repository;

import com.shogun.suzukisan.entity.Mentee;
import com.shogun.suzukisan.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenteeRepository extends CrudRepository<Mentee, Long> {
    Mentee findByUserId(User userId);
    Mentee findByRoomName(String roomName);
}
