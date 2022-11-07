package com.ywz.nkcommunity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.ywz.nkcommunity.mapper")
public class NkCommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(NkCommunityApplication.class, args);
    }

}
