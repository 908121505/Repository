package com.honglu.quickcall.user.service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.honglu.quickcall.common.api.exception.RemoteException;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.core.util.StringUtil;
import com.honglu.quickcall.user.facade.code.UserBizReturnCode;
import com.honglu.quickcall.user.facade.entity.Customer;
import com.honglu.quickcall.user.facade.entity.Interest;
import com.honglu.quickcall.user.facade.entity.Occupation;
import com.honglu.quickcall.user.facade.entity.in.PersonHomePage;
import com.honglu.quickcall.user.facade.exchange.request.PersonInfoRequest;
import com.honglu.quickcall.user.service.service.CustomerRedisManagement;
import com.honglu.quickcall.user.service.service.InterestMapper;
import com.honglu.quickcall.user.service.service.OccupationMapper;
import com.honglu.quickcall.user.service.service.PersonInfoService;

import cn.jiguang.commom.utils.StringUtils;
@Service
@Transactional
public class UserInfoServiceImpl implements PersonInfoService{
	@Autowired
	private CustomerRedisManagement customerRedisManagement;
	@Autowired
	private InterestMapper interestMapper;
	@Autowired
	private OccupationMapper occupationMapper;
	/**
	 * @author liuyinkai
	 * 查看个人信息
	 */
	@Override
	public CommonResponse queryPersonInfo(PersonInfoRequest params) {
		CommonResponse commonResponse=new CommonResponse();
		PersonHomePage personHomePage=null;
		if(null!=(params.getCustomerId())||null!=(params.getOtherId())) {
			throw new RemoteException(UserBizReturnCode.paramError,"参数错误 request.getJson()="+params.getCustomerId());
		}
		try {
			//判断是不是查看自己的资料
			if (!params.getCustomerId().equals(params.getOtherId())) {
				 personHomePage=queryPersonal(params.getCustomerId());
			}else {
				 personHomePage=queryPersonal(params.getOtherId());
			}
			commonResponse.setData(personHomePage);
			commonResponse.setCode(UserBizReturnCode.Success);
			commonResponse.setMessage(UserBizReturnCode.Success.desc());
		} catch (Exception e) {
			throw new RemoteException(UserBizReturnCode.UserNotExist,"用户不存在");
		}
		
		return commonResponse;
	}
	/**
	 * @Title 查询详细展示信息
	 * @modify liuyinkai
	 * @param accountId
	 * @return outPacket
	 */
	@SuppressWarnings("unchecked")
	public PersonHomePage queryPersonal(Long customerId) {
		Customer customer = customerRedisManagement.getCustomer(customerId);
		PersonHomePage personHomePage = new PersonHomePage();
		if (customer != null) {
			personHomePage.setCustomerId(customerId);//id
			personHomePage.setNickName(customer.getNickName());//昵称
			personHomePage.setSex(customer.getSex());//性别
			personHomePage.setHeadPortraitUrl(customer.getHeadPortraitUrl());//头像
			personHomePage.setBirthday(customer.getBirthday());//生日
			// 查询兴趣爱好 by customerId
			List<Interest> interestList = interestMapper.selectInterestByCustomerId(customerId);
			personHomePage.setInterest(interestList);
			// 查询职业 by accountId
			String occupation = occupationMapper.selectByCustomerId(customerId);
			personHomePage.setOccupation(occupation);
			return personHomePage;
		} else {
			return null;
		}

	}

}
