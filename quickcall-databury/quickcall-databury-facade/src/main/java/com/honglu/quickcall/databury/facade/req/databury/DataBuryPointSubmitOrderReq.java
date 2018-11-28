package com.honglu.quickcall.databury.facade.req.databury;

import com.honglu.quickcall.databury.facade.req.abs.AbstractBaseReq;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 提交订单--数据埋点--数据
 *
 * @author xiangping
 * @date 2018-10-30 19:08
 */
public class DataBuryPointSubmitOrderReq extends AbstractBaseReq {

    /**订单id**/
    @NotBlank
    private String order_id;

    /**订单金额**/
    @NotBlank
    private Double order_amount;

    /**购买者id**/
    @NotBlank
    private String user_id;

    /**订单品类**/
    @NotBlank
    private String order_type;

    /**订单数量**/
    @NotBlank
    private Double order_quantity;

    /**实付金额**/
    @NotBlank
    private Double actual_payment_amount;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public Double getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(Double order_amount) {
        this.order_amount = order_amount;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public Double getOrder_quantity() {
        return order_quantity;
    }

    public void setOrder_quantity(Double order_quantity) {
        this.order_quantity = order_quantity;
    }

    public Double getActual_payment_amount() {
        return actual_payment_amount;
    }

    public void setActual_payment_amount(Double actual_payment_amount) {
        this.actual_payment_amount = actual_payment_amount;
    }
}
