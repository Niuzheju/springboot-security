package com.niuzj.springbootsecurity.dao;

import com.niuzj.springbootsecurity.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
