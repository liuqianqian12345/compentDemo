package com.lq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: liuqianqian
 * @CreateTime: 2023-03-02  09:50
 * @Description: TODO
 * @Version: 1.0
 */

@SpringBootApplication(scanBasePackages = "com.lq")
public class MqStarter {
    public static void main(String[] args) {

        SpringApplication.run(MqStarter.class);

        System.out.println("服务启动成功");
    }
}
