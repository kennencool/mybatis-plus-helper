package com.kennen.springbootplushelper.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.kennen.springbootplushelper.config.DataSourceConfig;
import org.apache.catalina.realm.DataSourceRealm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author: hejiyuan
 * @Date: 2021/3/19 18:14
 * @Description: 查找表
 */
@RestController
@RequestMapping("/table")
public class TableController {
    @Resource private DruidDataSource druidDataSource;
    
    @GetMapping("/")
    public int getTables(){
        int tablenumbers = 0;
        try (DruidPooledConnection connection = druidDataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from employee");
            while(resultSet.next()){
                tablenumbers = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tablenumbers;
    }
}
