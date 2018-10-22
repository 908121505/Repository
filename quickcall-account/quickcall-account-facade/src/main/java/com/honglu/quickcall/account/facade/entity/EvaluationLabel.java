package com.honglu.quickcall.account.facade.entity;

public class EvaluationLabel {
    private Integer labelId;
    private String labelName;
    private String borderColor;

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

}