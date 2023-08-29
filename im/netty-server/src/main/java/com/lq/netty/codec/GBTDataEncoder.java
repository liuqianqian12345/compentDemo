package com.lq.netty.codec;

import com.lq.netty.pojo.NeCommand;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.springframework.stereotype.Component;

/**
 * 编码器
 * @author chb
 *
 */
@Component
public class GBTDataEncoder extends MessageToByteEncoder<NeCommand> {

	@Override
	protected void encode(ChannelHandlerContext ctx, NeCommand msg, ByteBuf out) {
		out.writeBytes(msg.getData());
	}
}
