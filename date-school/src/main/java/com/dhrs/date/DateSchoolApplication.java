package com.dhrs.date;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.dhrs.date.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class DateSchoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(DateSchoolApplication.class,args);
    }
}
