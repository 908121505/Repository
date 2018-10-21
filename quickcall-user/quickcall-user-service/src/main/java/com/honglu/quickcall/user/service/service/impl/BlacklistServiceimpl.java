package com.honglu.quickcall.user.service.service.impl;

import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.code.UserBizReturnCode;
import com.honglu.quickcall.user.facade.exchange.request.editprofile.QueryBlacklistReq;
import com.honglu.quickcall.user.facade.exchange.request.editprofile.RemoveBlacklistReq;
import com.honglu.quickcall.user.facade.vo.BlacklistVo;
import com.honglu.quickcall.user.service.dao.BlacklistMapper;
import com.honglu.quickcall.user.service.service.BlacklistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description: 黑名单管理
 *
 * @author chenpeng
 * @date 2018/10/21 19:19
 */
@Service
@Transactional
public class BlacklistServiceimpl implements BlacklistService {

    private static final Logger logger = LoggerFactory.getLogger(PersonInfoServiceImpl.class);

    @Autowired
    private BlacklistMapper blacklistMapper;

    @Override
    public CommonResponse removeBlacklist(RemoveBlacklistReq params) {
        CommonResponse commonResponse = new CommonResponse();

        if(params.getId() == null){
            throw new BizException(UserBizReturnCode.paramError, "id不能为空");
        }

        int result = blacklistMapper.deleteByPrimaryKey(params.getId());
        logger.info("删除黑名单 removeBlacklist,更新数量" + result);
        if (result > 0) {
            commonResponse.setCode(UserBizReturnCode.Success);
            commonResponse.setMessage(UserBizReturnCode.Success.desc());
            return commonResponse;
        } else {
            logger.error("删除黑名单 异常");
            throw new BizException(UserBizReturnCode.jdbcError, "操作数据库异常");
        }
    }

    @Override
    public CommonResponse queryBlacklist(QueryBlacklistReq params) {
        CommonResponse commonResponse = new CommonResponse();
        if(params.getCustomerId() == null){
            throw new BizException(UserBizReturnCode.paramError, "customerId不能为空");
        }

        List<BlacklistVo> customerList = blacklistMapper.selectListByCustomerId(params.getCustomerId());

        commonResponse.setData(customerList);
        commonResponse.setCode(UserBizReturnCode.Success);
        commonResponse.setMessage(UserBizReturnCode.Success.desc());
        return commonResponse;
    }
}
