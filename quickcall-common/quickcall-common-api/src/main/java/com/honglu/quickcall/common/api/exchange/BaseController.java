package com.honglu.quickcall.common.api.exchange;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.honglu.quickcall.common.api.util.HttpUtils;


/**
 * Created by bruce on 2017/4/26.
 */
public class BaseController  {


    protected <T> T getParams(HttpServletRequest hsr, Class<T> t) throws IOException {
        return HttpUtils.getReqT(hsr, t);
    }

    /**
     * 获取客户的真实IP地址
     *
     * @param request
     * @return
     */
    public String getRemoteHost(HttpServletRequest request) {
    	String ip = request.getHeader("X-Forwarded-For");
		if (isEffective(ip) && ip.indexOf(",") > -1) {
			String[] array = ip.split(",");
			for (String str : array) {
				if (isEffective(str)) {
					ip = str;
					break;
				}
			}
		}
		if (!isEffective(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (!isEffective(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (!isEffective(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (!isEffective(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (!isEffective(ip)) {
			ip = request.getRemoteAddr();
		}
        return ip.equals("0:0:0:0:0:0:0:1") ? null : ip;
    }
    
    //判断IP是否为空
    private boolean isEffective(String remoteAddr) {
		if ((null != remoteAddr) && (!"".equals(remoteAddr.trim()))
				&& (!"unknown".equalsIgnoreCase(remoteAddr.trim()))) {
			return true;
		}
		return false;
	}

}
