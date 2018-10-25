package com.calf.module.internal.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.DateUtil;
import com.calf.module.customer.entity.Customer;
import com.calf.module.internal.constant.RedisCons;
import com.calf.module.internal.entity.Message;
import com.calf.module.internal.entity.MessageCustomer;
import com.calf.module.internal.service.InternalService;
import com.calf.module.mq.service.CustomerMessageService;
import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import com.honglu.quickcall.common.third.rongyun.util.RongYunUtil;
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

    private static final int BATCH_INSERT_SIZE = 1000;

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
    @Transactional(rollbackFor = Exception.class)
    public int sendMessage(String messageId) {
        Message entity = baseManager.get("MessageMapper.selectByPrimaryKey", new Object[]{messageId});
        entity.setBespeakSendTime(new Date());
        int count = baseManager.update("MessageMapper.update",entity);
        if(count != 1){
            throw new RuntimeException("消息更新失败，消息编号："+messageId);
        }

        int total = 0;
        //发送给指定用户
        if(entity.getSendType().intValue() == 0){
            String key = RedisCons.ADMIN_MESSAGE_CUSTOMER + entity.getMessageDescribe();
            Set<String> phoneSet = JSON.parseObject(JedisUtil.get(key), new TypeReference<HashSet<String>>() {});
            if(!CollectionUtils.isEmpty(phoneSet)){
                //保存记录
                saveMessageCustomer(phoneSet, entity );
            }
            total = phoneSet.size();
        }else if(entity.getSendType().intValue() == 1){
            //发送给所有的用户
            total = sendMessageAllUser(entity);
        }else {
            LOGGER.info("消息编号：{}，发送失败，发送对象【{}】不存在", messageId, entity.getSendType());
        }

        LOGGER.info("消息编号：{}，发送成功", messageId);
        return total;
    }

    /**
     * 发送站内消息给所有用户（近6月活跃用户）
     * @param entity
     * @return
     */
    private int sendMessageAllUser(Message entity){
        //TODO 直接查询全量用户，循环发送，此处有风险点：若用户几何级增长，轮询发送会出问题，折中取了近6月
        Integer  pageSize =  BATCH_INSERT_SIZE;
        Map<String, Object>  params =  new HashMap<String, Object>();
        params.put("pageSize", pageSize);
        //当前时间
        params.put("endTime", DateUtil.getSixMonth()[0]);
        //六个月之前时间
        params.put("startTime", DateUtil.getSixMonth()[1]);
        int page = 0;
        int pageIndex = 0;
        while (true) {
            pageIndex = page > 0 ? page * pageSize + 1 : pageIndex;
            params.put("pageIndex", pageIndex);
            List<Customer>  customerList = baseManager.query("Customer.selectCustomerList",params);
            if(CollectionUtils.isEmpty(customerList)){
                break;
            }
            List<MessageCustomer> messageCustomerList = new ArrayList<MessageCustomer>();
            for(int i=0;i<customerList.size();i++){
                MessageCustomer messageCustomer = new MessageCustomer();
                Customer customer = customerList.get(i);
                String customerId = customer.getCustomerId();
                String id = UUIDUtils.getId().toString();
                messageCustomer.setId(id);
                messageCustomer.setMessageId(Long.parseLong(entity.getMessageId()));
                messageCustomer.setPhone(Long.parseLong(customer.getPhone()));
                messageCustomer.setReceiverId(Long.parseLong(customerId));
                messageCustomer.setMessageStatus(Byte.parseByte("0"));
                messageCustomer.setCreateMan(entity.getCreateMan());
                messageCustomer.setCreateTime(new Date());
                messageCustomer.setModifyMan(entity.getModifyMan());
                messageCustomer.setModifyTime(new Date());
                messageCustomerList.add(messageCustomer);
                this.imSendMessage(entity.getType().intValue(), customerId, entity.getMessageContent(), id);
            }
            baseManager.batchInsert("MessageCustomerMapper.insertBatch", messageCustomerList);

            if(customerList.size() < pageSize){
                pageIndex = pageIndex + customerList.size();
                break ;
            }
            page++;
        }
        return pageIndex;
    }

    /**
     * 批量保存客户消息明细
     * @param phoneSet
     * @param entity
     */
    private void saveMessageCustomer(Set<String> phoneSet, Message entity){
        int size = phoneSet.size();
        List<MessageCustomer> messageCustomerList = new ArrayList<MessageCustomer>();
        int i = 0;
        while (phoneSet.iterator().hasNext()){
            String phone = phoneSet.iterator().next();
            String customerId = baseManager.get("Customer.selectCustomerIdByPhone", new Object[]{phone});
            MessageCustomer messageCustomer = new MessageCustomer();
            String id = UUIDUtils.getId().toString();
            messageCustomer.setId(id);
            messageCustomer.setMessageId(Long.parseLong(entity.getMessageId()));
            messageCustomer.setPhone(Long.parseLong(phone));
            messageCustomer.setReceiverId(Long.parseLong(customerId));
            messageCustomer.setMessageStatus(Byte.parseByte("0"));
            messageCustomer.setCreateMan(entity.getCreateMan());
            messageCustomer.setCreateTime(new Date());
            messageCustomer.setModifyMan(entity.getModifyMan());
            messageCustomer.setModifyTime(new Date());
            messageCustomerList.add(messageCustomer);
            this.imSendMessage(entity.getType().intValue(), customerId, entity.getMessageContent(), id);
            if (i % BATCH_INSERT_SIZE == 0 || i == (size - 1)) {
                int batchCount = baseManager.batchInsert("MessageCustomerMapper.insertBatch", messageCustomerList);
                messageCustomerList.clear();
                LOGGER.info("保存客户站内消息详情成功，保存数量:" + batchCount);
            }
        }
    }

    /**
     * 发送消息
     * @param type          消息类型
     * @param customerId    客户编号
     * @param content       消息内容
     * @param id            客户消息编号
     */
    private void imSendMessage(int type, String customerId, String content, String id){
        if(type == 0){
            RongYunUtil.sendSystemMessage(Long.parseLong(customerId), content);
        }else if(type == 1){
            RongYunUtil.sendActivityMessage(Long.parseLong(customerId), content);
        }else{
            LOGGER.info("消息类型【{}】不存在，客户编号：{}，客户消息编号：{}", type, customerId, id);
        }
    }

    public static void main(String[] args) {
        String str = "[\n" +
                "  \"13100000009\",\n" +
                "  \"13100000010\",\n" +
                "  \"13100000001\",\n" +
                "  \"13100000002\",\n" +
                "  \"13100000003\",\n" +
                "  \"13100000004\",\n" +
                "  \"13100000005\",\n" +
                "  \"13100000006\",\n" +
                "  \"13100000007\",\n" +
                "  \"13100000008\"\n" +
                "]";
        Set<String> phoneSet = JSON.parseObject(str, new TypeReference<HashSet<String>>() {});

        System.out.println(phoneSet.size());
    }
}
