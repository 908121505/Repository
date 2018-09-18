package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.account.facade.enums.AccountBusinessTypeEnum;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

/**
 * Created by len.song on 2017-12-19.
 */
public class CreateOrderWithProductRequest extends AbstractRequest {
    private  Integer outUserId;                         //出账用户编号
    private  Integer inUserId;                          //入账用户编号
    private  String productName;                        //产品名称
    private  AccountBusinessTypeEnum inBusiness;        //入账业务名称
    private  AccountBusinessTypeEnum outBusiness;       //出账账户名称
    private  Integer OrderType;                         //账单类型
    private  String outName;                            //出账用户名称
    private  String inName;                             //入账用户名称
    private  Integer isProfit;                          //是否需要分成  0：不需要  1：需要
    private  String clientOrderId;                      //客户端传输的唯一标识  (为了防刷)

    @Override
    public String getBizCode() {
        return AccountFunctionType.CreateOrderWithProduct;
    }

    public Integer getOutUserId() {
        return outUserId;
    }

    public void setOutUserId(Integer outUserId) {
        this.outUserId = outUserId;
    }

    public Integer getInUserId() {
        return inUserId;
    }

    public void setInUserId(Integer inUserId) {
        this.inUserId = inUserId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public AccountBusinessTypeEnum getInBusiness() {
        return inBusiness;
    }

    public void setInBusiness(AccountBusinessTypeEnum inBusiness) {
        this.inBusiness = inBusiness;
    }

    public AccountBusinessTypeEnum getOutBusiness() {
        return outBusiness;
    }

    public void setOutBusiness(AccountBusinessTypeEnum outBusiness) {
        this.outBusiness = outBusiness;
    }

    public Integer getOrderType() {
        return OrderType;
    }

    public void setOrderType(Integer orderType) {
        OrderType = orderType;
    }

    public Integer getIsProfit() {
        return isProfit;
    }

    public void setIsProfit(Integer isProfit) {
        this.isProfit = isProfit;
    }

    public String getOutName() {
        return outName;
    }

    public void setOutName(String outName) {
        this.outName = outName;
    }

    public String getInName() {
        return inName;
    }

    public void setInName(String inName) {
        this.inName = inName;
    }

    public String getClientOrderId() {
        return clientOrderId;
    }

    public void setClientOrderId(String clientOrderId) {
        this.clientOrderId = clientOrderId;
    }
}
