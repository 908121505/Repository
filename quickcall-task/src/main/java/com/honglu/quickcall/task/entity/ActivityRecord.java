package com.honglu.quickcall.task.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liuqiu
 */
public class ActivityRecord implements Serializable {
    private Integer id;

    private Integer activity_id;

    private Integer task_id;

    private Integer user_id;
    private Integer other_id;

    private String ac_title;

    private Integer schedule;

    private BigDecimal recharge_amount;

    private BigDecimal award_amount;

    private String record;

    private Date create_time;
    private BigDecimal totalRecharge;
    private BigDecimal totalAward;


    private String remark;
    
    private BigDecimal ratio_amount;
    
    private Integer type;//1分享活动，2畅游包
    
    

    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getOther_id() {
		return other_id;
	}

	public void setOther_id(Integer other_id) {
		this.other_id = other_id;
	}

	public BigDecimal getRatio_amount() {
		return ratio_amount;
	}

	public void setRatio_amount(BigDecimal ratio_amount) {
		this.ratio_amount = ratio_amount;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivityId() {
        return activity_id;
    }

    public void setActivityId(Integer activityId) {
        this.activity_id = activityId;
    }

    public Integer getTaskId() {
        return task_id;
    }

    public void setTaskId(Integer taskId) {
        this.task_id = taskId;
    }

    public Integer getUserId() {
        return user_id;
    }

    public void setUserId(Integer userId) {
        this.user_id = userId;
    }

    public String getAcTitle() {
        return ac_title;
    }

    public void setAcTitle(String acTitle) {
        this.ac_title = acTitle == null ? null : acTitle.trim();
    }

    public Integer getSchedule() {
        return schedule;
    }

    public void setSchedule(Integer schedule) {
        this.schedule = schedule;
    }

    public BigDecimal getRechargeAmount() {
        return recharge_amount;
    }

    public void setRechargeAmount(BigDecimal rechargeAmount) {
        this.recharge_amount = rechargeAmount;
    }

    public BigDecimal getAwardAmount() {
        return award_amount;
    }

    public void setAwardAmount(BigDecimal awardAmount) {
        this.award_amount = awardAmount;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record == null ? null : record.trim();
    }

    public Date getCreateTime() {
        return create_time;
    }

    public void setCreateTime(Date createTime) {
        this.create_time = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }



	public BigDecimal getTotalRecharge() {
		return totalRecharge;
	}

	public void setTotalRecharge(BigDecimal totalRecharge) {
		this.totalRecharge = totalRecharge;
	}

	public BigDecimal getTotalAward() {
		return totalAward;
	}

	public void setTotalAward(BigDecimal totalAward) {
		this.totalAward = totalAward;
	}
    
}