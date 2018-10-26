package com.honglu.quickcall.common.third.pay.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import net.sf.json.util.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class AlipayUtils {

    private static final Logger logger = LoggerFactory.getLogger(AlipayUtils.class);

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {
        List<String> keys = new ArrayList<String>(params.keySet());

        Collections.sort(keys);//排序

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }

    /**
     * 检测支付宝异步反馈是否真实
     *
     * @param params
     * @return null 验证失败  不为null即成功
     */
    public static Map<String, String> checkAliPayParam(Map<String, String> params) {

        boolean flag = true;//初始参数

//        Map<String, String> params = toMap(maps);//获取支付宝POST过来反馈信息
//        try {
//            AlipaySignature.rsaCheckV1(params, AlipayConfig.ali_public_key, "UTF-8");//支付宝jar包进行验签
//        } catch (AlipayApiException e) {
//            logger.error("验签出错了，错误原因为：" + e.getMessage());
//        }

        if (flag) {
            //验证一些基本参数是否是自己的
            if (AlipayConfig.partner.equals(params.get("seller_id")) && AlipayConfig.appid.equals(params.get("app_id"))) {
                String trade_status = params.get("trade_status");
                //查看支付宝是否是通知成功
                if ("TRADE_FINISHED".equalsIgnoreCase(trade_status) ||
                        "TRADE_SUCCESS".equalsIgnoreCase(trade_status) ||
                        "TRADE_CLOSED".equalsIgnoreCase(trade_status)) {
                    return params;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static Map<String, Object> GetInfoFromALiPay(Map<String, Object> maps) {
        Map<String, Object> params = new HashMap<String, Object>();
        Map requestParams = maps;
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i]
                        + ",";
            }
            params.put(name, valueStr);
        }
        return params;
    }

    public static Map<String, String> toMap(Map<String, Object> maps) {
        Map<String, String> params = new HashMap<String, String>();
        for (Map.Entry<String, Object> m : maps.entrySet()) {
            params.put(m.getKey(), m.getValue().toString().replace("\\r\\n\\t",""));
        }
        return params;
    }
}
