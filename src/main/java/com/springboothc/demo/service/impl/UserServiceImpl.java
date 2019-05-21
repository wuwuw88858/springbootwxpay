package com.springboothc.demo.service.impl;

import com.springboothc.demo.config.WeChatConfig;

import com.springboothc.demo.mapper.UserMapper;
import com.springboothc.demo.pojo.User;
import com.springboothc.demo.service.UserService;
import com.springboothc.demo.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

/**
 * @program: springboothighercourse
 * @description:
 * @author: zhijie
 * @create: 2019-04-09 15:29
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    WeChatConfig weChatConfig;

    @Autowired
    UserMapper userMapper;
    /*
       * @Description: 换取access_token后，获取用户的信息
       * @Param: [code]
       * @return: com.springboothc.demo.pojo.User
       * @Author:  zhijie
       * @Date: 2019/4/9
       */
    @Override
    public User saveWeChatUser(String code) {
        String accessTokenUrl = String.format(weChatConfig.getOpenAccessTokenUrl(),
                                                weChatConfig.getOpenAppid(),
                                                weChatConfig.getOpenAppSecret(),
                                                 code);
        Map<String, Object> baseMap = HttpUtils.doGet(accessTokenUrl);  //发送get请求

        if(baseMap == null || baseMap.isEmpty()) {
            return null;
        }

//        //第四步：拉取用户信息
        String userInfoUrl = String.format(weChatConfig.getOpenUserInfoUrl(),
                                            baseMap.get("access_token"), baseMap.get("openid"));
        Map<String, Object> infoMap = HttpUtils.doGet(userInfoUrl);

        if(baseMap == null || baseMap.isEmpty()) {
            return null;
        }

        String openid = (String) infoMap.get("openid");
        User dbUser = userMapper.findByOpenId(openid);
        if(dbUser != null) {
            return dbUser;
        }
        String nickname = (String) infoMap.get("nickname");
        //重新编码,防止乱码

        String headImg = (String) infoMap.get("headimgurl");
        Double sexTemp = (Double) infoMap.get("sex");
        int sex = sexTemp.intValue();
        String country = (String) infoMap.get("country");
        String province = (String) infoMap.get("province");
        String city = (String) infoMap.get("city");
        StringBuilder sb = new StringBuilder(country).append(province).append(city);
        String adress = sb.toString();
        try {
            nickname = new String(nickname.getBytes("ISO-8859-1"), "UTF-8");
            adress = new String(adress.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        User user = new User();
        user.setName(nickname);
        user.setOpenid(openid);
        user.setHeadImg(headImg);
        user.setCity(adress);
        user.setSex(sex);
        user.setCreateTime(new Date());
        userMapper.saveUser(user);
       return user;
    }
}
