package com.lq.netty.pojo;

import com.lq.netty.base.IStatus;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 数据流信息数据
 *
 * @Author: Damon
 * @Date: 2021/9/28 18:05
 */
@Slf4j
@Data
public class DataFlowStatus implements IStatus {

    public static final short INVALID_VALUE = 0xFF;
    /**
     * 车速
     * <p>
     * 数据长度：2 bytes
     * 精度：1/256 km/h per bit
     * 偏移量：0
     * 数据范围：0 ~ 250.996 km/h
     * “0xFF,0xFF”表示无效
     */
    private int speed;

    /**
     * 大气压力
     * <p>
     * 数据长度：1 byte
     * 精度：0.5 kPa/bit
     * 偏移量：0
     * 数据范围：0 ~ 125 kPa
     * “0xFF”表示无效
     */
    private short atmosphericPressure;

    /**
     * 发动机净输出扭矩
     * <p>
     * 数据长度：1 byte
     * 精度：1%/bit
     * 偏移量：-125
     * 数据范围：-125 ~ 125 %
     * “0xFF”表示无效
     */
    private short engineOutputTorque;

    /**
     * 摩擦扭矩
     * <p>
     * 数据长度：1 byte
     * 精度：1%/bit
     * 偏移量：-125
     * 数据范围：-125 ~ 125 %
     * “0xFF”表示无效
     */
    private short frictionTorque;

    /**
     * 发动机转速
     * <p>
     * 数据长度：2 bytes
     * 精度：0.125rpm/bit
     * 偏移量：0
     * 数据范围：0 ~ 8031.875 rpm
     * “0xFF,0xFF”表示无效
     */
    private int engineSpeed;

    /**
     * 发动机燃料流量
     * <p>
     * 数据长度：2 bytes
     * 精度：0.05L/h
     * 偏移量：0
     * 数据范围：0 ~ 3212.75 L/h
     * “0xFF,0xFF”表示无效
     */
    private int engineFuelFlow;

    /**
     * SCR上游Nox传感器输出值
     * <p>
     * 数据长度：2 bytes
     * 精度：0.05ppm/bit
     * 偏移量：-200
     * 数据范围：-200 ~ 3012.75ppm
     * “0xFF,0xFF”表示无效
     */
    private int scrUpstreamNoxSensor;

    /**
     * SCR下游Nox传感器输出值
     * <p>
     * 数据长度：2 bytes
     * 精度：0.05 ppm/bit
     * 偏移量：-200
     * 数据范围：-200 ~ 3012.75 ppm
     * “0xFF,0xFF”表示无效
     */
    private int scrDownstreamNoxSensor;

    /**
     * 反应剂余量
     * <p>
     * 数据长度：1 byte
     * 精 度 ：0.4%/bit
     * 偏移量：0
     * 数据范围：0 ~ 100 %
     * “0xFF”表示无效
     */
    private short reagentAllowance;

    /**
     * 进气量
     * <p>
     * 数据长度：2 bytes
     * 精度：0.05kg/h per bit
     * 偏移量：0
     * 数据范围：0 ~ 3212.75 kg/h
     * “0xFF,0xFF”表示无效
     */
    private int volume;

    /**
     * SCR入口温度
     * <p>
     * 数据长度：2 bytes
     * 精度：0.03125deg C/bit
     * 偏移量：-273
     * 数据范围：-273 ~ 1734.96875deg C
     * “0xFF,0xFF”表示无效
     */
    private int scrInletTemperature;

    /**
     * SCR出口温度
     * <p>
     * 数据长度：2 bytes
     * 精度：0.03125deg C/bit
     * 偏移量：-273
     * 数据范围：-273 ~ 1734.96875deg C
     * “0xFF,0xFF”表示无效
     */
    private int scrOutletTemperature;

    /**
     * DPF压差
     * <p>
     * 数据长度：2 bytes
     * 精度：0.1kPa/bit
     * 偏移量：0
     * 数据范围：0 ~ 6425.5 kPa
     * “0xFF,0xFF”表示无效
     */
    private int dpfDifferentialPressure;

    /**
     * 发动机冷却液温度
     * <p>
     * 数据长度：1 byte
     * 精度：1deg C/bit
     * 偏移量：-40
     * 数据范围：-40 ~ 210 deg C
     * “0xFF”表示无效
     */
    private short engineCoolantTemperature;

    /**
     * 油箱液位
     * <p>
     * 数据长度：1 byte
     * 精度 ：0.4%/bit
     * 偏移量：0
     * 数据范围：0 ~ 100 %
     * “0xFF”表示无效
     */
    private short tankOfLiquidLevel;

    /**
     * 定位状态
     * <p>
     * 每一位的定义如下:
     * 0   是否为有效定位. 0:有效定位；1:无效定位（当数据通信正常，而不能获取定位信息时，发送最后一次有效定位信息，并将定位状态置为无效）
     * 1   纬度. 0:北纬；1:南纬。
     * 2   经度. 0:东经；1:西经。
     */
    private short positioningState;

    /**
     * 经度
     * <p>
     * 数据长度：4 bytes
     * 精度：0.000001°/
     * 偏移量：0
     * 数据范围：0 ~ 180.000000°
     * “0xFF，0xFF，0xFF，0xFF”表示无效
     */
    private long longitude;

    /**
     * 纬度
     * <p>
     * 数据长度：4 bytes
     * 精度：0.000001°/
     * 偏移量：0
     * 数据范围：0 ~ 90.000000 °
     * “0xFF，0xFF，0xFF，0xFF”表示无效
     */
    private long latitude;

    /**
     * 累计里程
     * <p>
     * 数据长度：4 bytes
     * 精度：0.1km/bit
     * 偏移量：0
     * “0xFF，0xFF，0xFF，0xFF”表示无效
     */
    private long totalMileage;

    @Override
    public IStatus decode(ByteBuf bf) {
        speed = bf.readUnsignedShort();
        atmosphericPressure = bf.readUnsignedByte();
        engineOutputTorque = bf.readUnsignedByte();
        frictionTorque = bf.readUnsignedByte();
        engineSpeed = bf.readUnsignedShort();
        engineFuelFlow = bf.readUnsignedShort();
        scrUpstreamNoxSensor = bf.readUnsignedShort();
        scrDownstreamNoxSensor = bf.readUnsignedShort();
        reagentAllowance = bf.readUnsignedByte();
        volume = bf.readUnsignedShort();
        scrInletTemperature = bf.readUnsignedShort();
        scrOutletTemperature = bf.readUnsignedShort();
        dpfDifferentialPressure = bf.readUnsignedShort();
        engineCoolantTemperature = bf.readUnsignedByte();
        tankOfLiquidLevel = bf.readUnsignedByte();
        positioningState = bf.readUnsignedByte();
        longitude = bf.readUnsignedInt();
        latitude = bf.readUnsignedInt();
        totalMileage = bf.readUnsignedInt();
        return this;
    }

    @Override
    public ByteBuf encode() {
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeShort(speed);
        byteBuf.writeByte(atmosphericPressure);
        byteBuf.writeByte(engineOutputTorque);
        byteBuf.writeByte(frictionTorque);
        byteBuf.writeShort(engineSpeed);
        byteBuf.writeShort(engineFuelFlow);
        byteBuf.writeShort(scrUpstreamNoxSensor);
        byteBuf.writeShort(scrDownstreamNoxSensor);
        byteBuf.writeByte(reagentAllowance);
        byteBuf.writeShort(volume);
        byteBuf.writeShort(scrInletTemperature);
        byteBuf.writeShort(scrOutletTemperature);
        byteBuf.writeShort(dpfDifferentialPressure);
        byteBuf.writeByte(engineCoolantTemperature);
        byteBuf.writeByte(tankOfLiquidLevel);
        byteBuf.writeByte(positioningState);
        byteBuf.writeInt((int) longitude);
        byteBuf.writeInt((int) latitude);
        byteBuf.writeInt((int) totalMileage);

        return byteBuf;
    }

}
