package com.honglu.quickcall.task.job;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.honglu.quickcall.account.facade.vo.BarrageMessageVO;
import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.task.dao.FadeCustomerMapper;
import com.honglu.quickcall.task.dao.SkillItemMapper;
import com.honglu.quickcall.task.entity.FadeCustomer;
import com.honglu.quickcall.task.entity.SkillItem;
import com.honglu.quickcall.task.entity.example.FadeCustomerExample;
import com.honglu.quickcall.task.entity.example.SkillItemExample;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 弹幕消息Job任务
 *
 * @author duanjun
 * @date 2018-10-18 14:35
 */
@Component
public class BarrageMessageJob {
	public static final Logger LOGGER = LoggerFactory.getLogger(BarrageMessageJob.class);

	@Autowired
	private FadeCustomerMapper fadeCustomerMapper;
	@Autowired
	private SkillItemMapper skillItemMapper;

	/**
	 * 弹幕消息队列redis key
	 */
	private static final String BARRAGE_MESSAGE_QUEUE_REDIS_KEY = "message:barrage_queue";

	@Scheduled(cron = "0/30 * * * * ?")
	public void lpushMessage() {
		LOGGER.info("执行弹幕消息Job任务----------------");
		Jedis jedis = null;
		JedisPool db2_pool = null;
		try {
			db2_pool = JedisUtil.getJedisPoolDB2();
			jedis = db2_pool.getResource();
			Long lenth = jedis.llen(BARRAGE_MESSAGE_QUEUE_REDIS_KEY);
			LOGGER.info("缓存 redis 队列中弹幕消息数量：{}", lenth);
			if (lenth > 0) {
				return;
			}
			LOGGER.info("弹幕消息队列为空，自动生成一条弹幕数据--------------");

			// 跳过index条查询一条数据
			FadeCustomer fadeCustomer = fadeCustomerMapper.selectOneByRandom();
			if(fadeCustomer == null){
				LOGGER.info("未查询到随机用户的配置数据 ----- 只有不查询假弹幕消息了----");
				return;
			}
			// 封装弹幕消息
			BarrageMessageVO barrageMessageVO = new BarrageMessageVO();
			barrageMessageVO.setNickName("*" + fadeCustomer.getNickName().substring(1));
			barrageMessageVO.setHeadPortraitUrl(fadeCustomer.getHeadPortraitUrl());

			// 随机选取一条技能数据
			SkillItem skill = skillItemMapper.selectOneByRandom();
			if(skill == null){
				LOGGER.info("未查询到可用技能配置数据 ----- 只有不查询假弹幕消息了----");
				return;
			}
			barrageMessageVO.setSkillId(skill.getId());
			barrageMessageVO.setProductName(skill.getSkillItemName());
			barrageMessageVO.setOrderAmounts(randomMoney(skill));
			barrageMessageVO.setOrderTime(new Date());

			String barrageJsonStr = JSON.toJSONString(barrageMessageVO);
			jedis.lpush(BARRAGE_MESSAGE_QUEUE_REDIS_KEY, barrageJsonStr);

			LOGGER.info("随机一条弹幕消息成功：" + barrageJsonStr);
		} catch (Exception e) {
			LOGGER.error("执行弹幕消息Job redis 异常：", e);
			db2_pool.returnBrokenResource(jedis);
		} finally {
			db2_pool.returnResource(jedis);
		}
	}

	/**
	 * 随机计算金额
	 *
	 * @param skill
	 * @return
	 */
	private BigDecimal randomMoney(SkillItem skill) {
		BigDecimal skillPrice = skillItemMapper.selectOneSkillPrice(skill.getId());
		if (skillPrice == null) {
			throw new IllegalArgumentException("未查询到技能的价格" + skill.getId() + " -- " + skill.getSkillItemName());
		}
		return skillPrice;
	}

}
