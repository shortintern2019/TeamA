package com.shogun.suzukisan.service.impl;

import com.shogun.suzukisan.entity.User;
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

        User user = userRepository.findByEmail(username);
        if (user == null)
        {
            throw new UsernameNotFoundException("");
        }

        return user;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }
}