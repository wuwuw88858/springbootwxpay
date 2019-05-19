package com.springboothc.demo.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboothc.demo.pojo.Video;
import com.springboothc.demo.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: springboothighercourse
 * @description: 管理员操作的接口
 * @author: zhijie
 * @create: 2019-04-02 16:03
 **/
@RestController
@RequestMapping(value = "/api/vi/video")
public class VideoAdminController {

    @Autowired
    VideoService videoService;

    /*
                 * @Description:  分页接口
                 * @Param: [page, size]当前第几页，默认第一页，每页几条，默认10条
                 * @return: java.lang.Object
                 * @Author:  zhijie
                 * @Date: 2019/4/2
                 */
    @GetMapping(value = "page")
    public Object findAll(@RequestParam(value = "page", defaultValue = "1")int page,
                          @RequestParam(value = "size", defaultValue = "10") int size) {
        PageHelper.startPage(page, size);
        List<Video> videos =  videoService.findAll();
        PageInfo<Video> videoPageInfo = new PageInfo<>(videos);
        Map<String, Object> data = new HashMap<>();
        data.put("total",videoPageInfo.getTotal()); //总条数
        data.put("totalPage", videoPageInfo.getPages());    //总页数
        data.put("currentPage", page);  //当前页
        data.put("data", videoPageInfo.getList());  //当前对象
        return videoPageInfo;
    }
    /*
         * @Description:根据id查找视频
         * @Param: [id]
         * @return: java.lang.Object
         * @Author:  zhijie
         * @Date: 2019/4/2
         */
    @GetMapping(value = "find_by_id")
    public Object findById(@RequestParam(value = "video_id", required = true) int videoId) {
        return videoService.findById(videoId);
    }

    /*
  * @Description:根据id更新
  *     @RequestBody映射实体类
  * @Param: [id, title]
  * @return: java.lang.Object
  * @Author:  zhijie
  * @Date: 2019/4/2
  */
    @PutMapping(value = "update_id")
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
    @DeleteMapping(value = "del_by_id")
    public Object delById(@RequestParam(value = "video_id", required = true) int id) {
        return videoService.delete(id);
    }


}
