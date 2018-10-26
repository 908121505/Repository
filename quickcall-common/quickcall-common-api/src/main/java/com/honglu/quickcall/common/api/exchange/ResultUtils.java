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
    public static CommonResponse result(BizCode bizCode) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(bizCode);
        commonResponse.setMessage(bizCode.desc());
        return commonResponse;
    }

    /**
     * 返回成功
     *
     * @return
     */
    public static CommonResponse result(BizCode bizCode, String msg) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(bizCode);
        commonResponse.setMessage(msg);
        return commonResponse;
    }

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
     * 返回成功
     *
     * @param data 返回数据
     * @return
     */
    public static CommonResponse resultSuccess(Object data) {
        CommonResponse commonResponse = resultSuccess();
        commonResponse.setData(data);
        return commonResponse;
    }

    /**
     * 返回参数为空错误
     *
     * @return
     */
    public static CommonResponse resultParamEmpty() {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(BizCode.ParamEmpty);
        commonResponse.setMessage(BizCode.ParamEmpty.desc());
        return commonResponse;
    }

    /**
     * 返回参数为空错误
     *
     * @param msg -- 错误信息
     * @return
     */
    public static CommonResponse resultParamEmpty(String msg) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(BizCode.ParamEmpty);
        commonResponse.setMessage(msg);
        return commonResponse;
    }

    /**
     * 返回数据不存在
     *
     * @return
     */
    public static CommonResponse resultDataNotExist() {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(BizCode.DataNotExist);
        commonResponse.setMessage(BizCode.DataNotExist.desc());
        return commonResponse;
    }

    /**
     * 返回数据不存在
     *
     * @param msg -- 错误信息
     * @return
     */
    public static CommonResponse resultDataNotExist(String msg) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(BizCode.DataNotExist);
        commonResponse.setMessage(msg);
        return commonResponse;
    }

    /**
     * 返回数据重复操作
     *
     * @param msg -- 错误信息
     * @return
     */
    public static CommonResponse resultDuplicateOperation(String msg) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(BizCode.DuplicateOperation);
        commonResponse.setMessage(msg);
        return commonResponse;
    }
}
