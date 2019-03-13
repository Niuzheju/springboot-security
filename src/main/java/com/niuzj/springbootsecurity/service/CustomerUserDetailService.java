package com.niuzj.springbootsecurity.service;

import com.niuzj.springbootsecurity.dao.RoleRepository;
import com.niuzj.springbootsecurity.model.Permission;
import com.niuzj.springbootsecurity.model.Role;
import com.niuzj.springbootsecurity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerUserDetailService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + "不存在");
        }
        List<Role> roles = user.getRoles();
        if (roles == null || roles.isEmpty()) {
            return null;
        }
        //查询所有角色的权限
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : roles) {
            List<Permission> permissions = permissionService.findByRoleId(role.getId());
            if (permissions != null && !permissions.isEmpty()) {
                for (Permission permission : permissions) {
                    authorities.add(new SimpleGrantedAuthority(String.format("%s-%s", permission.getModule(), permission.getCode())));
                }
            }
        }
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), user.isEnabled(), user.isAccountNonExpired()
                , user.isCredentialsNonExpired(), user.isAccountNonLocked(), authorities);
    }
}
