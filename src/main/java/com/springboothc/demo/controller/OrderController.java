package com.springboothc.demo.controller;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.springboothc.demo.dto.VideoOrderDto;
import com.springboothc.demo.pojo.JsonData;
import com.springboothc.demo.service.VideoOrderService;
import com.springboothc.demo.utils.GetIpUtils;
import com.springboothc.demo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: springboothighercourse
 * @description:
 * @author: zhijie
 * @create: 2019-04-10 14:15
 **/
@RestController
//@RequestMapping(value = "/user/api/v1")
@RequestMapping(value = "/api/v1")
public class OrderController {

    @Autowired
    VideoOrderService videoOrderService;

    @GetMapping(value = "add")
    public void saveOrder(@RequestParam(value = "video_id", required = true) int videoId,
                              HttpServletRequest request, HttpServletResponse response) {
//        String ip = GetIpUtils.getIppAddr(request); //获取用户IP
//            int userId = request.getAttribute("user_id");
        int userId = 7;

        VideoOrderDto videoOrderDto = new VideoOrderDto();
        videoOrderDto.setUserId(userId);
        videoOrderDto.setVideoId(videoId);
        videoOrderDto.setIp("172.16.111.45");
        //生成二维码
        String codeUrl = videoOrderService.save(videoOrderDto);

        if (codeUrl == null) {
            try {
                throw new Exception("nodeUrl 为空");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Map<EncodeHintType, Object> hints = new HashMap<>();

        //设置纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(codeUrl,
                    BarcodeFormat.QR_CODE,
                    400,
                    400, hints);
            OutputStream outputStream = response.getOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "png", outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
