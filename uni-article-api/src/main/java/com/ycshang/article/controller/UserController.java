package com.ycshang.article.controller;

import com.ycshang.article.common.ResponseResult;
import com.ycshang.article.common.ResultCode;

import com.ycshang.article.model.dto.BindPhoneDto;
import com.ycshang.article.model.dto.LoginDto;
import com.ycshang.article.model.dto.WxLoginDto;
import com.ycshang.article.model.entity.User;
import com.ycshang.article.service.RedisService;
import com.ycshang.article.service.UserService;
import com.ycshang.article.util.FileResource;
import com.ycshang.article.util.SmsUtil;
import com.ycshang.article.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @Resource
    private FileResource fileResource;

    @PostMapping("/passwordLogin")
    public ResponseResult passowrdLogin(@RequestBody LoginDto loginDto) {
        boolean flag = userService.passwordLogin(loginDto);
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
        //    ???????????????
        String code = StringUtil.getVerifyCode();
        //    ??????????????????????????????
        boolean flag = smsUtil.sendSms(phone, code);
        //???????????????redis
        redisService.set(phone, code, 1L);
        if (flag) {
            //    ????????????????????????
            return ResponseResult.success(code);
        }
        return ResponseResult.failure(ResultCode.SMS_ERROR);
    }

    @PostMapping(value = "/login/wx")
    public ResponseResult wxLogin(@RequestBody WxLoginDto wxLoginDto) {
        User user = userService.wxLogin(wxLoginDto);
        if (user == null) {
            return ResponseResult.success(userService.findByOpenId(wxLoginDto.getWxOpenId()));
        }
        return ResponseResult.success(userService.findByOpenId(wxLoginDto.getWxOpenId()));
    }


    @PostMapping(value = "/upload")
    public ResponseResult uploadFile(MultipartFile file) {
        log.info("????????????");
        //    ?????????????????????????????????????????????
        String path = null;
        //    ????????????????????????
        if (file != null) {
            //    ???????????????????????????
            String fileName = file.getOriginalFilename();
            log.info(fileName);
            //    ???????????????????????????????????????????????????
            path = userService.uploadFile(file);
        }
        if (StringUtils.isNotBlank(path)) {
            //    ???????????????????????????????????????????????????????????????url
            path = fileResource.getOssHost() + path;
            log.info(path);
        }
        return ResponseResult.success(path);
    }


    @PostMapping(value = "/update")
    public ResponseResult update(@RequestBody User user) {
        log.info("user:" + user);
        return ResponseResult.success(userService.updateUser(user));
    }

    @PostMapping(value = "/bind")
    public ResponseResult bindPhone(@RequestBody BindPhoneDto bindPhoneDto) {
        log.info(String.valueOf(bindPhoneDto));
        User user = userService.bindPhone(bindPhoneDto);
        return ResponseResult.success(user);
    }
}