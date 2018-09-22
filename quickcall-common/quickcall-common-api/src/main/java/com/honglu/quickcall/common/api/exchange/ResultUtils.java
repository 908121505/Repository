package com.honglu.quickcall.common.api.exchange;

import com.honglu.quickcall.common.api.code.BizCode;

/**
 * 接口公用返回对象工具类
 *
 * @author duanjun
 * @date 2018-09-21 17:37
 */
public class ResultUtils {

    /**
     * 返回成功
     *
     * @return
     */
    public static CommonResponse resultSuccess() {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(BizCode.Success);
        commonResponse.setMessage(BizCode.Success.desc());
        return commonResponse;
    }

    /**
     * @param data 返回数据
     * @return
     */
    public static CommonResponse resultSuccess(Object data) {
        CommonResponse commonResponse = resultSuccess();
        commonResponse.setData(data);
        return commonResponse;
    }


}
