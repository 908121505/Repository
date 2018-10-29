package com.honglu.quickcall.databury.consumer;

/**
 * 数据埋点消费端
 * @author xiangping
 * @date 2018-10-28 22:33
 */
public class DataBuryCustomer{// implements ChannelAwareMessageListener {
//    private final static Logger LOGGER = LoggerFactory.getLogger(DataBuryCustomer.class);
//
//    private static final String encoding = "UTF-8";
//    private static final String exchange = "dead-letter-exchange";
//    private static final String routingKey = "consumer-dead-routing-key";
//
//
//    @Autowired
//    private DataBuriedPointService dataBuriedPointService;
//
//    @Override
//    public void onMessage(Message message, Channel channel) throws Exception {
//        String json = "";
//        try {
//            json = new String(message.getBody(), encoding);
//            LOGGER.info("数据埋点消费端收到--【BuriedPointCustomer】 RabbitMQ消息 :" + json);
//            LOGGER.info("consumer--:"+message.getMessageProperties()+":"+ new String(message.getBody()));
//            Map<String,Object> data = JSON.parseObject(json);
//            String mqType = (String)data.get("mqType");
//            LOGGER.info("========开始消费=========");
//            switch (mqType) {
//                case "pageview":
//                    dataBuriedPointService.saveWebBrowseThePageData(data);
//                    break;
//                case "WebClick" :
//                    dataBuriedPointService.saveWebClickData(data);
//                    break;
//                default:break;
//            }
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//        }catch (Exception e){
//            e.printStackTrace();
//            //ack返回false，并重新回到队列
//            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
//            AMQP.BasicProperties properties = new AMQP.BasicProperties();
//            channel.basicPublish(exchange, routingKey, properties,json.getBytes());
//        }
//    }
}
