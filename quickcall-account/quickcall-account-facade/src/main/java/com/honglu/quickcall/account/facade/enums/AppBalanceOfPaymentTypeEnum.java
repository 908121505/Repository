package com.honglu.quickcall.account.facade.enums;

/**
 * Created by len.song on 2017-12-18.
 */
public enum AppBalanceOfPaymentTypeEnum {
    CHARACTER(1,"文字聊天")
    ,VOICE(2,"语音聊天")
    ,VIDEO(3,"视频聊天")
    ,GIFT(4,"礼物")
    ,UNLOCK_SINGLE_VIDEO(5,"平台消费-私密视频")
    ,RECHARGE_OR_WITHDRAW(6,"充值提现")
    ,REGISTE_FREE(7,"新人奖励")
    ,UNLOCK_VIDEO(8,"解锁Vip视频专区")

    ,INVITE_REBATE_RECHARGE(9,"邀请好友返利收入")
    ,INVITE_REBATE_INCOME(10,"邀请好友收入")
    ,RECHARGE_REBATE(11,"充值返利-活动")
    ,CHAT_REBATE(12,"聊天赠送-活动")
    ,SystemDeduct(13,"被举报扣款")
    ,SystemAward(14,"平台奖励")
    ,TranError(15,"转账失败")
    ,SmashingEggs(16,"砸蛋奖励-奖励针对男性")
    ,VALENTINE_TURNPLATE(17,"情人节幸运转盘-活动")
    ,HOGMANAY(18,"除夕活动-红包奖励")
    ;


    private Integer type;
    private String  desc;

    AppBalanceOfPaymentTypeEnum(Integer code, String desc){
        this.type = code;
        this.desc = desc;
    }

    public static AppBalanceOfPaymentTypeEnum fromValue(Integer value) {
        for (AppBalanceOfPaymentTypeEnum type : AppBalanceOfPaymentTypeEnum.values()) {
            if (type.getType().equals(value)) {
                return type;
            }
        }
        return null;
    }

    public static String getNameByValue(Integer value) {
        for(AppBalanceOfPaymentTypeEnum type : AppBalanceOfPaymentTypeEnum.values()) {
            if (type.getType().equals(value)) {
                return type.getDesc();
            }
        }
        return "";
    }

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
