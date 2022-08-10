package com.mmall.controller;

import com.mmall.common.ApplicationContextHelper;
import com.mmall.common.JsonData;
import com.mmall.dao.SysAclModuleMapper;
import com.mmall.exception.ParamException;
import com.mmall.exception.PermissionException;
import com.mmall.model.SysAclModule;
import com.mmall.param.TestVo;
import com.mmall.util.BeanValidator;
import com.mmall.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

    // 总结：pom.xml引入依赖-
    // web.xml配置spring上下文使用applicationContext.xml+配置servlet命名为spring，指定配置spring-servlet.xml
    // 配置spring-servlet.xml，扫描+http接口配置
    // 配置applicationContext.xml，DB，mybatis
    // 配置mybatis-config.xml
    // 核心配置结束
    // 日志配置logback
    @RequestMapping("/hello")
    @ResponseBody
    public String helloTest() {
        log.info("hello");

        Queue<Integer> temp = new LinkedList<>();

        //throw new PermissionException("test exception");
        return "hello, permission";
    }

    @RequestMapping("/hello.json")
    @ResponseBody
    public JsonData hello() {
        log.info("hello");
        throw new PermissionException("test exception");
        // return JsonData.success("hello, permission");
    }

    @RequestMapping("/validate.json")
    @ResponseBody
    public JsonData validate(TestVo vo) throws ParamException {
        log.info("validate");
        SysAclModuleMapper moduleMapper = ApplicationContextHelper.popBean(SysAclModuleMapper.class);
        SysAclModule module = moduleMapper.selectByPrimaryKey(1);
        log.info(JsonMapper.obj2String(module));
        BeanValidator.check(vo);
        return JsonData.success("test validate");
    }
}
