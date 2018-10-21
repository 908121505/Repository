package com.calf.module.appconfig.entity;

import java.util.Date;

public class EvaluationLabel {
    private Integer labelId;
    private String skillItemId;
    private Byte customerSex;
    private String labelName;
    private String borderColor;
    private Date createTime;
    private Date modifyTime;
    private String createMan;
    private String modifyMan;

    /** 扩展查询字段 **/
    private String skillItemName;

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public String getSkillItemId() {
        return skillItemId;
    }

    public void setSkillItemId(String skillItemId) {
        this.skillItemId = skillItemId;
    }

    public Byte getCustomerSex() {
        return customerSex;
    }

    public void setCustomerSex(Byte customerSex) {
        this.customerSex = customerSex;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan == null ? null : createMan.trim();
    }

    public String getModifyMan() {
        return modifyMan;
    }

    public void setModifyMan(String modifyMan) {
        this.modifyMan = modifyMan == null ? null : modifyMan.trim();
    }

    public String getSkillItemName() {
        return skillItemName;
    }

    public void setSkillItemName(String skillItemName) {
        this.skillItemName = skillItemName;
    }
}