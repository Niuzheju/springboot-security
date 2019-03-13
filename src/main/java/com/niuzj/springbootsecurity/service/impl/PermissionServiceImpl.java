package com.niuzj.springbootsecurity.service.impl;

import com.niuzj.springbootsecurity.dao.PermissionRespository;
import com.niuzj.springbootsecurity.model.Permission;
import com.niuzj.springbootsecurity.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionRespository permissionRespository;

    @Override
    public List<Permission> findByRoleId(Long id) {
        try {
            return permissionRespository.findByRoleId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
