package com.calf.module.customer.entity;

import java.util.Date;
import java.util.List;

public class CustomerVo {
    private String customerId; //用户对内ID

    private String appId;      //用户对外ID；

    private String nickName;   //用户昵称

    private Integer type;      //用户类型（0=普通用户，1=大V）

    private String frontPortraitUrl; //证件正面照

    private List<String> customerSkills; //用户的技能

    private Integer custStatus;  //用户状态（1=正常,4=已封禁-无法接单,6=已封禁-无法接指定技能,8=已封禁-账户登录权限,10=已封禁-设备登录权限）

    private Date blockStartTime; //封禁开始时间

    private Date blockEndTime;  //封禁结束时间

    private String formatString;

    private String operate; //0 解禁 1 分禁

    private String closureDate; //封禁时间

    public String getClosureDate() {
        return closureDate;
    }

    public void setClosureDate(String closureDate) {
        this.closureDate = closureDate;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getFormatString(){
        if(customerSkills.size()>0){
            StringBuilder builder = new StringBuilder();
            for(String s:customerSkills){
                builder.append(s);
                builder.append("、");
            }
            builder.deleteCharAt(builder.lastIndexOf("、"));
            return builder.toString();
        }else{
            return "-";
        }
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getFrontPortraitUrl() {
        return frontPortraitUrl;
    }

    public void setFrontPortraitUrl(String frontPortraitUrl) {
        this.frontPortraitUrl = frontPortraitUrl;
    }

    public Integer getCustStatus() {
        return custStatus;
    }

    public void setCustStatus(Integer custStatus) {
        this.custStatus = custStatus;
    }

    public Date getBlockStartTime() {
        return blockStartTime;
    }

    public void setBlockStartTime(Date blockStartTime) {
        this.blockStartTime = blockStartTime;
    }

    public Date getBlockEndTime() {
        if(custStatus == -1){
            return null;
        }
        return blockEndTime;
    }

    public void setBlockEndTime(Date blockEndTime) {
        this.blockEndTime = blockEndTime;
    }

    public List<String> getCustomerSkills() {
        return customerSkills;
    }

    public void setCustomerSkills(List<String> customerSkills) {
        this.customerSkills = customerSkills;
    }

}
