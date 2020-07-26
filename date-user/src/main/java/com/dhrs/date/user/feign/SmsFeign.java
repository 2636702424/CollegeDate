package com.dhrs.date.user.feign;

import com.dhrs.date.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Classname SmsFeign
 * @Description TODO
 * @Date 2020/7/26 9:42
 * @Author 冷心影翼
 */
@FeignClient("date-third")
public interface SmsFeign {

    @GetMapping("/sms/msg/{phone}/{msg}")
    public R sendVerifyCode(@PathVariable("phone") String phone, @PathVariable("msg") String msg);
}
