package com.springboothc.demo.service;

import com.springboothc.demo.pojo.Video;

import java.util.List;

/**
 * @program: springboothighercourse
 * @description:视频业务类接口
 * @author: zhijie
 * @create: 2019-04-01 22:02
 **/
public interface VideoService {

    List<Video> findAll();

    Video findById(int id);

    int update(Video video);

    int delete(int id);
}
