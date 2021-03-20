package com.zhangyan.webflux.config;

import com.netflix.client.DefaultLoadBalancerRetryHandler;
import com.netflix.client.RequestSpecificRetryHandler;
import com.netflix.client.RetryHandler;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixObservableCommand;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
import com.zhangyan.webflux.api.UserFeginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactivefeign.cloud.CloudReactiveFeign;

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

        return CloudReactiveFeign.<UserFeginService>builder()
                .retryWhen(retry(3))
//                .enableLoadBalancer()
                .setLoadBalancerCommandFactory(name ->
                        LoadBalancerCommand.builder()
                                .withLoadBalancer(clientFactory.getLoadBalancer(USER_SERVICE_NAME))
                                .withRetryHandler(retryHandler)
                                .build())

                .setHystrixCommandSetterFactory(getSetterFactoryWithTimeoutDisabled())
                .target(UserFeginService.class, "http://" + USER_SERVICE_NAME);
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




}
