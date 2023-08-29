package com.lq.netty.pojo;

import com.lq.netty.base.IStatus;
import com.lq.netty.utils.DataUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: Damon
 * @Date: 2021/9/27 19:22
 */
@Slf4j
@Getter
@Setter
public class ObdStatus implements IStatus {


    private static final Charset CHARSET = StandardCharsets.UTF_8;
    public static final int INVALID_VALUE = 0xFE;
    public static final int EACH_FAULT_CODE_LENGTH = 4;
    private static final String LETTER_NUMBER = "^[A-Za-z0-9]+$";

    /**
     * OBD诊断协议
     */
    private DiagnosisAgreement diagnosisAgreement;
    /**
     * MIL状态
     */
    private MILStatus milStatus;
    /**
     * 诊断支持状态
     * <p>
     * 每一位的定义如下：
     * 1	Catalyst monitoring Status 催化转化器监控
     * 2	Heated catalyst monitoring Status 加热催化转化器监控
     * 3	Evaporative system monitoring Status 蒸发系统监控
     * 4	Secondary air system monitoring Status 二次空气系统监控
     * 5	A/C system refrigerant monitoring Status A/C 系统致冷剂监控
     * 6	Exhaust Gas Sensor monitoring Status 排气传感器加热器监控
     * 7	Exhaust Gas Sensor heater monitoring Status 排气传感器加热器监控
     * 8	EGR/VVT system monitoring EGR 系统和 VVT 监控
     * 9	Cold start aid system monitoring Status 冷启动辅助系统监控
     * 10	Boost pressure control system monitoring Status 增压压力控制系统
     * 11	Diesel Particulate Filter (DPF) monitoring Status DPF 监 控
     * 12	NOx converting catalyst and/or NOx adsorber monitoring Status 选择性催化还原系统（SCR）或 NOx 吸附器
     * 13	NMHC converting catalyst monitoring Status NMHC 氧化催化器监控
     * 14	Misfire monitoring support 失火监控
     * 15	Fuel system monitoring support 燃油系统监控
     * 16	Comprehensive component monitoring support 综合成分监控
     * 每一位的含义：0=不支持；1=支持
     */
    private int diagnosisSupportStatus;
    /**
     * 诊断就绪状态
     * <p>
     * 每一位的定义如下：
     * 1	Catalyst monitoring Status 催化转化器监控
     * 2	Heated catalyst monitoring Status 加热催化转化器监控
     * 3	Evaporative system monitoring Status 蒸发系统监控
     * 4	Secondary air system monitoring Status 二次空气系统监控
     * 5	A/C system refrigerant monitoring Status A/C 系统致冷剂监控
     * 6	Exhaust Gas Sensor monitoring Status 排气传感器加热器监控
     * 7	Exhaust Gas Sensor heater monitoring Status 排气传感器加热器监控
     * 8	EGR/VVT system monitoring EGR 系统和 VVT 监控
     * 9	Cold start aid system monitoring Status 冷启动辅助系统监控
     * 10	Boost pressure control system monitoring Status 增压压力控制系统
     * 11	Diesel Particulate Filter (DPF) monitoring Status DPF 监 控
     * 12	NOx converting catalyst and/or NOx adsorber monitoring Status 选择性催化还原系统（SCR）或 NOx 吸附器
     * 13	NMHC converting catalyst monitoring Status NMHC 氧化催化器监控
     * 14	Misfire monitoring support 失火监控
     * 15	Fuel system monitoring support 燃油系统监控
     * 16	Comprehensive component monitoring support 综合成分监控
     * 每一位的含义：0=测试完成或者不支持；1=测试未完成
     */
    private int diagnosisReadyStatus;
    /**
     * 车辆唯一识别码
     */
    private String vin;
    /**
     * 软件标定识别号
     * <p>
     * 软件标定识别号由生产企业自定义，字母或数字组成，不足后面补字符“0”。
     */
    private String softwareId;
    /**
     * 标定验证码
     * <p>
     * 标定验证码由生产企业自定义，字母或数字组成，不足后面补字符“0”。
     */
    private String cvn;
    /**
     * IUPR值
     * <p>
     * 有效值范围：0~253，“0xFE”表示无效。
     */
    private String iupr;
    /**
     * 故障码总数
     */
    private short faultCount;
    /**
     * 故障码信息列表。
     * <p>
     * 每个故障码为四字节，可按故障实际顺序进行排序。
     */
//    private List<byte[]> faultCodeList;

    private List<String> faultCodeList;



    @Override
    public ObdStatus  decode(ByteBuf bf) {
        faultCodeList = new LinkedList<>();

        diagnosisAgreement = DiagnosisAgreement.valueOf(bf.readByte());
        milStatus = MILStatus.valueOf(bf.readByte());
//        diagnosisSupportStatus = Short.toUnsignedInt(bf.getShort());
        diagnosisSupportStatus = bf.readUnsignedShort();
//        diagnosisReadyStatus = Short.toUnsignedInt(bf.getShort());
        diagnosisReadyStatus = bf.readUnsignedShort();

        decodeVin(bf);
        if (!vin.matches(LETTER_NUMBER)) {
            log.warn("vin :{} is invalid", vin);
        }
        decodeSoftwareId(bf);
        decodeCvn(bf);
        decodeIupr(bf);

//        faultCount = (short) Byte.toUnsignedInt(bf.get());
        faultCount = bf.readUnsignedByte();
        if (faultCount != INVALID_VALUE) {
            for (int i = 0; i < faultCount; i++) {
                byte[] faultBytes = new byte[EACH_FAULT_CODE_LENGTH];
                bf.readBytes(faultBytes);
                String s = new String(faultBytes);
                s = s.trim();
                faultCodeList.add(s);
            }
        }
        return this;
    }



    @Override
    public ByteBuf encode() {
        int capacity = 96 + EACH_FAULT_CODE_LENGTH * faultCount;
        ByteBuf byteBuf = Unpooled.buffer(capacity);
        byteBuf.writeByte(diagnosisAgreement.getId());
        byteBuf.writeByte(milStatus.getId());
        byteBuf.writeShort(diagnosisSupportStatus);
        byteBuf.writeShort(diagnosisReadyStatus);
        byteBuf.writeCharSequence(vin,CHARSET);
        byteBuf.writeCharSequence(softwareId,CHARSET);
        byteBuf.writeCharSequence(cvn,CHARSET);
        byteBuf.writeCharSequence(iupr,CHARSET);
        byteBuf.writeByte(faultCount);

        for (String faultCode : faultCodeList) {
            byteBuf.writeCharSequence(faultCode,Charset.defaultCharset());
        }

        return byteBuf;
    }

    private void decodeIupr(ByteBuf bf) {
        ByteBuf byteBuf = bf.readSlice(36);
        iupr =  DataUtil.getParamValue(byteBuf,36,null,true);
    }

    private void decodeCvn(ByteBuf bf) {
        ByteBuf byteBuf = bf.readSlice(18);
//        cvn =  IotUtil.bufToString(byteBuf,(byte)0,CHARSET);
        cvn =  DataUtil.getParamValue(byteBuf,18,null,true);
    }

    private void decodeSoftwareId(ByteBuf bf) {
        ByteBuf byteBuf = bf.readSlice(18);
//        softwareId =  IotUtil.bufToString(byteBuf,(byte)0,CHARSET);
        softwareId =  DataUtil.getParamValue(byteBuf,18,null,true);
    }

    private void decodeVin(ByteBuf bf) {
        vin = (String) bf.readCharSequence(17,CHARSET);
    }

    /**
     * OBD诊断协议
     */
    @Getter
    public enum DiagnosisAgreement {

        /**
         *
         */
        IOS15765(0x00, "IOS15765"),
        IOS27145(0x01, "IOS27145"),
        SAEJ1939(0x02, "SAEJ1939"),
        INVALID(INVALID_VALUE, "无效"),
        ;

        private final byte id;
        private final String name;

        DiagnosisAgreement(int id, String name) {
            this.id = ((byte) id);
            this.name = name;
        }

        public static DiagnosisAgreement valueOf(byte id) {
            for (DiagnosisAgreement value : DiagnosisAgreement.values()) {
                if (value.getId() == id) {
                    return value;
                }
            }
            log.info("unmatched DiagnosisAgreement id : {}", id);
            return INVALID;
        }
    }

    /**
     * MIL状态
     */
    @Getter
    public enum MILStatus {
        /**
         *
         */
        NOT_LIGHT_UP(0x00, "未点亮"),
        LIGHT_UP(0x01, "点亮"),
        INVALID(INVALID_VALUE, "无效");

        private final byte id;
        private final String name;

        MILStatus(int id, String name) {
            this.id = ((byte) id);
            this.name = name;
        }

        public static MILStatus valueOf(byte id) {
            for (MILStatus value : MILStatus.values()) {
                if (value.getId() == id) {
                    return value;
                }
            }
            log.info("unmatched DiagnosisAgreement id : {}", id);
            return INVALID;
        }
    }
}
