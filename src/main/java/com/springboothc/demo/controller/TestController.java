package com.springboothc.demo.controller;

import com.github.pagehelper.PageHelper;
import com.springboothc.demo.config.WeChatConfig;
import com.springboothc.demo.mapper.VideoMapper;
import com.springboothc.demo.pojo.JsonData;
import com.springboothc.demo.pojo.Video;
import com.springboothc.demo.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: springboothighercourse
 * @description:
 * @author: zhijie
 * @create: 2019-04-01 20:34
 **/
@RestController
public class TestController {

    @GetMapping(value = "test")
    public String test() {
        return "helloss";
    }

    @Autowired
    WeChatConfig weChatConfig;
    @GetMapping(value = "testpro")
    public Object testpro() {
        return JsonData.buildSuccess(weChatConfig.getAppid());
    }

    @Autowired
    VideoService videoService;

    /*
                 * @Description:  分页接口
                 * @Param: [page, size]当前第几页，默认第一页，每页几条，默认10条
                 * @return: java.lang.Object
                 * @Author:  zhijie
                 * @Date: 2019/4/2
                 */
    @GetMapping(value = "findvideo")
    public Object findAll(@RequestParam(value = "page", defaultValue = "1")int page,
                    @RequestParam(value = "size", defaultValue = "10") int size) {

        PageHelper.startPage(page, size);
         return videoService.findAll();
    }
    /*
         * @Description:根据id查找
         * @Param: [id]
         * @return: java.lang.Object
         * @Author:  zhijie
         * @Date: 2019/4/2
         */
    @GetMapping(value = "getvideo")
    public Object testfindById(@RequestParam(value = "video_id", required = true) int id) {
     return videoService.findById(id);
    }

    /*
  * @Description:根据id更新
  * @Param: [id, title]
  * @return: java.lang.Object
  * @Author:  zhijie
  * @Date: 2019/4/2
  */
    @PutMapping(value = "updatevideo")
    public Object updatevideo(@RequestBody Video video) {
        return videoService.update(video);
    }

    /*
   * @Description: 根据id删除
   * @Param: [id]
   * @return: java.lang.Object
   * @Author:  zhijie
   * @Date: 2019/4/2
   */
    @DeleteMapping(value = "delvideo")
    public Object delById(int id) {
     return videoService.delete(id);
    }
}
