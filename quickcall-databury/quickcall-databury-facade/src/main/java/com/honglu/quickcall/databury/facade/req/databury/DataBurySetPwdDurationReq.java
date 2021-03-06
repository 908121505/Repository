package com.honglu.quickcall.databury.facade.req.databury;

import com.honglu.quickcall.databury.facade.req.abs.AbstractBaseReq;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 设置密码页码
 *
 * @author xiangping
 * @date 2018-10-30 19:08
 */
public class DataBurySetPwdDurationReq extends AbstractBaseReq {

    /**用户手机号**/
    @NotBlank
    private String vcUserId;

    /**是否成功**/
    @NotBlank
    private boolean doseSucceed;

    public String getVcUserId() {
        return vcUserId;
    }

    public void setVcUserId(String vcUserId) {
        this.vcUserId = vcUserId;
    }

    public boolean isDoseSucceed() {
        return doseSucceed;
    }

    public void setDoseSucceed(boolean doseSucceed) {
        this.doseSucceed = doseSucceed;
    }

    @Override
    public String toString() {
        return "DataBurySetPwdDurationReq{" +
                "vcUserId='" + vcUserId + '\'' +
                ", doseSucceed=" + doseSucceed +
                '}';
    }
}
