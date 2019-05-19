package com.springboothc.demo.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @program: springboothighercourse
 * @description:mybatis分页插件配置
 * @author: zhijie
 * @create: 2019-04-02 16:39
 **/
@Configuration
public class MybatisPageHelperConfig {

    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();

        //设置会ture,会将rowBounds第一个参数offset当成pageNum页码使用
        properties.setProperty("offsetAsPageNum", "true");
        //将rowBounds分页进行count查询
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
