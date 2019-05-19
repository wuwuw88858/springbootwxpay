package com.springboothc.demo.controller;

import com.springboothc.demo.config.WeChatConfig;
import com.springboothc.demo.pojo.JsonData;
import com.springboothc.demo.pojo.User;
import com.springboothc.demo.service.UserService;
import com.springboothc.demo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @program: springboothighercourse
 * @description:
 * @author: zhijie
 * @create: 2019-04-07 23:06
 **/
@Controller
public class WeChatController {

    @Autowired
    WeChatConfig weChatConfig;

    @Autowired
    UserService userService;
    /*
        * @Description:
        * https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1419316505&token=&lang=zh_CN
        * 拼装微信扫一扫，请求code
        *           使用appid redirect_uri  response_type scope state 换取code
        * @Param: []
        * @return: com.springboothc.demo.pojo.JsonData
        * @Author:  zhijie
        * @Date: 2019/4/7
        */
    @GetMapping("getloginurl")
    @ResponseBody
    public void loginUrl(@RequestParam(value = "access_page", required = true) String accessPage, HttpServletResponse response) {
        String redirectUrl = weChatConfig.getOpenRedirectUrl(); //获取开放平台重定向地址
        try {
            String callbackUrl = URLEncoder.encode(redirectUrl, "GBK"); //进行编码
            String qrcodeUrl = String.format(weChatConfig.getOpenQrcodeUrl(), weChatConfig.getOpenAppid(), callbackUrl, accessPage);    //字符串拼接
            response.sendRedirect(qrcodeUrl);   //重定向到微信登录
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
            * @Description: 重定向地址的方法,用code获取access_token
            * @Param: [code, state, response]
            * @return: void
            * @Author:  zhijie
            * @Date: 2019/4/9
            */
    @GetMapping("/user/callback")
    public void weChatCallBack(@RequestParam(name = "code", required = true) String code,
                       String state, HttpServletResponse response) {
        User user = userService.saveWeChatUser(code);
        //使用jwt生成token,用于封装用户敏感信息
        String token = JwtUtils.genJsonWebToken(user);
        try {
            //重定向后的跳转地址
            response.sendRedirect(state + "?token=" + token + "&head_img=" + user.getHeadImg() + "&name=" + URLEncoder.encode(user.getName(), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
