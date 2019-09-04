package com.shogun.suzukisan.repository;

import com.shogun.suzukisan.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByName(String name);
    public User findByEmail(String Email);
    List<User> findByMentorScore(Integer mentorScore);
    List<User> findByMenteeScore(Integer menteeScore);
    List<User> findByMentorCount(Integer mentorCount);
    List<User> findByMenteeCount(Integer menteeCount);
}
