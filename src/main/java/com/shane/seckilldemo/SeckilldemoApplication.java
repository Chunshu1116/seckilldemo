package com.shane.seckilldemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@MapperScan(value = "com.shane.seckilldemo.dao")
public class SeckilldemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeckilldemoApplication.class, args);
    }

}
