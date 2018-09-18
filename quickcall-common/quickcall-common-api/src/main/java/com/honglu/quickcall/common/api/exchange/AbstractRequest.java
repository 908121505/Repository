package com.honglu.quickcall.common.api.exchange;

import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.code.ServiceCode;
import com.honglu.quickcall.common.api.code.SourceCode;
import com.honglu.quickcall.common.api.util.SystemUtils;

/**
 * Created by len.song on 2017-12-07.
 */
public abstract class AbstractRequest extends AbstractModel {
    private ServiceCode service;                //当前服务编码 例如 用户中心 MyServiceCode.USER
    private SourceCode source;                  //当前资源地址 例如 是 用户中的API调用，则写 SourceCode.OpenApi
    private String localIp = SystemUtils.getLocalIp();
    private String hostName = SystemUtils.getHostName();

    public AbstractRequest() {
    }

    public String getLocalIp() {
        return this.localIp;
    }

    public void setLocalIp(String localIp) {
        this.localIp = localIp;
    }

    public String getHostName() {
        return this.hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public ServiceCode getService() {
        return this.service;
    }

    public void setService(ServiceCode service) {
        this.service = service;
    }

    public SourceCode getSource() {
        return this.source;
    }

    public void setSource(SourceCode source) {
        this.source = source;
    }

    public abstract String getBizCode() ;

}
