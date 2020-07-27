package com.dhrs.date;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.dhrs.date.mapper")
public class DateSchoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(DateSchoolApplication.class,args);
    }
}
