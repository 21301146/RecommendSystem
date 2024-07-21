package com.bjtu.admin;

import com.bjtu.clients.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;


@MapperScan(basePackages = "com.bjtu.admin.mapper")
@SpringBootApplication
@EnableCaching
@EnableFeignClients(clients = {CategoryClient.class,UserClient.class,
        SearchClient.class, ProductClient.class, CartClient.class, OrderClient.class})
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);
    }
}
