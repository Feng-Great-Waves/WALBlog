package com.wal;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wal.mapper")
public class WalBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(WalBlogApplication.class,args);
    }
}
