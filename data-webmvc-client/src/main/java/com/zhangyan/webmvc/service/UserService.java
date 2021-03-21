package com.zhangyan.webmvc.service;

import com.zhangyan.webmvc.api.Result;
import com.zhangyan.webmvc.api.UserFeginService;
import com.zhangyan.webmvc.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2021/03/21/3:03 下午
 * @Description:
 */
@Service
public class UserService {

    @Autowired
    private UserFeginService userFeginService;

    public List<User> getAllUser() {
        return userFeginService.getAllUser().getData();
    }

    public Result<List<User>> getAllUserWithTime(Integer latency) {
        return userFeginService.getAllUserWithTime(latency);
    }


}
