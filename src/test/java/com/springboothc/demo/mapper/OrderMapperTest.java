package com.springboothc.demo.mapper;

import com.springboothc.demo.pojo.VideoOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;
    @Test
    public void insertOrder() throws Exception {
        VideoOrder videoOrder = new VideoOrder();
        videoOrder.setDel(0);
        videoOrder.setTotalFee(111);
        videoOrder.setCreateTime(new Date());
        videoOrder.setHeadImg("xxx");
        videoOrder.setVideoTitle("springBoot高级支付教程");
        orderMapper.insertOrder(videoOrder);
        assertNotNull(videoOrder.getId());
        System.out.println(videoOrder.getId());
    }

    @Test
    public void findById() throws Exception {

        VideoOrder videoOrder = orderMapper.findById(1);
        System.out.println(videoOrder.getVideoTitle());
    }

    @Test
    public void findByOutTradeNo() throws Exception {
    }

    @Test
    public void del() throws Exception {
    }

    @Test
    public void findOrderList() throws Exception {
    }

    @Test
    public void updateVideoOrderByOutTradeNo() throws Exception {
    }

}