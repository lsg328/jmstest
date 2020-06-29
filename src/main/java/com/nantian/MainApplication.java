package com.nantian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Classname SpringBootApplication
 * @Description TODO
 * @Date 2020/6/29 16:32
 * @Created by Administrator
 */
@SpringBootApplication
@EnableTransactionManagement
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
