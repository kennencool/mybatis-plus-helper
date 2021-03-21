package com.kennen.springbootplushelper.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.kennen.springbootplushelper.config.properties.DruidProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: hejiyuan
 * @Date: 2021/3/19 18:14
 * @Description: 查找表
 */
@RestController
@RequestMapping("/table")
public class TableController {
    @Resource private DruidProperties druidProperties;
    @Resource private RedisTemplate redisTemplate;
    
    @GetMapping("/{database}")
    public List<String> getTables(@PathVariable("database") String database){
        System.out.println(database);
        DruidDataSource druidDataSource = new DruidDataSource();
        // 从redis获取连接参数
        druidProperties.config(druidDataSource,
                (String)redisTemplate.opsForHash().get(database,"url"),
                (String)redisTemplate.opsForHash().get(database,"user"),
                (String)redisTemplate.opsForHash().get(database,"password"));
        List<String> tables = new ArrayList<>();
        try{
            DruidPooledConnection connection = druidDataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Show tables");
            while(resultSet.next()){
                tables.add(resultSet.getString(1));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tables;
    }
}
