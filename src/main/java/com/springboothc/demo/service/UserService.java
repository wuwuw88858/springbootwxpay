package com.springboothc.demo.service;

import com.springboothc.demo.pojo.User;

/**
 * @program: springboothighercourse
 * @description:
 * @author: zhijie
 * @create: 2019-04-09 15:29
 **/
public interface UserService {

    User saveWeChatUser(String code);
}
