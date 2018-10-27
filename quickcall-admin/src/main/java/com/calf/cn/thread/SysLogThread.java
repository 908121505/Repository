package com.calf.cn.thread;

import com.calf.cn.service.BaseManager;
import com.calf.module.system.entity.SysLog;

public class SysLogThread implements Runnable{
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		baseManager.insert(syslog);
	}
	
	private BaseManager baseManager;
	private SysLog syslog;
	public BaseManager getBaseManager() {
		return baseManager;
	}
	public void setBaseManager(BaseManager baseManager) {
		this.baseManager = baseManager;
	}
	public SysLog getSyslog() {
		return syslog;
	}
	public void setSyslog(SysLog syslog) {
		this.syslog = syslog;
	}
	
}
