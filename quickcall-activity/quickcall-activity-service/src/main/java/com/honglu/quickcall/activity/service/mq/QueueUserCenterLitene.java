package com.honglu.quickcall.activity.service.mq;


import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import com.rabbitmq.client.Channel;

public class QueueUserCenterLitene implements ChannelAwareMessageListener{
	
	private Logger logger = Logger.getLogger(QueueUserCenterLitene.class);
	
	
	@Override
	public void onMessage(Message message, Channel channel) throws Exception {/*
		logger.info("===============55555555555555555===============");
		String json = "";
		try {
			json = new String(message.getBody(), "UTF-8");
			this.logger.info("【QueueUserCenterLitener】 RabbitMQ消息 :" + json);
			this.logger.info("consumer--:"+message.getMessageProperties()+":"+new String(message.getBody()));
			Map<String,Object> map = JSON.parseObject(json);
			int mqtype = Integer.parseInt(map.get("mqtype")+"");
			switch (mqtype) {
				case MQtype.MQ_TYPE_ACTIVITY:
					PresonMQRequest presonMQRequest = JSON.parseObject(json, PresonMQRequest.class);
					activityService.rechargeActivity(presonMQRequest);
					break;
				case MQtype.MQ_RECHARGE_ALWAYS_REWARD:
					logger.info("进入了充值 首冲/充值就送 mq逻辑");
					activityService.rechargedAlwaysAward((Integer)map.get("presonId"), (BigDecimal)(map.get("money")));
					break;
				default:
					logger.error("mq消息未走对应方法:参数为"+map);
					break;
			}
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
			     
		}catch (Exception e){
			e.printStackTrace();
			//ack返回false，并重新回到队列
			channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
			AMQP.BasicProperties properties = new AMQP.BasicProperties();
			channel.basicPublish("dead-letter-exchange", "dead", properties,
			json.getBytes());

		}
	*/}

}
