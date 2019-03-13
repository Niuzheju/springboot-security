package com.niuzj.springbootsecurity.service.impl;

import com.niuzj.springbootsecurity.dao.RoleRepository;
import com.niuzj.springbootsecurity.dao.UserRepository;
import com.niuzj.springbootsecurity.model.Role;
import com.niuzj.springbootsecurity.model.User;
import com.niuzj.springbootsecurity.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    /**
     * 根据用户名查询用户
     * 用户关联的角色也要查询出来
     */
    @Override
    public User findByUsername(String username) {
        try {
            User query = new User();
            query.setUsername(username);
            User user = userRepository.selectOne(query);
            if (user != null){
                List<Role> roles = roleRepository.findByUserId(user.getId());
                user.setRoles(roles);
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
