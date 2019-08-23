package com.ljw.management;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ljw.management.mapper")
public class ChildArchivesManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChildArchivesManagementApplication.class, args);
    }

}
