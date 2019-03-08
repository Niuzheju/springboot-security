package com.niuzj.springbootsecurity.service;

import com.niuzj.springbootsecurity.model.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findByNames(List<String> roles);
}
