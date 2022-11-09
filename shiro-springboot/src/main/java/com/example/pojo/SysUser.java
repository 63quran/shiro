package com.example.pojo;

import lombok.Data;

@Data
public class SysUser {

    private int id;
    private String userName;
    private String userPassword;
    private String age;
    private String gender;
    private String createTime;
    private String perms;
}
