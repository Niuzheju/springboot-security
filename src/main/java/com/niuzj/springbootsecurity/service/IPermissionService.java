package com.niuzj.springbootsecurity.service;

import com.niuzj.springbootsecurity.model.Permission;

import java.util.List;

public interface IPermissionService {
    List<Permission> findByRoleId(Long id);
}
