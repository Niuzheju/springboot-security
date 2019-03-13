package com.niuzj.springbootsecurity.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Table(name = "permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = -3364071680833366400L;

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    private String code;

    private String module;

    private String desc;

    @Column(name = "create_time")
    private Date createTime;



}
