package com.calf.module.system.entity;

import java.io.Serializable;

public class SysMenu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String names;//菜单名称
	private String url;//访问地址
	private String img_icon;//菜单ICON
	private int orders;//显示顺序
	private Long parent_id;//父级菜单
	private int checked;//
	private String author;//权限编码
	private String menu_type;//菜单类别
	private String menu_authority;
	private String menuState;
	
	public String getMenu_authority() {
		return menu_authority;
	}
	public void setMenu_authority(String menu_authority) {
		this.menu_authority = menu_authority;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getImg_icon() {
		return img_icon;
	}
	public void setImg_icon(String img_icon) {
		this.img_icon = img_icon;
	}
	public int getOrders() {
		return orders;
	}
	public void setOrders(int orders) {
		this.orders = orders;
	}
	public Long getParent_id() {
		return parent_id;
	}
	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}
	public int getChecked() {
		return checked;
	}
	public void setChecked(int checked) {
		this.checked = checked;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getMenu_type() {
		return menu_type;
	}
	public void setMenu_type(String menu_type) {
		this.menu_type = menu_type;
	}
	public String getMenuState() {
		return menuState;
	}
	public void setMenuState(String menuState) {
		this.menuState = menuState;
	}
	
	
	
	
}
