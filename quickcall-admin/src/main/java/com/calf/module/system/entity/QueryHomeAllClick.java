package com.calf.module.system.entity;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 首页显示全部的点击量和点击率
 * @author guixin
 *
 */
public class QueryHomeAllClick implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codes;
	private String names;
	private String level;
	private int pvNum;
	private int uvNum;
	private float uvPv;
	private int[] senvenDay = new int[7];

	public String getCodes() {
		return codes;
	}
	public void setCodes(String codes) {
		this.codes = codes;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public int getPvNum() {
		return pvNum;
	}
	public void setPvNum(int pvNum) {
		this.pvNum = pvNum;
	}
	public int getUvNum() {
		return uvNum;
	}
	public void setUvNum(int uvNum) {
		this.uvNum = uvNum;
	}
	public float getUvPv() {
		return uvPv;
	}
	public void setUvPv(float uvPv) {
		this.uvPv = uvPv;
	}
	public int[] getSenvenDay() {
		return senvenDay;
	}
	public void setSenvenDay(int[] senvenDay) {
		this.senvenDay = senvenDay;
	}
	
	@Override
	public String toString() {
		return "QueryHomeAllClick [codes=" + codes + ", names=" + names
				+ ", level=" + level + ", pvNum=" + pvNum + ", uvNum=" + uvNum
				+ ", uvPv=" + uvPv + ", senvenDay="
				+ Arrays.toString(senvenDay) + "]";
	}
	
}
