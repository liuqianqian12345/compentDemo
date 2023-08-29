//package com.lq.netty.handler;
//
//import cn.hutool.json.JSONUtil;
//import com.lq.netty.enums.NettyPacketType;
////import com.lq.netty.pojo.NettyPacket;
//import com.lq.netty.service.NettyServer;
//import io.netty.channel.ChannelId;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.ApplicationListener;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
///**
// * @Author: liuqianqian
// * @CreateTime: 2023-08-01  09:41
// * @Description: TODO
// * @Version: 1.0
// */
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class NettyServerPacketListener implements ApplicationListener<NettyPacketEvent> {
//
//    private final NettyServer nettyServer;
//
//    @Async
//    @Override
//    public void onApplicationEvent(NettyPacketEvent event) {
//        ChannelId channelId = (ChannelId) event.getSource();
//        String nettyPacketType = event.getNettyPacket().getNettyPacketType();
//        if (nettyPacketType.equals(NettyPacketType.HEARTBEAT.getValue())) {
//            log.info("server receive heartbeat!! channelId:{}", channelId);
//            NettyPacket<String> nettyResponse = NettyPacket.buildResponse("server receive heartbeat " + new Date().toString());
//            nettyResponse.setNettyPacketType(NettyPacketType.HEARTBEAT.getValue());
//
//            boolean success = nettyServer.channelWrite(channelId, JSONUtil.toJsonStr(nettyResponse));
//
//        } else if (nettyPacketType.equals(NettyPacketType.REQUEST.getValue())) {
//            String command = event.getNettyPacket().getCommand();
//            Object data = event.getNettyPacket().getData();
//            if (command.equals(NettyPacket.REQUEST_TEST_1)) {
//                log.info("处理客户端【{}】的请求，请求ID:{}，请求参数:{}", channelId, event.getNettyPacket().getRequestId(), JSONUtil.toJsonStr(data));
//                requestTest1(channelId, event.getNettyPacket().getRequestId(), "收到小名信息");
//            } else {
//                log.warn("unknown command!! channelId:{} data:{}", channelId, JSONUtil.toJsonStr(data));
//            }
//        } else if (nettyPacketType.equals(NettyPacketType.RESPONSE.getValue())) {
//            // TODO RESPONSE
//            log.info("channelId:{} RESPONSE!! data:{}", channelId, JSONUtil.toJsonStr(event.getNettyPacket().getData()));
//        } else {
//            log.warn("unknown NettyPacketType!! channelId:{} event:{}", channelId, JSONUtil.toJsonStr(event));
//        }
//    }
//
//    /**
//     * 处理请求:RequestTest1
//     */
//    private void requestTest1(ChannelId channelId, String requestId, Object param) {
////        log.info("处理客户端【{}】的请求，请求ID:{}，请求参数:{}", channelId, requestId, JSONUtil.toJsonStr(param));
////        ResponseTestVO response = ResponseTestVO.builder().message("server receive param").date(new Date()).build();
//        NettyPacket<Object> nettyResponse = NettyPacket.buildResponse(requestId, param);
//        nettyServer.channelWrite(channelId, JSONUtil.toJsonStr(nettyResponse));
//    }
//}
