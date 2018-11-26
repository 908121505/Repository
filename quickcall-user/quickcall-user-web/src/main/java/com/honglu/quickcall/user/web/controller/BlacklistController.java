package com.honglu.quickcall.user.web.controller;

import com.honglu.quickcall.common.api.exchange.WebResponseModel;
import com.honglu.quickcall.user.facade.exchange.request.editprofile.QueryBlacklistReq;
import com.honglu.quickcall.user.facade.exchange.request.editprofile.RemoveBlacklistReq;
import com.honglu.quickcall.user.facade.exchange.request.editprofile.SaveBlacklistReq;
import com.honglu.quickcall.user.web.service.UserCenterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 黑名单管理
 *
 * @author chenpeng
 * @date 2018/10/21 19:10
 */
@RestController
@RequestMapping(value = "/blacklist")
public class BlacklistController {
    private static final Logger logger = LoggerFactory.getLogger(BlacklistController.class);

    @Autowired
    private UserCenterService userCenterService;

    /**
     * 查询黑名单列表
     *
     * @param params
     * @return
     */
    @PostMapping(value = "/queryBlacklist")
    public WebResponseModel queryBlacklist(QueryBlacklistReq params) {
        logger.info("查询黑名单 请求参数：" + params.toString());
        WebResponseModel response = userCenterService.execute(params);
        return response;
    }

    /**
     * 删除黑名单
     *
     * @param params
     * @return
     */
    @PostMapping(value = "/removeBlacklist")
    public WebResponseModel removeBlacklist(RemoveBlacklistReq params) {
        logger.info("删除黑名单 请求参数：" + params.toString());
        WebResponseModel response = userCenterService.execute(params);
        return response;
    }
    /**
     * 添加黑名单
     *
     * @param params
     * @return
     */
    @PostMapping(value = "/saveBlacklist")
    public WebResponseModel saveBlacklist(SaveBlacklistReq params) {
        logger.info("添加黑名单 请求参数：" + params.toString());
        WebResponseModel response = userCenterService.execute(params);
        return response;
    }
}
