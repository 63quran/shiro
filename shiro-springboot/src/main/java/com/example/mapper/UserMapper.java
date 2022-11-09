package com.example.mapper;

import com.example.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
//@Mapper
public interface UserMapper {
    SysUser queryUser(@Param("userName") String userName);

    String test();
}
