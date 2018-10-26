package com.honglu.quickcall.common.third.pay.alipay.model;

public class AlipayTransferResultModel {
    private String batchNo;
    private String payeeAccountNo;
    private String payeeAccountName;
    private String withdrawAmount;
    private String resultCode;
    private String resultMessage;
    private String alipayBatchNo;
    private String successTime;

    public AlipayTransferResultModel() {
    }

    public AlipayTransferResultModel(String batchNo, String payeeAccountNo, String payeeAccountName, String withdrawAmount, String resultCode, String resultMessage, String alipayBatchNo, String successTime) {
        this.batchNo = batchNo;
        this.payeeAccountNo = payeeAccountNo;
        this.payeeAccountName = payeeAccountName;
        this.withdrawAmount = withdrawAmount;
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.alipayBatchNo = alipayBatchNo;
        this.successTime = successTime;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getPayeeAccountNo() {
        return payeeAccountNo;
    }

    public void setPayeeAccountNo(String payeeAccountNo) {
        this.payeeAccountNo = payeeAccountNo;
    }

    public String getPayeeAccountName() {
        return payeeAccountName;
    }

    public void setPayeeAccountName(String payeeAccountName) {
        this.payeeAccountName = payeeAccountName;
    }

    public String getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(String withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getAlipayBatchNo() {
        return alipayBatchNo;
    }

    public void setAlipayBatchNo(String alipayBatchNo) {
        this.alipayBatchNo = alipayBatchNo;
    }

    public String getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(String successTime) {
        this.successTime = successTime;
    }
}
