package com.honglu.quickcall.producer.facade.req.databury;

import com.honglu.quickcall.producer.facade.req.abs.AbstractBaseReq;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 接单按钮状态--数据埋点--数据
 *
 * @author xiangping
 * @date 2018-10-30 19:08
 */
public class DataBuriedPointOrderButtonReq extends AbstractBaseReq {

    /**用户id**/
    @NotBlank
    private String user_id;

    /**接单按钮状态**/
    @NotBlank
    private String orderbutton_status;

    /**按钮生效时间**/
    @NotBlank
    private String buttonexecution_time;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOrderbutton_status() {
        return orderbutton_status;
    }

    public void setOrderbutton_status(String orderbutton_status) {
        this.orderbutton_status = orderbutton_status;
    }

    public String getButtonexecution_time() {
        return buttonexecution_time;
    }

    public void setButtonexecution_time(String buttonexecution_time) {
        this.buttonexecution_time = buttonexecution_time;
    }

    @Override
    public String toString() {
        return "DataBuriedPointOrderButtonResp{" +
                "user_id='" + user_id + '\'' +
                ", orderbutton_status='" + orderbutton_status + '\'' +
                ", buttonexecution_time='" + buttonexecution_time + '\'' +
                '}';
    }
}
