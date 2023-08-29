package com.lq.netty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Author: liuqianqian
 * @CreateTime: 2023-08-01  10:26
 * @Description: TODO
 * @Version: 1.0
 */
@EnableAsync
@SpringBootApplication(scanBasePackages = "com.lq")
public class NettyServerStarter {
    public static void main(String[] args) {
        SpringApplication.run(NettyServerStarter.class, args);
    }
}
