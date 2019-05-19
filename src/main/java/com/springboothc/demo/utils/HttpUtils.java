package com.springboothc.demo.utils;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: springboothighercourse
 * @description: 封装Http方法
 * @author: zhijie
 * @create: 2019-04-08 23:11
 **/
public class HttpUtils {


    private static final Gson g = new Gson();
  /*
    * @Description:  doGET方法
    * @Param: [url]
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author:  zhijie
    * @Date: 2019/4/8
    */
    public static Map<String, Object> doGet(String url) {
      CloseableHttpClient httpClient = HttpClients.createDefault();
        Map<String, Object> map = new HashMap<>();

        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(5000)  //连接超时
                        .setSocketTimeout(5000)
                        .setConnectionRequestTimeout(5000)  //请求超时
                        .setRedirectsEnabled(true)  //允许重定向
                        .build();
        HttpGet get = new HttpGet(url);
        get.setConfig(requestConfig);

        try{
            HttpResponse response = httpClient.execute(get);
            if(response.getStatusLine().getStatusCode() == 200) {
               String responseStr =  EntityUtils.toString(response.getEntity());
                map = g.fromJson(responseStr, map.getClass());
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /*
 * @Description:
 * @Param: [url, data, timeout]
 * @return: java.lang.String
 * @Author:  zhijie
 * @Date: 2019/4/9
 */
    public static String doPost(String url, String data, int timeout) {
    CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeout)
                            .setConnectionRequestTimeout(timeout)
                            .setSocketTimeout(timeout)
                            .setRedirectsEnabled(true)
                            .build();
        httpPost.setConfig(requestConfig);
        httpPost.setHeader("Content-Type", "text/html; chartset=UTF-8");
        if(data != null && data instanceof String) {    //字符串传参
            StringEntity entity = new StringEntity(data, "UTF-8");
            httpPost.setEntity(entity);
        }
        try {
            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if(response.getStatusLine().getStatusCode() == 200) {
                String responseStr = EntityUtils.toString(entity);
                return responseStr;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  null;
    }
}
