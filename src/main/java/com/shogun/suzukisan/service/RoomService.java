package com.shogun.suzukisan.service;

import com.shogun.suzukisan.entity.Room;
import com.shogun.suzukisan.entity.User;
import com.shogun.suzukisan.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;

    // CREATE
    public Room create(Room room) {
        return roomRepository.save(room);
    }

    // READ
    public Iterable<Room> findAll() {
        return roomRepository.findAll();
    }
    public Optional<Room> findById(Long id) {
        return roomRepository.findById(id);
    }
    public Optional<Room> findByName(String name) {
        return roomRepository.findByName(name);
    }
    public Optional<Room> findByMentorId(User mentor) {
        return roomRepository.findByMentorId(mentor);
    }
    public Optional<Room> findByMenteeId(User mentee) {
        return roomRepository.findByMenteeId(mentee);
    }

    // DELETE
    //    TODO: リレーションを消してからでないとエラー(参照整合性制約違反)が起こる
    public void deleteById(Long id) {
        roomRepository.deleteById(id);
    }
    public void deleteByName(String name) {
        roomRepository.deleteByName(name);
    }
    public void deleteByMentorId(User mentor) {
        roomRepository.deleteByMentorId(mentor);
    }
    public void deleteByMenteeId(User mentee) {
        roomRepository.deleteByMenteeId(mentee);
    }
}
