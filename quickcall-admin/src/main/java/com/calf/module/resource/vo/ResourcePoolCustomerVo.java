package com.calf.module.resource.vo;

public class ResourcePoolCustomerVo {
    private Integer id;//ID

    private Integer resourcePoolId;//资源池ID'

    private Integer customerId;//客户号

    private String appId;//客户端 对外 显示的Id

    private Integer status;//状态(0=不可用，1=可用)

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResourcePoolId() {
        return resourcePoolId;
    }

    public void setResourcePoolId(Integer resourcePoolId) {
        this.resourcePoolId = resourcePoolId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}