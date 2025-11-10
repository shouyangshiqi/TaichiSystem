package com.bsu.taichi.auth.entity.vo;

import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * @author khran
 * @version 1.0
 * @description
 */

@Data
public class RegistParamsRequest {

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
