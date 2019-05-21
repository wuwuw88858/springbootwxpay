package com.springboothc.demo.service;

import com.springboothc.demo.mapper.VideoMapper;
import com.springboothc.demo.pojo.Video;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VideoServiceTest {

    @Autowired
    private VideoService videoService;
    @Autowired
    private VideoMapper videoMapper;

    @Test
    public void findAll() throws Exception {
        List<Video> list = videoService.findAll();
        assertNotNull(list);
        for(Video video : list) {
            System.out.println(video.getTitle());
        }
    }

    @Test
    public void findById() throws Exception {
        Video video = videoService.findById(1);
        assertNotNull(video);
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

}