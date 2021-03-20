package com.zhangyan.webflux.fegin;

import com.netflix.client.DefaultLoadBalancerRetryHandler;
import com.netflix.client.RequestSpecificRetryHandler;
import com.netflix.client.RetryHandler;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixObservableCommand;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
import feign.RequestLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import reactivefeign.cloud.CloudReactiveFeign;
import reactor.core.publisher.Mono;

import static reactivefeign.ReactiveRetryers.retry;

//import static reactivefeign.ReactiveRetryers.retry;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2021/03/16/9:40 下午
 * @Description:
 */
@Service
public class TestService {

    @Autowired
    private SpringClientFactory clientFactory;

    public Mono<String> findAllCity() {
        RetryHandler retryHandler = new RequestSpecificRetryHandler(true, true,
                new DefaultLoadBalancerRetryHandler(0, 2, true), null);

        String serviceName = "EUREKA-SERVER";


        /**
         * serviceName ->
         *                     LoadBalancerCommand.builder()
         *                     .withLoadBalancer(ClientFactory.getNamedLoadBalancer(serviceName))
         *                     .withRetryHandler(retryHandler)
         *                     .build()
         */


        TestInterface client = CloudReactiveFeign.<TestInterface>builder()
                .retryWhen(retry(3))
//                .enableLoadBalancer()

                .setLoadBalancerCommandFactory(name ->
                        LoadBalancerCommand.builder()
                                .withLoadBalancer(clientFactory.getLoadBalancer(serviceName))
                                .withRetryHandler(retryHandler)
                                .build())

                .setHystrixCommandSetterFactory(getSetterFactoryWithTimeoutDisabled())
                .target(TestInterface.class, "http://EUREKA-SERVER");

        System.out.println("group" + Thread.currentThread().getThreadGroup());
        System.out.println("thread" + Thread.currentThread().getName());




        return client.get();
    }



    private static CloudReactiveFeign.SetterFactory getSetterFactoryWithTimeoutDisabled() {
        return (target, methodMetadata) -> {
            String groupKey = target.name();
            HystrixCommandKey commandKey = HystrixCommandKey.Factory.asKey(methodMetadata.configKey());
            return HystrixObservableCommand.Setter
                    .withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey))
                    .andCommandKey(commandKey)
                    .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                            .withExecutionTimeoutEnabled(false)
                    );
        };
    }

    @FeignClient
    interface TestInterface {
        @RequestLine("GET /get")
        Mono<String> get();
    }
}
