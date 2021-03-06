package com.kennen.springbootplushelper.pojo.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: hejiyuan
 * @Date: 2021/3/20 23:40
 * @Description: 数据库连接实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConnectionParam {
    private String host;
    private String port;
    private String user;
    private String password;
    private String database;
}
