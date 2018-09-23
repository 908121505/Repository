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
     * 获取客户的真是IP地址
     *
     * @param request
     * @return
     */
    public String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? null : ip;
    }

}
