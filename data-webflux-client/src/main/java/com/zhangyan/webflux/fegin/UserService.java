package com.zhangyan.webflux.fegin;

import com.zhangyan.webflux.api.UserFeginService;
import com.zhangyan.webflux.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2021/03/20/10:41 下午
 * @Description:
 */
@Service
public class UserService {

    @Autowired
    private UserFeginService userFeginService;

    public Mono<List<User>> getAllUser() {
        return userFeginService.getAllUser().map(result -> {
            if (result.getCode() == 200) {
                System.out.println("sucess.....");
                return result.getData();
            } else {
                return null;
            }
        });
    }

    public Mono<List<User>> getAllUserWithTime(Integer latency) {
        return userFeginService.getAllUserWithTime(latency).map(result -> {
            if (result.getCode() == 200) {
                System.out.println("sucess.....");
                return result.getData();
            } else {
                return null;
            }
        });
    }

}
