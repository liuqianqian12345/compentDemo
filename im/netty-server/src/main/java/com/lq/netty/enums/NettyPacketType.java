package com.lq.netty.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author: liuqianqian
 * @CreateTime: 2023-08-01  09:03
 * @Description: TODO
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum NettyPacketType {
    HEARTBEAT("心跳", "HEARTBEAT"),
    REQUEST("请求", "REQUEST"),
    RESPONSE("响应", "RESPONSE");

    private final String name;
    private final String value;

}
