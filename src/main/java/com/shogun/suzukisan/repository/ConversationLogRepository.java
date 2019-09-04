package com.shogun.suzukisan.repository;

import com.shogun.suzukisan.entity.ConversationLog;
import com.shogun.suzukisan.entity.Mentee;
import com.shogun.suzukisan.entity.Mentor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConversationLogRepository extends CrudRepository<ConversationLog, Long> {
    List<ConversationLog> findByMentorId(Mentor mentorId);
    List<ConversationLog> findByMenteeId(Mentee menteeId);
    List<ConversationLog> findByRoomName(String roomName);
    List<ConversationLog> findByMenteeScore(Integer menteeScore);
    List<ConversationLog> findByMentorScore(Integer mentorScore);
}
