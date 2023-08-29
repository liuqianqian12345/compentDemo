//package com.lq.netty.pojo;
//
//import cn.afterturn.easypoi.excel.annotation.Excel;
//import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
//import com.lq.netty.enums.InfoType;
//import com.wit.tsp.common.protocol.Invalid;
//import com.wit.tsp.common.utils.GPSUtil;
//import com.wit.tsp.feign.model.shadow.fv.FvgbStatusTypeEnum;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//
//import java.math.BigDecimal;
//
///**
// * @Author: Damon
// * @Date: 2021/10/11 15:55
// */
//@Data
//public class DataFlowStatus {
//
//    /**
//     * 信息类型标识
//     */
//    protected InfoType statusType;
//
//    /**
//     * 车速
//     * <p>
//     * 数据长度：2 bytes
//     * 精度：1/256 km/h per bit
//     * 偏移量：0
//     * 数据范围：0 ~ 250.996 km/h
//     * “0xFF,0xFF”表示无效
//     */
//    private int speed;
//    private String speedCov;
//
//    /**
//     * 大气压力
//     * <p>
//     * 数据长度：1 byte
//     * 精度：0.5 kPa/bit
//     * 偏移量：0
//     * 数据范围：0 ~ 125 kPa
//     * “0xFF”表示无效
//     */
//    private short atmosphericPressure;
//    private String atmosphericPressureCov;
//
//    /**
//     * 发动机净输出扭矩
//     * <p>
//     * 数据长度：1 byte
//     * 精度：1%/bit
//     * 偏移量：-125
//     * 数据范围：-125 ~ 125 %
//     * “0xFF”表示无效
//     */
//    private short engineOutputTorque;
//
//    private String engineOutputTorqueCov;
//
//    /**
//     * 摩擦扭矩
//     * <p>
//     * 数据长度：1 byte
//     * 精度：1%/bit
//     * 偏移量：-125
//     * 数据范围：-125 ~ 125 %
//     * “0xFF”表示无效
//     */
//    private short frictionTorque;
//
//
//    private String frictionTorqueCov;
//
//    /**
//     * 发动机转速
//     * <p>
//     * 数据长度：2 bytes
//     * 精度：0.125rpm/bit
//     * 偏移量：0
//     * 数据范围：0 ~ 8031.875 rpm
//     * “0xFF,0xFF”表示无效
//     */
//    private int engineSpeed;
//
//    private String engineSpeedCov;
//
//    /**
//     * 发动机燃料流量
//     * <p>
//     * 数据长度：2 bytes
//     * 精度：0.05L/h
//     * 偏移量：0
//     * 数据范围：0 ~ 3212.75 L/h
//     * “0xFF,0xFF”表示无效
//     */
//    private int engineFuelFlow;
//
//    private String engineFuelFlowCov;
//
//    /**
//     * SCR上游Nox传感器输出值
//     * <p>
//     * 数据长度：2 bytes
//     * 精度：0.05ppm/bit
//     * 偏移量：-200
//     * 数据范围：-200 ~ 3012.75ppm
//     * “0xFF,0xFF”表示无效
//     */
//    private int scrUpstreamNoxSensor;
//
//    private String scrUpstreamNoxSensorCov;
//
//    /**
//     * SCR下游Nox传感器输出值
//     * <p>
//     * 数据长度：2 bytes
//     * 精度：0.05 ppm/bit
//     * 偏移量：-200
//     * 数据范围：-200 ~ 3012.75 ppm
//     * “0xFF,0xFF”表示无效
//     */
//    private int scrDownstreamNoxSensor;
//
//    private String scrDownstreamNoxSensorCov;
//
//    /**
//     * 反应剂余量
//     * <p>
//     * 数据长度：1 byte
//     * 精 度 ：0.4%/bit
//     * 偏移量：0
//     * 数据范围：0 ~ 100 %
//     * “0xFF”表示无效
//     */
//    private short reagentAllowance;
//
//    private String reagentAllowanceCov;
//
//    /**
//     * 进气量
//     * <p>
//     * 数据长度：2 bytes
//     * 精度：0.05kg/h per bit
//     * 偏移量：0
//     * 数据范围：0 ~ 3212.75 kg/h
//     * “0xFF,0xFF”表示无效
//     */
//    private int volume;
//
//    private String volumeCov;
//
//    /**
//     * SCR入口温度
//     * <p>
//     * 数据长度：2 bytes
//     * 精度：0.03125deg C/bit
//     * 偏移量：-273
//     * 数据范围：-273 ~ 1734.96875deg C
//     * “0xFF,0xFF”表示无效
//     */
//    private int scrInletTemperature;
//
//
//    private String scrInletTemperatureCov;
//
//    /**
//     * SCR出口温度
//     * <p>
//     * 数据长度：2 bytes
//     * 精度：0.03125deg C/bit
//     * 偏移量：-273
//     * 数据范围：-273 ~ 1734.96875deg C
//     * “0xFF,0xFF”表示无效
//     */
//    private int scrOutletTemperature;
//
//    private String scrOutletTemperatureCov;
//
//    /**
//     * DPF压差
//     * <p>
//     * 数据长度：2 bytes
//     * 精度：0.1kPa/bit
//     * 偏移量：0
//     * 数据范围：0 ~ 6425.5 kPa
//     * “0xFF,0xFF”表示无效
//     */
//    private int dpfDifferentialPressure;
//
//    private String dpfDifferentialPressureCov;
//
//
//    /**
//     * 发动机冷却液温度
//     * <p>
//     * 数据长度：1 byte
//     * 精度：1deg C/bit
//     * 偏移量：-40
//     * 数据范围：-40 ~ 210 deg C
//     * “0xFF”表示无效
//     */
//    private short engineCoolantTemperature;
//
//    private String engineCoolantTemperatureCov;
//
//    /**
//     * 油箱液位
//     * <p>
//     * 数据长度：1 byte
//     * 精度 ：0.4%/bit
//     * 偏移量：0
//     * 数据范围：0 ~ 100 %
//     * “0xFF”表示无效
//     */
//    private short tankOfLiquidLevel;
//    private String tankOfLiquidLevelCov;
//
//    /**
//     * 定位状态
//     * <p>
//     * 每一位的定义如下:
//     * 0   是否为有效定位. 0:有效定位；1:无效定位（当数据通信正常，而不能获取定位信息时，发送最后一次有效定位信息，并将定位状态置为无效）
//     * 1   纬度. 0:北纬；1:南纬。
//     * 2   经度. 0:东经；1:西经。
//     */
//    private short positioningState;
//    private String positioningStateCov;
//
//    /**
//     * 经度
//     * <p>
//     * 数据长度：4 bytes
//     * 精度：°/bit
//     * 偏移量：0
//     * 数据范围：0 ~ 180.000000°
//     * “0xFF，0xFF，0xFF，0xFF”表示无效
//     */
//    private long longitude;
//    private String longitudeCov;
//
//    /**
//     * 纬度
//     * <p>
//     * 数据长度：4 bytes
//     * 精度：0.000001°/
//     * 偏移量：0
//     * 数据范围：0 ~ 90.000000 °
//     * “0xFF，0xFF，0xFF，0xFF”表示无效
//     */
//    private long latitude;
//    private String latitudeCov;
//
//    /**
//     * 累计里程
//     * <p>
//     * 数据长度：4 bytes
//     * 精度：0.1km/bit
//     * 偏移量：0
//     * “0xFF，0xFF，0xFF，0xFF”表示无效
//     */
//    private long totalMileage;
//    private String totalMileageCov;
//
//    private int provinceCode;
//    private String provinceName;
//
//    private int cityCode;
//    private String cityName;
//
//
//    private int districtCode;
//    private String districtName;
//
//    /**
//     * 以下自定义getter
//     * 原因:要计算偏移量
//     */
//
//    public String getSpeedCov() {
//        if (speed == Invalid.WORD) {
//            return Invalid.SHOW;
//        }
//        return String.valueOf(new BigDecimal(speed).divide(new BigDecimal(256), 3, BigDecimal.ROUND_HALF_UP).doubleValue());
//    }
//
//    public String getAtmosphericPressureCov() {
//        if (atmosphericPressure == Invalid.BYTE) {
//            return Invalid.SHOW;
//        }
//        return String.valueOf(new BigDecimal(atmosphericPressure).divide(new BigDecimal(2), 1, BigDecimal.ROUND_HALF_UP).doubleValue());
//    }
//
//    public String getEngineOutputTorqueCov() {
//        if (Invalid.BYTE == engineOutputTorque) {
//            return Invalid.SHOW;
//        }
//        return String.valueOf(engineOutputTorque - 125);
//    }
//
//    public String getFrictionTorqueCov() {
//        if (Invalid.BYTE == frictionTorque) {
//            return Invalid.SHOW;
//        }
//        return String.valueOf(frictionTorque - 125);
//    }
//
//    public String getEngineSpeedCov() {
//        if (Invalid.WORD == engineSpeed) {
//            return Invalid.SHOW;
//        }
//        return String.valueOf(new BigDecimal(engineSpeed).divide(new BigDecimal(8), 3, BigDecimal.ROUND_HALF_UP).doubleValue());
//    }
//
//    public String getEngineFuelFlowCov() {
//        if (Invalid.WORD == engineFuelFlow) {
//            return Invalid.SHOW;
//        }
//        return String.valueOf(new BigDecimal(engineFuelFlow).divide(new BigDecimal(20), 2, BigDecimal.ROUND_HALF_UP).doubleValue());
//    }
//
//
//    public String getScrUpstreamNoxSensorCov() {
//        if (Invalid.WORD == scrUpstreamNoxSensor) {
//            return Invalid.SHOW;
//        }
//        return String.valueOf(new BigDecimal(scrUpstreamNoxSensor).divide(new BigDecimal(20), 2, BigDecimal.ROUND_HALF_UP).
//                subtract(new BigDecimal(200)).doubleValue());
//    }
//
//    public String getScrDownstreamNoxSensorCov() {
//        if (Invalid.WORD == scrDownstreamNoxSensor) {
//            return Invalid.SHOW;
//        }
//        return String.valueOf(new BigDecimal(scrDownstreamNoxSensor).divide(new BigDecimal(20), 2, BigDecimal.ROUND_HALF_UP)
//                .subtract(new BigDecimal(200)).doubleValue());
//    }
//
//    public String getReagentAllowanceCov() {
//        if (Invalid.BYTE == reagentAllowance) {
//            return Invalid.SHOW;
//        }
//        return String.valueOf(new BigDecimal(reagentAllowance).multiply(new BigDecimal(0.4))
//                .setScale(1, BigDecimal.ROUND_HALF_UP).floatValue());
//    }
//
//    public String getVolumeCov() {
//        if (Invalid.WORD == volume) {
//            return Invalid.SHOW;
//        }
//        return String.valueOf(new BigDecimal(volume).divide(new BigDecimal(20), 2, BigDecimal.ROUND_HALF_UP).floatValue());
//    }
//
//    public String getScrInletTemperatureCov() {
//        if (Invalid.WORD == scrInletTemperature) {
//            return Invalid.SHOW;
//        }
//        return String.valueOf(new BigDecimal(scrInletTemperature).multiply(new BigDecimal("0.03125")).subtract(new BigDecimal(273))
//                .setScale(5, BigDecimal.ROUND_HALF_UP).floatValue());
//    }
//
//    public String getScrOutletTemperatureCov() {
//        if (Invalid.WORD == scrOutletTemperature) {
//            return Invalid.SHOW;
//        }
//        return String.valueOf(new BigDecimal(scrOutletTemperature).multiply(new BigDecimal("0.03125")).subtract(new BigDecimal(273))
//                .setScale(5, BigDecimal.ROUND_HALF_UP).floatValue());
//    }
//
//    public String getDpfDifferentialPressureCov() {
//        if (Invalid.WORD == dpfDifferentialPressure) {
//            return Invalid.SHOW;
//        }
//        return String.valueOf(new BigDecimal(dpfDifferentialPressure).divide(new BigDecimal(10), 1, BigDecimal.ROUND_HALF_UP).floatValue());
//    }
//
//    public String getEngineCoolantTemperatureCov() {
//        if (Invalid.BYTE == engineCoolantTemperature) {
//            return Invalid.SHOW;
//        }
//        return String.valueOf(engineCoolantTemperature - 40);
//    }
//
//
//    public short getPositioningStateCov() {
//        return positioningState;
//    }
//
//    public String getTankOfLiquidLevelCov() {
//        if (Invalid.BYTE == tankOfLiquidLevel) {
//            return Invalid.SHOW;
//        }
//        return String.valueOf(new BigDecimal(tankOfLiquidLevel).multiply(new BigDecimal("0.4"))
//                .setScale(1, BigDecimal.ROUND_HALF_UP).floatValue());
//    }
//
//    public String getLongitudeUnitCov(){
//        if (Invalid.DWORD == longitude) {
//            return Invalid.SHOW;
//        }
//        return String.valueOf(new BigDecimal(longitude).divide(new BigDecimal(1000000), 6, BigDecimal.ROUND_HALF_UP).doubleValue());
//    }
//
//    public String getLatitudeUnitCov(){
//        if (Invalid.DWORD == longitude) {
//            return Invalid.SHOW;
//        }
//        return String.valueOf(new BigDecimal(latitude).divide(new BigDecimal(1000000), 6, BigDecimal.ROUND_HALF_UP).doubleValue());
//    }
//
//    public String getLongitudeCov() {
//        if (Invalid.DWORD == longitude) {
//            return Invalid.SHOW;
//        }
//        double lon = new BigDecimal(longitude).divide(new BigDecimal(1000000), 6, BigDecimal.ROUND_HALF_UP).doubleValue();
//        double lat = new BigDecimal(latitude).divide(new BigDecimal(1000000), 6, BigDecimal.ROUND_HALF_UP).doubleValue();
//        double[] loc = GPSUtil.gps84_To_Gcj02(lat, lon);
//        return String.valueOf(loc[1]);
//    }
//
//    public String getLatitudeCov() {
//        if (Invalid.DWORD == latitude) {
//            return Invalid.SHOW;
//        }
//        double lon = new BigDecimal(longitude).divide(new BigDecimal(1000000), 6, BigDecimal.ROUND_HALF_UP).doubleValue();
//        double lat = new BigDecimal(latitude).divide(new BigDecimal(1000000), 6, BigDecimal.ROUND_HALF_UP).doubleValue();
//        double[] loc = GPSUtil.gps84_To_Gcj02(lat, lon);
//        return String.valueOf(loc[0]);
//    }
//
//    public String getTotalMileageCov() {
//        if (Invalid.DWORD == totalMileage) {
//            return Invalid.SHOW;
//        }
//        return String.valueOf(new BigDecimal(totalMileage).divide(new BigDecimal(10), 1, BigDecimal.ROUND_HALF_UP).doubleValue());
//    }
//
//}
