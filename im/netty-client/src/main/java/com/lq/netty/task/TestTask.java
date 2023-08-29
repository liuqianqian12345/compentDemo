package com.lq.netty.task;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
//import com.lq.netty.pojo.NettyPacket;
import com.lq.netty.service.NettyClient;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @Author: liuqianqian
 * @CreateTime: 2023-08-01  13:33
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@Component
public class TestTask {

    @Resource
    NettyClient nettyClient;

    @Scheduled(cron = "*/10 * * * * ?")
    public void run() throws Exception {
        System.out.println("====定时任务开始====" + new Date());
        RequestDto requestDto = new RequestDto();
        requestDto.setName("小明");
        requestDto.setAction("跑起来");
//        NettyPacket<RequestDto> nettyRequest = NettyPacket.buildRequest(requestDto);

//        boolean success=nettyClient.sendMsg(JSONUtil.toJsonStr(nettyRequest));
        boolean success=nettyClient.sendMsg("232302fe4c45545945434732384d483035353530380101e917020e0d212e01c8d70100fea000004c45545945434732384d483035353530384d44314343383738203439334c44303330302d2cecf4303030303030303030303030303004d80c3c005f005f085f04d80000000000810081029e04d803c404d5050c04d507ef04d801200c6468021ca9c9828723f0000a1b4a10f84907d8372b3615000779c700068761a30129ded7000fba04021b5bc985871f74000c169c11ee4905d2372e3618000679c700068761e90129ded4000fba040219f8c984861b54000817de136c4904f83735361b000479c700068762330129ded5000fba040218d4c984861a580009170e1342490474373b3622000379c700068762770129ded8000fba04021783c9848619d4000817aa120e490452373e3625000379c700068762b70129ded6000fba04021631c98486198c000817ae11344903fa37423628000279c700068762f60129ded4000fba040214d1c984861950000817f610ea4904203745362b000379c700068763320129ded2000fba040214d1c984861950000817f610ea4904203745362b000379c700068763320129ded2000fba040213b1c986861c40000b182210da4904b03748362f000379c7000687636a0129decf000fba04021263c982861ca00008183e10c4490538374b3632000379c7000687639e0129ded3000fba0453");


//        NettyPacket<Object> response = nettyRequest.futureResponse();
//        if (response != null) {
//            log.info("requestId:{} response:{}", nettyRequest.getRequestId(), JSONUtil.toJsonStr(response.getData()));
//        }

    }

    @Getter
    @Setter
    static class RequestDto{
        private String name;
        private String action;
    }
}
