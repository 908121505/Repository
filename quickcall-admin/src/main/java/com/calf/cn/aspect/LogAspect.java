package com.calf.cn.aspect;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.calf.cn.aspect.MethodLog;
import com.calf.cn.service.BaseManager;
import com.calf.cn.thread.SysLogThread;
import com.calf.cn.utils.DateUtil;
import com.calf.cn.utils.MacUtil;
import com.calf.cn.utils.SearchUtil;
import com.calf.module.system.entity.SysLog;

/**
 * AOP 日志工具类
 * 
 * @author zhoujain 
 *
 */
@Aspect
@Component
public class LogAspect {

	@Autowired
	private BaseManager baseManager;
	@Autowired
	private HttpServletRequest request;
	
	@SuppressWarnings("unchecked")
	@Around("execution(* com.calf.module..*(..)) && @annotation(methodLog)")
	public Object around(ProceedingJoinPoint point,MethodLog methodLog) throws Throwable{
		String create_time = DateUtil.dateFormat();
		String ip_addr = MacUtil.getIpAddr(request);
		Subject currentUser = SecurityUtils.getSubject();
		String oper_param = SearchUtil.convertorEntitysToMap(request.getParameterMap()).toString();
		
		SysLog syslog = new SysLog((String)currentUser.getPrincipal(), methodLog.operType(), request.getRequestURI(), 
				oper_param, ip_addr, create_time);
		
		SysLogThread logThread = new SysLogThread();
		logThread.setBaseManager(baseManager);
		logThread.setSyslog(syslog);
		new Thread(logThread).start();
		
		return point.proceed();
	}

}