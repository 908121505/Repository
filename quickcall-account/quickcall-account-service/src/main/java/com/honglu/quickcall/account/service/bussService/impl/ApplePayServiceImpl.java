package com.honglu.quickcall.account.service.bussService.impl;

import com.alibaba.fastjson.JSONObject;
import com.honglu.quickcall.account.facade.entity.Recharge;
import com.honglu.quickcall.account.facade.enums.*;
import com.honglu.quickcall.account.facade.exchange.request.ApplePayRequest;
import com.honglu.quickcall.account.facade.exchange.request.ApplePurchaseRequest;
import com.honglu.quickcall.account.service.bussService.AccountService;
import com.honglu.quickcall.account.service.bussService.ApplePayService;
import com.honglu.quickcall.account.service.dao.RechargeMapper;
import com.honglu.quickcall.account.service.utils.IosVerifyUtil;
import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.ResultUtils;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiangxianjin
 * @date: 2018/10/20 15:19
 * @description: 苹果支付服务
 */
@Service
public class ApplePayServiceImpl implements ApplePayService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplePayServiceImpl.class);

    @Autowired
    private RechargeMapper rechargeMapper;

    @Autowired
    private AccountService accountService;

    /**
     * <p>@Title: createOrder</p>
     * <p>@Description: 创建苹果预支付订单</p>
     * @param applePayRequest
     * @return map
     */
    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public CommonResponse createOrder(ApplePayRequest applePayRequest) {
        Map<String, Object> resMap = new HashMap<>();
        try {
            //参数校验
//            Map<String, String> validMap = this.validateRequestParams(appleOrderRequest, headerBo.getSign());
//            LOGGER.info("ApplePayBizImpl.createOrder，创建苹果预支付订单参数校验结果validMap：{}", JSONObject.toJSONString(validMap, SerializerFeature.WriteMapNullValue));
//            if(!"0".equalsIgnoreCase(validMap.get("code"))) {
//                this.putResultToMap(validMap.get("code"), validMap.get("msg"), resMap);
//                return resMap;
//            }

//            //限制同一cp订单号，一分钟内只能有一次请求
//            Long setNx = RedisUtils.setNx(SysParamsCons.GM_PAYAPI_CP_ORDER_ID + ":" + appleOrderRequest.getBizData().getCpOrderId(), SysConstants.CP_ORDER_ID_EXPIRE_TIME, appleOrderRequest.getBizData().getCpOrderId());
//            LOGGER.info("ApplePayBizImpl.createOrder setNx ：{}", setNx);
//
//            //session校验
//            boolean sessionFlag = this.rpcInvokeService.checkSession(appleOrderRequest.getUserId(), appleOrderRequest.getSessionId(), appleOrderRequest.getAppId());
//            LOGGER.info("ApplePayBizImpl.createOrder，创建苹果预支付订单session校验是否通过：{}", sessionFlag);
//            if(!sessionFlag) {
//                this.putResultToMap(ResponseCode.USER_SESSION_EXPIRE.getValue(), ResponseCode.USER_SESSION_EXPIRE.getDesc(), resMap);
//                return resMap;
//            }
//            //验签
//            boolean signFlag = this.payCheckSignBiz.checkSign(appleOrderRequest, appleOrderRequest.getBizData(), headerBo.getSign());
//            LOGGER.info("ApplePayBizImpl.createOrder，创建苹果预支付订单参数验签是否通过：{}", signFlag);
//            if(!signFlag) {
//                this.putResultToMap(ResponseCode.ORDER_PAY_SIGN_IS_ERROR.getValue(), ResponseCode.ORDER_PAY_SIGN_IS_ERROR.getDesc(), resMap);
//                return resMap;
//            }
//            //查询appKey
//            MAppVo mAppVo = this.rpcInvokeService.getMAppByAppId(appleOrderRequest.getAppId());
//            if(mAppVo == null || StringUtils.isEmpty(mAppVo.getAppKey())) {
//                LOGGER.error("appId为：{}，未能查询到appKey", appleOrderRequest.getUserId());
//                throw new RuntimeException("appId为"+appleOrderRequest.getAppId()+"，未能查询到appKey");
//            }
//            String appKey = String.valueOf(mAppVo.getAppKey());

            //创建充值订单
            String rechargeOrderId = createReChargeOrder(applePayRequest.getCustomerId(), applePayRequest.getAmount());
            resMap.put("rechargeOrderId", rechargeOrderId);

        } catch (Exception e) {
            LOGGER.error("苹果预支付订单创建失败，参数params：{}", JSONObject.toJSONString(applePayRequest), e);
            throw new BizException(BizCode.Unknown);
        }
        return ResultUtils.resultSuccess(resMap);
    }

    /**
     * <p>@Title: applePurchase</p>
     * <p>@Description: 苹果内购验证</p>
     * @param applePurchaseRequest
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public CommonResponse applePurchase(ApplePurchaseRequest applePurchaseRequest){
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            /**
             * 1.参数校验
             * 2.session校验
             * 3.验签
             * 4.查询充值订单
             */

            //苹果内购校验
            // 1.先线上测试，发送平台验证
            String verifyResult = IosVerifyUtil.buyAppVerify(applePurchaseRequest.getAppReceipt(), 1);
            if (verifyResult == null) {
                return ResultUtils.result(BizCode.DataNotExist);
            }
            // 苹果验证有返回结果 online：0， sandbox：1
            Integer sandboxOrOnline = 0;
            LOGGER.info("苹果支付返回验证结果verifyResult：{}", verifyResult);
            JSONObject job = JSONObject.parseObject(verifyResult);
            String states = job.getString("status");
            // 是沙盒环境，应沙盒测试，否则执行下面
            if ("21007".equals(states)) {
                sandboxOrOnline = 1;
                // 2.再沙盒测试 ，发送平台验证
                verifyResult = IosVerifyUtil.buyAppVerify(applePurchaseRequest.getAppReceipt(), 0);
                LOGGER.info("苹果支付返回验证结果verifyResult：{}", verifyResult);
                job = JSONObject.parseObject(verifyResult);
                states = job.getString("status");
            }

            LOGGER.info("苹果平台返回值：job：{}", job);
            // 前端所提供的数据是有效的 验证成功
            if (states.equals("0")) {
                String r_receipt = job.getString("receipt");
                JSONObject returnJson = JSONObject.parseObject(r_receipt);
                String in_app = returnJson.getString("in_app");
                String product_id = returnJson.getString("product_id");

                //订单号
                JSONObject data = null;
                String transaction_id = "";
                if (StringUtils.isNotEmpty(in_app)) {
                    //in_app是一个jsonarray，只有一个元素去除外面的中括号
                    JSONObject in_appJson = JSONObject.parseObject(in_app.substring(1, in_app.length() - 1));
                    transaction_id = in_appJson.getString("transaction_id");
                    data = in_appJson;
                } else {
                    transaction_id = returnJson.getString("transaction_id");
                    data = returnJson;
                }
                /***************************************** +自己的业务逻辑 ********************************************/
                //单号一致
                if(!applePurchaseRequest.getTradeNo().equalsIgnoreCase(transaction_id)){
                    LOGGER.error("单号不一致，请求单号transNo：{}，返回单号transaction_id：{}"
                            , applePurchaseRequest.getTradeNo(), transaction_id);
                    return ResultUtils.result(BizCode.DataNotExist);
                }
                //充值单去重
                Recharge rechargeVO = rechargeMapper.selectByOrderNo(applePurchaseRequest.getOrderId());
                if(RechargeStateEnum.PAY_SUCCESS.getType().equals(rechargeVO.getState())){
                    LOGGER.warn("单号已充值成功，请勿重复操作，请求单号transNo：{}，充值单号：{}"
                            , applePurchaseRequest.getTradeNo(),applePurchaseRequest.getOrderId());
                    return ResultUtils.resultSuccess();
                }

                // 1. 更新订单状态为成功
                Recharge recharge = new Recharge();
                recharge.setFinishDate(new Date());
                recharge.setState(RechargeStateEnum.PAY_SUCCESS.getType());
                recharge.setAlipayordersn(applePurchaseRequest.getTradeNo());
                recharge.setOrdersn(applePurchaseRequest.getOrderId());
                recharge.setTokenPackage(sandboxOrOnline);
                rechargeMapper.updateByOrderNo(recharge);

                // 2. 账户充值操作
                accountService.inAccount(rechargeVO.getCustomerId(), rechargeVO.getAmount().multiply(BigDecimal.valueOf(100))
                        , TransferTypeEnum.RECHARGE, AccountBusinessTypeEnum.Recharge);
                return ResultUtils.resultSuccess();
                /**************************************** +自己的业务逻辑end ******************************************/

            } else {
                Recharge recharge = new Recharge();
                recharge.setFinishDate(new Date());
                recharge.setState(RechargeStateEnum.PAY_FAIL.getType());
                recharge.setAlipayordersn(applePurchaseRequest.getTradeNo());
                recharge.setOrdersn(applePurchaseRequest.getOrderId());
                rechargeMapper.updateByOrderNo(recharge);
                return ResultUtils.result(BizCode.ChargeOrderFail);
            }
        } catch (Exception e) {
            LOGGER.error("苹果内购验证异常：", e.getMessage());
            e.printStackTrace();
            throw new BizException(BizCode.Unknown, "苹果内购异常");
        }
    }

    /**
     * 保存充值订单信息
     * @param customerId    客户编号
     * @param amount        充值金额
     * @return
     */
    private String createReChargeOrder(Long customerId, BigDecimal amount){
        String orderNo = UUIDUtils.getUUID();
        Recharge recharge = new Recharge();
        recharge.setCustomerId(customerId);
        recharge.setAmount(amount);
        recharge.setType(TradeTypeEnum.Pay.getType());
        recharge.setOrdersn(orderNo);
        recharge.setState(RechargeStateEnum.PAY_APPLY.getType());
        recharge.setRechargeType(RechargeTypeEnum.APPLE_PAY.getType());
        recharge.setCreateDate(new Date());
        int count = rechargeMapper.insertSelective(recharge);
        if(count != 1){
            throw new BizException(BizCode.ChargeOrderSaveFail);
        }
        return orderNo;
    }

}
