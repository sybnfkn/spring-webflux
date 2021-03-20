package com.zhangyan.server.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2021/02/23/9:16 下午
 * @Description:
 */
@RestController
public class UserService {

    @RequestMapping(value = "/sayHello/{name}",
            method = RequestMethod.GET)
    public String sayHello(@PathVariable("name") String name) {
        System.out.println("..............");
        try {
            Thread.sleep(5 * 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello " + name;
    }
}
