package com.springboothc.demo.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @program: demo
 * @description: 常用工具类, md5, UUID
 * @author: zhijie
 * @create: 2019-05-21 18:51
 **/
public class CommonUtils {

    /*
      * @Description:  生成UUID,表示订单流水号
      * @Param: []
      * @return: java.lang.String
      * @Author:  zhijie
      * @Date: 2019-5-21
      */
    public static String generatedUUID() {
       return UUID.randomUUID().toString().replace("-", "").substring(0, 32);
    }

    public static String MD5(String data) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            try {
                byte[] arr = md5.digest(data.getBytes("UTF-8"));
                StringBuilder sb = new StringBuilder();
                for (byte item : arr) {
                    sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
                }
                return sb.toString().toUpperCase();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
