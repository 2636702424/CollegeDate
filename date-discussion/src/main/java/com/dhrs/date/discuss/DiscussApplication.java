package com.dhrs.date.discuss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author zxq
 * @Date 2020/7/25 10:18
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.dhrs")
public class DiscussApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiscussApplication.class);
    }
}
