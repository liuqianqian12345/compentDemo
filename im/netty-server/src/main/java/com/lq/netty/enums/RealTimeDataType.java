package com.lq.netty.enums;

import com.lq.netty.base.IStatus;
import com.lq.netty.pojo.DataFlowStatus;
import com.lq.netty.pojo.ObdStatus;

/**
 * 实时数据上报中的九组数据
 */
@SuppressWarnings("all")
public enum RealTimeDataType {

    OBD((short)1, "OBD数据", new ObdStatus()),
    ENGINE_DATA((short)2,"发动机数据数据", new DataFlowStatus())
    ;

    private Short id;
    private String desc;
    private IStatus status;

    RealTimeDataType(Short id, String desc, IStatus status) {
        this.id = id;
        this.desc = desc;
        this.status = status;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public IStatus getStatus() {
        return status;
    }

    public void setStatus(IStatus status) {
        this.status = status;
    }

    public static RealTimeDataType valuesOf(short id) {
        for (RealTimeDataType enums : RealTimeDataType.values()) {
            if (enums.getId().shortValue()==id) {
                return enums;
            }
        }
        return null;
    }
}
