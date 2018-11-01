package com.honglu.quickcall.gateway.core.configure;

public class AppAccount {
    private String appId;
    private String appSecret;

    public AppAccount() {
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return this.appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String toString() {
        return "AppAccount{appId='" + this.appId + '\'' + ", appSecret='" + this.appSecret + '\'' + '}';
    }
}
