package com.springboothc.demo.mapper;

import com.springboothc.demo.pojo.Video;
import com.springboothc.demo.provider.VideoProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: springboothighercourse
 * @description:VedioMapper层
 * @author: zhijie
 * @create: 2019-04-02 13:37
 **/
@Repository
public interface VideoMapper {

    @Select("select * from video")
    List<Video> findList();

    @Select("select * from video where id = #{id}")
    Video findById(int id);

   // @Update("update video set title=#{title} where id=#{id}")
    @UpdateProvider(type = VideoProvider.class, method = "updateVideo")
    int update(Video video);

    @Delete("delete from video where id = #{id}")
    int delete(int id);
}
