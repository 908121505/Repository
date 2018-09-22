package com.honglu.quickcall.activity.service.service.impl;

import com.honglu.quickcall.activity.facade.entity.Banner;
import com.honglu.quickcall.activity.facade.exchange.request.BannerRequest;
import com.honglu.quickcall.activity.service.dao.BannerMapper;
import com.honglu.quickcall.activity.service.service.AppConfigDataService;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        Banner bannerParms = new Banner();
        List<Banner> list = bannerMapper.queryBannerInfo(bannerParms);
        if (list == null || list.size() == 0) {
            return ResultUtils.resultSuccess();
        }

        // 只返回部分数据
        List<Banner> resultData = new ArrayList<>();
        for (Banner banner : list) {
            Banner data = new Banner();
            data.setImageUrl(banner.getImageUrl());
            data.setUrl(banner.getUrl());
            data.setTitle(banner.getTitle());
            resultData.add(data);
        }

        return ResultUtils.resultSuccess(resultData);
    }
}
