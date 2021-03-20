package com.kennen.springbootplushelper.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.kennen.springbootplushelper.config.properties.DruidProperties;
import org.springframework.web.bind.annotation.GetMapping;
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
    
    @GetMapping("/")
    public List<String> getTables(){
        DruidDataSource druidDataSource = new DruidDataSource();
        // 从redis获取连接参数
        druidProperties.config(druidDataSource,"jdbc:mysql://localhost:3306/"+ "vhr" +"?useUnicode=true&useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC",
                "root","nbuser");
        List<String> tables = new ArrayList<>();
        try (DruidPooledConnection connection = druidDataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Show tables");
            while(resultSet.next()){
                tables.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tables;
    }
}
