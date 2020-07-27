package com.dhrs.date.exception;


import com.dhrs.date.common.exception.AuthException;
import com.dhrs.date.common.exception.ErrCodeEnume;
import com.dhrs.date.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Classname GulimallExceptionControllerAdvice
 * @Description TODO
 * @Date 2020/5/26 17:33
 * @Author 冷心影翼
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.dhrs.date.user")
public class ExceptionControllerAdvice {


    @ExceptionHandler(AuthException.class)
    public R authException(AuthException e) {
        return R.error(ErrCodeEnume.TOKEN_AUTH_FAIL);
    }

    @ExceptionHandler(Exception.class)
    public R handleException(Exception e) {
        log.error("未知异常",e);
        System.out.print(e);
        return R.error(ErrCodeEnume.UNKNOW_EXCEPTION);
    }
}
