package com.lq.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 认证授权中心
 *
 * @author tongxin
 */
@SpringBootApplication(scanBasePackages = "com.lq")
public class OAuthStarter {
    public static void main(String[] args) {
        SpringApplication.run(OAuthStarter.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  Oauth认证授权中心启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}
