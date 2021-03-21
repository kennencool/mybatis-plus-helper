package com.kennen.springbootplushelper.controller;

import com.kennen.springbootplushelper.pojo.param.GeneratorParam;
import com.kennen.springbootplushelper.pojo.resp.RespBean;
import com.kennen.springbootplushelper.util.CodeGenerator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: hejiyuan
 * @Date: 2021/3/21 21:53
 * @Description: 代码生成器接口
 */
@RestController
@RequestMapping("/generator")
public class GeneratorCodeController {
    @Resource private CodeGenerator codeGenerator;
    
    @PostMapping("/")
    public RespBean generator(@RequestBody GeneratorParam param){
        codeGenerator.generate(param);
        return RespBean.success("代码生成成功！");
    }
}
