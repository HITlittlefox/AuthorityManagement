package com.mmall.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // 可以直接访问
    @RequestMapping("/")
    public String home() {
        return "hello spring boot";
    }

    //1. 需要权限认证
    @RequestMapping("/hello")
    public String hello() {
        return "You have been certified";
    }


    //2. normal无法访问 只有管理员能访问的 页面
    //需要注解@EnableGlobalMethodSecurity(prePostEnabled = true)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/roleAdminAuth")
    public String role() {
        return "You are an admin!";
    }


    @PreAuthorize("#id<10 and principal.username.equals(#username) and #user.username.equals('abc')")
    @PostAuthorize("returnObject%2==0")
    @RequestMapping("/test")
    public Integer test(Integer id, String username, User user) {
        // ...
        return id;
    }

    @PreFilter("filterObject%2==0")
    @PostFilter("filterObject%4==0")
    @RequestMapping("/test2")
    public List<Integer> test2(List<Integer> idList) {
        // ...
        return idList;
    }

}
