package com.springboothc.demo.interceoter;

import com.alibaba.druid.util.StringUtils;
import com.google.gson.Gson;
import com.springboothc.demo.pojo.JsonData;
import com.springboothc.demo.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.HandlerInterceptor;

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
public class LoginInterceptor implements HandlerInterceptor {

    private static final Gson gson = new Gson();
    /**
     * 调用控制器的方法前拦截
     * */
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       String token = request.getHeader("token");
       if(token == null) {
           token = request.getParameter("token");
       }
       if(token != null) {
           Claims claims = JwtUtils.checkJWT(token);
           if(claims != null) {
               Integer userId = (Integer) claims.get("id");
               String name = (String) claims.get("name");
               String img = (String) claims.get("img");
               request.setAttribute("user_id", userId);
               request.setAttribute("name", name);
               request.setAttribute("img", img);
               return true;
           }
       }
       sendJsonMessage(response, JsonData.buildError("请登录"));
       return false;
    }

    /*
    * 响应数据给前端
    *
    * */
    public static void sendJsonMessage(HttpServletResponse response, Object obj) {
        response.setContentType("application/json; charset=utf-8");
        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.println(gson.toJson(obj));
            printWriter.close();
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

}
