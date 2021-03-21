package com.zhangyan.webmvc.controller;

import com.zhangyan.webmvc.api.Result;
import com.zhangyan.webmvc.domain.User;
import com.zhangyan.webmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2021/03/21/3:02 下午
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;



    @GetMapping()
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/{latency}")
    public Result<List<User>> getAllUserWithTime(@PathVariable Integer latency) {
        return userService.getAllUserWithTime(latency);
    }
}
