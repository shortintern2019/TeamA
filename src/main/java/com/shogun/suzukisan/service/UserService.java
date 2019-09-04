package com.shogun.suzukisan.service;

import com.shogun.suzukisan.entity.User;
import com.shogun.suzukisan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    // CREATE
    public User create(User user){
        return userRepository.save(user);
    }

    // READ
    // all
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }
    // by id
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    // by name
    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }
    // by email
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    // = mentorScore
    public List<User> findByMentorScoreEquals(Integer mentorScore) {
        return userRepository.findByMentorScoreEquals(mentorScore);
    }
    // = menteeScore
    public List<User> findByMenteeScoreEquals(Integer menteeScore) {
        return userRepository.findByMenteeScoreEquals(menteeScore);
    }
    // <= mentorScore
    public List<User> findByMentorScoreGraterThanEqual(Integer mentorScore) {
        return userRepository.findByMentorScoreGreaterThanEqual(mentorScore);
    }
    // <= menteScore
    public List<User> findByMenteeScoreGraterThanEqual(Integer menteeScore) {
        return userRepository.findByMenteeScoreGreaterThanEqual(menteeScore);
    }
    // >= mentorScore
    public List<User> findByMentorScoreLessThanEqual(Integer mentorScore) {
        return userRepository.findByMentorScoreLessThanEqual(mentorScore);
    }
    // >= menteeScore
    public List<User> findByMenteeScoreLessThanEqual(Integer menteeScore) {
        return userRepository.findByMenteeScoreLessThanEqual(menteeScore);
    }

    // = mentorCount
    public List<User> findByMentorCountEquals(Integer mentorCount) {
        return userRepository.findByMentorCountEquals(mentorCount);
    }
    // = menteeCount
    public List<User> findByMenteeCountEquals(Integer menteeCount) {
        return userRepository.findByMenteeCountEquals(menteeCount);
    }
    // <= mentorCount
    public List<User> findByMentorCountGraterThanEqual(Integer mentorCount) {
        return userRepository.findByMentorCountGreaterThanEqual(mentorCount);
    }
    // <= menteCount
    public List<User> findByMenteeCountGraterThanEqual(Integer menteeCount) {
        return userRepository.findByMenteeCountGreaterThanEqual(menteeCount);
    }
    // >= mentorCount
    public List<User> findByMentorCountLessThanEqual(Integer mentorCount) {
        return userRepository.findByMentorCountLessThanEqual(mentorCount);
    }
    // >= menteeCount
    public List<User> findByMenteeCountLessThanEqual(Integer menteeCount) {
        return userRepository.findByMenteeCountLessThanEqual(menteeCount);
    }

    // UPDATE
    // update name
    public Integer updateName(String name, Long userId) {
        return userRepository.updateName(name, userId);
    }
    // update password
    public Integer updatePassword(String password, Long userId) {
        return userRepository.updatePassword(password, userId);
    }
    // update email
    public Integer updateEmail(String email, Long userId)  {
        return userRepository.updateEmail(email, userId);
    }
    // update mentorScore
    public Integer updateMentorScore(Integer mentorScore, Long userId) {
        return userRepository.updateMentorScore(mentorScore, userId);
    }
    // update menteeScore
    public Integer updateMenteeScore(Integer menteeScore, Long userId) {
        return userRepository.updateMenteeScore(menteeScore, userId);
    }
    // update mentorCounter
    public Integer updateMentorCounter(Integer mentorCounter, Long userId) {
        return userRepository.updateMentorCount(mentorCounter, userId);
    }
    // update menteeCounter
    public Integer updateMenteeCounter(Integer menteeConter, Long userId) {
        return userRepository.updateMenteeCount(menteeConter, userId);
    }

    // DELETE
    // by id
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
    // by email
    public void deleteByEmail(String email) {
        userRepository.deleteByEmail(email);
    }
}
