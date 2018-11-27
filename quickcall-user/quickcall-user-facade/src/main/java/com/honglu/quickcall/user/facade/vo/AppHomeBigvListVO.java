package com.honglu.quickcall.user.facade.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 功能描述：首页分类列表信息
 *
 * @author duanjun
 * @date 2018-10-25 14:24
 */
public class AppHomeBigvListVO implements Serializable {

    /**
     * 技能ID
     */
    private Long skillItemId;
    /**
     * 技能名称
     */
    private String skillItemName;

    /**
     * 大V技能列表
     */
    private List<BigvInfoVO> daVinfoList;

    public class BigvInfoVO implements Serializable {

        private static final long serialVersionUID = -743451614902665338L;
        /**
         * 客户Id
         */
        private Long customerId;
        /**
         * 用户技能ID
         */
        private Long customerSkillId;
        /**
         * 运营标签URL
         */
        private String bussTagUrl;
        /**
         * 品类标签URL
         */
        private String categoryTagUrl;
        /**
         * 技能背景
         */
        private String skillBackColor;
        /**
         * 主播昵称
         */
        private String nickName;
        /**
         * 主播性别：性别(0=女,1=男)
         */
        private Integer sex;
        /**
         * 年龄
         */
        private Integer age;
        /**
         * 单价
         */
        private BigDecimal price;
        /**
         * 币种名称
         */
        private String currencyName = "音符";
        /**
         * 服务单位名称
         */
        private String unitName;
        /**
         * 主播封面URL
         */
        private String coverUrl;

        /**
         * 技能分类名称
         */
        private String skillItemName;
        /**
         * 声音时长
         */
        private BigDecimal voiceTime;
        /**
         * 声音URL
         */
        private String voiceUrl;

        public Long getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Long customerId) {
            this.customerId = customerId;
        }

        public Long getCustomerSkillId() {
            return customerSkillId;
        }

        public void setCustomerSkillId(Long customerSkillId) {
            this.customerSkillId = customerSkillId;
        }

        public String getBussTagUrl() {
            return bussTagUrl;
        }

        public void setBussTagUrl(String bussTagUrl) {
            this.bussTagUrl = bussTagUrl;
        }

        public String getCategoryTagUrl() {
            return categoryTagUrl;
        }

        public void setCategoryTagUrl(String categoryTagUrl) {
            this.categoryTagUrl = categoryTagUrl;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public Integer getSex() {
            return sex;
        }

        public void setSex(Integer sex) {
            this.sex = sex;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public String getCurrencyName() {
            return currencyName;
        }

        public void setCurrencyName(String currencyName) {
            this.currencyName = currencyName;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public String getSkillItemName() {
            return skillItemName;
        }

        public void setSkillItemName(String skillItemName) {
            this.skillItemName = skillItemName;
        }

        public BigDecimal getVoiceTime() {
            return voiceTime;
        }

        public void setVoiceTime(BigDecimal voiceTime) {
            this.voiceTime = voiceTime;
        }

        public String getVoiceUrl() {
            return voiceUrl;
        }

        public void setVoiceUrl(String voiceUrl) {
            this.voiceUrl = voiceUrl;
        }

        public String getSkillBackColor() {
            return skillBackColor;
        }

        public void setSkillBackColor(String skillBackColor) {
            this.skillBackColor = skillBackColor;
        }

        @Override
        public String toString() {
            return "BigvInfoVO{" +
                    "customerId=" + customerId +
                    ", customerSkillId=" + customerSkillId +
                    ", bussTagUrl='" + bussTagUrl + '\'' +
                    ", categoryTagUrl='" + categoryTagUrl + '\'' +
                    ", nickName='" + nickName + '\'' +
                    ", sex=" + sex +
                    ", age=" + age +
                    ", price=" + price +
                    ", currencyName='" + currencyName + '\'' +
                    ", unitName='" + unitName + '\'' +
                    ", coverUrl='" + coverUrl + '\'' +
                    ", skillItemName='" + skillItemName + '\'' +
                    ", voiceTime=" + voiceTime +
                    ", voiceUrl='" + voiceUrl + '\'' +
                    '}';
        }
    }

    public Long getSkillItemId() {
        return skillItemId;
    }

    public void setSkillItemId(Long skillItemId) {
        this.skillItemId = skillItemId;
    }

    public String getSkillItemName() {
        return skillItemName;
    }

    public void setSkillItemName(String skillItemName) {
        this.skillItemName = skillItemName;
    }

    public List<BigvInfoVO> getDaVinfoList() {
        return daVinfoList;
    }

    public void setDaVinfoList(List<BigvInfoVO> daVinfoList) {
        this.daVinfoList = daVinfoList;
    }

    @Override
    public String toString() {
        return "AppHomeBigvListVO{" +
                "skillItemId=" + skillItemId +
                ", skillItemName='" + skillItemName + '\'' +
                ", daVinfoList=" + daVinfoList +
                '}';
    }
}
