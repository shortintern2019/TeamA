package com.shogun.suzukisan.repository;

import com.shogun.suzukisan.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByName(String name);
//    TODO: Optionalで良いか？
    Optional<User> findByEmail(String Email);

    // = mentorScore
    List<User> findByMentorScoreEquals(Integer mentorScore);
    // = menteeScore
    List<User> findByMenteeScoreEquals(Integer menteeScore);
    // <= mentorScore
    List<User> findByMentorScoreGreaterThanEqual(Integer mentorScore);
    // <= menteecore
    List<User> findByMenteeScoreGreaterThanEqual(Integer menteeScore);
    // >= mentorScore
    List<User> findByMentorScoreLessThanEqual(Integer mentorScore);
    // >= menteeScore
    List<User> findByMenteeScoreLessThanEqual(Integer menteeScore);

    // = mentorCount
    List<User> findByMentorCountEquals(Integer mentorCount);
    // = menteeCount
    List<User> findByMenteeCountEquals(Integer menteeCount);
    // <= mentorCount
    List<User> findByMentorCountGreaterThanEqual(Integer mentorCount);
    // <= menteecore
    List<User> findByMenteeCountGreaterThanEqual(Integer menteeCount);
    // >= mentorCount
    List<User> findByMentorCountLessThanEqual(Integer mentorCount);
    // >= menteeCount
    List<User> findByMenteeCountLessThanEqual(Integer menteeCount);

    // UPDATE
    // update name
    @Modifying
    @Transactional
    @Query("UPDATE User h SET h.name = name where h = userId")
    Integer updateName(@Param("name") String name, @Param("userId") Long userId);
    // update password
    @Modifying
    @Transactional
    @Query("UPDATE User h SET h.password = password where h = userId")
    Integer updatePassword(@Param("password") String password, @Param("userId") Long userId);
    // update email
    @Modifying
    @Transactional
    @Query("UPDATE User h SET h.email = email where h = userId")
    Integer updateEmail(@Param("email") String email, @Param("userId") Long userId);
    // update mentorScore
    @Modifying
    @Transactional
    @Query("UPDATE User h SET h.mentorScore = mentorScore where h = userId")
    Integer updateMentorScore(@Param("mentorScore") Integer mentorScore, @Param("userId") Long userId);
    // update menteeScore
    @Modifying
    @Transactional
    @Query("UPDATE User h SET h.menteeScore = menteeScore where h = userId")
    Integer updateMenteeScore(@Param("menteeScore") Integer menteeScore, @Param("userId") Long userId);
    // update mentorCounter
    @Modifying
    @Transactional
    @Query("UPDATE User h SET h.mentorCount = mentorCount where h = userId")
    Integer updateMentorCount(@Param("mentorCount") Integer mentorCount, @Param("userId") Long userId);
    // update menteeCounter
    @Modifying
    @Transactional
    @Query("UPDATE User h SET h.menteeCount = menteeCount where h = userId")
    Integer updateMenteeCount(@Param("menteeCount") Integer menteeCount, @Param("userId") Long userId);

    // DELETE
    // by email
    @Modifying
    @Transactional
    void deleteByEmail(String email);
}
