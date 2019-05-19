package com.springboothc.demo.utils;

import com.springboothc.demo.pojo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @program: springboothighercourse
 * @description: JWT工具类
 * @author: zhijie
 * @create: 2019-04-05 23:06
 **/
public class JwtUtils {

    public static final String SUBJECT = "xdclass";
    public static Long expireTime = 1000 * 60 * 60 * 24L;    //设置过期（一天）
    public static final String APPSECRET = "xd666"; //密钥

    /*
      * @Description:  JWT 生成 token
      * @Param: [user]
      * @return: java.lang.String
      * @Author:  zhijie
      * @Date: 2019-5-19
      */
    public static String genJsonWebToken(User user) {
        if (user == null || user.getId() == null || user.getName() == null || user.getHeadImg() == null) {
            return null;
        }
        return Jwts.builder().setSubject(SUBJECT)   //JWT主题（头部：Header）
                .claim("id", user.getId())      //JWT信息(负载：Payload)
                .claim("name", user.getName())
                .claim("img", user.getHeadImg())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(SignatureAlgorithm.HS256, APPSECRET).compact();   //JWT签名以及密钥（签名：Signature）
    }

    /*
     * @Description:  使用密钥解析token
     * @Param: [token]
     * @return: io.jsonwebtoken.Claims
     * @Author:  zhijie
     * @Date: 2019-5-19
     */
    public static Claims checkJWT(String token) {

        try {
            return Jwts.parser().setSigningKey(APPSECRET)
                    .parseClaimsJws(token).getBody();   //payload 返回负载信息
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

