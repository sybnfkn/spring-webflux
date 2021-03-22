package com.zhangyan.webflux.config;

import com.netflix.client.DefaultLoadBalancerRetryHandler;
import com.netflix.client.RequestSpecificRetryHandler;
import com.netflix.client.RetryHandler;
import com.netflix.hystrix.*;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
import com.zhangyan.webflux.api.Result;
import com.zhangyan.webflux.api.UserFeginService;
import com.zhangyan.webflux.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactivefeign.cloud.CloudReactiveFeign;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static reactivefeign.ReactiveRetryers.retry;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2021/03/20/10:37 下午
 * @Description:
 */
@Configuration
public class ConfigBean {

    @Autowired
    private SpringClientFactory clientFactory;

    private static final String USER_SERVICE_NAME = "data-server";

    @Bean
    public UserFeginService userFeginService() {
        RetryHandler retryHandler = new RequestSpecificRetryHandler(true, true,
                new DefaultLoadBalancerRetryHandler(0, 2, true), null);

        CloudReactiveFeign.Builder<UserFeginService> builder = CloudReactiveFeign.<UserFeginService>builder()
//                .retryWhen(retry(3))
//                .enableLoadBalancer()
                .setLoadBalancerCommandFactory(name ->
                        LoadBalancerCommand.builder()
                                .withLoadBalancer(clientFactory.getLoadBalancer(USER_SERVICE_NAME))
//                                .withRetryHandler(retryHandler)
                                .build());
        // 使用hystrix需要打开下面注释
//                .setHystrixCommandSetterFactory(getSetterFactoryWithTimeoutDisabled());
//                .setFallback(new UserFeginService() {
//                    @Override
//                    public Mono<Result<List<User>>> getAllUser() {
//                        Result<List<User>> result = new Result<>();
//                        result.setCode(400);
//                        return Mono.just(result);
//                    }
//
//                    @Override
//                    public Mono<Result<List<User>>> getAllUserWithTime(Integer latency) {
//                        Result<List<User>> result = new Result<>();
//                        result.setCode(400);
//                        return Mono.just(result);
//                    }
//                });

        /**
         * 禁用hytrix，否则影响并发请求
         * 使用hystrix的话，系统fullgc频繁，请求耗时增加------待验证
         * 异步feign只提供HystrixObservableCommand支持，但是HystrixObservableCommand不支持设置线程池大小，只能使用固定的10个线程，大并发情况下触发线程池拒绝策略问题----待验证
         */
        builder.disableHystrix();
        return builder.target(UserFeginService.class, "http://" + USER_SERVICE_NAME);
    }

    private static CloudReactiveFeign.SetterFactory getSetterFactoryWithTimeout() {
        return (target, methodMetadata) -> {
            String groupKey = target.name();
            HystrixCommandKey commandKey = HystrixCommandKey.Factory.asKey(methodMetadata.configKey());
            return HystrixObservableCommand.Setter
                    .withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey))
                    .andCommandKey(commandKey)
                    .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                            // 测试默认给了100
                            .withExecutionTimeoutInMilliseconds(100)
                            // 关闭断路器
                            .withCircuitBreakerEnabled(false)
                            // 还必须要设置隔离级别是线程池，我以为默认就是线程池
                            .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
//                            .withExecutionTimeoutEnabled(false)
//                            .withExecutionTimeoutInMilliseconds()

                    );
        };
    }






}
