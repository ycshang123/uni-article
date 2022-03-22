package com.ycshang.article.mapper;

import com.ycshang.article.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Date;

@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserMapperTest {
    @Resource
    private UserMapper userMapper;


    @Test
    public void addUser() {
        User user = User.builder()
                .nickname("ycshang")
                .password("4297f44b13955235245b2497399d7a93")
                .phone("13913457284")
                .address("江苏南京")
                .createTime(new Date())
                .avatar("https://cdn.jsdelivr.net/gh/ycshang123/image-hosting@master/me.583hffnfvkw0.png")
                .birthday(LocalDate.now())
                .gender(1).build();
        userMapper.insert(user);
    }

    @Test
    void findUserByPhone() {
        User user = userMapper.findUserByPhone("13913457284");
        log.info(String.valueOf(user));
    }

}