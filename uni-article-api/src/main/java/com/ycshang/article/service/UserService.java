package com.ycshang.article.service;

import com.ycshang.article.model.dto.LoginDto;
import com.ycshang.article.model.entity.User;

/**
 * @program: uni-article-api
 * @description:
 * @author: ycshang
 * @create: 2022-03-22 17:27
 **/
public interface UserService {
    /**
     * 登录
     *
     * @param loginDto
     * @return
     */
    boolean passowrdLogin(LoginDto loginDto);

    /**
     * 根据手机号查找用户
     *
     * @param phone
     * @return
     */
    User getUser(String phone);

    /**
     * 验证码登录
     * @param loginDto
     * @return
     */
    boolean verifyCodeLogin(LoginDto loginDto);
}