package com.springboot.elementuser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.springboot.elementuser.dao")
public class ElementuserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElementuserApplication.class, args);
    }

}
