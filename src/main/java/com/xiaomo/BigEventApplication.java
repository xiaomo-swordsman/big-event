package com.xiaomo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//(scanBasePackages = {"com.xiaomo"})
public class BigEventApplication {

    public static void main(String[] args) {
        SpringApplication.run(BigEventApplication.class,args);
    }
}
