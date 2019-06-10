package com.springboothc.demo.provider;

import com.springboothc.demo.pojo.Video;
import org.apache.ibatis.jdbc.SQL;

/**
 * @program: springboothighercourse
 * @description: 构建动态的SQL语句
 * @author: zhijie
 * @create: 2019-04-02 16:11
 **/
public class  VideoProvider {


    /*
      * @Description:  动态更新语句
      * @Param: [video]
      * @return: java.lang.String
      * @Author:  zhijie
      * @Date: 2019/4/2
      */
    public String updateVideo(final Video video) {
      return new SQL() {
            {
                UPDATE("video");
                //条件写法
                if(video.getTitle() != null) {
                    SET("title = #{title}");
                }
                if(video.getSummary() != null) {
                    SET("summary = #{summary}");
                }
                if (video.getCoverImg() != null) {
                    SET("cover_img = #{coverImg}");
                }
                if(video.getViewNum() != null) {
                    SET("view_num = #{viewNum}");
                }
                if(video.getPrice() != null) {
                    SET("price = #{price}   ");
                }
                if(video.getCreateTime() != null) {
                    SET("create_time = #{createTime}");
                }
                if(video.getOnline() != null) {
                    SET("online = #{online}");
                }
                if(video.getPoint() != null) {
                    SET("point = #{point}");
                }
                if(video.getId() != null) {
                    WHERE("id = #{id}");
                }

            }
        }.toString();
    }
}
