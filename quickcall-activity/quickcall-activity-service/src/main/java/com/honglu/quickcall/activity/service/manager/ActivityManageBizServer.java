 package com.honglu.quickcall.activity.service.manager;

 import com.honglu.quickcall.common.core.processer.Adapter;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.context.support.ClassPathXmlApplicationContext;


 /**
  * <p>Title: 业务服务启动入口</p>
  * <p>Description: 提供dubbo服务，其它客户端采用tcp长连接调用本服务</p>
  * <p>Company: xiaoniu</p>
  *
  * @author conly.wang
  */
 public class ActivityManageBizServer implements Runnable {
     private static Logger logger = LoggerFactory.getLogger(ActivityManageBizServer.class);


     private static ClassPathXmlApplicationContext context;

     public static void main(String[] args) {
         logger.info("----------本地启动服务：开始加载----------");
         //只负责加载配置文件，启动服务
         context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
         context.start();
         Adapter.setContext(context);
         new Thread(new ActivityManageBizServer()).start();
         logger.info("----------本地启动服务：结束加载----------");
     }

     @Override
     public void run() {
         while (true) {
              try {
                  Thread.sleep(1000 * 60);
                  logger.info("============================= ActivityCenter is alive ! =======================================");
             } catch (InterruptedException e) {
                 logger.error(e.getMessage());
             }
         }
     }

 }
