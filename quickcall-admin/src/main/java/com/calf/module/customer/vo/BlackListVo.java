package com.calf.module.customer.vo;

/**
 * 拉黑名单vo
 */
public class BlackListVo {
    private String id;//主键ID

    private String customerId;//客户号

    private String nickName;   //用户昵称

    private String phone;//电话

    private String blackCustomerId;//客户号

    private String blackNickName; //用户昵称

    private String blackTime;//被拉黑时间

    private Integer status;//删除状态(0=删除，1=可用)

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBlackCustomerId() {
        return blackCustomerId;
    }

    public void setBlackCustomerId(String blackCustomerId) {
        this.blackCustomerId = blackCustomerId;
    }

    public String getBlackNickName() {
        return blackNickName;
    }

    public void setBlackNickName(String blackNickName) {
        this.blackNickName = blackNickName;
    }

    public String getBlackTime() {
        return blackTime;
    }

    public void setBlackTime(String blackTime) {
        this.blackTime = blackTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}