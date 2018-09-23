package com.honglu.quickcall.user.facade.enums;

/**
 * 推送APP消息类型
 *
 * @author duanjun
 * @date 2018-09-23 16:55
 */
public enum PushAppMsgTypeEnum {

    /**
     * 主播端提醒：客户预约新的订单，提醒主播
     */
    NEW_ORDER(1, "【新订单】您有一笔新的预约订单，点击查看详情"),
    /**
     * 主播端提醒：客户申请退款，提醒主播
     */
    REFUND_TIP(2, "【退款提醒】您有一笔订单申请退款，点击查看详情"),
    /**
     * 主播端 + 客户端提醒：订单开始前5分钟，提示主播和客户
     */
    WILL_BE_START(3, "【订单提醒】您有一笔订单即将开始，点击查看详情"),
    /**
     * 客户端提醒：主播取消订单，提醒客户
     */
    CANCEL_ORDER(4, "【订单提醒】您有一笔订单被取消，点击查看详情"),
    /**
     * 客户端提醒：主播申请提前开始服务，提醒客户端
     */
    APPLY_ADVANCED(5, "【新提醒】您有一笔订单申请提前服务，点击查看详情"),
    /**
     * 客户端提醒：主播拒绝退款，提醒客户端
     */
    REFUND_REFUSE(6, "【退款提醒】您的订单申请退款被拒绝，点击查看详情"),
    /**
     * 客户端提醒：申请退款成功，提醒客户端
     */
    REFUND_SUCCESS(7, "【退款提醒】您的订单申请退款成功，点击查看详情"),
    ;

    private Integer type;
    private String msgContent;

    PushAppMsgTypeEnum(Integer type, String msgContent) {
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
