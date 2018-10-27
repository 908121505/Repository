package com.calf.module.system.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 查询左侧菜单实体类
 * @author Administrator
 *
 */
public class QueryHomeMenu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String names;
	private String url;
	private String img_icon;
	private List<SysMenu> mList;
	
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
	public List<SysMenu> getmList() {
		return mList;
	}
	public void setmList(List<SysMenu> mList) {
		this.mList = mList;
	}	
}
