package com.shogun.suzukisan.repository;

import com.shogun.suzukisan.entity.Mentee;
import com.shogun.suzukisan.entity.Mentor;
import com.shogun.suzukisan.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MentorRepository extends CrudRepository<Mentor, Long> {
    Mentor findByUserId(User userId);
    Mentor findByRoomName(String roomName);
}
