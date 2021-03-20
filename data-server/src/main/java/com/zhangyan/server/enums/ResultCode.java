package com.zhangyan.server.enums;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Messi
 * @Date: 2020/06/21/2:49 上午
 * @Description:
 */
public enum ResultCode {
    SUCCESS(200, "操作成功"),
    FAILED(201, "操作失败"),
    PARAM_ERROR(202, "参数异常"),
    ACCESS_KEY_ERROR(203, "appId,appKey校验失败"),
    GEN_SOURCE_ID_ERROR(204, "生成sourceId异常，请重试！"),
    DATABASE_ERROR(205, "数据库操作异常"),
    DOWNLOAD_ERROR(206, "下载异常"),
    REPEATED_NODE_NAME(207, "同级节点名称重复"),
    INTERNAL_ERROR(208, "接口内部错误"),
    POJO_CONVERT_ERROR(209, "pojo转换错误");

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
