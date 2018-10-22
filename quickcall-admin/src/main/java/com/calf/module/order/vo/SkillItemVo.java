package com.calf.module.order.vo;

public class SkillItemVo {
    private String id;

    private String skillItemName;

    private String skillDescribe;

    private String imageUrl;

    private Integer sort;

    private Integer skillStatus;

    private String remark;

    private String lockIcon;

    private String unlockIcon;

    private String backColor;

    private String fontColor;

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public String getLockIcon() {
        return lockIcon;
    }

    public void setLockIcon(String lockIcon) {
        this.lockIcon = lockIcon;
    }

    public String getUnlockIcon() {
        return unlockIcon;
    }

    public void setUnlockIcon(String unlockIcon) {
        this.unlockIcon = unlockIcon;
    }

    public String getBackColor() {
        return backColor;
    }

    public void setBackColor(String backColor) {
        this.backColor = backColor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSkillItemName() {
        return skillItemName;
    }

    public void setSkillItemName(String skillItemName) {
        this.skillItemName = skillItemName;
    }

    public String getSkillDescribe() {
        return skillDescribe;
    }

    public void setSkillDescribe(String skillDescribe) {
        this.skillDescribe = skillDescribe;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSkillStatus() {
        return skillStatus;
    }

    public void setSkillStatus(Integer skillStatus) {
        this.skillStatus = skillStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
