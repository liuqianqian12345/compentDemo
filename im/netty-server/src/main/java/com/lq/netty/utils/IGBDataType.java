//package com.lq.netty.utils;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.ByteBufUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
///**
// * 数据类型接口，本模块中的所有数据类型都需要继承本接口。 每个数据类型都应该具有编码、解码、判断数值是否相等的功能
// * @Author: Damon
// * @Date: 2021/9/28 17:13
// */
//@Slf4j
//public abstract class IGBDataType {
////    private static final Logger dataTypeLogger = LoggerFactory.getLogger(IGBDataType.class);
//
//    //是否校验数据的有效性，即是否根据国标校验数据的有效值范围，如果在国标过检的时候，建议关闭，调试时打开
//    @Value("${server.gateway.isCheckDataValidity:false}")
//    private boolean isCheckDataValidity;
//    /**
//     * 数据类型解码
//     * @param bytes
//     * @return
//     */
//    public abstract IGBDataType decode(byte [] bytes);
//    /**
//     * 数据类型解码，在某些数据为动态长度时，需要传递ByteBuffer给解析对象。
//     * 大部分对象都实现了decode(byte [] bytes);接口，但没有重载本方法，如果提示"not implemented"，
//     * 请直接调用decode(byte [] bytes)方法或重载本方法
//     * @param bf
//     * @return
//     */
//    public IGBDataType decode(ByteBuf bf){
//        throw new RuntimeException("not implemented");
//    }
//
//    /**
//     * 数据对象编码
//     * @return
//     */
//    public abstract byte [] encode();
//
//    /**
//     * 判断两个对象是否相同
//     * @param dataType
//     * @return
//     */
//    public abstract boolean equation(IGBDataType dataType);
//
//    /**
//     * 将当前对象转换为国标16进制字符串
//     * @return
//     */
//    public String toHexString(){
//       return ByteBufUtil.hexDump(encode());
////        return ByteUtil.bytesToHexString(encode());
//    }
//
//    /**
//     * 将当前对象转换为JSON字符串
//     * @return
//     */
//    @Override
//    public String toString() {
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            return  mapper.writeValueAsString(this);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * 对当前数据类型的16进制进行可读性格式化，默认不做变更
//     * @return
//     */
//    public String toReadableHexString(){
//        return toHexString();
//    }
//
//    /**
//     * 检查当前数据类型是否符合国标约束
//     */
//    public abstract void checkGBViolation();
//
//    /**
//     * 判断数据是否符合指定的大小区间
//     * @param dataName
//     * @param source
//     * @param min
//     * @param max
//     */
//    protected void checkDataGBConstraint(String dataName,int source,int min,int max){
//        if(true){
//            return;
//        }
//        if(source>max | source<min){
//            //由于数据解析成功，但数据不符合国标的约束，只打印日志 //TODO:需要处理不符合国标的数据，否则可能国标检测不过关
//            log.warn("Data {} GB constraint violation,source:{} min:{} max:{}",dataName,source,min,max);
//            throw new RuntimeException("GB constraint violation : ["+dataName+"],source : "+source+" min : "+min+" max : "+max);
//        }
//    }
//}
