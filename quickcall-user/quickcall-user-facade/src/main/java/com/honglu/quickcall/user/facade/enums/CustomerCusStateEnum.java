package com.honglu.quickcall.user.facade.enums;

/**
 * 用户状态类型
 *
 * @author duanjun
 * @date 2018-09-23 16:55
 */
public enum CustomerCusStateEnum {

    
	OFF_LINE(1, "离线"),
    ON_LINE(2,"在线");

    private Integer type;
    private String msgContent;

    CustomerCusStateEnum(Integer type, String msgContent) {
        this.type = type;
        this.msgContent = msgContent;
    }

    public static String getNameByValue(Integer value) {
        for (PushAppMsgTypeEnum type : PushAppMsgTypeEnum.values()) {
            if (type.getType().equals(value)) {
                return type.getMsgContent();
            }
        }
        return "";
    }

    public Integer getType() {
        return type;
    }

    public String getMsgContent() {
        return msgContent;
    }
}
