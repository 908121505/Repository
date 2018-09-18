package com.calf.module.common.entity;

/**
 * 树形菜单显示
 * @author guixin
 *
 */
public class Ztree {

	private String id;
	private String name;
	private String pId;
	private Boolean checked;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	
}
