package com.honglu.quickcall.user.facade.exchange;

import java.io.Serializable;

/**
 * 经验值发送到MQ消息对象
 *
 * @author duanjun
 * @date 2018-10-23 13:09
 */
public class ExperienceSendMq implements Serializable {
    private static final long serialVersionUID = 8298710509385584756L;

    private Long customerId;
    private Integer experience;

    private int bizCode;

    public ExperienceSendMq() {
    }

    public ExperienceSendMq(int bizCode) {
        this.bizCode = bizCode;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public int getBizCode() {
        return bizCode;
    }

    public void setBizCode(int bizCode) {
        this.bizCode = bizCode;
    }
}
