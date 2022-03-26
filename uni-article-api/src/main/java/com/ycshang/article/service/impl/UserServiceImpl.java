package com.ycshang.article.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.ycshang.article.mapper.UserMapper;
import com.ycshang.article.model.dto.LoginDto;
import com.ycshang.article.model.dto.WxLoginDto;
import com.ycshang.article.model.entity.User;
import com.ycshang.article.service.RedisService;
import com.ycshang.article.service.UserService;
import com.ycshang.article.util.AliyunResource;
import com.ycshang.article.util.FileResource;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

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

    @Resource
    private FileResource fileResource;
    @Resource
    private AliyunResource aliyunResource;

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
                            .bgImg("https://cdn.jsdelivr.net/gh/ycshang123/image-hosting@master/pag3.1ao7erlnhbcw.jpg")
                            .avatar("https://cdn.jsdelivr.net/gh/ycshang123/image-hosting@master/user-avatar-fill.700e2vn53rc0.webp").createTime(new Date()).build();
                    userMapper.insert(user);
                }
                return true;
            }
        }
        return false;
    }


    @Override
    public User findByOpenId(String wxOpenId) {
        return userMapper.findUserByOpenId(wxOpenId);
    }

    @Override
    public User wxLogin(WxLoginDto wxLoginDto) {

        User user = findByOpenId(wxLoginDto.getWxOpenId());
        if (user == null) {
            user = User.builder()
                    .wxOpenId(wxLoginDto.getWxOpenId())
                    .gender(wxLoginDto.getGender())
                    .nickname(wxLoginDto.getNickname())
                    .bgImg("https://cdn.jsdelivr.net/gh/ycshang123/image-hosting@master/pag3.1ao7erlnhbcw.jpg")
                    .address("江苏南京")
                    .avatar(wxLoginDto.getAvatar()).createTime(new Date()).build();
            userMapper.insert(user);

        }
        return user;
    }

    @Override
    public String uploadFile(MultipartFile file) {
        //    读入配置文件
        String endpoint = fileResource.getEndPoint();
        String accessKeyId = aliyunResource.getAccessKeyId();
        String accessKeySecret = aliyunResource.getAccessKeySecret();
        //    创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String fileName = file.getOriginalFilename();
        //    分割文件名，获得文件后缀名
        assert fileName != null;
        String[] fileNameArr = fileName.split("\\.");
        String suffix = fileNameArr[fileNameArr.length - 1];
        //    拼接得到新的上传文件名
        String uploadFileName = fileResource.getObjectName() + UUID.randomUUID() + "." + suffix;
        //   上传网络需要用的字节流
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            System.err.println("上传文件出现异常");
        }
        //    执行阿里云上传文件操作
        ossClient.putObject(fileResource.getBucketName(), uploadFileName, inputStream);
        //    关闭OSSClient
        ossClient.shutdown();
        return uploadFileName;
    }

    @Override
    public User updateUser(User user) {
        //先根据手机号查出数据库用户信息
        User saveUser = getUser(user.getPhone());
        //如果是修改密码的请求，将密码加密
        if (!user.getPassword().equals(saveUser.getPassword())) {
            saveUser.setPassword(DigestUtils.md5Hex(user.getPassword()));
        } else {
            saveUser.setPassword(user.getPassword());
        }
        saveUser.setNickname(user.getNickname());
        saveUser.setAvatar(user.getAvatar());
        saveUser.setGender(user.getGender());
        saveUser.setBirthday(user.getBirthday());
        saveUser.setAddress(user.getAddress());
        saveUser.setBgImg(user.getBgImg());
        //更新数据
        userMapper.update(saveUser);
        //将修改后的用户信息返回
        return saveUser;
    }
}