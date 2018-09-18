package com.honglu.quickcall.account.facade.exchange.request;

import com.honglu.quickcall.common.api.exchange.AbstractRequest;
import com.honglu.quickcall.common.third.pay.alipay.model.AlipayTransferModel;

import java.io.Serializable;
import java.util.List;

/**
 * 批量转账请求实体
 */
public class AlipayBatchTransferRequest extends AbstractRequest {
    List<AlipayTransferModel> batchTransfers;
    String interfaceType;//接口类型
    String batchNo;//批次号
    String accountFunctionType;//类型枚举

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public AlipayBatchTransferRequest() {
    }

    public String getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }

    public String getAccountFunctionType() {
        return accountFunctionType;
    }

    public void setAccountFunctionType(String accountFunctionType) {
        this.accountFunctionType = accountFunctionType;
    }

    public List<AlipayTransferModel> getBatchTransfers() {
        return batchTransfers;
    }

    public void setBatchTransfers(List<AlipayTransferModel> batchTransfers) {
        this.batchTransfers = batchTransfers;
    }

    @Override
    public String getBizCode() {
        return accountFunctionType;
    }

}
