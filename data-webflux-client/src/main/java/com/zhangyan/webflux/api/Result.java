package com.zhangyan.webflux.api;

import lombok.Builder;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2020/06/21/3:01 上午
 * @Description:
 */
@Data
public class Result<T> {

    private Integer code;
    private String msg;
    private String traceId;
    private T data;

    public Result() {}

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.msg = message;
        this.data = data;
    }


}
