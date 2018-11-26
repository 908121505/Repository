package com.honglu.quickcall.account.facade.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 弹幕消息页面对象
 *
 * @author duanjun
 * @date 2018-10-18 9:51
 */
public class BarrageMessageVO implements Serializable {

    private static final long serialVersionUID = -7160838423061020263L;
    /**
     * 用户昵称
     **/
    private String nickName;
    /**
     * 用户头像
     **/
    private String headPortraitUrl;
    /**
     * 技能ID
     */
    private Long skillId;
    /**
     * 订单金额
     */
    private BigDecimal orderAmounts;
    /**
     * 服务名称
     */
    private String productName;
    /**
     * 下单时间
     */
    private Date orderTime;

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

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public BigDecimal getOrderAmounts() {
        return orderAmounts;
    }

    public void setOrderAmounts(BigDecimal orderAmounts) {
        this.orderAmounts = orderAmounts;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public String toString() {
        return "BarrageMessageVO{" +
                "nickName='" + nickName + '\'' +
                ", headPortraitUrl='" + headPortraitUrl + '\'' +
                ", skillId=" + skillId +
                ", orderAmounts=" + orderAmounts +
                ", productName='" + productName + '\'' +
                ", orderTime=" + orderTime +
                '}';
    }
}
