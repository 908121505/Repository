package com.calf.module.system.entity;

import java.util.List;

/**
 * 查询分配角色实体类
 * @author guixin
 *
 */
public class QueryOperUserRole {

	private List<SysRole> roles;
	private List<SysRole> selectRoles;
	
	public List<SysRole> getRoles() {
		return roles;
	}
	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}
	public List<SysRole> getSelectRoles() {
		return selectRoles;
	}
	public void setSelectRoles(List<SysRole> selectRoles) {
		this.selectRoles = selectRoles;
	}
	
	
}
