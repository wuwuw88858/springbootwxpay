package com.springboothc.demo.controller;

import com.springboothc.demo.pojo.JsonData;
import com.springboothc.demo.utils.GetIpUtils;
import com.springboothc.demo.utils.JwtUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: springboothighercourse
 * @description:
 * @author: zhijie
 * @create: 2019-04-10 14:15
 **/
@RestController
//@RequestMapping(value = "/user/api/v1")
@RequestMapping(value = "/api/v1")
public class OrderController {

    @GetMapping(value = "add")
    public JsonData saveOrder(@RequestParam(value = "video_id", required = true)int videoId,
                              HttpServletRequest request) {
            String ip = GetIpUtils.getIppAddr(request); //获取用户IP
//            int userId = request.getAttribute("user_id");
        int userId = 1;

        return JsonData.buildSuccess(JwtUtils.checkJWT(token).toString(), "下单成功");
    }
}
