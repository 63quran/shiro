package com.example.Service;

import com.example.pojo.SysUser;

public interface UserService {

    SysUser queryUserByName(String userName);
}
