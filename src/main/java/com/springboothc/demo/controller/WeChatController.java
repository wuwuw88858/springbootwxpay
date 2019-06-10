package com.springboothc.demo.controller;

import com.springboothc.demo.config.WeChatConfig;
import com.springboothc.demo.pojo.JsonData;
import com.springboothc.demo.pojo.User;
import com.springboothc.demo.service.UserService;
import com.springboothc.demo.utils.JwtUtils;
import com.springboothc.demo.utils.wxPay.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;

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
        * https://open.weixin.qq.com/cgi-bin/showdocument?
        * action=dir_list&t=resource/res_list&verify=1&id=open1419316505&token=&lang=zh_CN
        * 拼装微信扫一扫，请求code
        *           使用appid redirect_uri  response_type scope state 换取code
        * @Param: []
        * @return: com.springboothc.demo.pojo.JsonData
        * @Author:  zhijie
        * @Date: 2019/4/7
        */

    @GetMapping(value = "loginurl")
    @ResponseBody
    public HttpServletResponse loginUrl(@RequestParam(value = "access_page", required = true) String accessPage, HttpServletResponse response) {
        String redirectUrl = weChatConfig.getOpenRedirectUrl(); //获取平台重定向地址
        try {
            String callbackUrl = URLEncoder.encode(redirectUrl, "GBK"); //编码
            String qrcodeUrl = String.format(weChatConfig.getOpenQrcodeUrl(),
                    weChatConfig.getOpenAppid(),    //appid
                    callbackUrl,    //重定向地址
                    accessPage);    //状态
            try {
                response.sendRedirect(qrcodeUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    /*
            * @Description: 重定向地址的方法,用code获取access_token
            * @Param: [code, state, response]
            * @return: void
            * @Author:  zhijie
            * @Date: 2019/4/9
            */
    @GetMapping("/user/callback")
    public void weChatUserCallBack(@RequestParam(name = "code", required = true) String code, String state, HttpServletResponse response) {


        User user = userService.saveWeChatUser(code);
        if(user != null) {
            String token = JwtUtils.genJsonWebToken(user);  //生成JWT
            try {
                //当前用户页面地址需要拼上http://
                response.sendRedirect("http://" + state + "?token=" + token + "&head_img=" + user.getHeadImg() + "&name=" + URLEncoder.encode(user.getName(), "UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
        * @Description:  微信支付回调地址 读取微信返回的流数据，包装成字符流的形式，在拼接起来 转换成map
        * @Param: [request, response]
        * @return: void
        * @Author:  zhijie
        * @Date: 2019-5-24
        */
    @PostMapping(value = "/wxpay/callback")
    public void wxPayCallBack(HttpServletRequest request, HttpServletResponse response) {
        InputStream inputStream = null;
            BufferedReader bufferedReader = null;
            StringBuffer stringBuffer = new StringBuffer();
        try {
            inputStream = request.getInputStream(); //定义字节输入流
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));//提供一个读取的字符缓冲流：字节流 --> 字符流 -->字符缓冲流
            String line;
            while((line = bufferedReader.readLine()) != null) { //readLine 读取文本中的字符流数据
                stringBuffer.append(line);
            }
            Map<String, String> callbackMap = WXPayUtil.xmlToMap(stringBuffer.toString());
            System.out.println(callbackMap.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
