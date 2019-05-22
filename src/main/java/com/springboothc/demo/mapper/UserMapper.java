package com.springboothc.demo.mapper;

import com.springboothc.demo.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import javax.annotation.Generated;

/**
 * @program: springboothighercourse
 * @description:
 * @author: zhijie
 * @create: 2019-04-09 19:34
 **/
@Repository
public interface UserMapper {

    @Insert("INSERT INTO `user` ( `openid`, `name`, `head_img`, `phone`, `sign`, `sex`, `city`, `create_time`)" +
            "VALUES" +
            "(#{openid},#{name},#{headImg},#{phone},#{sign},#{sex},#{city},#{createTime});")
    @Options(useGeneratedKeys=true,keyProperty = "id", keyColumn = "id")
    int saveUser(User user);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") int userId);
    @Select("select * from user where openid = #{openid}")
    User findByOpenId(@Param("openid") String openid);

}
