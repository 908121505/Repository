package com.calf.module.system.entity;

/**
 * Created by bruce on 2017/5/12.
 */
public class BizParam {

    private String bizParamId;
    private String bizParamValue;
    private String bizParamName;
    private String bizParamCode;
    private Integer state;
    private String createTime;
    private String createMan;
    private String modifyTime;
    private String modifyMan;
    private String remark;
    private String bizParamTypeId;
    private String bizParamTypeName;
    

    

	

    public String getBizParamTypeName() {
		return bizParamTypeName;
	}

	public void setBizParamTypeName(String bizParamTypeName) {
		this.bizParamTypeName = bizParamTypeName;
	}

	public String getBizParamTypeId() {
		return bizParamTypeId;
	}

	public void setBizParamTypeId(String bizParamTypeId) {
		this.bizParamTypeId = bizParamTypeId;
	}

	public String getBizParamId() {
		return bizParamId;
	}

	public void setBizParamId(String bizParamId) {
		this.bizParamId = bizParamId;
	}

	public String getBizParamValue() {
		return bizParamValue;
	}

	public void setBizParamValue(String bizParamValue) {
		this.bizParamValue = bizParamValue;
	}

	public String getBizParamName() {
		return bizParamName;
	}

	public void setBizParamName(String bizParamName) {
		this.bizParamName = bizParamName;
	}

	public String getBizParamCode() {
		return bizParamCode;
	}

	public void setBizParamCode(String bizParamCode) {
		this.bizParamCode = bizParamCode;
	}

	public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyMan() {
        return modifyMan;
    }

    public void setModifyMan(String modifyMan) {
        this.modifyMan = modifyMan;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
