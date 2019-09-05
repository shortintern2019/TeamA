package com.shogun.suzukisan.service;

import com.shogun.suzukisan.entity.ConversationLog;
import com.shogun.suzukisan.entity.Mentee;
import com.shogun.suzukisan.entity.Mentor;
import com.shogun.suzukisan.entity.User;
import com.shogun.suzukisan.repository.ConversationLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConversationLogService {
    @Autowired
    ConversationLogRepository conversationLogRepository;

    // CREATE
    public ConversationLog create(ConversationLog conversationLog) {
        return conversationLogRepository.save(conversationLog);
    }

    // READ
    public Iterable<ConversationLog> findAll() {
        return conversationLogRepository.findAll();
    }
    public Optional<ConversationLog> findById(Long id) {
        return conversationLogRepository.findById(id);
    }
    public List<ConversationLog> findByMentorId(User mentor) {
        return conversationLogRepository.findByMentorId(mentor);
    }
    public List<ConversationLog> findByMenteeId(User mentee) {
        return conversationLogRepository.findByMenteeId(mentee);
    }
    public List<ConversationLog> findByRoomName(String roomName) {
        return conversationLogRepository.findByRoomName(roomName);
    }
    public List<ConversationLog> findByMenteeScoreEquals(Integer menteeScore) {
        return conversationLogRepository.findByMenteeScoreEquals(menteeScore);
    }
    public List<ConversationLog> findByMentorScoreEquals(Integer mentorScore) {
        return conversationLogRepository.findByMentorScoreEquals(mentorScore);
    }
    public List<ConversationLog> findByMenteeScoreGraterThanEqual(Integer menteeScore) {
        return conversationLogRepository.findByMenteeScoreGreaterThanEqual(menteeScore);
    }
    public List<ConversationLog> findByMenteeScoreLessThanEqual(Integer menteeScore) {
        return conversationLogRepository.findByMenteeScoreLessThanEqual(menteeScore);
    }
    public List<ConversationLog> findByMentorScoreGraterThanEqual(Integer mentorScore) {
        return conversationLogRepository.findByMentorScoreGreaterThanEqual(mentorScore);
    }
    public List<ConversationLog> findByMentorScoreLessThanEqual(Integer mentorScore) {
        return conversationLogRepository.findByMentorScoreLessThanEqual(mentorScore);
    }


    // UPDATE
    public Integer updateMentorScore(Integer mentorScore, Long conversationLogId) {
        return conversationLogRepository.updateMentorScore(mentorScore, conversationLogId);
    }
    public Integer updateMenteeScore(Integer menteeScore, Long conversationLogId) {
        return conversationLogRepository.updateMenteeScore(menteeScore, conversationLogId);
    }

    // DELETE
    public void deleteById(Long id) {
        conversationLogRepository.deleteById(id);
    }
}
