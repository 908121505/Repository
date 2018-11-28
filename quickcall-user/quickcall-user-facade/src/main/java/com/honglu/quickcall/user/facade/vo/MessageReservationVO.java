package com.honglu.quickcall.user.facade.vo;

public class MessageReservationVO {
    /**
     * 主键ID(15位时间+4位随机数)
     */
    private Long id;

    /**
     * 客户ID（普通用户ID）
     */
    private Long customerId;

    /**
     * 客户名字（普通用户）
     */
    private String customerName;

    /**
     * 客户头像
     */
    private String customerIconUrl;

    /**
     * 消息接受者（大V用户ID）
     */
    private Long receiverId;

    /**
     * 大V技能
     */
    private String title;


    /**
     * 大V技能图标
     */
    private String skillIconUrl;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 单价：10音符/3小时
     */
    private String priceUnit;

    /**
     * 单价倍数
     */
    private String priceUnitTimeCount;

    /**
     * 0.未读，1.已读
     */
    private Byte status;

    /**
     * 创建时间
     */
    private String createTime;


    /**
     * 备注
     */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerIconUrl() {
        return customerIconUrl;
    }

    public void setCustomerIconUrl(String customerIconUrl) {
        this.customerIconUrl = customerIconUrl;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSkillIconUrl() {
        return skillIconUrl;
    }

    public void setSkillIconUrl(String skillIconUrl) {
        this.skillIconUrl = skillIconUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public String getPriceUnitTimeCount() {
        return priceUnitTimeCount;
    }

    public void setPriceUnitTimeCount(String priceUnitTimeCount) {
        this.priceUnitTimeCount = priceUnitTimeCount;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}