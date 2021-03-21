package com.kennen.springbootplushelper.pojo.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: hejiyuan
 * @Date: 2021/3/21 21:40
 * @Description: 代码生成器实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneratorParam {
    // 项目路径
    private String projectPath;
    
    private String database;
    // 包路径
    private String packagePath;
    // 数据库名
    private String tables;
}
