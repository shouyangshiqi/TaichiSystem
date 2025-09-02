package com.bsu.taichi.auth.entity.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bsu.taichi.auth.entity.dbo.SysUser;
import com.bsu.taichi.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author khran
 * @version 1.0
 * @description
 */

@Component
public class SysUserDao {

    @Autowired
    private SysUserMapper sysUserMapper;


    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    public SysUser selectByUsername(String username) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username);
        return sysUserMapper.selectOne(wrapper);

    }

    public void insert(SysUser newUser) {
        sysUserMapper.insert(newUser);
    }
}
