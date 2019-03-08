package com.niuzj.springbootsecurity.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "role")
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "create_time")
    private long createdTime = System.currentTimeMillis();

    @Column(name = "is_removed")
    private Boolean isRemoved = Boolean.FALSE;

    private String name;

    private String nickname;

    private String description;

    //是否为内置
    @Column(name = "built_in")
    private boolean builtIn = false;

    //是否被禁用
    private Boolean banned = Boolean.FALSE;

    //角色创建者
    private String proposer;

    private List<Permission> permissions = new ArrayList<>();

    public void setName(String name) {
        if (name == null || "".equals(name.trim())){
            return;
        }
        if (name.startsWith("ROLE_")){
            this.name = name;
        } else {
            this.name = "ROLE_" + name;
        }
    }
}
