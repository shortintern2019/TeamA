package com.shogun.suzukisan.repository;

import com.shogun.suzukisan.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long>
{
    public UserEntity findByUsername(String username);
}