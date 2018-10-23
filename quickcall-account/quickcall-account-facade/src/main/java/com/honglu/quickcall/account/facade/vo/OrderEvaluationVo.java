package com.honglu.quickcall.account.facade.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 订单评价页面数据
 *
 * @author duanjun
 * @date 2018-10-22 14:32
 */
public class OrderEvaluationVo implements Serializable {

    private static final long serialVersionUID = 8520521825521198861L;

    /**
     * 订单ID
     */
    private Long orderId;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户头像
     */
    private String headPortraitUrl;

    /**
     * 评价标签列表
     */
    private List<EvaluationLabel> evaluationLabelList;

    /**
     * 评价标签
     */
    public class EvaluationLabel implements Serializable{
        private static final long serialVersionUID = -5168753120800412L;
        /**
         * 标签Id
         */
        private Integer labelId;
        /**
         * 标签名称
         */
        private String labelName;
        /**
         * 边框颜色
         */
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
            this.labelName = labelName;
        }

        public String getBorderColor() {
            return borderColor;
        }

        public void setBorderColor(String borderColor) {
            this.borderColor = borderColor;
        }
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public List<EvaluationLabel> getEvaluationLabelList() {
        return evaluationLabelList;
    }

    public void setEvaluationLabelList(List<EvaluationLabel> evaluationLabelList) {
        this.evaluationLabelList = evaluationLabelList;
    }
}
