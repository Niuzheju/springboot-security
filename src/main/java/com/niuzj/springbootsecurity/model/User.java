package com.niuzj.springbootsecurity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User implements UserDetails {

    private static final long serialVersionUID = 7212049228053094624L;

    //密码加密工具
    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Long createTime = System.currentTimeMillis();

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 登录密码
     */
    @JsonIgnore
    private String password;

    /**
     * 用户类型
     */
    private String type;

    /**
     * 该用户关联的企业/区块id
     */
    private Map<String, Object> associatedResources = new HashMap<>();

    /**
     * 用户关注的企业列表
     */
    private List<String> favourite = new ArrayList<>();

    /**
     * 用户拥有的角色
     */
    private List<String> roles = new ArrayList<>();

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}