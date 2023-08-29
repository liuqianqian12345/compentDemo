package com.lq.netty.handler;

import cn.hutool.json.JSONUtil;
import com.lq.netty.enums.NettyPacketType;
//import com.lq.netty.pojo.NettyPacket;
import com.lq.netty.service.NettyClient;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author: liuqianqian
 * @CreateTime: 2023-08-01  13:08
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    @Resource
    @Lazy
    private  NettyClient nettyClient;

    @Resource
    private  ApplicationEventPublisher applicationEventPublisher;

    /**
     * 建立连接时
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.info("建立Netty连接!!");
        ctx.fireChannelActive();
    }

    /**
     * 关闭连接时
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.warn("Netty连接关闭!!");
        reconnect(ctx);
    }

    /**
     * 心跳处理，每5秒发送一次心跳请求
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            if (idleStateEvent.state() == IdleState.WRITER_IDLE) {
                log.info("已经30秒没有发送消息给服务端!!");
                // 向服务端发送心跳包
//                NettyPacket<String> nettyRequest = NettyPacket.buildRequest("client heartbeat " + new Date().toString());
//                nettyRequest.setNettyPacketType(NettyPacketType.HEARTBEAT.getValue());
                // 发送心跳消息，并在发送失败时关闭该连接
//                ctx.writeAndFlush(JSONUtil.toJsonStr(nettyRequest));
                ctx.writeAndFlush("发送心跳");
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    /**
     * 收到服务端消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            // 报文解析处理
//            NettyPacket<Object> nettyPacket = JSONUtil.toBean(JSONUtil.toJsonStr(msg.toString()), NettyPacket.class);
            // 发布自定义Netty数据包处理事件
//            applicationEventPublisher.publishEvent(new NettyPacketEvent(ctx.channel().id(), nettyPacket));
            log.info("================");

        } catch (Exception e) {
            log.error("channelId:【{}】 报文解析失败!! msg:{} error:{}", ctx.channel().id(), msg.toString(), e.getMessage());
//            NettyPacket<String> nettyResponse = NettyPacket.buildRequest("报文解析失败!!");
//            ctx.writeAndFlush(JSONUtil.toJsonStr(nettyResponse));
        }
    }

    /**
     * 当连接发生异常时触发
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 当出现异常就关闭连接
        ctx.close();
    }

    private void reconnect(ChannelHandlerContext ctx) {
        log.info("准备30s后断线重连!!");
        ctx.channel().eventLoop().schedule(() -> nettyClient.run(), 30, TimeUnit.SECONDS);
    }
}
