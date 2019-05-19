package com.springboothc.demo.service.impl;

import com.springboothc.demo.mapper.VideoMapper;
import com.springboothc.demo.pojo.Video;
import com.springboothc.demo.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: springboothighercourse
 * @description:
 * @author: zhijie
 * @create: 2019-04-01 22:02
 **/
@Service
public class VideoServiceImpl implements VideoService{

    @Autowired
    VideoMapper videoMapper;
    @Override
    public List<Video> findAll() {

        return videoMapper.findList();
    }

    @Override
    public Video findById(int id) {
        return videoMapper.findById(id);
    }

    @Override
    public int update(Video video) {
        return videoMapper.update(video);
    }

    @Override
    public int delete(int id) {
        return videoMapper.delete(id);
    }
}
