package com.ljw.management;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.ljw.management.mapper")
@EnableScheduling
public class ChildArchivesManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChildArchivesManagementApplication.class, args);
    }

}
