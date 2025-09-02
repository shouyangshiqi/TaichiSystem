package com.bsu.taichi.auth.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bsu.taichi.auth.entity.dao.SysUserDao;
import com.bsu.taichi.auth.entity.dbo.SysUser;
import com.bsu.taichi.base.execption.TaichiException;
import com.bsu.taichi.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author khran
 * @version 1.0
 * @description
 */

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private SysUserDao sysUserDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 获取用户信息
        SysUser sysUser = sysUserDao.selectByUsername(username);

        if(sysUser==null){
            throw new TaichiException("用户不存在");
        }

        return User.builder()
                .username(sysUser.getUsername())
                .password(sysUser.getPassword())
                .authorities("ROLE_USER", "P1")  // ROLE_USER是角色，P1是权限
                .build();
    }
}
