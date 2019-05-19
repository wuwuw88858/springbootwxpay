package com.springboothc.demo.controller;

import com.springboothc.demo.pojo.JsonData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springboothighercourse
 * @description:
 * @author: zhijie
 * @create: 2019-04-10 14:15
 **/
@RestController
@RequestMapping(value = "/user/api/v1")
public class OrderController {

    @GetMapping(value = "add")
    public JsonData saveOrder() {
        return JsonData.buildSuccess(2, "下单成功");
    }
}
