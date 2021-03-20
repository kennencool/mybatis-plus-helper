package com.kennen.springbootplushelper.pojo;

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
    private String url;
    private String user;
    private String password;
}
