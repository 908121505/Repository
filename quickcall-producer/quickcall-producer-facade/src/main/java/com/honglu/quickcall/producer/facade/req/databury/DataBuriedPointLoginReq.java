package com.honglu.quickcall.producer.facade.req.databury;

import com.honglu.quickcall.producer.facade.req.abs.AbstractBaseReq;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 登陆成功--数据埋点--数据
 *
 * @author xiangping
 * @date 2018-10-31 11:02
 */
public class DataBuriedPointLoginReq extends AbstractBaseReq {

    /**登陆方式**/
    @NotBlank
    private String loginmethod;

    /**用户id**/
    @NotBlank
    private String user_id;

    /**电话号码**/
    @NotBlank
    private String phoneNumber;

    private UserBean userBean;

    public String getLoginmethod() {
        return loginmethod;
    }

    public void setLoginmethod(String loginmethod) {
        this.loginmethod = loginmethod;
    }

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

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    @Override
    public String toString() {
        return "DataBuryPointLoginReq{" +
                "loginmethod='" + loginmethod + '\'' +
                ", user_id='" + user_id + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userBean=" + userBean +
                '}';
    }
}
