//package com.lq.netty.utils;
//
//import com.wit.utsp.protocol.common.GBEncryptionModel;
//import com.wit.utsp.protocol.common.codec.ICodec;
//import com.wit.utsp.protocol.common.payload.Command;
//import com.wit.utsp.protocol.common.util.ByteUtil;
//import com.wit.tsp.protocol.g6.GBCommand;
//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.Unpooled;
//import io.netty.util.CharsetUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.nio.ByteBuffer;
//import java.nio.ByteOrder;
//import java.nio.charset.Charset;
//import java.util.Optional;
//
///**
// * @Author: Damon
// * @Date: 2021/9/28 15:17
// */
//public class GBPayload <T extends ICodec> extends BaseDataPayload<T> {
//
//    /**
//     * 固定为ASCII字符‘##’，用“0x23, 0x23”表示。
//     */
//    private static final String START_SYMBOL = "##";
//    private static final Logger log = LoggerFactory.getLogger(GBPayload.class);
//
//    /**
//     * 数据单元加密方式
//     */
//    private GBEncryptionModel encryptionModel;
//    /**
//     * 应答标志
//     */
////    private GBResponseCode responseCode;
//    /**
//     * bcc校验码
//     */
//    private byte bcc;
//
//    /**
//     * decode的对象
//     */
//    protected ByteBuf byteBuffer;
//
//    public GBPayload() {
//    }
//
//    public GBPayload(GBCommand command, String vin, GBEncryptionModel encryptionModel, T dataCell) {
//        super();
//        this.command = command;
//        this.encryptionModel = encryptionModel;
////        this.responseCode = responseCode;
//        this.vin = vin;
//        this.body = dataCell;
//    }
//
//    @Override
//    public void decode(ByteBuf byteBuffer) {
//        this.byteBuffer = byteBuffer;
//
//        decodeAndValidStartSymbol();
//
//        command = decodeCommand();
//        vin = decodeVin();
//        //终端版本号
//        softwareVersion = byteBuffer.readUnsignedByte();
//        encryptionModel = decodeEncryptionModel();
//        byte[] bodyBytes = decodeBody();
//
//        decodeAndValidBcc();
//
//        if (bodyBytes.length == 0) {
//            log.warn("Parsed body data length is 0 for command:{}", command);
//            return;
//        }
//
//        try {
//            ByteBuf byteBuf = Unpooled.wrappedBuffer(bodyBytes);
//            decodeBody(byteBuf);
//        } catch (Exception e) {
//            // 运行时异常需要抛出, 若客户端有需要则可捕获判定decode()是否执行成功
//            if (e instanceof RuntimeException) {
//                throw ((RuntimeException) e);
//            }
//            log.error("decode error:{}",e.getMessage());
//        }
//    }
//
//    /**
//     * 编码数据
//     *
//     * @return
//     */
//    @Override
//    public byte[] encode() {
//        int msgSize = 25;
//        int bodySize = 0;
//        byte[] bodyBytes = new byte[0];
//        if (body != null) {
//            bodyBytes = body.encode();
//            bodySize = bodyBytes.length;
//            msgSize += bodySize;
//        }
//
//        ByteBuffer byteBuffer = ByteBuffer.allocate(msgSize);
//        //设置编码数据为大端模式
//        byteBuffer.order(ByteOrder.BIG_ENDIAN);
//
//        //begin sign
//        byteBuffer.put(START_SYMBOL.getBytes());
//        //command
//        byteBuffer.put(command.getId());
//        //responseCode
////        byteBuffer.put(responseCode.getCode());
//        //vin
//        byteBuffer.put(vin.getBytes());
//        //encryptionModel
//        byteBuffer.put((byte) softwareVersion);
//        byteBuffer.put(encryptionModel.getId());
//        //param size
//        byteBuffer.putShort((short) bodySize);
//
//        //param
//        byteBuffer.put(bodyBytes);
//
//        byteBuffer.flip();
//        byte[] payloadBytes = new byte[byteBuffer.remaining()];
//        byteBuffer.get(payloadBytes);
//        //8.bcc
//        bcc = ByteUtil.getBCC(payloadBytes);
//
//        byteBuffer.clear();
//        byteBuffer.put(payloadBytes);
//        byteBuffer.put(bcc);
//        byteBuffer.flip();
//        payloadBytes = new byte[byteBuffer.remaining()];
//        byteBuffer.get(payloadBytes);
//        return payloadBytes;
//    }
//
//    @Override
//    public String toReadableHexString() {
//        return null;
//    }
//
//    @Override
//    public void decodeHeader(ByteBuf buf) {
//
//    }
//
//    @Override
//    public void setCommand(Command command) {
//        if (command instanceof GBCommand) {
//            this.command = command;
//            return;
//        }
//        throw new RuntimeException("command must be instance of GBCommand.");
//    }
//
//    public GBEncryptionModel getEncryptionModel() {
//        return encryptionModel;
//    }
//
//    public void setEncryptionModel(GBEncryptionModel encryptionModel) {
//        this.encryptionModel = encryptionModel;
//    }
//
////    public GBResponseCode getResponseCode() {
////        return responseCode;
////    }
//
////    public void setResponseCode(GBResponseCode responseCode) {
////        this.responseCode = responseCode;
////    }
//
//    public byte getBcc() {
//        return bcc;
//    }
//
//    public void setBcc(byte bcc) {
//        this.bcc = bcc;
//    }
//
//
//
//    protected void decodeAndValidBcc() {
//
//        this.bcc = byteBuffer.readByte();
//        byteBuffer.readerIndex(0);
//        byte[] bytes = new byte[byteBuffer.readableBytes() - 1];
//        byteBuffer.readBytes(bytes);
//        byte generatedBcc = ByteUtil.getBCC(bytes);
//        if (this.bcc != generatedBcc) {
//            throw new RuntimeException("Data Invalid, BCC not equal received: " + this.bcc + " Calculated:" + generatedBcc);
//        }
//        byteBuffer.readByte();
//    }
//
//
//    protected byte[] decodeBody() {
//        int bodySize = byteBuffer.readUnsignedShort();
//        byte[] bodyBytes;
//        if (bodySize > 0) {
//            bodyBytes = new byte[bodySize];
//            byteBuffer.readBytes(bodyBytes);
//        } else {
//            bodyBytes = new byte[0];
//        }
//        return bodyBytes;
//    }
//
//
//    protected GBEncryptionModel decodeEncryptionModel() {
//        return GBEncryptionModel.valueOf(byteBuffer.readByte());
//    }
//
//    protected String decodeVin() {
//        return (String) byteBuffer.readCharSequence(17, Charset.defaultCharset());
//    }
//
//    protected void decodeAndValidStartSymbol() {
//        String beginSignStr = (String) byteBuffer.readCharSequence(2, Charset.defaultCharset());
//        //校验报文头
//        if (!START_SYMBOL.equals(beginSignStr)) {
//            //todo
//            byte[] bytes = new byte[byteBuffer.readableBytes()];
//            byteBuffer.readBytes(bytes);
//            throw new RuntimeException("unknown begin sign");
//        }
//    }
//
//
//    protected Command decodeCommand() {
//        return GBCommand.valuesOf(byteBuffer.readByte());
//    }
//
//
//    protected void decodeBody(ByteBuf bf) throws Exception {
//        Optional<Class<? extends ICodec>> optional = ((GBCommand) getCommand()).getBodyClass();
//        if (optional.isPresent()) {
//            decodeBody(bf, optional.get());
//        }
//    }
//
//}
