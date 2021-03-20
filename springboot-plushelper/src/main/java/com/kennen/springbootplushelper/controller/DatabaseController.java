package com.kennen.springbootplushelper.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.kennen.springbootplushelper.config.properties.DruidProperties;
import com.kennen.springbootplushelper.pojo.ConnectionParam;
import com.kennen.springbootplushelper.pojo.resp.RespBean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * @Author: hejiyuan
 * @Date: 2021/3/20 22:46
 * @Description: 数据库接口
 */
@RestController
@RequestMapping("/database")
public class DatabaseController {
    @Resource
    private DruidProperties druidProperties;

    @PostMapping("/")
    public void getConnection(@RequestBody ConnectionParam connectionParam) throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidProperties.config(druidDataSource,
                connectionParam.getUrl(),
                connectionParam.getUser(),
                connectionParam.getPassword());
        druidDataSource.getConnection();
        //  如果成功，将数据库连接数据存储到redis以供之后获取
        
    }
}
