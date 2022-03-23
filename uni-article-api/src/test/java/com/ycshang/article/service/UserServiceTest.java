package com.ycshang.article.service;

import com.ycshang.article.model.dto.LoginDto;
import com.ycshang.article.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Slf4j
class UserServiceTest {
    @Resource
    private UserService userService;

    @Test
    void login() {
        LoginDto loginDto = LoginDto.builder()
                .phone("13913457284").password("123123").build();
        boolean flag = userService.passowrdLogin(loginDto);
        assertTrue(flag);
    }

    @Test
    void getUser(){
        User user = userService.getUser("13913457284");
        log.info(String.valueOf(user));
    }

}