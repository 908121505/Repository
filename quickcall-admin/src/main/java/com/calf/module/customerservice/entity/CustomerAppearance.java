package com.calf.module.customerservice.entity;


import java.util.Date;

/**
 * 客户相关照片表bean
 * @author luoyanchong
 * @date Sun Oct 21 11:10:37 CST 2018
 **/
public class CustomerAppearance {

	/**主键id(15位时间+4位随机数)**/
	private String id;

	/**客户ID**/
	private String customerId;

	private String nickName;
	/**手机号**/
	private String phone;

	/**待审核照片**/
	private String auditAppearance;

	/**照片（正在使用的）**/
	private String appearance;

	/**0=形象照,1=头像照,2=声鉴卡**/
	private Integer type;

	/**审核状态 0=待审核,1=已通过,2=已拒绝**/
	private Integer auditStatus;

	/**排序（暂时没用）**/
	private Integer sort;

	/**状态（0=不可用，1=可用）**/
	private Integer status;

	/**创建时间**/
	private Date createTime;

	/**更新时间**/
	private Date modifyTime;

	/**创建人**/
	private String createMan;

	/**更新人**/
	private String modifyMan;

	/**备注**/
	private String remark;

	public CustomerAppearance() {
	}

	public CustomerAppearance(String id, String customerId, String nickName, String auditAppearance, String appearance, Integer type, Integer auditStatus, Integer sort, Integer status, Date createTime, Date modifyTime, String createMan, String modifyMan, String remark,String phone) {
		this.id = id;
		this.customerId = customerId;
		this.nickName = nickName;
		this.auditAppearance = auditAppearance;
		this.appearance = appearance;
		this.type = type;
		this.auditStatus = auditStatus;
		this.sort = sort;
		this.status = status;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.createMan = createMan;
		this.modifyMan = modifyMan;
		this.remark = remark;
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAuditAppearance() {
		return auditAppearance;
	}

	public void setAuditAppearance(String auditAppearance) {
		this.auditAppearance = auditAppearance;
	}

	public String getAppearance() {
		return appearance;
	}

	public void setAppearance(String appearance) {
		this.appearance = appearance;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
		this.createMan = createMan;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
