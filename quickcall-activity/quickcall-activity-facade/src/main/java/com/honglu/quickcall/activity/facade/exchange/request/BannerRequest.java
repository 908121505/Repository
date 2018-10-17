package com.honglu.quickcall.activity.facade.exchange.request;

import com.honglu.quickcall.activity.facade.code.ActivityFunctionType;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**
 * Banner 信息查询接口请求对象
 *
 * @author duanjun
 * @date 2018-09-21 17:45
 */
public class BannerRequest extends AbstractRequest {

    /** banner类型：1-首页顶部banner；2-首页中部banner；3-分类页banner； **/
    private Byte bannerType;
    /**
     * 设备类型 0-所有,1-ios,2-andriod
     */
    private Byte deviceType;
    /**
     * app版本
     */
    private String appVersion;
    /**
     * 版本号匹配规则 0-所有,1-大于,2-小于,3-等于,4-大于等于,5-小于等于
     */
    private Integer appVersionRule;

    public Byte getBannerType() {
        return bannerType;
    }

    public void setBannerType(Byte bannerType) {
        this.bannerType = bannerType;
    }

    public Byte getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Byte deviceType) {
        this.deviceType = deviceType;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public Integer getAppVersionRule() {
        return appVersionRule;
    }

    public void setAppVersionRule(Integer appVersionRule) {
        this.appVersionRule = appVersionRule;
    }

    @Override
    public String getBizCode() {
        return ActivityFunctionType.QUERY_BANNER;
    }
}
