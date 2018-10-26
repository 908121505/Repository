package com.calf.module.system.entity;

public class ProjectLog implements Comparable<ProjectLog> {

	private String file_name;//文件名称
	private String file_size;//文件大小
	private long lastModified;//最后修改时间
	
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_size() {
		return file_size;
	}
	public void setFile_size(String file_size) {
		this.file_size = file_size;
	}
	public long getLastModified() {
		return lastModified;
	}
	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}
	
	public ProjectLog(String file_name, String file_size,long lastModified) {
		super();
		this.file_name = file_name;
		this.file_size = file_size;
		this.lastModified = lastModified;
	}
	
	@Override
	public int compareTo(ProjectLog o) {
		// TODO Auto-generated method stub
		if(o.getLastModified() > this.lastModified){
			return 1;
		} else if(o.getLastModified() < this.lastModified){
			return -1;
		} else {
			return 0;
		} 
	}
}
