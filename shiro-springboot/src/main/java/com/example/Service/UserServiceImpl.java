package com.example.Service;

import com.example.mapper.UserMapper;
import com.example.pojo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public SysUser queryUserByName(String userName) {
//        String test = userMapper.test();
        return userMapper.queryUser(userName);
    }
}
