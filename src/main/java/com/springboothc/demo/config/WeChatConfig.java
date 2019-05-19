package com.springboothc.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @program: springboothighercourse
 * @description:
 * @author: zhijie
 * @create: 2019-04-01 22:49
 **/
@Configuration
@PropertySource(value = "classpath:application.properties")
public class WeChatConfig {

    @Value("${wxpay.appid}")
    private String appid;

    @Value("${wxpay.appsercet}")
    private String appsecret;


    /**
     * 开放平台：appid, appsecret, redirecturl
     */
    @Value("${wxopen.appid}")
    private String openAppid;

    @Value("${wxopen.appsecret}")
    private String openAppSecret;

    @Value("${wxopen.redirecturl}")
    private String openRedirectUrl;

    /**
     * 微信开放平台获取code的链接
     */
    private final static String OPEN_QRCODE_URL =
            "https://open.weixin.qq.com/connect/qrconnect?appid=%s" +
                    "&redirect_uri=%s" +
                    "&response_type=code" +
                    "&scope=snsapi_login" +
                    "&state=%s#wechat_redirect";

    public static String getOpenAccessTokenUrl() {
        return OPEN_ACCESS_TOKEN_URL;
    }

    /*

        * 微信开放平台获取access_toeken地址
        *
        * */
    private final static String OPEN_ACCESS_TOKEN_URL =
            "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s" +
                    "&secret=%s" +
                    "&code=%s" +
                    "&grant_type=authorization_code";

    /*
    *   微信开发平台，获取个人信息
    * */

    private static final String OPEN_USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";

    public static String getOpenUserInfoUrl() {
        return OPEN_USER_INFO_URL;
    }

    public String getOpenRedirectUrl() {
        return openRedirectUrl;
    }

    public void setOpenRedirectUrl(String openRedirectUrl) {
        this.openRedirectUrl = openRedirectUrl;
    }

    public static String getOpenQrcodeUrl() {
        return OPEN_QRCODE_URL;
    }

    public String getAppid() {
        return appid;
    }

    public String getOpenAppid() {
        return openAppid;
    }

    public void setOpenAppid(String openAppid) {
        this.openAppid = openAppid;
    }

    public String getOpenAppSecret() {
        return openAppSecret;
    }

    public void setOpenAppSecret(String openAppSecret) {
        this.openAppSecret = openAppSecret;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }
}
