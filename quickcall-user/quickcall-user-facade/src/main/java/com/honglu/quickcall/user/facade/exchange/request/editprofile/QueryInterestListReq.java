package com.honglu.quickcall.user.facade.exchange.request.editprofile;

import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.UserCenterRequest;

/**
 * Description: 查询星座列表
 *
 * @author chenpeng
 * @date 2018/10/22 11:51
 */
public class QueryInterestListReq extends UserCenterRequest {

    @Override
    public String getBizCode() {
        return UserFunctionType.queryInterestList;
    }
}
