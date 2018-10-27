package com.honglu.quickcall.user.service.service.impl;

import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.ResultUtils;
import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.common.third.rongyun.util.RongYunUtil;
import com.honglu.quickcall.user.facade.entity.Customer;
import com.honglu.quickcall.user.facade.entity.MessageReservation;
import com.honglu.quickcall.user.facade.exchange.request.BookingMessageQueryRequest;
import com.honglu.quickcall.user.facade.exchange.request.BookingMessageSaveRequest;
import com.honglu.quickcall.user.facade.exchange.request.UserUnreadMessageNumRequest;
import com.honglu.quickcall.user.facade.vo.MessageReservationVO;
import com.honglu.quickcall.user.service.dao.MessageCustomerMapper;
import com.honglu.quickcall.user.service.dao.MessageMapper;
import com.honglu.quickcall.user.service.dao.MessageReservationMapper;
import com.honglu.quickcall.user.service.service.CustomerService;
import com.honglu.quickcall.user.service.service.UserMessageService;
import com.honglu.quickcall.user.service.util.DateUtil;
import com.honglu.quickcall.user.service.util.RadomUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private CustomerService customerService;

    static String reidsKey = "bookingMessage:";

    @Override
    public CommonResponse queryUserUnreadMessageNum(UserUnreadMessageNumRequest params) {
        int num = messageMapper.queryUserUnreadMessageNum(params.getCustomerId());
        return ResultUtils.resultSuccess(num);
    }

    @Override
    public CommonResponse saveBookingMessage(BookingMessageSaveRequest params) {
        String key = reidsKey+params.getCustomerId()+":"+params.getReceiverId();
        String value = JedisUtil.get(key);
        if (StringUtils.isNotBlank(value)){
            return ResultUtils.resultSuccess("您已预约了查看了这个大V");
        }else {
            //设置redis且设置超时时间
            JedisUtil.set(key,params.getCustomerId()+"-"+params.getReceiverId(),60*30);

            //发送IM消息
            RongYunUtil.sendBespokeMessage(params.getReceiverId(),"您有一条预约消息");

            //保存消息
            MessageReservation messageReservation = new MessageReservation();
            String msgId = DateUtil.dateFormat(new Date(), "yyyyMMddHHmmss") + RadomUtil.generateNumber2(4);
            Date curDate = new Date();
            messageReservation.setId(Long.parseLong(msgId));
            messageReservation.setContent("希望能快一点看到我的消息给我回复");
            messageReservation.setCreateTime(curDate);
            messageReservation.setCustomerId(params.getCustomerId());
            messageReservation.setPriceUnit(params.getPriceUnit()+"-"+params.getPriceUnitTimeCount());
            messageReservation.setReceiverId(params.getReceiverId());
            messageReservation.setStatus((byte) 0);
            messageReservation.setTitle(params.getTitle());
            int insert = messageReservationMapper.saveBookingMessage(messageReservation);
            return ResultUtils.resultSuccess(insert);
        }
    }

    @Override
    public CommonResponse queryBookingMessage(BookingMessageQueryRequest params) {
        List<MessageReservation> messageReservationList = messageReservationMapper.queryBookingMessage(params.getReceiverId());
        List<MessageReservationVO> mrsList = new ArrayList<>();
        for (MessageReservation mr: messageReservationList){
            Customer customer = customerService.queryCustomerByCustomerId(mr.getCustomerId());
            MessageReservationVO mrv = new MessageReservationVO();
            mrv.setId(mr.getId());
            mrv.setCustomerId(mr.getCustomerId());
            mrv.setContent(mr.getContent());
            mrv.setCreateTime(DateUtil.dateFormat(mr.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            if (customer!=null){
                mrv.setCustomerName(customer.getNickName());
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
}
