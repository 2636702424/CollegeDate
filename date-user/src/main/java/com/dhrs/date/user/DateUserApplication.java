package com.dhrs.date.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Classname DateUserApplication
 * @Description TODO
 * @Date 2020/7/24 14:47
 * @Author 冷心影翼
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class DateUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(DateUserApplication.class);
    }
}
