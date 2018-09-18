package com.honglu.quickcall.account.service.common;

import com.honglu.quickcall.common.api.util.DateUtils;
import com.honglu.quickcall.common.core.util.StringUtil;
import com.honglu.quickcall.common.third.pay.alipay.AlipayQuery;
import com.honglu.quickcall.common.third.pay.alipay.model.AlipayTransferResultModel;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class CommonUtils {
    /**
     * 生成随机数号
     *
     * @return
     */
    public static String generateRandomNo() {
        int code = UUID.randomUUID().hashCode();
        String currentDateTime = DateUtils.getCurrentDateTime("yyyyMMddHHmmss");
        if (code < 0){
            code = -code;
        }
        String formatCode = String.format("%010d", -code);
        return currentDateTime + formatCode;
    }

    /**
     * 生成带前缀的随机号
     *
     * @param prefix 自定义前缀
     * @return
     */
    public static String generateRandomNo(String prefix) {
        int code = UUID.randomUUID().hashCode();
        String currentDateTime = DateUtils.getCurrentDateTime("yyyyMMddHHmmss");
        if (code < 0){
            code = -code;
        }
        String formatCode = String.format("%010d", code);
        return prefix + currentDateTime + formatCode;
    }

    /**
     * 解析转账结果
     *
     * @return 转账成功格式为：流水号^收款方账号^收款账号姓名^付款金额^成功标识(S)^成功原因^支付宝内部流水号^完成时间。
     * 0315001^gonglei1@handsome.com.cn^龚本林^20.00^S^null^200810248427067^20081024143652|
     * 转账失败格式为：流水号^收款方账号^收款账号姓名^付款金额^失败标识(F)^失败原因^支付宝内部流水号^完成时间。
     * 0315006^xinjie_xj@163.com^星辰公司1^20.00^F^TXN_RESULT_TRANSFER_OUT_CAN_NOT_EQUAL_IN^200810248427065^20081024143651|
     */
    public static List<AlipayTransferResultModel> analyzeTransferResult(String data) {
        List<AlipayTransferResultModel> batchLsit = new ArrayList<AlipayTransferResultModel>();
        if (StringUtil.isNull(data)) {
            if (data.indexOf("|") > -1) {
                //1.多笔根据|符号分割s
                String[] mul_result = data.split("\\|");
                for (String s : mul_result) {
                    //2.解析^符号
                    batchLsit.add(analyzeResult(s));
                }
            } else {
                //2.解析^符号
                batchLsit.add(analyzeResult(data));
            }
        }

        return batchLsit;
    }

    private static AlipayTransferResultModel analyzeResult(String data) {
        String[] result = data.split("\\^");
        AlipayTransferResultModel model = new AlipayTransferResultModel();
        model.setBatchNo(result[0]);
        model.setPayeeAccountNo(result[1]);
        model.setPayeeAccountName(result[2]);
        model.setWithdrawAmount(result[3]);
        model.setResultCode(result[4]);
        model.setResultMessage(result[5] == null ? "" : result[5]);
        model.setAlipayBatchNo(result[6]);
        model.setSuccessTime(result[7]);
        return model;
    }


   

}
