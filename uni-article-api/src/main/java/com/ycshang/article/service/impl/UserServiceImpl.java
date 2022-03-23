package com.ycshang.article.service.impl;

import com.ycshang.article.mapper.UserMapper;
import com.ycshang.article.model.dto.LoginDto;
import com.ycshang.article.model.entity.User;
import com.ycshang.article.service.RedisService;
import com.ycshang.article.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @program: uni-article-api
 * @description:
 * @author: ycshang
 * @create: 2022-03-22 17:29
 **/
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisService redisService;

    @Override
    public boolean passowrdLogin(LoginDto loginDto) {
        User user = getUser(loginDto.getPhone());
        if (user != null) {
            return DigestUtils.md5Hex(loginDto.getPassword()).equals(user.getPassword());
        }
        return false;
    }

    @Override
    public User getUser(String phone) {
        return userMapper.findUserByPhone(phone);
    }

    @Override
    public boolean verifyCodeLogin(LoginDto loginDto) {
        //判断redis中是否存在对应的key
        boolean flag = redisService.existsKey(loginDto.getPhone());
        if (flag) {
            String code = redisService.getValue(loginDto.getPhone(), String.class);
            if (code.equalsIgnoreCase(loginDto.getCode())) {
                User user = userMapper.findUserByPhone(loginDto.getPhone());
                if (user == null) {
                    user = User.builder().phone(loginDto.getPhone())
                            .nickname("新用户")
                            .bgImg("https://cdn.jsdelivr.net/gh/ycshang123/image-hosting@master/xxzl.5gkix7c0skw0.jpg")
                            .avatar("https://cdn.jsdelivr.net/gh/ycshang123/image-hosting@master/user-avatar-fill.700e2vn53rc0.webp").createTime(new Date()).build();
                    userMapper.insert(user);
                }
                return true;
            }
        }
        return false;
    }
}