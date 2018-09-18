package com.honglu.quickcall.account.facade.enums;

public enum TransferTypeEnum {
    MF(1,"免费接口(无需财务审核)"),
    SF(2,"收费接口(需财务审核)");


    private Integer type;
    private String desc;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    TransferTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
