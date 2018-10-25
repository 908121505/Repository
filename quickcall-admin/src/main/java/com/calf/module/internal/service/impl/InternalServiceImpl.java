package com.calf.module.internal.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.calf.cn.service.BaseManager;
import com.calf.module.internal.constant.RedisCons;
import com.calf.module.internal.entity.Message;
import com.calf.module.internal.entity.MessageCustomer;
import com.calf.module.internal.service.InternalService;
import com.calf.module.mq.service.CustomerMessageService;
import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author: xiangxianjin
 * @date: 2018/10/25 16:59
 * @description:
 */
@Service
public class InternalServiceImpl implements InternalService {
    private static final Logger LOGGER = LoggerFactory.getLogger(InternalServiceImpl.class);

    @Autowired
    private BaseManager baseManager;

    @Autowired
    private CustomerMessageService customerMessageService;

    private static final int BATCH_INSERT_SIZE = 100;

    /**
     * 新增
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addMessage(Message entity) {
        int count = baseManager.insert("MessageMapper.insert",entity);
        //消息实时发送,发送通知消息到mq，异步发布消息给客户
        if(entity.getSendStatus() == 2){
            customerMessageService.sendInternalMessage(entity.getMessageId());
        }
        return count;
    }

    /**
     * 新增
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateMessage(Message entity) {
        int count = baseManager.update("MessageMapper.update",entity);
        //消息实时发送,发送通知消息到mq，异步发布消息给客户
        if(entity.getSendStatus() == 2){
            customerMessageService.sendInternalMessage(entity.getMessageId());
        }
        return count;
    }

    /**
     * 发送消息
     *
     * @param messageId
     */
    @Override
    public void sendMessage(String messageId) {
        Message entity = baseManager.get("MessageMapper.selectByPrimaryKey", new Object[]{messageId});

        String key = RedisCons.ADMIN_MESSAGE_CUSTOMER + entity.getMessageDescribe();
        Set<String> phoneSet = JSON.parseObject(JedisUtil.get(key), new TypeReference<HashSet<String>>() {});
        if(!CollectionUtils.isEmpty(phoneSet)){
            int i = 0;
            int size = phoneSet.size();
            List<MessageCustomer> messageCustomerList = new ArrayList<MessageCustomer>();
            phoneSet.stream().forEach(phone ->{
                String customerId = baseManager.get("Customer.selectCustomerIdByPhone", new Object[]{phone});
                MessageCustomer messageCustomer = new MessageCustomer();
                messageCustomer.setId(UUIDUtils.getId().toString());
                messageCustomer.setMessageId(Long.parseLong(entity.getMessageId()));
                messageCustomer.setPhone(Long.parseLong(phone));
                messageCustomer.setReceiverId(Long.parseLong(customerId));
                messageCustomer.setMessageStatus(Byte.parseByte("0"));
                messageCustomer.setCreateMan(entity.getCreateMan());
                messageCustomer.setCreateTime(new Date());
                messageCustomer.setModifyMan(entity.getModifyMan());
                messageCustomer.setModifyTime(new Date());

                if (i % BATCH_INSERT_SIZE == 0 || i == (size - 1)) {
                    int count = baseManager.batchInsert("MessageCustomerMapper.selectCustomerIdByPhone", messageCustomerList);
                    messageCustomerList.clear();
                    LOGGER.info("保存客户站内消息详情成功，保存数量:" + count);
                }
            });
        }

        LOGGER.info("消息编号：{}，发送成功", messageId);
    }
}
