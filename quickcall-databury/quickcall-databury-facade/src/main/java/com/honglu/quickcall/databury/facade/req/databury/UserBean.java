package com.honglu.quickcall.databury.facade.req.databury;

import java.io.Serializable;

/**
 * @author xiangping
 * @date 2018-11-05 15:32
 */
public class UserBean implements Serializable {
    /**性别**/
    private String gender;
    /**手机号**/
    private String phoneNumber;
    /**出生年份**/
    private String yearOfBirth;
    /**注册ID**/
    private String vc_user_id;
    /**注册渠道**/
    private String registSource;
    /**注册时间**/
    private String registDate;
    /**用户昵称**/
    private String nick;
    /**粉丝量**/
    private String vermicelli;
    /**关注数**/
    private String numberOfCencerns;
    /**用户身份（声优/普通用户）**/
    private String userIdentity;
    /**用户设备（小米8，华为p10...）**/
    private String userEquipment;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getVc_user_id() {
        return vc_user_id;
    }

    public void setVc_user_id(String vc_user_id) {
        this.vc_user_id = vc_user_id;
    }

    public String getRegistSource() {
        return registSource;
    }

    public void setRegistSource(String registSource) {
        this.registSource = registSource;
    }

    public String getRegistDate() {
        return registDate;
    }

    public void setRegistDate(String registDate) {
        this.registDate = registDate;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getVermicelli() {
        return vermicelli;
    }

    public void setVermicelli(String vermicelli) {
        this.vermicelli = vermicelli;
    }

    public String getNumberOfCencerns() {
        return numberOfCencerns;
    }

    public void setNumberOfCencerns(String numberOfCencerns) {
        this.numberOfCencerns = numberOfCencerns;
    }

    public String getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity;
    }

    public String getUserEquipment() {
        return userEquipment;
    }

    public void setUserEquipment(String userEquipment) {
        this.userEquipment = userEquipment;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", yearOfBirth='" + yearOfBirth + '\'' +
                ", vc_user_id='" + vc_user_id + '\'' +
                ", registSource='" + registSource + '\'' +
                ", registDate='" + registDate + '\'' +
                ", nick='" + nick + '\'' +
                ", vermicelli='" + vermicelli + '\'' +
                ", numberOfCencerns='" + numberOfCencerns + '\'' +
                ", userIdentity='" + userIdentity + '\'' +
                ", userEquipment='" + userEquipment + '\'' +
                '}';
    }
}
