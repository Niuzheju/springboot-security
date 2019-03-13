package com.niuzj.springbootsecurity.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role implements Serializable {

    private static final long serialVersionUID = -6904968559702309994L;

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    @Column(name = "create_time")
    private Date createdTime;

    private String name;

    private String description;

    private String state;

    @Transient
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
