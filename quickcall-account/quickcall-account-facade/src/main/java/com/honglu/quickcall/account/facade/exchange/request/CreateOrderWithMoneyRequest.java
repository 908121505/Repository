package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.account.facade.code.AccountFunctionType;
import com.honglu.quickcall.account.facade.enums.AccountBusinessTypeEnum;
import com.honglu.quickcall.account.facade.enums.AccountInOutEnum;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;

import java.math.BigDecimal;

/**
 * Created by len.song on 2017-12-19.
 */
public class CreateOrderWithMoneyRequest extends AbstractRequest {
    private  Integer outUserId;                         //出账用户编号
    private  Integer inUserId;                          //入账用户编号
    private BigDecimal inAmount;                        //入账金额
    private BigDecimal outAmount;                       //出账金额
    private AccountBusinessTypeEnum inBusiness;         //入账业务名称
    private  AccountBusinessTypeEnum outBusiness;       //出账账户名称
    private  Integer productId;                         //产品id (目前就礼物编号用到)
    private  String outName;                            //出账用户名称
    private  String inName;                             //入账用户名称
    private  Integer OrderType;
    private  String clientOrderId;                      //客户端唯一标识  （为了防刷）
    private  Integer sex;                               //性别
    private  Integer unitNums;                          //个数
    private  Integer familyId;                          //家族编号id
    private  BigDecimal familyIncome;                   //家族收入

    public CreateOrderWithMoneyRequest(){

    }



	/**
     * 出账/入账 请求参数构造器
     */
    public CreateOrderWithMoneyRequest(Integer userId, BigDecimal amount, AccountBusinessTypeEnum business, String userName
            , Integer balanceOfPaymentType, AccountInOutEnum inOut){
        switch (inOut){
            case in:
                this.inUserId = userId;
                this.inAmount = amount;
                this.inBusiness = business;
                this.inName = userName;

                break;

            case out:
                this.outUserId = userId;
                this.outAmount = amount;
                this.outBusiness = business;
                this.outName = userName;
                break;
            default:
                break;
        }
        this.OrderType = balanceOfPaymentType;
    }

    public CreateOrderWithMoneyRequest(Integer userId, BigDecimal amount, AccountBusinessTypeEnum business, String userName
            , Integer balanceOfPaymentType, AccountInOutEnum inOut, Integer sex){
        switch (inOut){
            case in:
                this.inUserId = userId;
                this.inAmount = amount;
                this.inBusiness = business;
                this.inName = userName;

                break;

            case out:
                this.outUserId = userId;
                this.outAmount = amount;
                this.outBusiness = business;
                this.outName = userName;
                break;
            default:
                break;
        }
        this.OrderType = balanceOfPaymentType;
        this.sex = sex;
    }

    /**
     * 有入账和出账订单 构造器
     */
    public CreateOrderWithMoneyRequest(Integer outUserId,BigDecimal outAmount,AccountBusinessTypeEnum outBusiness,String outUserName,
           Integer inUserId,BigDecimal inAmount,AccountBusinessTypeEnum inBusiness,String inUserName,Integer balanceOfPaymentType,String clientOrderId){
        this.outUserId = outUserId;
        this.outAmount = outAmount;
        this.outBusiness = outBusiness;
        this.outName = outUserName;

        this.inUserId = inUserId;
        this.inAmount = inAmount;
        this.inBusiness = inBusiness;
        this.inName = inUserName;

        this.OrderType = balanceOfPaymentType;
        this.clientOrderId = clientOrderId;
    }

    @Override
    public String getBizCode() {
        return AccountFunctionType.CreateOrderWithMoney;
    }


    public Integer getOutUserId() {
        return outUserId;
    }

    public void setOutUserId(Integer outUserId) {
        this.outUserId = outUserId;
    }

    public String getClientOrderId() {
        return clientOrderId;
    }

    public void setClientOrderId(String clientOrderId) {
        this.clientOrderId = clientOrderId;
    }

    public Integer getInUserId() {
        return inUserId;
    }

    public void setInUserId(Integer inUserId) {
        this.inUserId = inUserId;
    }

    public BigDecimal getInAmount() {
        return inAmount;
    }

    public void setInAmount(BigDecimal inAmount) {
        this.inAmount = inAmount;
    }

    public BigDecimal getOutAmount() {
        return outAmount;
    }

    public void setOutAmount(BigDecimal outAmount) {
        this.outAmount = outAmount;
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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getUnitNums() {
        return unitNums;
    }

    public void setUnitNums(Integer unitNums) {
        this.unitNums = unitNums;
    }

    public Integer getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    public BigDecimal getFamilyIncome() {
        return familyIncome;
    }

    public void setFamilyIncome(BigDecimal familyIncome) {
        this.familyIncome = familyIncome;
    }
}
