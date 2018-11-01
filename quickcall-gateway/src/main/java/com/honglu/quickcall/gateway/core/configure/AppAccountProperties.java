package com.honglu.quickcall.gateway.core.configure;

import org.springframework.stereotype.Component;

import java.util.Map;

//@Configuration
//@ConfigurationProperties(
//    prefix = "api.security.app"
//)
@Component
public class AppAccountProperties {
    public static final String API_SECURITY_APP_PREFIX = "api.security.app";
    private Map<String, AppAccount> accountConfig;

    public AppAccountProperties() {
    }

    public void initAccountList(){

    }

    public Map<String, AppAccount> getAccountConfig() {
        return this.accountConfig;
    }

    public void setAccountConfig(Map<String, AppAccount> accountConfig) {
        this.accountConfig = accountConfig;
    }

    public AppAccount getAppAcount(String deviceType) {
        return this.accountConfig.containsKey(deviceType) ? (AppAccount)this.accountConfig.get(deviceType) : null;
    }
}
