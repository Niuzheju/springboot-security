package com.niuzj.springbootsecurity;

import com.niuzj.springbootsecurity.dao.RoleRepository;
import com.niuzj.springbootsecurity.dao.UserRepository;
import com.niuzj.springbootsecurity.model.Role;
import com.niuzj.springbootsecurity.model.User;
import com.niuzj.springbootsecurity.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class SystemInitializer {

    @Value("${initialzation.file.users}")
    private String userFileName;

    @Value(("${initialzation.file.roles}"))
    private String roleFileName;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public boolean initialize(){
        try {
            ClassLoader classLoader = SystemInitializer.class.getClassLoader();
            InputStream userInput = classLoader.getResourceAsStream(userFileName);
            InputStream roleInput = classLoader.getResourceAsStream(roleFileName);
            if (userInput == null){
                throw new Exception("用户配置文件不存在");
            }
            if (roleInput == null){
                throw new Exception("角色配置文件不存在");
            }
            List<Role> roles = JsonUtil.readerToList(new InputStreamReader(roleInput, StandardCharsets.UTF_8), Role.class);
            roles.forEach((role) -> {
                if (roleRepository.findByName(role.getName()) == null){
                    roleRepository.save(role);
                }
            });

            List<User> users = JsonUtil.readerToList(new InputStreamReader(userInput, StandardCharsets.UTF_8), User.class);
            users.forEach((user) -> {
                if (userRepository.findByUsername(user.getUsername()) == null){
                    userRepository.save(user);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }





}
