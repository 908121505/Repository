package com.honglu.quickcall.account.facade.enums;

/**
 * Created by len.song on 2017-12-16.
 * 账户操作的业务类别
 */
public enum AccountBusinessTypeEnum {
    Recharge(1,"充值")
    ,Withdraw(2,"提现")
    ,PullPresent(3,"收礼")
    ,PushPresent(4,"送礼")
    ,VideoIN(5,"视频收入")
    ,VideoOut(6,"视频花费")
    ,VoiceIN(7,"语音收入")
    ,VoiceOut(8,"语音花费")
    ,MessageIn(9,"聊天收入")
    ,MessageOut(10,"聊天花费")
    ,RegisterPresented(11,"注册赠送")
    ,ULockVipVideo(12,"解锁vip视频")
    ,ULockSingleVideoOut(13,"解锁女生单个视频-消费")
    ,ULockSingleVideoIn(14,"解锁女生单个视频-收入")

    ,ChristmasRecharge(15,"双旦充值活动返现-入账收入")
    ,ChristmasTalk(16,"双旦聊天任务返现-入账收入")
    ,SystemDeduct(17,"系统举报扣款")
    ,SystemAward(18,"平台奖励")
    ,WithdrawError(19,"提现转账失败--入账,给账户加钱")
    ,WithdrawSucceed(20,"提现转账成功--出账,只减少冻结金额")
    ,SmashingEggs(21,"砸蛋中奖-入账收入")
    ,ValentineTurnplate(22,"情人节幸运转盘-入账收入")




    //家族账户操作类型
    ,FamilyWithdrawApplication(101,"家族提现申请-出账")
    ,FamilyWithdrawSuccess(102,"家族提现成功-出账")
    ,FamilyWithdrawFailure(103,"家族提现失败-入账")
    ;


    private Integer type;
    private String  desc;

    AccountBusinessTypeEnum(Integer code,String desc){
        this.type = code;
        this.desc = desc;
    }

    public static AccountBusinessTypeEnum fromValue(Integer value) {
        for (AccountBusinessTypeEnum type : AccountBusinessTypeEnum.values()) {
            if (type.getType().equals(value)) {
                return type;
            }
        }
        return null;
    }

    public static String getNameByValue(Integer value) {
        for(AccountBusinessTypeEnum type : AccountBusinessTypeEnum.values()) {
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
