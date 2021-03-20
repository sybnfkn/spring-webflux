package com.zhangyan.server.controller;

import com.zhangyan.server.api.Result;
import com.zhangyan.server.domain.User;
import com.zhangyan.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2021/02/23/9:16 下午
 * @Description:
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/getAllUser",
            method = RequestMethod.GET)
    public Result<List<User>> getAllUser() {
        System.out.println("getAllUser ....");
        return Result.success(userService.getAllUser());
    }
}
