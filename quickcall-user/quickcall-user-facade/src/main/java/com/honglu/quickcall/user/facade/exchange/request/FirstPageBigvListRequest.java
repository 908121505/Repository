package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.common.api.exchange.AbstractRequest;
import com.honglu.quickcall.user.facade.code.UserFunctionType;

/**
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * <p>
 * 功能描述：首页大V列表展示
 *
 * @Package: com.honglu.quickcall.account.facade.exchange.req
 * @author: chenliuguang
 * @date: 2018年9月22日 下午3:39:53
 */
public class FirstPageBigvListRequest extends AbstractRequest {


    private static final long serialVersionUID = -5301377502608000151L;
    /**
     * 登录客户ID
     */
    private Long customerId;

    @Override
    public String getBizCode() {
        return UserFunctionType.QUERY_FIRST_PAGE_BIGV_LIST;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "FirstPageBigvListRequest{" +
                "customerId=" + customerId +
                '}';
    }
}
