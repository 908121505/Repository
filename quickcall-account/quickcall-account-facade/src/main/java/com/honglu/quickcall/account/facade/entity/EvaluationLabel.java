package com.honglu.quickcall.account.facade.entity;

public class EvaluationLabel {
    /**
     * 评价Id
     */
    private Long evaluationId;
    /**
     * 订单Id
     */
    private Long orderId;
    /**
     * 技能项ID
     */
    private Long skillItemId;
    /**
     *客户Id
     */
    private Long customerId;
    /**
     * 标签Id
     */
    private Integer labelId;
    private String labelName;
    private String borderColor;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName == null ? null : labelName.trim();
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor == null ? null : borderColor.trim();
    }

    public Long getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Long evaluationId) {
        this.evaluationId = evaluationId;
    }

    public Long getSkillItemId() {
        return skillItemId;
    }

    public void setSkillItemId(Long skillItemId) {
        this.skillItemId = skillItemId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}