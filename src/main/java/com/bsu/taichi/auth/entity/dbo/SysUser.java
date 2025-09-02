package com.bsu.taichi.auth.entity.dbo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.io.Serializable;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
* 用户表
* @TableName sys_user
*/

@Data
@TableName("sys_user")
public class SysUser implements Serializable {

    /**
    * 用户ID
    */
    @ApiModelProperty("用户ID")
    private Long id;
    /**
    * 用户名
    */
    @NotBlank(message="[用户名]不能为空")
    @Size(max= 50,message="编码长度不能超过50")
    @ApiModelProperty("用户名")
    @Length(max= 50,message="编码长度不能超过50")
    private String username;
    /**
    * 加密密码
    */
    @NotBlank(message="[加密密码]不能为空")
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("加密密码")
    @Length(max= 255,message="编码长度不能超过255")
    private String password;
    /**
    * 邮箱
    */
    @Size(max= 100,message="编码长度不能超过100")
    @ApiModelProperty("邮箱")
    @Length(max= 100,message="编码长度不能超过100")
    private String email;
    /**
    * 手机号
    */
    @Size(max= 20,message="编码长度不能超过20")
    @ApiModelProperty("手机号")
    @Length(max= 20,message="编码长度不能超过20")
    private String phone;
    /**
    * 昵称
    */
    @Size(max= 100,message="编码长度不能超过100")
    @ApiModelProperty("昵称")
    @Length(max= 100,message="编码长度不能超过100")
    private String nickname;
    /**
    * 头像URL
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("头像URL")
    @Length(max= 255,message="编码长度不能超过255")
    private String avatar;
    /**
    * 状态: 0-禁用, 1-正常
    */
    @ApiModelProperty("状态: 0-禁用, 1-正常")
    private Integer status;
    /**
    * 用户类型: USER-普通用户, ADMIN-管理员, SUPER_ADMIN-超级管理员
    */
    @NotBlank(message="[用户类型: USER-普通用户, ADMIN-管理员, SUPER_ADMIN-超级管理员]不能为空")
    @Size(max= 20,message="编码长度不能超过20")
    @ApiModelProperty("用户类型: USER-普通用户, ADMIN-管理员, SUPER_ADMIN-超级管理员")
    @Length(max= 20,message="编码长度不能超过20")
    private String userType;
    /**
    * 最后登录时间
    */
    @ApiModelProperty("最后登录时间")
    private Date lastLoginTime;
    /**
    * 创建时间
    */
    @ApiModelProperty("创建时间")
    private Date createTime;
    /**
    * 更新时间
    */
    @ApiModelProperty("更新时间")
    private Date updateTime;

}
