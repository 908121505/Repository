package com.calf.module.customerservice.vo;


/**
 * 用户举报表bean
 * @author zhaozheyi
 * @date Thu Oct 25 13:23:42 CST 2018
 **/
public class CustomerDelateVO {

	/**主键ID**/
	private Integer id;

	/**被举报人ID**/
	private String customerId;
	
	private String nickName;
	
	private String phone;

	/**举报人ID**/
	private String delateCustId;

	/**举报内容ID**/
	private Integer delateId;

	/**举报内容**/
	private String delateContent;

	/**处理状态(0=未处理,1=已处理)**/
	private Integer handleStatus;

	/**处理结果**/
	private String handleResult;

	/**创建时间**/
	private String createTime;

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


	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setDelateId(Integer delateId){
		this.delateId = delateId;
	}

	public Integer getDelateId(){
		return this.delateId;
	}

	public void setDelateContent(String delateContent){
		this.delateContent = delateContent;
	}

	public String getDelateContent(){
		return this.delateContent;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDelateCustId() {
		return delateCustId;
	}

	public void setDelateCustId(String delateCustId) {
		this.delateCustId = delateCustId;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

}
