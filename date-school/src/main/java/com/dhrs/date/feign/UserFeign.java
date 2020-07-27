package com.dhrs.date.feign;

import com.dhrs.date.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Classname UserFeign
 * @Description TODO
 * @Date 2020/7/27 11:37
 * @Author 冷心影翼
 */
@FeignClient("date-user")
public interface UserFeign {

    @RequestMapping("/user/member/guest/info/{id}")
    public R info(@PathVariable("id") Long id);
}
