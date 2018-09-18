package com.honglu.quickcall.activity.facade.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liuqiu
 */
public class Activity implements Serializable {
    private Integer id;

    private String ac_title;

    private String ac_content;

    private Date ac_begin_time;

    private Date ac_end_time;

    private String ac_pic_one;

    private String ac_pic_two;

    private String ac_pic_three;

    private String ac_url;

    private Integer ac_type;

    private Integer ac_sex;

    private Integer ac_state;

    private Date create_time;

    private String create_man;

    private String remark;

    private Integer check_number;

    private Integer return_money;

    private Integer participants_amount;

    private BigDecimal activity_money;
    private String ac_uuid;


    public String getAc_uuid() {
		return ac_uuid;
	}

	public void setAc_uuid(String ac_uuid) {
		this.ac_uuid = ac_uuid;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAcTitle() {
        return ac_title;
    }

    public void setAcTitle(String acTitle) {
        this.ac_title = acTitle == null ? null : acTitle.trim();
    }

    public String getAcContent() {
        return ac_content;
    }

    public void setAcContent(String acContent) {
        this.ac_content = acContent == null ? null : acContent.trim();
    }

    public Date getAcBeginTime() {
        return ac_begin_time;
    }

    public void setAcBeginTime(Date acBeginTime) {
        this.ac_begin_time = acBeginTime;
    }

    public Date getAcEndTime() {
        return ac_end_time;
    }

    public void setAcEndTime(Date acEndTime) {
        this.ac_end_time = acEndTime;
    }

    public String getAcPicOne() {
        return ac_pic_one;
    }

    public void setAcPicOne(String acPicOne) {
        this.ac_pic_one = acPicOne == null ? null : acPicOne.trim();
    }

    public String getAcPicTwo() {
        return ac_pic_two;
    }

    public void setAcPicTwo(String acPicTwo) {
        this.ac_pic_two = acPicTwo == null ? null : acPicTwo.trim();
    }

    public String getAcPicThree() {
        return ac_pic_three;
    }

    public void setAcPicThree(String acPicThree) {
        this.ac_pic_three = acPicThree == null ? null : acPicThree.trim();
    }

    public String getAcUrl() {
        return ac_url;
    }

    public void setAcUrl(String acUrl) {
        this.ac_url = acUrl == null ? null : acUrl.trim();
    }

    public Integer getAcType() {
        return ac_type;
    }

    public void setAcType(Integer acType) {
        this.ac_type = acType;
    }

    public Integer getAcSex() {
        return ac_sex;
    }

    public void setAcSex(Integer acSex) {
        this.ac_sex = acSex;
    }

    public Integer getAcState() {
        return ac_state;
    }

    public void setAcState(Integer acState) {
        this.ac_state = acState;
    }

    public Date getCreateTime() {
        return create_time;
    }

    public void setCreateTime(Date createTime) {
        this.create_time = createTime;
    }

    public String getCreateMan() {
        return create_man;
    }

    public void setCreateMan(String createMan) {
        this.create_man = createMan == null ? null : createMan.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getCheckNumber() {
        return check_number;
    }

    public void setCheckNumber(Integer checkNumber) {
        this.check_number = checkNumber;
    }

    public Integer getReturnMoneye() {
        return return_money;
    }

    public void setReturnMoney(Integer returnMoney) {
        this.return_money = returnMoney;
    }

	public Integer getParticipants_amount() {
		return participants_amount;
	}

	public void setParticipants_amount(Integer participants_amount) {
		this.participants_amount = participants_amount;
	}

	public BigDecimal getActivity_money() {
		return activity_money;
	}

	public void setActivity_money(BigDecimal activity_money) {
		this.activity_money = activity_money;
	}



    
}