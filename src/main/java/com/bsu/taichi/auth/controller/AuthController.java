package com.bsu.taichi.auth.controller;

import com.bsu.taichi.auth.entity.dbo.SysUser;
import com.bsu.taichi.auth.entity.vo.LoginRequest;
import com.bsu.taichi.auth.entity.vo.LoginResponse;
import com.bsu.taichi.auth.entity.vo.RegistParamsRequest;
import com.bsu.taichi.auth.service.AuthService;
import com.bsu.taichi.auth.service.UserService;
import com.bsu.taichi.auth.util.JwtUtils;
import com.bsu.taichi.base.model.RestResponse;
import com.bsu.taichi.mapper.SysUserMapper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map; /**
 * @author khran
 * @version 1.0
 * @description
 */

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;


    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private AuthService authService;



    @PostMapping("/login")
    public RestResponse<LoginResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        UserDetails userDetails = userService.loadUserByUsername(username);
        String jwt = jwtUtils.generateToken(userDetails);

        LoginResponse response =  new LoginResponse();
        response.setToken(jwt);
        return RestResponse.success(response);
    }

    @GetMapping("/login-success")
    public String loginSuccess() {
        return "登录成功";
    }

    @ApiOperation("注册用户信息")
    @PostMapping("/register")
    public RestResponse<String> regester(@RequestBody RegistParamsRequest registParamsRequest){
        return authService.register(registParamsRequest);
    }

    @GetMapping("/user/{id}")
    public RestResponse<SysUser>  getuser(@PathVariable("id") String id) {
        SysUser user = sysUserMapper.selectById(id);
        return RestResponse.success(user);
    }
}
