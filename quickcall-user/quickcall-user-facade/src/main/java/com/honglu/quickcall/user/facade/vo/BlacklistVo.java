package com.honglu.quickcall.user.facade.vo;

import java.io.Serializable;

/**
 * Description: 查询黑名单
 *
 * @author chenpeng
 * @date 2018/10/21 20:24
 */
public class BlacklistVo implements Serializable {

    private Long id;
    private Long customerId;
    private Long blackCustomerId;
    private String createTime;

    private String nickName;
    private String headPortraitUrl;

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

    public Long getBlackCustomerId() {
        return blackCustomerId;
    }

    public void setBlackCustomerId(Long blackCustomerId) {
        this.blackCustomerId = blackCustomerId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadPortraitUrl() {
        return headPortraitUrl;
    }

    public void setHeadPortraitUrl(String headPortraitUrl) {
        this.headPortraitUrl = headPortraitUrl;
    }

    @Override
    public String toString() {
        return "BlacklistVo{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", blackCustomerId=" + blackCustomerId +
                ", createTime=" + createTime +
                ", nickName='" + nickName + '\'' +
                ", headPortraitUrl='" + headPortraitUrl + '\'' +
                '}';
    }
}
