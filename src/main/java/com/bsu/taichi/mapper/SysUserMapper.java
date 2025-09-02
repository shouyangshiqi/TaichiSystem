package com.bsu.taichi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bsu.taichi.auth.entity.dbo.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 10906
* @description 针对表【sys_user(用户表)】的数据库操作Mapper
* @createDate 2025-09-01 15:48:34
* @Entity com/bsu/taichi.dbo.SysUser
*/

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

}




