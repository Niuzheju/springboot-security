package com.niuzj.springbootsecurity.dao;

import com.niuzj.springbootsecurity.model.Permission;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PermissionRespository extends Mapper<Permission> {
    List<Permission> findByRoleId(Long id);
}
