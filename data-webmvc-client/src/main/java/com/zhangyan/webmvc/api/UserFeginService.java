package com.zhangyan.webmvc.api;

import com.zhangyan.webmvc.domain.User;
import feign.Param;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2021/03/20/10:35 下午
 * @Description:
 */
@FeignClient(name = "data-server")
public interface UserFeginService {

    @GetMapping(value = "/getAllUser")
    Result<List<User>> getAllUser();


    @GetMapping(value = "/getAllUser/time")
    Result<List<User>> getAllUserWithTime(@RequestParam("latency") Integer latency);



}
