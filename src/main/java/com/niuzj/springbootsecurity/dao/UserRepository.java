package com.niuzj.springbootsecurity.dao;

import com.niuzj.springbootsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
