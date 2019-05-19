package com.springboothc.demo.pojo;

import java.io.Serializable;

/**
 * @program: springboothighercourse
 * @description:
 * @author: zhijie
 * @create: 2019-04-07 22:44
 **/
public class JsonData implements Serializable{

    private static final long serializableID = 1L;

    private Integer code;   //状态码
    private String msg;     //回调信息
    private Object data;    //回调数据

    public JsonData(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public JsonData() {

    }

    public Integer getCode() {

        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    //成功，传入数据
    public static JsonData buildSuccess() {
        return new JsonData(0, null, null);
    }
    //成功，显示数据
    public static JsonData buildSuccess(Object data) {
        return new JsonData(0, null, data);
    }
    //成功， 传入数据， 传入描述信息
    public static JsonData buildSuccess(Object data, String msg) {
        return new JsonData(0, msg, data);
    }
    //成功， 传入数据， 传入状态码
    public static JsonData buildSuccess(Object data, Integer code) {
        return new JsonData(code, null, data);
    }
    //失败， 传入信息
    public static JsonData buildError(String msg) {
        return new JsonData(-1, msg, null);
    }

    //失败，传入信息，传入状态码
    public static JsonData buildError(String msg, Integer code) {
        return new JsonData(code, msg, null);
    }


}
