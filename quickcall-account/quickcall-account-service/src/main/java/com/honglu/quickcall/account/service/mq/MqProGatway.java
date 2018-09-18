package com.honglu.quickcall.account.service.mq;


import java.util.UUID;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Service;

@Service("mqProGatway")
public class MqProGatway {

	private Logger logger = LoggerFactory.getLogger(MqProGatway.class);

	  @Resource
	  private AmqpTemplate amqpTemplate;
	  @Resource
	  private RabbitTemplate rmqpTemplate;
	  
	  public void sendDataToCrQueue(String queueKey,Object obj) {
	      amqpTemplate.convertAndSend(queueKey, obj);
	  }
	  
	  public void send(String exchange, String routingKey, Object message) {  
	    	amqpTemplate.convertAndSend(exchange, routingKey, message);
	    }  
	  
	  public void sendToCorrelationData(String exchange, String routingKey, Object obj) {
			String msgId = UUID.randomUUID().toString();
			Message message = MessageBuilder.withBody(obj.toString().getBytes())
					.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
					.setCorrelationId(msgId.getBytes()).build();
			CorrelationData date = new CorrelationData(msgId);
			// TODO 将 msgId 与 message 的关系保存起来,例如放到缓存中
			rmqpTemplate.send(exchange, routingKey, message, date);
		}
}
