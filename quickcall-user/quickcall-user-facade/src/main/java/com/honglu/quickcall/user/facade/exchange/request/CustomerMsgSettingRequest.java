package com.honglu.quickcall.user.facade.exchange.request;

import java.util.Date;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

public class CustomerMsgSettingRequest extends UserCenterRequest  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5702087654682913731L;

	/**
     * 主键ID(15位时间+4位随机数)
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    private Long id;

    /**
     * 客户ID
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    private Long customerId;

    /**
     * 接收类型(0=接收所有，1=仅接收粉丝)
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    private Integer receiveType;

    /**
     * 用户最小等级阈值,默认1000
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    private Integer rangeMinLimit;

    /**
     * 接收状态(0=开启，1=关闭)
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    private Integer receiveStatus;

    /**
     * 创建时间
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    private Date createTime;

    /**
     * 修改时间
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    private Date modifyTime;

    /**
     * 创建人
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    private String createMan;

    /**
     * 修改人
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    private String modifyMan;

    /**
     * 备注
     * @mbggenerated Fri Nov 02 15:38:25 CST 2018
     */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(Integer receiveType) {
        this.receiveType = receiveType;
    }

    public Integer getRangeMinLimit() {
        return rangeMinLimit;
    }

    public void setRangeMinLimit(Integer rangeMinLimit) {
        this.rangeMinLimit = rangeMinLimit;
    }

    public Integer getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(Integer receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan == null ? null : createMan.trim();
    }

    public String getModifyMan() {
        return modifyMan;
    }

    public void setModifyMan(String modifyMan) {
        this.modifyMan = modifyMan == null ? null : modifyMan.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	@Override
	public String getBizCode() {
		return UserFunctionType.ADD_CUSYOMER_MESSAGE_SETTING;
	}
}