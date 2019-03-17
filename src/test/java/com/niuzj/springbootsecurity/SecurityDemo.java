package com.niuzj.springbootsecurity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 简单演示security认证过程
 */
public class SecurityDemo {

    private static AuthenticationManager authenticationManager = new SampleAuthenticationManager();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("输入用户名");
            String username = reader.readLine();
            System.out.println("输入密码");
            String password = reader.readLine();
            try {
                Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
                System.out.println("before:" + authentication);
                Authentication result = authenticationManager.authenticate(authentication);
                System.out.println("after:" + result);
                SecurityContextHolder.getContext().setAuthentication(result);
                break;
            } catch (AuthenticationException e) {
                System.out.println("authentication failed" + e.getMessage());
            }
        }
        System.out.println("successfully authenticated security context contains " + SecurityContextHolder.getContext().getAuthentication());


    }
}

class SampleAuthenticationManager implements AuthenticationManager {

    private static final List<GrantedAuthority> AUTHORITIES = new ArrayList<>();

    static {
        AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
    }

    /**
     * 此处自定义验证规则
     * 如果用户名和密码相同就验证通过
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication.getName().equals(authentication.getCredentials())) {
            //认证通过,重新创建authentication,并授予权限
            return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), AUTHORITIES);
        }
        throw new BadCredentialsException("bad credentials");
    }
}
