package com.kennen.springbootplushelper.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.kennen.springbootplushelper.config.properties.DruidProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

//@Configuration
//public class DataSourceConfig {
//    @Resource
//    private DruidProperties druidProperties;
//
//    /**
//     * 单数据源连接池配置
//     */
//    @Bean
//    public DruidDataSource singleDatasource() {
//        DruidDataSource dataSource = new DruidDataSource();
//        druidProperties.config(dataSource);
//        return dataSource;
//    }
//}
