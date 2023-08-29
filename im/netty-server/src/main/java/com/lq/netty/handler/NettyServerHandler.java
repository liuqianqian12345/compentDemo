package com.lq.netty.handler;

import cn.hutool.json.JSONUtil;
//import com.lq.netty.pojo.NettyPacket;
import com.lq.netty.base.IStatus;
import com.lq.netty.codec.GBTDataDecoder;
import com.lq.netty.enums.CommandType;
import com.lq.netty.enums.InfoType;
import com.lq.netty.enums.RealTimeDataType;
import com.lq.netty.pojo.NeCommand;
import com.lq.netty.pojo.OriginalDataDTO;
import com.lq.netty.pojo.DataFlowStatus;
import com.lq.netty.utils.DataUtil;
import com.lq.netty.pojo.ObdStatus;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: liuqianqian
 * @CreateTime: 2023-08-01  09:26
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
@ChannelHandler.Sharable
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    // 管理一个全局map，保存连接进服务端的通道
    public static final Map<ChannelId, ChannelHandlerContext> CHANNEL_MAP = new ConcurrentHashMap<>();

    private final ApplicationEventPublisher applicationEventPublisher;

    /**
     * 当客户端主动连接服务端，通道活跃后触发
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        InetSocketAddress inetSocketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = inetSocketAddress.getAddress().getHostAddress();
        int clientPort = inetSocketAddress.getPort();
        // 获取连接通道唯一标识
        ChannelId channelId = ctx.channel().id();
        // 如果map中不包含此连接，就保存连接
        if (CHANNEL_MAP.containsKey(channelId)) {
            log.info("客户端【{}】是连接状态，连接通道数量:{}", channelId, CHANNEL_MAP.size());
        } else {
            // 保存连接
            CHANNEL_MAP.put(channelId, ctx);
            log.info("客户端【{}】连接Netty服务端!![clientIp:{} clientPort:{}]", channelId, clientIp, clientPort);
            log.info("连接通道数量:{}", CHANNEL_MAP.size());
        }
    }

    /**
     * 当客户端主动断开连接，通道不活跃触发
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        InetSocketAddress inetSocketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = inetSocketAddress.getAddress().getHostAddress();
        int clientPort = inetSocketAddress.getPort();
        // 获取终止连接的客户端ID
        ChannelId channelId = ctx.channel().id();
        // 包含此客户端才去删除
        if (CHANNEL_MAP.containsKey(channelId)) {
            // 删除连接
            CHANNEL_MAP.remove(channelId);
            log.warn("客户端【{}】断开Netty连接!![clientIp:{} clientPort:{}]", channelId, clientIp, clientPort);
            log.info("连接通道数量:{}", CHANNEL_MAP.size());
        }
    }

    /**
     * 通道有消息触发
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        //todo 可以不采用固定结构 不采用监听器。直接进行异步处理
        try {
            byte[] bytes = DataUtil.getBytesByHexString((String) msg);
            ByteBuf byteBuf = Unpooled.wrappedBuffer(bytes);

            GBTDataDecoder gbtDataDecoder = new GBTDataDecoder();

            NeCommand n = gbtDataDecoder.decode(byteBuf);

            OriginalDataDTO original = (OriginalDataDTO) n;

            int cmdId = original.getCmdID();
            String address = ctx.channel().remoteAddress().toString();
            String vin = original.getVin();
            String dataTime = original.getDTime();
            String originalData = DataUtil.getHexStringByBytes(original.getData().array());
            String dataUnit = DataUtil.getHexStringByBytes(original.getContent());

            log.info("车辆数据命令单元:{}", cmdId);
            log.info("车辆数据时间:{}", dataTime);
            log.info("车辆vin:{}", vin);
            log.info("车辆originalData:{}", originalData);
            log.info("车辆dataUnit:{}", dataUnit);

            /**
             * 解析数据单元
             */
            if (cmdId == CommandType.REALTIME_DATA_REPORTING.getId()
                    || cmdId == CommandType.REPLACEMENT_DATA_REPORTING.getId()) {
                //跳过数据时间，解析信息流水号
                ByteBuf dataBuf = Unpooled.wrappedBuffer(original.getContent());
                dataBuf.skipBytes(6);
                Integer sid=null;

                while (dataBuf.readableBytes() > 0) {
                    //信息类型标志
                    short typeId =dataBuf.readUnsignedByte();
                    RealTimeDataType type = RealTimeDataType.valuesOf(typeId);
                    //第一个数据体需要读取信息流水号
                    if (sid==null){
                        //两个字节
                        sid=dataBuf.readUnsignedShort();
                    }
                    if (typeId== InfoType.OBD.getId()){
                        //解析OBD信息
                        IStatus<ObdStatus> status = type.getStatus();
                        status.decode(dataBuf);
                        log.info("解析后OBD信息：{}",JSONUtil.toJsonStr(status));
                    }else if(typeId==InfoType.DATA_FLOW.getId()){
                        //解析数据流信息
                        IStatus<DataFlowStatus> status = type.getStatus();
                        status.decode(dataBuf);
                        log.info("解析后发动机信息：{}",JSONUtil.toJsonStr(status));
                    }
                }


            }

            // 报文解析处理
//            NettyPacket<Object> nettyPacket = JSONUtil.toBean(msg.toString(),NettyPacket.class);
            // 发布自定义Netty数据包处理事件
//            applicationEventPublisher.publishEvent(new NettyPacketEvent(ctx.channel().id(), nettyPacket));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("channelId:【{}】 报文解析失败!! error:{}", ctx.channel().id(), e.getMessage());
//            NettyPacket<String> nettyResponse = NettyPacket.buildRequest("报文解析失败!!");
//            ctx.writeAndFlush(JSONUtil.toJsonStr(nettyResponse));
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        String socketString = ctx.channel().remoteAddress().toString();
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                log.warn("Client: 【{}】 READER_IDLE 读超时", socketString);
                ctx.close();
            } else if (event.state() == IdleState.WRITER_IDLE) {
                log.warn("Client: 【{}】 WRITER_IDLE 写超时", socketString);
                ctx.close();
            } else if (event.state() == IdleState.ALL_IDLE) {
                log.warn("Client: 【{}】 ALL_IDLE 读/写超时", socketString);
                ctx.close();
            }
        }
    }

    /**
     * 当连接发生异常时触发
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        // 当出现异常就关闭连接
        log.error("===========连接异常============");
        ctx.close();
    }
}
