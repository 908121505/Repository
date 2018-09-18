package com.honglu.quickcall.task.entity;

import java.util.Date;

public class Userstatestatistics {
    private Integer id;

    private Integer userId;//用户i

    private Integer totaltime;//在线总时长

    private Integer stotaltime;//视频总时间

    private Integer stotalcapital;//总金额

    private Integer ytotaltime;//语音总时间

    private Integer ytotalcapital;//总金额

    private Integer yconnectnum;//语音接通次数

    private Integer yunconnectnum;//语音未接通次数

    private Integer yrefusenum;//语音拒绝次数

    private Integer sconnectnum;//视频接通次数

    private Integer sunconnectnum;//视频未接通次数

    private Integer srefusenum;//视频拒绝次数

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

  

    public Integer getTotaltime() {
		return totaltime;
	}

	public void setTotaltime(Integer totaltime) {
		this.totaltime = totaltime;
	}

	public Integer getStotaltime() {
        return stotaltime;
    }

    public void setStotaltime(Integer stotaltime) {
        this.stotaltime = stotaltime;
    }

    public Integer getStotalcapital() {
        return stotalcapital;
    }

    public void setStotalcapital(Integer stotalcapital) {
        this.stotalcapital = stotalcapital;
    }

 

    public Integer getYtotaltime() {
		return ytotaltime;
	}

	public void setYtotaltime(Integer ytotaltime) {
		this.ytotaltime = ytotaltime;
	}

	public Integer getYtotalcapital() {
        return ytotalcapital;
    }

    public void setYtotalcapital(Integer ytotalcapital) {
        this.ytotalcapital = ytotalcapital;
    }

    public Integer getYconnectnum() {
        return yconnectnum;
    }

    public void setYconnectnum(Integer yconnectnum) {
        this.yconnectnum = yconnectnum;
    }

    public Integer getYunconnectnum() {
        return yunconnectnum;
    }

    public void setYunconnectnum(Integer yunconnectnum) {
        this.yunconnectnum = yunconnectnum;
    }

    public Integer getYrefusenum() {
        return yrefusenum;
    }

    public void setYrefusenum(Integer yrefusenum) {
        this.yrefusenum = yrefusenum;
    }

    public Integer getSconnectnum() {
        return sconnectnum;
    }

    public void setSconnectnum(Integer sconnectnum) {
        this.sconnectnum = sconnectnum;
    }

    public Integer getSunconnectnum() {
        return sunconnectnum;
    }

    public void setSunconnectnum(Integer sunconnectnum) {
        this.sunconnectnum = sunconnectnum;
    }

    public Integer getSrefusenum() {
        return srefusenum;
    }

    public void setSrefusenum(Integer srefusenum) {
        this.srefusenum = srefusenum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@Override
	public String toString() {
		return "Userstatestatistics [id=" + id + ", userId=" + userId + ", totaltime=" + totaltime + ", stotaltime="
				+ stotaltime + ", stotalcapital=" + stotalcapital + ", ytotaltime=" + ytotaltime + ", ytotalcapital="
				+ ytotalcapital + ", yconnectnum=" + yconnectnum + ", yunconnectnum=" + yunconnectnum + ", yrefusenum="
				+ yrefusenum + ", sconnectnum=" + sconnectnum + ", sunconnectnum=" + sunconnectnum + ", srefusenum="
				+ srefusenum + ", createTime=" + createTime + "]";
	}
    
    
}