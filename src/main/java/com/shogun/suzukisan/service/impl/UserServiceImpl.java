package com.shogun.suzukisan.service.impl;

import com.shogun.suzukisan.entity.UserEntity;
import com.shogun.suzukisan.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserDetailsService
{
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        if (StringUtils.isEmpty(username))
        {
            throw new UsernameNotFoundException("");
        }

        UserEntity memberEntity = userRepository.findByUsername(username);
        if (memberEntity == null)
        {
            throw new UsernameNotFoundException("");
        }

        return memberEntity;
    }

    @Autowired
    public void setMemberRepository(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }
}