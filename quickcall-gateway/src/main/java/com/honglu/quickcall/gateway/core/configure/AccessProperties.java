package com.honglu.quickcall.gateway.core.configure;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@Configuration
//@ConfigurationProperties(
//    prefix = "api.security.access"
//)
@Component
public class AccessProperties {
    public static final String API_SECURITY_ACCESS_PREFIX = "api.security.access";
    private List<String> ignoreTokenUriList = new ArrayList<>();
    private List<String> noneWrapUriList;
    private List<String> noneCheckHeaderList;

    public AccessProperties() {
    }

    public List<String> getIgnoreTokenUriList() {
        return this.ignoreTokenUriList;
    }

    public void setIgnoreTokenUriList(List<String> ignoreTokenUriList) {
        this.ignoreTokenUriList = ignoreTokenUriList;
    }

    public List<String> getNoneWrapUriList() {
        return this.noneWrapUriList;
    }

    public void setNoneWrapUriList(List<String> noneWrapUriList) {
        this.noneWrapUriList = noneWrapUriList;
    }

    public List<String> getNoneCheckHeaderList() {
        return this.noneCheckHeaderList;
    }

    public void setNoneCheckHeaderList(List<String> noneCheckHeaderList) {
        this.noneCheckHeaderList = noneCheckHeaderList;
    }
}
