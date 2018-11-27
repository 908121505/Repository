package com.honglu.quickcall.producer.facade.req.databury;

import java.io.Serializable;
import java.util.Date;

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
    private Date yearOfBirth;
    /**注册渠道**/
    private String registSource;
    /**注册时间**/
    private Date registDate;
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

    public Date getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Date yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getRegistSource() {
        return registSource;
    }

    public void setRegistSource(String registSource) {
        this.registSource = registSource;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
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
                ", yearOfBirth=" + yearOfBirth +
                ", registSource='" + registSource + '\'' +
                ", registDate=" + registDate +
                ", nick='" + nick + '\'' +
                ", vermicelli='" + vermicelli + '\'' +
                ", numberOfCencerns='" + numberOfCencerns + '\'' +
                ", userIdentity='" + userIdentity + '\'' +
                ", userEquipment='" + userEquipment + '\'' +
                '}';
    }
}
