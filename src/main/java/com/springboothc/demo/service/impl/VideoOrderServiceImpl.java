package com.springboothc.demo.service.impl;

import com.springboothc.demo.config.WeChatConfig;
import com.springboothc.demo.dto.VideoOrderDto;
import com.springboothc.demo.mapper.UserMapper;
import com.springboothc.demo.mapper.VideoMapper;
import com.springboothc.demo.mapper.VideoOrderMapper;
import com.springboothc.demo.pojo.User;
import com.springboothc.demo.pojo.Video;
import com.springboothc.demo.pojo.VideoOrder;
import com.springboothc.demo.service.VideoOrderService;
import com.springboothc.demo.utils.CommonUtils;
import com.springboothc.demo.utils.HttpUtils;
import com.springboothc.demo.utils.wxPay.WXPay;
import com.springboothc.demo.utils.wxPay.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @program: demo
 * @description:
 * @author: zhijie
 * @create: 2019-05-22 15:00
 **/
@Service
public class VideoOrderServiceImpl implements VideoOrderService {

    @Autowired
    WeChatConfig weChatConfig;

    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private VideoOrderMapper videoOrderMapper;

    @Autowired
    private UserMapper userMapper;

    /*
    * @Description:  获取订单信息，生成支付二维码
    * @Param: [videoOrderDto]
    * @return: java.lang.String
    * @Author:  zhijie
    * @Date: 2019-5-23
    */
    @Override
    public String save(VideoOrderDto videoOrderDto) {
       Video video = videoMapper.findById(videoOrderDto.getVideoId());  //查找视频信息
        User user = userMapper.findById(videoOrderDto.getUserId()); //查找用户信息

        //1、生成订单
        VideoOrder videoOrder = new VideoOrder();
        videoOrder.setTotalFee(video.getPrice());   //价格
        videoOrder.setVideoImg(video.getCoverImg());
        videoOrder.setVideoId(video.getId());
        videoOrder.setVideoTitle(video.getTitle());
        videoOrder.setCreateTime(new Date());
        videoOrder.setState(0); //未支付状态

        videoOrder.setUserId(user.getId());
        videoOrder.setHeadImg(user.getHeadImg());
        videoOrder.setNickname(user.getName());

        videoOrder.setIp(videoOrderDto.getIp());
        videoOrder.setOutTradeNo(CommonUtils.generatedUUID());  //流水号
        videoOrder.setDel(0);
        videoOrderMapper.insertOrder(videoOrder);

        //2、生成签名
        unifiedOrder(videoOrder);

        //3、获取code_rudl
        String codeUrl = unifiedOrder(videoOrder);

        return codeUrl;
    }
    /*
         * @Description:  https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1
         * 生成签名 -->统一下单
         * @Param: [videoOrder]
         * @return: java.lang.String 返回一个二维码Urdl
         * @Author:  zhijie
         * @Date: 2019-5-22
         */
    private String unifiedOrder(VideoOrder videoOrder) {
      //生成签名
        SortedMap<String, String> params = new TreeMap<>();
        params.put("appid", weChatConfig.getAppid());   //公众号Id
        params.put("mch_id", weChatConfig.getPayMchId());   //商户号
        params.put("nonce_str", CommonUtils.generatedUUID());   //随机字符串

        params.put("body", videoOrder.getVideoTitle()); //标题
        params.put("out_trade_no", videoOrder.getOutTradeNo()); //流水账户订单号
        params.put("total_fee", videoOrder.getTotalFee().toString());   //总金额
        params.put("spbill_create_ip", videoOrder.getIp()); //终端ip
        params.put("notify_url", weChatConfig.getPayCallBackUrl());
        params.put("trade_type", "NATIVE");

        String sign = null;
        try {
            sign = WXPayUtil.generateSignature(params, weChatConfig.getPayKey());
        } catch (Exception e) {
            e.printStackTrace();
        }
        params.put("sign", sign);   //签名

        //mapt -->XML
       String payXml = WXPayUtil.mapToXml(params);
        //统一下单：下单地址和数据进行post请求 生成xml数据格式，将xml数据格式转化成map形式
        String orderStr = HttpUtils.doPost(weChatConfig.getUnifledOrderUrl(), payXml);

        if (orderStr == null) { return null;}

        try {
            Map<String, String> unifledOrderMap = WXPayUtil.xmlToMap(orderStr);
            if (unifledOrderMap != null) {
                return unifledOrderMap.get("code_url");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
