package com.honglu.quickcall.user.facade.exchange.request;

import com.honglu.quickcall.common.api.exchange.AbstractRequest;
import com.honglu.quickcall.user.facade.code.UserFunctionType;

/**
 * 初始化大V评分排名数据
 *
 * @author duanjun
 * @date 2018-11-05 14:04
 */
public class InitBigvScoreRankDataRequest extends AbstractRequest {

    private static final long serialVersionUID = -650783022059271775L;
    /**
     * 登录客户ID
     */
    private String password;

    @Override
    public String getBizCode() {
        return UserFunctionType.INIT_BIGV_SCORE_RANK_DATA;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "InitBigvScoreRankDataRequest{" +
                "password='" + password + '\'' +
                '}';
    }
}
