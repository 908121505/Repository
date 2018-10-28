package com.honglu.quickcall.task.entity;

import java.util.Date;

public class SkillItem {

    /**
     * 主键ID(15位时间+4位随机数)
     **/
    private Long id;

    /**
     * 技能项名称
     **/
    private String skillItemName;

    /**
     * 未解锁后的图标
     **/
    private String lockIcon;

    /**
     * 解锁图标
     **/
    private String unlockIcon;

    /**
     * 背景颜色编码（例如：#CCC）
     **/
    private String backColor;

    private String fontColor;

    private Integer skillType;

    /**
     * 显示排序
     **/
    private Integer sort;

    /**
     * 状态(0=不可用，1=可用)
     **/
    private Integer skillStatus;

    /**
     * 描述
     **/
    private String skillDescribe;

    /**
     * 创建时间
     **/
    private Date createTime;

    /**
     * 修改时间
     **/
    private Date modifyTime;

    /**
     * 创建人
     **/
    private String createMan;

    /**
     * 修改人
     **/
    private String modifyMan;

    /**
     * 备注
     **/
    private String remark;

    public SkillItem() {
        super();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setSkillItemName(String skillItemName) {
        this.skillItemName = skillItemName;
    }

    public String getSkillItemName() {
        return this.skillItemName;
    }

    public void setLockIcon(String lockIcon) {
        this.lockIcon = lockIcon;
    }

    public String getLockIcon() {
        return this.lockIcon;
    }

    public void setUnlockIcon(String unlockIcon) {
        this.unlockIcon = unlockIcon;
    }

    public String getUnlockIcon() {
        return this.unlockIcon;
    }

    public void setBackColor(String backColor) {
        this.backColor = backColor;
    }

    public String getBackColor() {
        return this.backColor;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setSkillStatus(Integer skillStatus) {
        this.skillStatus = skillStatus;
    }

    public Integer getSkillStatus() {
        return this.skillStatus;
    }

    public void setSkillDescribe(String skillDescribe) {
        this.skillDescribe = skillDescribe;
    }

    public String getSkillDescribe() {
        return this.skillDescribe;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getModifyTime() {
        return this.modifyTime;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }

    public String getCreateMan() {
        return this.createMan;
    }

    public void setModifyMan(String modifyMan) {
        this.modifyMan = modifyMan;
    }

    public String getModifyMan() {
        return this.modifyMan;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return this.remark;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public Integer getSkillType() {
        return skillType;
    }

    public void setSkillType(Integer skillType) {
        this.skillType = skillType;
    }
}