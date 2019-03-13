package com.niuzj.springbootsecurity.dao;

import com.niuzj.springbootsecurity.model.Role;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface RoleRepository extends Mapper<Role> {

    List<Role> findByUserId(Long id);
}
