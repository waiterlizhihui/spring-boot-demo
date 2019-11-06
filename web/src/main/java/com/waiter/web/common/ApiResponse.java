package com.waiter.web.common;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @ClassName ApiRespnse
 * @Description 通用API返回类
 * @Author lizhihui
 * @Date 2019/9/5 10:38
 * @Version 1.0
 */
@Data
public class ApiResponse {
    private String message;
    private Object data;

    public ApiResponse() {

    }

    public ApiResponse(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public static ApiResponse of(String message, Object data) {
        return new ApiResponse(message, data);
    }

    public static ApiResponse ok(Object data) {
        return new ApiResponse(HttpStatus.OK.getReasonPhrase(), data);
    }

    public static ApiResponse msg(String message) {
        return of(message, null);
    }
}
