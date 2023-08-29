package com.lq.netty.enums;

/**
 * 信息类型标志
 * created by dyy
 */
@SuppressWarnings("all")
public enum InfoType {

    //上行指令
    OBD((short)1, "OBD信息"),
    DATA_FLOW((short)2,"数据流信息")
    ;

    private Short id;
    private String desc;

    InfoType(Short id, String desc) {
        this.id = id;
        this.desc = desc;
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

    public static InfoType valuesOf(Short id) {
        for (InfoType enums : InfoType.values()) {
            if (enums.getId()==id) {
                return enums;
            }
        }
        return null;
    }

    public static InfoType valuesOf(String name) {
        for (InfoType enums : InfoType.values()) {
            if (enums.name().equals(name)) {
                return enums;
            }
        }
        return null;
    }
}
