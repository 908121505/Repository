package com.honglu.quickcall.databury.consumer;

/**
 * MQ基类
 *
 * @author xiangpign
 * @date 2018-10-28
 */
public abstract class AbstractConsumer {

//    /**
//     * 创建简单消息监听器容器
//     *
//     * @param connectionFactory
//     * @param queueNames
//     * @return
//     */
//    public SimpleMessageListenerContainer createSimpleMessageListenerContainer(ConnectionFactory connectionFactory,
//                                                                               String... queueNames) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        // 监听的队列
//        container.setQueueNames(queueNames);
//        // 根据情况确认消息
//        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
//        container.setMessageListener((MessageListener) (message) -> consumerAround(message));
//        return container;
//    }
//
//    /**
//     * 记录日志
//     * @param message
//     */
//    public void consumerAround(Message message){
//        try{
//            consumer(message);
//            //TODO 记录日志
//        }catch (Exception e){
//            e.printStackTrace();
//            //TODO 记录日志
//        }
//    }
//
//    /**
//     * 具体消费消息的业务逻辑方法
//     *
//     * @param message
//     */
//    public abstract void consumer(Message message);

}
