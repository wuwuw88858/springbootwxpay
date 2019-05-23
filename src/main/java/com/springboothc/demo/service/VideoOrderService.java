package com.springboothc.demo.service;

import com.springboothc.demo.dto.VideoOrderDto;
import com.springboothc.demo.pojo.VideoOrder;

/**
 * @program: demo
 * @description: 订单接口
 * @author: zhijie
 * @create: 2019-05-22 14:56
 **/
public interface VideoOrderService {

    /*
    * @Description:  下单保存用户信息接口
    * @Param:
    * @return:
    * @Author:  zhijie
    * @Date: 2019-5-22
    */

    String save(VideoOrderDto videoOrderDto);
}
