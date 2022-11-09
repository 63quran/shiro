package com.example.config;

import com.example.Service.UserService;
import com.example.pojo.SysUser;
import jdk.nashorn.internal.parser.Token;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=>授权");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        info.addStringPermission("user:add");

        //拿到当前登录对象
        Subject subject = SecurityUtils.getSubject();
        SysUser currentUser = (SysUser) subject.getPrincipal();


        //设置当前用户权限
        info.addStringPermission(currentUser.getPerms());
        return info;

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了=>认证");

        UsernamePasswordToken userToken =(UsernamePasswordToken) authenticationToken;

        SysUser sysUser = userService.queryUserByName(userToken.getUsername());

        if (sysUser == null){
            return null;//抛出异常UnknownAccountException
        }

        //密码认证 shiro做
        return new SimpleAuthenticationInfo(sysUser,sysUser.getUserPassword(),"");
    }
}
