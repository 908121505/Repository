package com.honglu.quickcall.user.facade.entity;


/**
 * 客户形象照表bean
 * @author luoyanchong
 * @date Sat Oct 20 11:28:42 CST 2018
 **/
public class CustomerAppearance {

	/**主键ID**/
	private String id;

	/**客户ID**/
	private Long customerId;

	/**形象照**/
	private String appearance;

	/**形象照审核状态 0=待审核,1=已通过,2=已拒绝**/
	private String appearanceStatus;

	/**排序)**/
	private String sequence;

	/**状态**/
	private String status;

	/**创建时间**/
	private String createTime;

	/**更新时间**/
	private String modifyTime;

	/**创建人**/
	private String createMan;

	/**更新人**/
	private String modifyMan;

	/**备注**/
	private String remark;


	public CustomerAppearance() {
		super();
	}
	public CustomerAppearance(String id,Long customerId,String appearance,String appearanceStatus,String sequence,String status,String createTime,String modifyTime,String createMan,String modifyMan,String remark) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.appearance = appearance;
		this.appearanceStatus = appearanceStatus;
		this.sequence = sequence;
		this.status = status;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.createMan = createMan;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
	}

	public void setCustomerId(Long customerId){
		this.customerId = customerId;
	}

	public Long getCustomerId(){
		return this.customerId;
	}

	public void setAppearance(String appearance){
		this.appearance = appearance;
	}

	public String getAppearance(){
		return this.appearance;
	}

	public void setAppearanceStatus(String appearanceStatus){
		this.appearanceStatus = appearanceStatus;
	}

	public String getAppearanceStatus(){
		return this.appearanceStatus;
	}

	public void setSequence(String sequence){
		this.sequence = sequence;
	}

	public String getSequence(){
		return this.sequence;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return this.status;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getCreateTime(){
		return this.createTime;
	}

	public void setModifyTime(String modifyTime){
		this.modifyTime = modifyTime;
	}

	public String getModifyTime(){
		return this.modifyTime;
	}

	public void setCreateMan(String createMan){
		this.createMan = createMan;
	}

	public String getCreateMan(){
		return this.createMan;
	}

	public void setModifyMan(String modifyMan){
		this.modifyMan = modifyMan;
	}

	public String getModifyMan(){
		return this.modifyMan;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

}
