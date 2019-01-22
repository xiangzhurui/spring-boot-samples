package com.xiangzhurui.example.springbootjpa.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;

import lombok.Data;


@Entity
@Table(name = "user")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1300893349519704285L;


    @Id
    private String username;
    private String email;
    private String password;
    private java.sql.Timestamp createTime;
}
