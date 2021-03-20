package com.zhangyan.webflux.controller;

import com.zhangyan.webflux.domain.User;
import com.zhangyan.webflux.fegin.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {


    @Autowired
    private UserService userService;



    @GetMapping()
    public Mono<List<User>> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/{latency}")
    public Mono<List<User>> getAllUserWithTime(@PathVariable Integer latency) {
        return userService.getAllUserWithTime(latency);
    }


}
