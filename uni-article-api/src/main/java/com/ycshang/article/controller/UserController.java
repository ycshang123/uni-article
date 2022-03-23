package com.ycshang.article.controller;

import com.ycshang.article.common.ResponseResult;
import com.ycshang.article.common.ResultCode;
import com.ycshang.article.model.dto.LoginDto;
import com.ycshang.article.service.RedisService;
import com.ycshang.article.service.UserService;
import com.ycshang.article.util.SmsUtil;
import com.ycshang.article.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    @Resource
    private SmsUtil smsUtil;
    @Resource
    private RedisService redisService;

    @PostMapping("/passwordLogin")
    public ResponseResult passowrdLogin(@RequestBody LoginDto loginDto) {
        boolean flag = userService.passowrdLogin(loginDto);
        if (flag) {
            return ResponseResult.success(userService.getUser(loginDto.getPhone()));
        } else {
            return ResponseResult.failure(ResultCode.USER_SIGN_IN_FAIL);
        }
    }


    @PostMapping("/verifyLogin")
    public ResponseResult validateLogin(@RequestBody LoginDto loginDto) {
        boolean flag = userService.verifyCodeLogin(loginDto);
        if (flag) {
            return ResponseResult.success(userService.getUser(loginDto.getPhone()));
        } else {
            return ResponseResult.failure(ResultCode.USER_VERIFY_CODE_ERROR);
        }
    }


    @PostMapping(value = "/sms")
    public ResponseResult sendSms(@RequestParam String phone) {
        //    随机验证码
        String code = StringUtil.getVerifyCode();
        //    给入参手机号发送短信
        boolean flag = smsUtil.sendSms(phone, code);
        //验证码存入redis
        redisService.set(phone, code, 1L);
        if (flag) {
            //    结果返回给客户端
            return ResponseResult.success(code);
        }
        return ResponseResult.failure(ResultCode.SMS_ERROR);
    }
}