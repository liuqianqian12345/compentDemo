package com.lq.netty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: liuqianqian
 * @CreateTime: 2023-08-01  10:26
 * @Description:
 * @Version: 1.0
 */
@EnableAsync
@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.lq")
public class NettyClientStarter {
    public static void main(String[] args) {
        SpringApplication.run(NettyClientStarter.class, args);
    }
}
