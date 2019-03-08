package com.niuzj.springbootsecurity.service;

import com.niuzj.springbootsecurity.model.User;

public interface IUserService {
    User findByUsername(String username);
}
