//package com.lq.netty.handler;
//
//import cn.hutool.core.lang.TypeReference;
//import cn.hutool.json.JSONUtil;
//import com.lq.netty.enums.NettyPacketType;
//import com.lq.netty.pojo.NettyPacket;
//import io.netty.channel.ChannelId;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.ApplicationListener;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Component;
//
///**
// * @Author: liuqianqian
// * @CreateTime: 2023-08-01  13:18
// * @Description: TODO
// * @Version: 1.0
// */
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class NettyClientPacketListener implements ApplicationListener<NettyPacketEvent> {
//    @Async
//    @Override
//    public void onApplicationEvent(NettyPacketEvent event) {
//        ChannelId channelId = (ChannelId) event.getSource();
//        String nettyPacketType = event.getNettyPacket().getNettyPacketType();
//        log.info("客服端收到监听消息：{}",nettyPacketType);
//        if (nettyPacketType.equals(NettyPacketType.HEARTBEAT.getValue())) {
//            log.info("client receive heartbeat!!");
//        } else if (nettyPacketType.equals(NettyPacketType.REQUEST.getValue())) {
//            // TODO REQUEST
//            log.info("channelId:{} REQUEST!! data:{}", channelId, JSONUtil.toJsonStr(event.getNettyPacket().getData()));
//        } else if (nettyPacketType.equals(NettyPacketType.RESPONSE.getValue())) {
//            NettyPacket<Object> nettyResponse =JSONUtil.toBean(JSONUtil.toJsonStr(event.getNettyPacket()), NettyPacket.class);
////            NettyPacket.response(event.getNettyPacket().getRequestId(), nettyResponse);
//            log.info("client receive response!! 响应参数：{}",JSONUtil.toJsonStr(nettyResponse.getData()));
//        } else {
//            log.warn("unknown NettyPacketType!! channelId:{} event:{}", channelId, JSONUtil.toJsonStr(event));
//        }
//    }
//}
