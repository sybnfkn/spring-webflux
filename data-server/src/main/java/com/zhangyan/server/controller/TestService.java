package com.zhangyan.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2021/03/16/9:48 下午
 * @Description:
 */
@RestController
public class TestService {

    @RequestMapping(value = "/get",
            method = RequestMethod.GET)
    public String sayHello() {
        System.out.println("hello ....");
        return "hello ....";
    }


}
