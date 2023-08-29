//package com.lq.netty.utils;
//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.Unpooled;
//import lombok.Getter;
//import lombok.Setter;
//
///**
// * @Author: Damon
// * @Date: 2021/9/27 19:18
// */
//@Getter
//@Setter
//public abstract class AbstractFvgbStatus extends IGBDataType {
//
//    /**
//     * 信息类型标识
//     */
//    protected FvgbStatusTypeEnum statusType;
//    /**
//     * 信息采集时间
//     */
////    protected GBTime gbTime;
//
//    /**
//     * 报文解码
//     *
//     * @param bytes
//     * @return
//     */
//    @Override
//    public AbstractFvgbStatus decode(byte[] bytes) {
//        ByteBuf byteBuf = Unpooled.wrappedBuffer(bytes);
//        return decode(byteBuf);
//    }
//
//    /**
//     * 报文解码
//     *
//     * @param bf
//     * @return
//     */
//    @Override
//    public abstract AbstractFvgbStatus decode(ByteBuf bf);
//}
