package com.shogun.suzukisan.repository;

import com.shogun.suzukisan.entity.Mentee;
import com.shogun.suzukisan.entity.Mentor;
import com.shogun.suzukisan.entity.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, Long> {
    Room findByName(String name);
    Room findByMentorId(Mentor mentorId);
    Room findByMenteeId(Mentee menteeId);
}
