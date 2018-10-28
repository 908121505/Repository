package com.honglu.quickcall.activity.service.service.impl;

import com.honglu.quickcall.activity.facade.entity.Banner;
import com.honglu.quickcall.activity.facade.exchange.request.BannerRequest;
import com.honglu.quickcall.activity.service.dao.BannerMapper;
import com.honglu.quickcall.activity.service.service.AppConfigDataService;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * App配置数据查询服务接口实现类
 *
 * @author duanjun
 * @date 2018-09-21 17:37
 */
@Service
public class AppConfigDataServiceImpl implements AppConfigDataService {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public CommonResponse queryBannerInfo(BannerRequest request) {
        if (request == null) {
            return ResultUtils.resultParamEmpty();
        }
        if (request.getBannerType() == null) {
            return ResultUtils.resultParamEmpty("Banner类型必传");
        }
        Banner bannerParms = new Banner();
        bannerParms.setBannerType(request.getBannerType());
        bannerParms.setDeviceType(request.getDeviceType());
        bannerParms.setAppVersionRule(request.getAppVersionRule());
        bannerParms.setAppVersion(request.getAppVersion());

        List<Banner> list = bannerMapper.queryBannerInfo(bannerParms);
        return ResultUtils.resultSuccess(list);
    }
}
