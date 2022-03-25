package com.ycshang.article.mapper;

import com.ycshang.article.model.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    /**
     * 新增用户，并返回自增主键
     *
     * @param user 入参user对象
     */
    @Insert("INSERT INTO t_user (phone,wx_openid,password,nickname,avatar,gender,birthday,address,create_time,bg_img) " +
            "VALUES (#{phone},#{wxOpenId}, #{password}, #{nickname},#{avatar}, #{gender}, #{birthday},#{address},#{createTime},#{bgImg}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    /**
     * 根据手机号查询用户信息
     *
     * @param phone
     * @return
     */
    @Select("SELECT * FROM t_user WHERE phone =#{phone}")
    User findUserByPhone(@Param("phone") String phone);

    /**
     * 根据威胁你openId查询
     * @param wxOpenId
     * @return
     */
    @Select("SELECT * FROM t_user WHERE wx_openid=#{wxOpenId}")
    User findUserByOpenId(@Param("wxOpenId") String wxOpenId);
}
