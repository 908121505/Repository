package com.honglu.quickcall.account.facade.entity;

import java.util.Date;

public class Advertisement {
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 广告标题
     */
    private String title;

    /**
     * 广告名称
     */
    private String name;

    /**
     * 广告描述
     */
    private String adDescrible;

    /**
     * 广告类型(0=弹窗广告)
     */
    private Byte type;

    /**
     * 广告状态(0=未启用,1=启用)
     */
    private Byte adStatus;

    /**
     * 图片链接地址
     */
    private String imageUrl;

    /**
     * 跳转地址
     */
    private String url;

    /**
     * 版本
     */
    private String appVersion;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 创建人
     */
    private String createMan;

    /**
     * 修改人
     */
    private String modifyMan;

    /**
     * 备注
     */
    private String remark;

    /**
     * 主键ID
     * @return id 主键ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键ID
     * @param id 主键ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 广告标题
     * @return title 广告标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 广告标题
     * @param title 广告标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 广告名称
     * @return name 广告名称
     */
    public String getName() {
        return name;
    }

    /**
     * 广告名称
     * @param name 广告名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 广告描述
     * @return ad_describle 广告描述
     */
    public String getAdDescrible() {
        return adDescrible;
    }

    /**
     * 广告描述
     * @param adDescrible 广告描述
     */
    public void setAdDescrible(String adDescrible) {
        this.adDescrible = adDescrible == null ? null : adDescrible.trim();
    }

    /**
     * 广告类型(0=弹窗广告)
     * @return type 广告类型(0=弹窗广告)
     */
    public Byte getType() {
        return type;
    }

    /**
     * 广告类型(0=弹窗广告)
     * @param type 广告类型(0=弹窗广告)
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 广告状态(0=未启用,1=启用)
     * @return ad_status 广告状态(0=未启用,1=启用)
     */
    public Byte getAdStatus() {
        return adStatus;
    }

    /**
     * 广告状态(0=未启用,1=启用)
     * @param adStatus 广告状态(0=未启用,1=启用)
     */
    public void setAdStatus(Byte adStatus) {
        this.adStatus = adStatus;
    }

    /**
     * 图片链接地址
     * @return image_url 图片链接地址
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 图片链接地址
     * @param imageUrl 图片链接地址
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    /**
     * 跳转地址
     * @return url 跳转地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 跳转地址
     * @param url 跳转地址
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 版本
     * @return app_version 版本
     */
    public String getAppVersion() {
        return appVersion;
    }

    /**
     * 版本
     * @param appVersion 版本
     */
    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion == null ? null : appVersion.trim();
    }

    /**
     * 开始时间
     * @return start_time 开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 开始时间
     * @param startTime 开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 结束时间
     * @return end_time 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 结束时间
     * @param endTime 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改时间
     * @return modify_time 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 创建人
     * @return create_man 创建人
     */
    public String getCreateMan() {
        return createMan;
    }

    /**
     * 创建人
     * @param createMan 创建人
     */
    public void setCreateMan(String createMan) {
        this.createMan = createMan == null ? null : createMan.trim();
    }

    /**
     * 修改人
     * @return modify_man 修改人
     */
    public String getModifyMan() {
        return modifyMan;
    }

    /**
     * 修改人
     * @param modifyMan 修改人
     */
    public void setModifyMan(String modifyMan) {
        this.modifyMan = modifyMan == null ? null : modifyMan.trim();
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}