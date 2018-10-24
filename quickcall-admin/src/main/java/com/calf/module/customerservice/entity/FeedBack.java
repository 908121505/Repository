package com.calf.module.customerservice.entity;


/**
 * 用户反馈表bean
 * @author zhaozheyi
 * @date Wed Oct 24 15:43:12 CST 2018
 **/
public class FeedBack {

	/**主键ID**/
	private Integer id;

	/**反馈人ID**/
	private Long customerId;

	/**反馈内容**/
	private String feedBackContent;

	/**处理状态(0=未处理,1=已处理)**/
	private Integer handleStatus;

	/**处理结果**/
	private String handleResult;

	/**创建时间**/
	private String createTime;

	/**联系方式**/
	private String contactWay;

	/**修改时间**/
	private String modifyTime;

	/**处理时间**/
	private String handleTime;

	/**创建人**/
	private String createMan;

	/**修改人**/
	private String modifyMan;

	/**处理人**/
	private String handleUser;

	/**备注**/
	private String remark;


	public FeedBack() {
		super();
	}
	public FeedBack(Integer id,Long customerId,String feedBackContent,Integer handleStatus,String handleResult,String createTime,String contactWay,String modifyTime,String handleTime,String createMan,String modifyMan,String handleUser,String remark) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.feedBackContent = feedBackContent;
		this.handleStatus = handleStatus;
		this.handleResult = handleResult;
		this.createTime = createTime;
		this.contactWay = contactWay;
		this.modifyTime = modifyTime;
		this.handleTime = handleTime;
		this.createMan = createMan;
		this.modifyMan = modifyMan;
		this.handleUser = handleUser;
		this.remark = remark;
	}
	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setCustomerId(Long customerId){
		this.customerId = customerId;
	}

	public Long getCustomerId(){
		return this.customerId;
	}

	public void setFeedBackContent(String feedBackContent){
		this.feedBackContent = feedBackContent;
	}

	public String getFeedBackContent(){
		return this.feedBackContent;
	}

	public void setHandleStatus(Integer handleStatus){
		this.handleStatus = handleStatus;
	}

	public Integer getHandleStatus(){
		return this.handleStatus;
	}

	public void setHandleResult(String handleResult){
		this.handleResult = handleResult;
	}

	public String getHandleResult(){
		return this.handleResult;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getCreateTime(){
		return this.createTime;
	}

	public void setContactWay(String contactWay){
		this.contactWay = contactWay;
	}

	public String getContactWay(){
		return this.contactWay;
	}

	public void setModifyTime(String modifyTime){
		this.modifyTime = modifyTime;
	}

	public String getModifyTime(){
		return this.modifyTime;
	}

	public void setHandleTime(String handleTime){
		this.handleTime = handleTime;
	}

	public String getHandleTime(){
		return this.handleTime;
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

	public void setHandleUser(String handleUser){
		this.handleUser = handleUser;
	}

	public String getHandleUser(){
		return this.handleUser;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

}
