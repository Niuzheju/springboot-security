package com.niuzj.springbootsecurity.service.impl;

import com.niuzj.springbootsecurity.dao.UserRepository;
import com.niuzj.springbootsecurity.model.User;
import com.niuzj.springbootsecurity.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User findByUsername(String username) {
        try {
            return userRepository.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
