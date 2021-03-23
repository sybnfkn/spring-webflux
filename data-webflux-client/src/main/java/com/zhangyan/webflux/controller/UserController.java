package com.zhangyan.webflux.controller;

import com.zhangyan.webflux.api.Result;
import com.zhangyan.webflux.domain.User;
import com.zhangyan.webflux.fegin.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
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
    public Mono<Result<List<User>>> getAllUserWithTime(@PathVariable Integer latency) {
        return userService.getAllUserWithTime(latency)
                // 110 ms超时
                .timeout(Duration.of(110, ChronoUnit.MILLIS),
                    Mono.just(new Result<>(300, "timeout", null)))
                .map(result -> {
                    /*if (result.getCode() == 400) {
                        System.out.println(result.toString());
                    } else */
                    if (result.getCode() == 300) {
                        System.out.println("超时:" + result.toString());
                    }
                    return result;
                });
    }
}
