package com.bsu.taichi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author khran
 * @version 1.0
 * @description
 */

@SpringBootApplication
@MapperScan("com.bsu.taichi.mapper")
public class TaiChiSystemApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(TaiChiSystemApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        System.out.println("Application started successfully!, 程序启动成功");
    }
}
