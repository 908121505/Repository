package com.honglu.quickcall.producer.facade.req.databury;

import com.honglu.quickcall.producer.facade.req.abs.AbstractBaseReq;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * 注册成功--数据埋点--数据
 *
 * @author xiangping
 * @date 2018-10-31 11:02
 */
public class DataBuriedPointRegistReq extends AbstractBaseReq {

    /**用户id**/
    @NotBlank
    private String user_id;

    /**注册手机号**/
    @NotBlank
    private String phoneNumber;

    /**注册时间**/
    @NotBlank
    private Date registDate;

    /**注册渠道**/
    @NotBlank
    private String registSource;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    public String getRegistSource() {
        return registSource;
    }

    public void setRegistSource(String registSource) {
        this.registSource = registSource;
    }

    @Override
    public String toString() {
        return "DataBuriedPointRegistResp{" +
                "user_id='" + user_id + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", registDate=" + registDate +
                ", registSource='" + registSource + '\'' +
                '}';
    }
}
