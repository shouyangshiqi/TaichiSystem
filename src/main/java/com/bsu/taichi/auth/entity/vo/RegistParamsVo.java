package com.bsu.taichi.auth.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author khran
 * @version 1.0
 * @description
 */

@Data
public class RegistParamsVo {

    @ApiParam("用户名")
    private String username;

    @ApiParam("密码")
    private String password;

    @ApiParam("邮箱")
    private String email;

    @ApiParam("手机号")
    private String phone;

    @ApiParam("昵称")
    private String nickname;


}
