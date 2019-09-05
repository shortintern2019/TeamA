package com.shogun.suzukisan.service;

import com.shogun.suzukisan.entity.Mentee;
import com.shogun.suzukisan.entity.User;
import com.shogun.suzukisan.repository.MenteeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenteeService {
    @Autowired
    MenteeRepository menteeRepository;

    // CREATE
    public Mentee create(Mentee mentee) {
        return menteeRepository.save(mentee);
    }

    // READ
    public Iterable<Mentee> findAll() {
        return menteeRepository.findAll();
    }
    public Optional<Mentee> findById(Long id) {
        return menteeRepository.findById(id);
    }
    public Optional<Mentee> findByUserId(User user) {
        return menteeRepository.findByUserId(user);
    }
    public Optional<Mentee> findByRoomName(String roomName) {
        return menteeRepository.findByRoomName(roomName);
    }

    // UPDATE
    public Integer updateRoomName(String roomName, Long menteeId) {
        return menteeRepository.updateRoomName(roomName, menteeId);
    }

    // DELETE
    //    TODO: リレーションを消してからでないとエラー(参照整合性制約違反)が起こる
    public void deleteById(Long id) {
        menteeRepository.deleteById(id);
    }
    public void deleteByUserId(User user) {
        menteeRepository.deleteByUserId(user);
    }
    public void deleteByRoomName(String roomName) {
        menteeRepository.deleteByRoomName(roomName);
    }
}
