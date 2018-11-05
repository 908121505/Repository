package com.honglu.quickcall.user.service.service.impl;

import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.ResultUtils;
import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import com.honglu.quickcall.common.third.rongyun.util.RongYunUtil;
import com.honglu.quickcall.user.facade.code.UserBizReturnCode;
import com.honglu.quickcall.user.facade.entity.*;
import com.honglu.quickcall.user.facade.exchange.request.BookingMessageQueryRequest;
import com.honglu.quickcall.user.facade.exchange.request.BookingMessageSaveRequest;
import com.honglu.quickcall.user.facade.exchange.request.CustomerMessageSettingQueryRequest;
import com.honglu.quickcall.user.facade.exchange.request.CustomerMsgSettingRequest;
import com.honglu.quickcall.user.facade.exchange.request.UserUnreadMessageNumRequest;
import com.honglu.quickcall.user.facade.vo.MessageReservationVO;
import com.honglu.quickcall.user.service.dao.*;
import com.honglu.quickcall.user.service.service.UserMessageService;
import com.honglu.quickcall.user.service.util.DateUtil;
import com.honglu.quickcall.user.service.util.RadomUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户消息服务实现类
 *
 * @author duanjun
 * @date 2018-09-22 17:28
 */
@Service
public class UserMessageServiceImpl implements UserMessageService {

    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private MessageReservationMapper messageReservationMapper;
    @Autowired
    private MessageCustomerMapper messageCustomerMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerSkillMapper customerSkillMapper;
    @Autowired
    private SkillItemMapper skillItemMapper;
    @Autowired
    private CustomerMsgSettingMapper customerMsgSettingMapper;

    static String reidsKey = "bookingMessage:";

    @Override
    public CommonResponse queryUserUnreadMessageNum(UserUnreadMessageNumRequest params) {
    	if(params.getCustomerId() == null){
			return ResultUtils.result(BizCode.CustomerNotExist);
		}
        int num = messageMapper.queryUserUnreadMessageNum(params.getCustomerId());
        return ResultUtils.resultSuccess(num);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResponse saveBookingMessage(BookingMessageSaveRequest params) {
    	if(params.getCustomerId() == null){
			return ResultUtils.result(BizCode.CustomerNotExist);
		}
        String key = reidsKey+params.getCustomerId()+":"+params.getReceiverId();
        String value = JedisUtil.get(key);
        if (StringUtils.isNotBlank(value)){
            return ResultUtils.resultSuccess("0");
        }else {
            //保存预约消息
            MessageReservation messageReservation = new MessageReservation();
            Date curDate = new Date();
            messageReservation.setId(getRandomId());
            messageReservation.setContent("希望能快一点看到我的消息给我回复");
            messageReservation.setCreateTime(curDate);
            messageReservation.setCustomerId(params.getCustomerId());
            messageReservation.setPriceUnit(params.getPriceUnit()+"-"+params.getPriceUnitTimeCount());
            messageReservation.setReceiverId(params.getReceiverId());
            messageReservation.setStatus((byte) 0);
            messageReservation.setTitle(params.getTitle());
            int insert = messageReservationMapper.saveBookingMessage(messageReservation);

            //保存一条用户消息
            Customer customer = customerMapper.selectByPrimaryKey(params.getCustomerId());
            MessageCustomer mc = new MessageCustomer();
            mc.setId(getRandomId());
            mc.setCreateTime(curDate);
            mc.setModifyTime(curDate);
            mc.setMessageId(getRandomId());
            mc.setMessageStatus((byte)0);
            mc.setCreateMan("admin");
            mc.setModifyMan("admin");
            if (customer!=null){
                mc.setPhone(Long.parseLong(customer.getPhone()));
            }
            mc.setReceiverId(params.getReceiverId());
            int insertMc = messageCustomerMapper.insertMessage(mc);
            if (insertMc>0 && insert>0) {
                //设置redis且设置超时时间
                JedisUtil.set(key,params.getCustomerId()+"-"+params.getReceiverId(),60*30);
                //发送IM消息
                RongYunUtil.sendBespokeMessage(params.getReceiverId(),"您有一条预约消息");

                return ResultUtils.resultSuccess(insert);
            }else{
                return ResultUtils.resultSuccess("0");
            }
        }
    }

    private Long getRandomId(){
        String id = DateUtil.dateFormat(new Date(), "yyyyMMddHHmmss") + RadomUtil.generateNumber2(4);
        return Long.parseLong(id);
    }

    @Override
    public CommonResponse queryBookingMessage(BookingMessageQueryRequest params) {
    	if(params.getCustomerId() == null){
			return ResultUtils.result(BizCode.CustomerNotExist);
		}
        List<MessageReservation> messageReservationList = messageReservationMapper.queryBookingMessage(params.getCustomerId());
        List<MessageReservationVO> mrsList = new ArrayList<>();
        for (MessageReservation mr: messageReservationList){
            Customer customer = customerMapper.selectByPrimaryKey(mr.getCustomerId());
            CustomerSkill customerSkill = customerSkillMapper.queryCustomerSkill(mr.getReceiverId());
            MessageReservationVO mrv = new MessageReservationVO();
            mrv.setId(mr.getId());
            mrv.setCustomerId(mr.getCustomerId());
            mrv.setContent(mr.getContent());
            mrv.setCreateTime(DateUtil.dateFormat(mr.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            if (customer!=null){
                mrv.setCustomerName(customer.getNickName());
                mrv.setCustomerIconUrl(customer.getHeadPortraitUrl());
            }
            if(customerSkill!=null){
                SkillItem skillItem = skillItemMapper.selectSkillItem(customerSkill.getSkillItemId());
                if(skillItem!=null){
                    mrv.setSkillIconUrl(skillItem.getUnlockIcon());
                }
            }
            String[] pus = mr.getPriceUnit().split("-");
            if (pus.length>1){
                mrv.setPriceUnit(pus[0]);
                mrv.setPriceUnitTimeCount(pus[1]);
            }
            mrv.setReceiverId(mr.getReceiverId());
            mrv.setRemark(mr.getRemark());
            mrv.setStatus(mr.getStatus());
            mrv.setTitle(mr.getTitle());
            mrsList.add(mrv);
        }
        return ResultUtils.resultSuccess(mrsList);
    }

	@Override
	public CommonResponse queryCustomerMessageSetting(CustomerMessageSettingQueryRequest params) {
		if(params.getCustomerId() == null){
			return ResultUtils.result(BizCode.CustomerNotExist);
		}
		List<CustomerMsgSetting> customerMsgSettingList = customerMsgSettingMapper.selectByPrimaryKey(params.getCustomerId());
		CustomerMsgSetting customerMsgSetting = new CustomerMsgSetting();
		if(customerMsgSettingList.size() < 1){
			customerMsgSetting.setCustomerId(params.getCustomerId());
			customerMsgSetting.setReceiveType(0);
			customerMsgSetting.setReceiveStatus(0);
			customerMsgSetting.setRangeMinLimit(1000);
		}else{
			customerMsgSetting = customerMsgSettingList.get(0);
		}
		
		return ResultUtils.resultSuccess(customerMsgSetting);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public CommonResponse saveCustomerMessageSetting(CustomerMsgSettingRequest params) {
		if(params.getCustomerId() == null){
			return ResultUtils.result(BizCode.CustomerNotExist);
		}
		CustomerMsgSetting customerMsgSetting = new CustomerMsgSetting();
		customerMsgSetting.setModifyTime(new Date());
		customerMsgSetting.setId(params.getId());
		customerMsgSetting.setCustomerId(params.getCustomerId());
		customerMsgSetting.setReceiveStatus(params.getReceiveStatus());
		customerMsgSetting.setReceiveType(params.getReceiveType());
		customerMsgSetting.setRangeMinLimit(params.getRangeMinLimit());
		customerMsgSetting.setRemark(null);
		int num = 0;
		if(params.getId() == null){
			List<CustomerMsgSetting> customerMsgSettingList = customerMsgSettingMapper.selectByPrimaryKey(params.getCustomerId());
			if(customerMsgSettingList.size() > 0){
				CommonResponse response = new CommonResponse();
				response.setCode(UserBizReturnCode.paramError);
				response.setMessage(UserBizReturnCode.paramError.desc());;
				return response;
			}
			customerMsgSetting.setCreateTime(new Date());
			customerMsgSetting.setId(UUIDUtils.getId());
			customerMsgSetting.setCreateMan(null);
			num = customerMsgSettingMapper.insertSelective(customerMsgSetting);
		}else{
			customerMsgSetting.setModifyMan(null);
			num = customerMsgSettingMapper.updateByPrimaryKeySelective(customerMsgSetting);
		}
		return ResultUtils.resultSuccess(num);
	}
}
