package com.shogun.suzukisan.repository;

import com.shogun.suzukisan.entity.ConversationLog;
import com.shogun.suzukisan.entity.Mentee;
import com.shogun.suzukisan.entity.Mentor;
import com.shogun.suzukisan.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ConversationLogRepository extends CrudRepository<ConversationLog, Long> {
    List<ConversationLog> findByMentorId(User mentor);
    List<ConversationLog> findByMenteeId(User mentee);
    List<ConversationLog> findByRoomName(String roomName);
    List<ConversationLog> findByMenteeScoreEquals(Integer menteeScore);
    List<ConversationLog> findByMentorScoreEquals(Integer mentorScore);
    List<ConversationLog> findByMenteeScoreGreaterThanEqual(Integer menteeScore);
    List<ConversationLog> findByMenteeScoreLessThanEqual(Integer menteeScore);
    List<ConversationLog> findByMentorScoreGreaterThanEqual(Integer mentorScore);
    List<ConversationLog> findByMentorScoreLessThanEqual(Integer mentorScore);

    @Modifying
    @Transactional
    @Query("UPDATE ConversationLog h SET h.mentorScore = :mentorScore where h.id = :conversationLog")
    Integer updateMentorScore(@Param("mentorScore") Integer mentorScore, @Param("conversationLog") Long conversationLogId);
    @Modifying
    @Transactional
    @Query("UPDATE ConversationLog h SET h.menteeScore = :menteeScore where h.id = :conversationLog")
    Integer updateMenteeScore(@Param("menteeScore") Integer menteeScore, @Param("conversationLog") Long conversationLogId);
}
