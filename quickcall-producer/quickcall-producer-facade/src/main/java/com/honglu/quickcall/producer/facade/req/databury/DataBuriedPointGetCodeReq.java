package com.honglu.quickcall.producer.facade.req.databury;

import com.honglu.quickcall.producer.facade.req.abs.AbstractBaseReq;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author xiangping
 * @date 2018-10-30 19:08
 */
public class DataBuriedPointGetCodeReq extends AbstractBaseReq {

    /**用户手机号**/
    @NotBlank
    private String phone;

    /**是否成功**/
    @NotBlank
    private boolean isSuccess;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    @Override
    public String toString() {
        return "DataBuriedPointGetCodeResp{" +
                "phone='" + phone + '\'' +
                ", isSuccess=" + isSuccess +
                '}';
    }
}
