package com.zhangyan.webflux.api;

import com.zhangyan.webflux.domain.User;
import feign.RequestLine;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2021/03/20/10:35 下午
 * @Description:
 */
public interface UserFeginService {

    @RequestLine("GET /getAllUser")
    Mono<List<User>> getAllUser();



}
