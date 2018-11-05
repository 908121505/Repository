package com.honglu.quickcall.user.facade.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 客户主页返回数据 -- 页面对象
 *
 * @author duanjun
 * @date 2018-10-20 11:37
 */
public class CustomerHomeVO implements Serializable {

    private static final long serialVersionUID = -976366791856814868L;
    /**
     * 登录客户ID
     */
    private Long loginCustomerId;
    /**
     * 浏览客户ID
     */
    private Long viewCustomerId;
    /**
     * 用户ID
     */
    private String customerAppId;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户头像
     */
    private String headPortraitUrl;
    /**
     * 用户性别：0=女,1=男
     */
    private Integer sex;
    /**
     * 用户年龄
     */
    private Integer age;
    /**
     * 用户等级
     */
    private Integer customerLevel;
    /**
     * 用户签名
     */
    private String signName;
    /**
     * 用户星座
     */
    private String starSign;
    /**
     * 用户是否实名认证：0=未认证,1=待审核,2=已通过,3=拒绝
     */
    private Integer identityStatus;
    /**
     * 大V认证状态：0=未认证,1=待审核,2=已通过,3=拒绝
     */
    private Integer vStatus;
    /**
     * 关注状态：0=未关注,1=已关注
     */
    private Integer attentionStatus = 0;
    /**
     * 客户黑名单状态：0=未拉黑，1=已拉黑，空=游客登录
     */
    private Integer backStatus;
    /**
     * 用户粉丝数
     */
    private Integer fansNum;
    /**
     * 声鉴卡
     */
    private String soundGuideCard;
    /**
     * 用户形象照列表
     */
    private List<String> appearanceUrlList;
    /**
     * 用户兴趣
     */
    private List<String> interestList;

    /**
     * 用户技能列表
     **/
    private List<CustomerSkill> skillList;

    /**
     * 分享标题
     */
    private String shareTitle;
    /**
     * 分享内容
     */
    private String shareContent;
    /**
     * 分享图标链接
     */
    private String shareIconUrl;
    /**
     * 查看原文链接
     */
    private String shareLinkUrl;

    /**
     * 用户技能对象
     */
    public class CustomerSkill implements Serializable{
        private static final long serialVersionUID = 2871296634994569921L;

        /**
         * 客户技能ID
         */
        private Long customerSkillId;
        /**
         * 技能ID
         */
        private Long skillId;
        /**
         * 是否可下单：1是；2否
         */
        private Integer canOrder;
        /**
         * 技能名称
         */
        private String skillName;
        /**
         * 技能背景颜色
         */
        private String skillBackColor;
        /**
         * 技能背字体色
         */
        private String skillFontColor;
        /**
         * 技能声音Url
         */
        private String skillVoiceUrl;
        /**
         * 技能声音时长
         */
        private BigDecimal skillVoiceTime;
        /**
         * 技能价格
         */
        private BigDecimal skillPrice;
        /**
         * 服务单位 - 例子：小时/半小时/次
         */
        private String serviceUnit;
        /**
         * 声量：该技能的综合表现维度
         */
        private Integer skillVolume;
        /**
         * 用户标签 -- 最多的3个标签
         */
        private List<String> customerLabel;
        /**
         * 技能订单数
         */
        private Integer skillOrderNo;

        public Long getCustomerSkillId() {
            return customerSkillId;
        }

        public void setCustomerSkillId(Long customerSkillId) {
            this.customerSkillId = customerSkillId;
        }

        public Long getSkillId() {
            return skillId;
        }

        public void setSkillId(Long skillId) {
            this.skillId = skillId;
        }

        public Integer getCanOrder() {
            return canOrder;
        }

        public void setCanOrder(Integer canOrder) {
            this.canOrder = canOrder;
        }

        public String getSkillName() {
            return skillName;
        }

        public void setSkillName(String skillName) {
            this.skillName = skillName;
        }

        public String getSkillBackColor() {
            return skillBackColor;
        }

        public void setSkillBackColor(String skillBackColor) {
            this.skillBackColor = skillBackColor;
        }

        public String getSkillFontColor() {
            return skillFontColor;
        }

        public void setSkillFontColor(String skillFontColor) {
            this.skillFontColor = skillFontColor;
        }

        public String getSkillVoiceUrl() {
            return skillVoiceUrl;
        }

        public void setSkillVoiceUrl(String skillVoiceUrl) {
            this.skillVoiceUrl = skillVoiceUrl;
        }

        public BigDecimal getSkillVoiceTime() {
            return skillVoiceTime;
        }

        public void setSkillVoiceTime(BigDecimal skillVoiceTime) {
            this.skillVoiceTime = skillVoiceTime;
        }

        public BigDecimal getSkillPrice() {
            return skillPrice;
        }

        public void setSkillPrice(BigDecimal skillPrice) {
            this.skillPrice = skillPrice;
        }

        public String getServiceUnit() {
            return serviceUnit;
        }

        public void setServiceUnit(String serviceUnit) {
            this.serviceUnit = serviceUnit;
        }

        public Integer getSkillVolume() {
            return skillVolume;
        }

        public void setSkillVolume(Integer skillVolume) {
            this.skillVolume = skillVolume;
        }

        public List<String> getCustomerLabel() {
            return customerLabel;
        }

        public void setCustomerLabel(List<String> customerLabel) {
            this.customerLabel = customerLabel;
        }

        public Integer getSkillOrderNo() {
            return skillOrderNo;
        }

        public void setSkillOrderNo(Integer skillOrderNo) {
            this.skillOrderNo = skillOrderNo;
        }

        @Override
        public String toString() {
            return "CustomerSkill{" +
                    "skillId=" + skillId +
                    ", skillName='" + skillName + '\'' +
                    ", skillBackColor='" + skillBackColor + '\'' +
                    ", skillFontColor='" + skillFontColor + '\'' +
                    ", skillVoiceUrl='" + skillVoiceUrl + '\'' +
                    ", skillVoiceTime=" + skillVoiceTime +
                    ", skillPrice=" + skillPrice +
                    ", serviceUnit='" + serviceUnit + '\'' +
                    ", skillVolume=" + skillVolume +
                    ", customerLabel=" + customerLabel +
                    '}';
        }
    }


    public Long getLoginCustomerId() {
        return loginCustomerId;
    }

    public void setLoginCustomerId(Long loginCustomerId) {
        this.loginCustomerId = loginCustomerId;
    }

    public Long getViewCustomerId() {
        return viewCustomerId;
    }

    public void setViewCustomerId(Long viewCustomerId) {
        this.viewCustomerId = viewCustomerId;
    }

    public String getCustomerAppId() {
        return customerAppId;
    }

    public void setCustomerAppId(String customerAppId) {
        this.customerAppId = customerAppId;
    }

    public List<String> getAppearanceUrlList() {
        return appearanceUrlList;
    }

    public void setAppearanceUrlList(List<String> appearanceUrlList) {
        this.appearanceUrlList = appearanceUrlList;
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

    public Integer getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(Integer customerLevel) {
        this.customerLevel = customerLevel;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getStarSign() {
        return starSign;
    }

    public void setStarSign(String starSign) {
        this.starSign = starSign;
    }

    public Integer getIdentityStatus() {
        return identityStatus;
    }

    public void setIdentityStatus(Integer identityStatus) {
        this.identityStatus = identityStatus;
    }

    public Integer getvStatus() {
        return vStatus;
    }

    public void setvStatus(Integer vStatus) {
        this.vStatus = vStatus;
    }

    public Integer getAttentionStatus() {
        return attentionStatus;
    }

    public void setAttentionStatus(Integer attentionStatus) {
        this.attentionStatus = attentionStatus;
    }

    public Integer getBackStatus() {
        return backStatus;
    }

    public void setBackStatus(Integer backStatus) {
        this.backStatus = backStatus;
    }

    public Integer getFansNum() {
        return fansNum;
    }

    public void setFansNum(Integer fansNum) {
        this.fansNum = fansNum;
    }

    public String getSoundGuideCard() {
        return soundGuideCard;
    }

    public void setSoundGuideCard(String soundGuideCard) {
        this.soundGuideCard = soundGuideCard;
    }

    public List<String> getInterestList() {
        return interestList;
    }

    public void setInterestList(List<String> interestList) {
        this.interestList = interestList;
    }

    public List<CustomerSkill> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<CustomerSkill> skillList) {
        this.skillList = skillList;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareContent() {
        return shareContent;
    }

    public void setShareContent(String shareContent) {
        this.shareContent = shareContent;
    }

    public String getShareIconUrl() {
        return shareIconUrl;
    }

    public void setShareIconUrl(String shareIconUrl) {
        this.shareIconUrl = shareIconUrl;
    }

    public String getShareLinkUrl() {
        return shareLinkUrl;
    }

    public void setShareLinkUrl(String shareLinkUrl) {
        this.shareLinkUrl = shareLinkUrl;
    }

    @Override
    public String toString() {
        return "CustomerHomeVO{" +
                "loginCustomerId=" + loginCustomerId +
                ", viewCustomerId=" + viewCustomerId +
                ", customerAppId='" + customerAppId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", headPortraitUrl='" + headPortraitUrl + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", customerLevel=" + customerLevel +
                ", signName='" + signName + '\'' +
                ", starSign='" + starSign + '\'' +
                ", identityStatus=" + identityStatus +
                ", vStatus=" + vStatus +
                ", attentionStatus=" + attentionStatus +
                ", backStatus=" + backStatus +
                ", fansNum=" + fansNum +
                ", soundGuideCard='" + soundGuideCard + '\'' +
                ", appearanceUrlList=" + appearanceUrlList +
                ", interestList=" + interestList +
                ", skillList=" + skillList +
                ", shareTitle='" + shareTitle + '\'' +
                ", shareContent='" + shareContent + '\'' +
                ", shareIconUrl='" + shareIconUrl + '\'' +
                ", shareLinkUrl='" + shareLinkUrl + '\'' +
                '}';
    }
}
