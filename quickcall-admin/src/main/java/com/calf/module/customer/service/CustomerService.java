package com.calf.module.customer.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calf.cn.service.BaseManager;
import com.calf.module.customer.entity.CustomerVo;
import com.calf.module.internal.entity.Message;
import com.calf.module.internal.entity.MessageCustomer;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import com.honglu.quickcall.common.third.newrongyun.RongYunUtil;

@Service
@Transactional
public class CustomerService {
	@Autowired
	private BaseManager baseManager;

	private static Logger logger = Logger.getLogger(CustomerService.class);

	private static final String OPERATE_UNLOCK = "0";

	private static final String OPERATE_LOCK = "1"; // 锁住

	private static final String UNLOCK_SYSMSG = "您好,您已经被解封";

	private static final String LOCK_REMARK = "prohibition_login_out";

	private static final int LOCK_SKILL = 6;

	private static final ImmutableMap<Integer, String> LOCK_MAP = new ImmutableMap.Builder<Integer, String>()
			.put(4, "已封禁-无法接单").put(6, "已封禁-无法接指定技能").put(8, "已封禁-账户登录权限").put(10, "已封禁-设备登录权限").build();

	private static final ImmutableMap<Integer, String> RETURN_FOREVER_REASON = new ImmutableMap.Builder<Integer, String>()
			.put(12, "因您违反声优规则，您的声优资格被永久封禁，如有疑问，请拨打客服电话：400-156-0606进行咨询。")
			.put(6, "因您违反声优规则，您的%s技能被永久封禁，如有疑问，请拨打客服电话：400-156-0606进行咨询。")
			.put(8, "因违反平台规则，您的账号被永久限制登陆，如有疑问，请拨打客服电话：400-156-0606进行咨询。")
			.put(10, "因违反平台规则，您的设备被永久限制登陆，如有疑问，请拨打客服电话：400-156-0606进行咨询。").build();

	private static final ImmutableMap<Integer, String> RETURN_DATE_REASON = new ImmutableMap.Builder<Integer, String>()
			.put(12, "因您违反声优规则，您的声优资格将封禁至%s年%s月%s日%s时%s分前，如有疑问，请拨打客服电话：400-156-0606进行咨询。")
			.put(6, "因您违反声优规则，您的%s技能将封禁至%s年%s月%s日%s时%s分前，如有疑问，请拨打客服电话：400-156-0606进行咨询。")
			.put(8, "因您违反平台规则，您的账号在%s年%s月%s日%s时%s分前限制登陆，如有疑问，请拨打客服电话：400-156-0606进行咨询。")
			.put(10, "因您违反平台规则，您的设备在%s年%s月%s日%s时%s分前限制登陆，如有疑问，请拨打客服电话：400-156-0606进行咨询。").build();

	private static final ImmutableList<Integer> LOCK_LOGIN_ID = ImmutableList.of(8, 10);

	public void updateCustomer(CustomerVo customerVo) {
		String operate = customerVo.getOperate();
		boolean isForever = false;
		LocalDateTime blockEndTime = null;

		// 插入消息数据库
		Message msg = new Message();
		MessageCustomer mc = new MessageCustomer();
		Long messageId = UUIDUtils.getId();
		msg.setMessageId(messageId.toString());
		msg.setTitle("封禁解禁");
		mc.setPhone(Long.valueOf(customerVo.getPhone()));
		mc.setId(UUIDUtils.getId().toString());
		mc.setMessageId(messageId);
		mc.setReceiverId(Long.parseLong(customerVo.getCustomerId()));
		String content = "";

		if (OPERATE_UNLOCK.equals(operate)) {
			Map<String, Object> params = new HashMap<>();
			params.put("id", customerVo.getCustomerId());
			baseManager.update("Customer.unlockCustomers", params);
			if (customerVo.getCustStatusDispalyId() == LOCK_SKILL) {
				baseManager.update("Customer.unlockSkill", params);
			}
			// 插入消息数据库
			content = UNLOCK_SYSMSG;
			msg.setMessageContent(content);
			baseManager.insert("MessageMapper.insertSelective", msg);
			baseManager.insert("MessageCustomerMapper.insertSelective", mc);
			RongYunUtil.sendSystemMessage(Long.valueOf(customerVo.getCustomerId()), UNLOCK_SYSMSG);
		} else if (OPERATE_LOCK.equals(operate)) {
			Long addDays = Long.valueOf(customerVo.getClosureDate());
			if (addDays > 0) {
				Map<String, Object> params = new HashMap<>();
				params.put("custStatus", customerVo.getCustStatus());
				ZoneId zoneId = ZoneId.systemDefault();
				LocalDateTime blockStartTime = LocalDateTime.now();
				ZonedDateTime zonedDateTime = blockStartTime.atZone(zoneId);
				blockEndTime = blockStartTime.plusDays(addDays);
				ZonedDateTime zonedDateTime1 = blockEndTime.atZone(zoneId);
				params.put("blockStartTime", Date.from(zonedDateTime.toInstant()));
				params.put("blockEndTime", Date.from(zonedDateTime1.toInstant()));
				params.put("id", customerVo.getCustomerId());
				baseManager.update("Customer.lockCustomers", params);
			} else {
				Map<String, Object> params = new HashMap<>();
				params.put("blockStartTime", new Date());
				params.put("id", customerVo.getCustomerId());
				baseManager.update("Customer.lockForever", params);
				isForever = true;
			}
			if (customerVo.getCustStatus() == LOCK_SKILL && customerVo.getCustomerSkills().size() > 0) {
				Map<String, Object> params = new HashMap<>();
				params.put("id", customerVo.getCustomerId());
				for (String s : customerVo.getCustomerSkills()) {
					params.put("skillName", s);
					baseManager.update("Customer.lockSkill", params);
				}
			}
			StringBuilder builder = new StringBuilder();
			if (isForever) {
				if (customerVo.getCustStatus() == LOCK_SKILL) {
					StringBuilder skillBuilder = new StringBuilder();
					for (String s : customerVo.getCustomerSkills()) {
						skillBuilder.append(s);
						skillBuilder.append(",");
					}
					skillBuilder.deleteCharAt(skillBuilder.lastIndexOf(","));
					String format = String.format(
							builder.append(RETURN_FOREVER_REASON.get(customerVo.getCustStatus())).toString(),
							skillBuilder.toString());
					logger.info(format);

					// 插入消息数据库
					content = format;
					msg.setMessageContent(content);
					baseManager.insert("MessageMapper.insertSelective", msg);
					baseManager.insert("MessageCustomerMapper.insertSelective", mc);

					RongYunUtil.sendSystemMessage(Long.valueOf(customerVo.getCustomerId()), format, LOCK_REMARK);
				} else {
					builder.append(RETURN_FOREVER_REASON.get(customerVo.getCustStatus()));
					logger.info(builder.toString());

					// 插入消息数据库
					content = builder.toString();
					msg.setMessageContent(content);
					baseManager.insert("MessageMapper.insertSelective", msg);
					baseManager.insert("MessageCustomerMapper.insertSelective", mc);
					RongYunUtil.sendSystemMessage(Long.valueOf(customerVo.getCustomerId()), builder.toString(),
							LOCK_REMARK);
				}
			} else {
				if (customerVo.getCustStatus() == LOCK_SKILL) {
					StringBuilder skillBuilder = new StringBuilder();
					for (String s : customerVo.getCustomerSkills()) {
						skillBuilder.append(s);
						skillBuilder.append(",");
					}
					skillBuilder.deleteCharAt(skillBuilder.lastIndexOf(","));
					builder.append(RETURN_DATE_REASON.get(customerVo.getCustStatus()));
					String format = String.format(builder.toString(), skillBuilder.toString(), blockEndTime.getYear(),
							blockEndTime.getMonth().getValue(), blockEndTime.getDayOfMonth(), blockEndTime.getHour(),
							blockEndTime.getMinute());
					logger.info(format);

					// 插入消息数据库
					content = format;
					msg.setMessageContent(content);
					baseManager.insert("MessageMapper.insertSelective", msg);
					baseManager.insert("MessageCustomerMapper.insertSelective", mc);
					RongYunUtil.sendSystemMessage(Long.valueOf(customerVo.getCustomerId()), format);
				} else {
					builder.append(RETURN_DATE_REASON.get(customerVo.getCustStatus()));
					String format = String.format(builder.toString(), blockEndTime.getYear(),
							blockEndTime.getMonth().getValue(), blockEndTime.getDayOfMonth(), blockEndTime.getHour(),
							blockEndTime.getMinute());
					logger.info(format);
					if (LOCK_LOGIN_ID.contains(customerVo.getCustStatus())) {
						// 插入消息数据库
						content = format;
						msg.setMessageContent(content);
						baseManager.insert("MessageMapper.insertSelective", msg);
						baseManager.insert("MessageCustomerMapper.insertSelective", mc);
						RongYunUtil.sendSystemMessage(Long.valueOf(customerVo.getCustomerId()), format, LOCK_REMARK);
					} else {
						// 插入消息数据库
						content = format;
						msg.setMessageContent(content);
						baseManager.insert("MessageMapper.insertSelective", msg);
						baseManager.insert("MessageCustomerMapper.insertSelective", mc);
						RongYunUtil.sendSystemMessage(Long.valueOf(customerVo.getCustomerId()), format);
					}
				}
			}
		} else {
			throw new RuntimeException("非法操作");
		}

	}

}
