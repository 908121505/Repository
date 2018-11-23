package com.honglu.quickcall.user.service.util;

/**
 * ip详情实体
 *
 * @author xiangping
 * @date 2018-11-23 15:04
 */
public class IpMessage {
    private Integer code;
    private String desc;
    private String ip;
    private String country;
    private String area;
    private String region;
    private String city;
    private String isp;
    private String countryId;
    private String regionId;
    private String areaId;
    private String cityId;
    private String ispId;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getIspId() {
        return ispId;
    }

    public void setIspId(String ispId) {
        this.ispId = ispId;
    }

    @Override
    public String toString() {
        return "IpMessage{" +
                "code=" + code +
                ", desc='" + desc + '\'' +
                ", ip='" + ip + '\'' +
                ", country='" + country + '\'' +
                ", area='" + area + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", isp='" + isp + '\'' +
                ", countryId='" + countryId + '\'' +
                ", regionId='" + regionId + '\'' +
                ", areaId='" + areaId + '\'' +
                ", cityId='" + cityId + '\'' +
                ", ispId='" + ispId + '\'' +
                '}';
    }
}