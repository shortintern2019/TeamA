package com.shogun.suzukisan.service;

import com.shogun.suzukisan.entity.Mentor;
import com.shogun.suzukisan.entity.User;
import com.shogun.suzukisan.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MentorService {
    @Autowired
    MentorRepository mentorRepository;

    // CREATE
    public Mentor create(Mentor mentor) {
        return mentorRepository.save(mentor);
    }

    // READ
    public Iterable<Mentor> findAll() {
        return mentorRepository.findAll();
    }
    public Optional<Mentor> findById(Long id) {
        return mentorRepository.findById(id);
    }
    public Optional<Mentor> findByUserId(User user) {
        return mentorRepository.findByUserId(user);
    }
    public Optional<Mentor> findByRoomName(String roomName) {
        return mentorRepository.findByRoomName(roomName);
    }

    // UPDATE
    public Integer updateRoomName(String roomName, Long mentorId) {
        return mentorRepository.updateRoomName(roomName, mentorId);
    }

    // DELETE
    //    TODO: リレーションを消してからでないとエラー(参照整合性制約違反)が起こる
    public void deleteById(Long id) {
        mentorRepository.deleteById(id);
    }
    public void deleteByUserId(User user) {
        mentorRepository.deleteByUserId(user);
    }
    public void deleteByRoomName(String roomName) {
        mentorRepository.deleteByRoomName(roomName);
    }
}
