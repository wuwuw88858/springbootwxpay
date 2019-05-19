package com.springboothc.demo;

import com.springboothc.demo.pojo.User;
import com.springboothc.demo.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.junit.Test;

/**
 * @program: springboothighercourse
 * @description: JWT测试类
 * @author: zhijie
 * @create: 2019-04-06 16:34
 **/
public class TestJWT {

    @Test
    public void testJWT() {
        User u = new User();
        u.setId(999);
        u.setHeadImg("www.abv.com");
        u.setName("xd");
        String token = JwtUtils.genJsonWebToken(u);
        System.out.println(token);

        System.out.println("============");
        Claims claims= JwtUtils.checkJWT(token);
        if(claims != null) {
            String name = (String) claims.get("name");
            String img = (String) claims.get("img");
            System.out.println(name + " : " + img);
        } else {
            System.out.println("不合法token");
        }
    }
}
