package com.springboothc.demo.mapper;

import com.springboothc.demo.pojo.Video;
import com.springboothc.demo.pojo.VideoOrder;
import org.apache.ibatis.annotations.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: demo
 * @description: 订单接口
 * @author: zhijie
 * @create: 2019-05-21 15:29
 **/
@Repository
public interface VideoOrderMapper {

    /*
    * @Description:  保存订单
    * @Param: [order]
    * @return: int
    * @Author:  zhijie
    * @Date: 2019-5-21
    */
    @Insert("insert into `video_order` (`openid`, `out_trade_no`, `state`, `create_time`," +
            "`notify_time`, `total_fee`, `nickname`, `head_img`, `video_id`, `video_title`," +
            "`video_img`, `user_id`, `ip`, `del`) values (" +
            "#{openid}, #{outTradeNo}, #{state}, #{createTime}, #{notifyTime}, #{totalFee}, #{nickname}, #{headImg}," +
            "#{videoId}, #{videoTitle}, #{videoImg}, #{userId}, #{ip}, #{del}" +
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
     int insertOrder(VideoOrder videoOrder);


    /*
    * @Description:  根据id查找订单
    * @Param: [orderIdd]
    * @return: com.springboothc.demo.pojo.VideoOrder
    * @Author:  zhijie
    * @Date: 2019-5-21
    */
    @Select("select * from video_order where id = #{order_id} and del = 0")
    VideoOrder findById(@Param("order_id") int orderId);   //@Param 参数 在两个以上需要添加该注解进行识别


    /*
  * @Description:  根据订单编号查找订单
  * @Param: [outTradeNo]
  * @return: com.springboothc.demo.pojo.VideoOrder
  * @Author:  zhijie
  * @Date: 2019-5-21
  */
    @Select("select * from video_order where out_trade_no = #{out_trade_no} and del = 0")
    VideoOrder findByOutTradeNo(@Param("out_trade_no") String outTradeNo);


    /*
 * @Description:  逻辑删除订单,并不真实删除
 * @Param: [id, userId]
 * @return: int
 * @Author:  zhijie
 * @Date: 2019-5-21
 */
    @Update("update video_order set del = 0 where id = #{id} and use_id = #{user_id}")
    int del(@Param("id") int id, @Param("user_id") int userId);


    /*
  * @Description:  查找用户的个人订单
  * @Param: [userId]
  * @return: java.util.List<com.springboothc.demo.pojo.VideoOrder>
  * @Author:  zhijie
  * @Date: 2019-5-21
  */
    @Select("select * from video_order where user_id = #{user_id}")
    List<VideoOrder> findOrderList(@Param("user_id") String userId);

    /*
       * @Description: 根据订单流水号更新
       * @Param: [videoOrder]
       * @return: int
       * @Author:  zhijie
       * @Date: 2019-5-21
       */
    @Update("update video_order set state=#{state}, notify_name=#{notifyName}, openid=#{openid} " +
            "where out_trade_no = #{outTradeNo} and state = 0 and del = 0")
   int updateVideoOrderByOutTradeNo(VideoOrder videoOrder);
}
