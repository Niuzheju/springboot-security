package com.niuzj.springbootsecurity.service.impl;

import com.niuzj.springbootsecurity.dao.RoleRepository;
import com.niuzj.springbootsecurity.model.Role;
import com.niuzj.springbootsecurity.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findByNames(List<String> roles) {
        try {
            Example example = new Example(Role.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andIn("name", roles);
            return roleRepository.selectByExample(example);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
