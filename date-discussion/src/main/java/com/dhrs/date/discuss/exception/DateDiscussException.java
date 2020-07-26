package com.dhrs.date.discuss.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zxq
 * @Date 2020/7/25 13:09
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateDiscussException extends  RuntimeException{
    /**
     * 状态码
     * */
    private Integer code;
    /**
     * 异常信息
     * */
    private String message;
}
