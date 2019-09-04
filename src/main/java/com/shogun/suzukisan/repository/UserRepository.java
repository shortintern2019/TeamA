package com.shogun.suzukisan.repository;

import com.shogun.suzukisan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
    public User findByEmail(String Email);
}