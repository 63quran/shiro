package com.example.Service;

import com.example.shirospringboot.ShiroSpringbootApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ShiroSpringbootApplication.class)
class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    void testUser(){
        System.out.println(userService.queryUserByName("李四").getUserName());
    }
}