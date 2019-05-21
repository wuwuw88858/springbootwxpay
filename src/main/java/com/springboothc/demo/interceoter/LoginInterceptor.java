package com.springboothc.demo.interceoter;


import com.google.gson.Gson;
import com.springboothc.demo.pojo.JsonData;
import com.springboothc.demo.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: springboothighercourse
 * @description:
 * @author: zhijie
 * @create: 2019-04-10 13:34
 **/
public class
LoginInterceptor implements HandlerInterceptor {

    private static final Gson gson = new Gson();
    /*
        * @Description:  进入controller进行拦截
        * @Param: [request, response, handler]
        * @return: boolean
        * @Author:  zhijie
        * @Date: 2019-5-20
    */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (token == null) {
            token = request.getParameter("token");
        }
        if (token != null) {
            Claims claims = JwtUtils.checkJWT(token);   //获取Claims
            if (claims != null) {
                Integer userId = (Integer) claims.get("id");
                String name = (String) claims.get("name");
                String headImg = (String) claims.get("img");
                request.setAttribute("user_id", userId);
                request.setAttribute("user_name", name);
                request.setAttribute("head_img", headImg);
                return true;
            }
        }
        sendJsonMessage(response, JsonData.buildError("请登录"));
        return  false;
    }


    public static void sendJsonMessage(HttpServletResponse response, Object object) {
        response.setContentType("application/json; charset=UTF-8");
        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.println(gson.toJson(object));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.flushBuffer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }

}
