package com.calf.module.export.entity;

import org.apache.commons.lang.StringUtils;

/**
 * 导出播放记录
 * @author guixin
 *
 */
public class ExportPlayData {
	private String app_code;//项目标识（appid），北京发放
	private String customer_code;//用户Id，对应customer_info表id
	private String course_code;//课程编号
	private String unit_code;//课程编号，可为空
	private String ware_code; //课件编号
	private String play_time;//播放开始时间
	private String end_time;//播放结束时间
	private Integer play_times;//播放时长，获取不到播放时长的项目，播放时长=播放开始时间-播放结束时间
	
	public String getApp_code() {
		return app_code;
	}
	public void setApp_code(String app_code) {
		this.app_code = app_code;
	}
	public String getCustomer_code() {
		return customer_code;
	}
	public void setCustomer_code(String customer_code) {
		this.customer_code = customer_code;
	}
	public String getCourse_code() {
		return course_code;
	}
	public void setCourse_code(String course_code) {
		this.course_code = course_code;
	}
	public String getUnit_code() {
		return unit_code;
	}
	public void setUnit_code(String unit_code) {
		this.unit_code = unit_code;
	}
	public String getWare_code() {
		return ware_code;
	}
	public void setWare_code(String ware_code) {
		this.ware_code = ware_code;
	}
	public String getPlay_time() {
		return play_time;
	}
	public void setPlay_time(String play_time) {
		this.play_time = play_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public Integer getPlay_times() {
		return play_times;
	}
	public void setPlay_times(Integer play_times) {
		this.play_times = play_times;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if(StringUtils.isBlank(end_time))
			end_time = "\\N";
		if(StringUtils.isBlank(unit_code))
			unit_code = "\\N";
		return String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%d\r\n", app_code,customer_code,course_code,unit_code,ware_code,play_time,end_time,play_times);
	}
}
