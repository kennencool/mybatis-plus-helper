package com.kennen.springbootplushelper.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.kennen.springbootplushelper.config.properties.DruidProperties;
import com.kennen.springbootplushelper.pojo.param.ConnectionParam;
import com.kennen.springbootplushelper.pojo.resp.RespBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: hejiyuan
 * @Date: 2021/3/20 22:46
 * @Description: 数据库接口
 */
@RestController
@RequestMapping("/database")
public class DatabaseController {
    @Resource private DruidProperties druidProperties;
    @Resource private RedisTemplate redisTemplate;

    @PostMapping("/")
    public RespBean getConnection(@RequestBody ConnectionParam connectionParam) throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        String url = "jdbc:mysql://"+connectionParam.getHost()+
                ":"+connectionParam.getPort()+
                "/"+ connectionParam.getDatabase() +
                "?useUnicode=true&useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC";
        druidProperties.config(druidDataSource,
                url,
                connectionParam.getUser(),
                connectionParam.getPassword());
        Connection connection = druidDataSource.getConnection();
        //  如果成功，将数据库连接数据存储到redis以供之后获取
        Map<String,String> map = new HashMap<>();
        map.put("url",url);
        map.put("user",connectionParam.getUser());
        map.put("password",connectionParam.getPassword());
        redisTemplate.opsForHash().putAll(connectionParam.getDatabase(),map);
        
        connection.close();
        return RespBean.success("数据库连接成功");
    }
}
