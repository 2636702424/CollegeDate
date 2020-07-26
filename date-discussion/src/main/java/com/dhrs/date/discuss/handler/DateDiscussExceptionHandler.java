package com.dhrs.date.discuss.handler;

import com.dhrs.date.common.exception.ErrCodeEnume;
import com.dhrs.date.common.utils.R;
import com.dhrs.date.discuss.exception.DateDiscussException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * @Author zxq
 * @Date 2020/7/25 13:10
 * @Version 1.0
 */
@ControllerAdvice
public class DateDiscussExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error(ErrCodeEnume.UNKNOW_EXCEPTION);
    }

    @ExceptionHandler(DateDiscussException.class)
    @ResponseBody
    public R error(DateDiscussException e){
        return R.error(ErrCodeEnume.UNKNOW_EXCEPTION);
    }
}
