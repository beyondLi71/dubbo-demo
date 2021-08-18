package com.beyondli.common.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author beyondLi
 * @desc 自定义异常.
 */
@Data
@NoArgsConstructor
public class PhantomException extends RuntimeException {


    public PhantomException(String id, String appName, String code, String msg) {
        this.id = id;
        this.appName = appName;
        this.code = code;
        this.msg = msg;
    }

    //异常id
    private String id;

    private String appName;

    //错误码
    private String code;

    //错误提示信息
    private String msg;

    @Override
    public String toString() {
        return String.format("{\"appName\":\"%s\",\"code\":\"%s\",\"id\":\"%s\",\"msg\":\"%s\"}",
                this.appName, this.code, this.id, this.msg);

    }
}
