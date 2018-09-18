package com.honglu.quickcall.task.entity;

import java.util.Date;

public class DateIntegral {
    private Integer id;

    private Integer personid;

    private Date cretaetime;

    private Integer presentlove;

    private Integer presentscore;

    private Integer servetime;

    private Integer servescore;

    private Integer creditscore;

    private Integer staus;
    private Integer isAdd;

    public Integer getIsAdd() {
		return isAdd;
	}

	public void setIsAdd(Integer isAdd) {
		this.isAdd = isAdd;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPersonid() {
        return personid;
    }

    public void setPersonid(Integer personid) {
        this.personid = personid;
    }

    public Date getCretaetime() {
        return cretaetime;
    }

    public void setCretaetime(Date cretaetime) {
        this.cretaetime = cretaetime;
    }

    public Integer getPresentlove() {
        return presentlove;
    }

    public void setPresentlove(Integer presentlove) {
        this.presentlove = presentlove;
    }



    public Integer getPresentscore() {
		return presentscore;
	}

	public void setPresentscore(Integer presentscore) {
		this.presentscore = presentscore;
	}

	public Integer getServescore() {
		return servescore;
	}

	public void setServescore(Integer servescore) {
		this.servescore = servescore;
	}

	public Integer getServetime() {
        return servetime;
    }

    public void setServetime(Integer servetime) {
        this.servetime = servetime;
    }



    public Integer getCreditscore() {
        return creditscore;
    }

    public void setCreditscore(Integer creditscore) {
        this.creditscore = creditscore;
    }

    public Integer getStaus() {
        return staus;
    }

    public void setStaus(Integer staus) {
        this.staus = staus;
    }
}