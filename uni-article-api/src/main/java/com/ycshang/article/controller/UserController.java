package com.ycshang.article.controller;

import com.ycshang.article.common.ResponseResult;
import com.ycshang.article.common.ResultCode;
import com.ycshang.article.model.dto.LoginDto;
import com.ycshang.article.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: uni-article-api
 * @description:
 * @author: ycshang
 * @create: 2022-03-22 17:42
 **/
@RestController
@RequestMapping(value = "/api/v1/users")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody LoginDto loginDto){
        boolean flag = userService.login(loginDto);
        if(flag){
            return ResponseResult.success(userService.getUser(loginDto.getPhone()));
        }else {
            return ResponseResult.failure(ResultCode.USER_SIGN_IN_FAIL);
        }
    }
}