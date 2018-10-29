package com.honglu.quickcall.databury.producer;

/**
 * rabbitmq消息处理对象
 * @author xiangping
 * @date 2018年10月28日 18:44:15
 */
public class XPMessage extends Object{

    private static final long serialVersionUID = -955848178701129240L;
    /**
     * 队列业务名称
     */
    private String businessCode;
    /**
     * 流水号
     */
    private String traceId;
    /**
     * 消息编号
     */
    private String messageId;
    /**
     * 交换机名称
     */
    private String exchangeName;
    /**
     * 路由键key
     */
    private String routingKey;
    /**
     * 信息内容
     */
    private String messageBody;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    @Override
    public String toString() {
        return "XPMessage{" +
                "businessCode='" + businessCode + '\'' +
                ", traceId='" + traceId + '\'' +
                ", messageId='" + messageId + '\'' +
                ", exchangeName='" + exchangeName + '\'' +
                ", routingKey='" + routingKey + '\'' +
                ", messageBody='" + messageBody + '\'' +
                '}';
    }
}
