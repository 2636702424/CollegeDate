package com.dhrs.date.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Classname DateGateWayApplication
 * @Description TODO
 * @Date 2020/7/24 17:11
 * @Author 冷心影翼
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DateGateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DateGateWayApplication.class);
    }
}
