package com.honglu.quickcall.user.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.honglu.quickcall.common.api.exception.RemoteException;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.code.UserBizReturnCode;
import com.honglu.quickcall.user.facade.entity.Person;
import com.honglu.quickcall.user.facade.exchange.request.PersonInfoRequest;
import com.honglu.quickcall.user.service.dao.PersonMapper;
import com.honglu.quickcall.user.service.service.PersonInfoService;
@Service
@Transactional
public class UserInfoServiceImpl implements PersonInfoService{
	@Autowired
    private PersonMapper persion_dao;
    /**@title 查询个人信息
     * @author lyk
     * @param request
     * @return
     */
	@Override
	public CommonResponse queryPersonInfo(PersonInfoRequest request) {{

        CommonResponse commonResponse=new CommonResponse();
        if(!"".equals(request.getId())||null!=(request.getId())){
            Person person = persion_dao.selectPersonInfo(request.getId());
            if(person==null) {
                throw new RemoteException(UserBizReturnCode.UserNotExist,"用户不存在");
            }
            //设置结果
            commonResponse.setData(person);
            commonResponse.setCode(UserBizReturnCode.Success);
            commonResponse.setMessage(UserBizReturnCode.Success.desc());
            return commonResponse;
        }else {
            throw new RemoteException(UserBizReturnCode.paramError,"参数错误");
        }
      }
	}

}
