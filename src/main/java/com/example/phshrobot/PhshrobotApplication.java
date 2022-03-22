package com.example.phshrobot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PhshrobotApplication {

    public static void main(String[] args) {

        SpringApplication.run(PhshrobotApplication.class, args);
        System.out.println("钉钉机器人启动成功");
    }

}
