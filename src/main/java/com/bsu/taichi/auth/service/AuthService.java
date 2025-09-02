package com.bsu.taichi.auth.service;

import com.bsu.taichi.auth.entity.dao.SysUserDao;
import com.bsu.taichi.auth.entity.dbo.SysUser;
import com.bsu.taichi.auth.entity.vo.RegistParamsVo;
import com.bsu.taichi.base.model.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author khran
 * @version 1.0
 * @description
 */

@Service
public class AuthService {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public RestResponse<String> register(RegistParamsVo registParamsVo) {
        // 检查用户名是否已存在
        SysUser existingUser = sysUserDao.selectByUsername(registParamsVo.getUsername());
        if (existingUser != null) {
            return RestResponse.validfail("用户名已存在");
        }

        // 创建新用户
        SysUser newUser = new SysUser();
        newUser.setUsername(registParamsVo.getUsername());
        newUser.setPassword(passwordEncoder.encode(registParamsVo.getPassword()));
        newUser.setNickname(registParamsVo.getNickname());

        // 保存用户到数据库
        sysUserDao.insert(newUser);
        return RestResponse.success("注册成功");
    }
}
