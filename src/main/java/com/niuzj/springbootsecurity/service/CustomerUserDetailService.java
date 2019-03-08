package com.niuzj.springbootsecurity.service;

import com.niuzj.springbootsecurity.model.Permission;
import com.niuzj.springbootsecurity.model.Role;
import com.niuzj.springbootsecurity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomerUserDetailService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + "不存在");
        }
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        List<String> roles = user.getRoles();
        List<Role> roleList = roleService.findByNames(roles);
        if (roleList == null || roleList.isEmpty()) {
            return null;
        }
        for (Role role : roleList) {
            List<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions) {
                for (String privileges : permission.getPrivileges().keySet()) {
                    authorities.add(new SimpleGrantedAuthority(String.format("%s-%s", permission.getResourceId(), privileges)));
                }
            }
        }
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), user.isEnabled(), user.isAccountNonExpired()
                , user.isCredentialsNonExpired(), user.isAccountNonLocked(), authorities);
    }
}
