package com.honglu.quickcall.common.third.shumei;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.honglu.quickcall.common.api.util.HttpClientUtils;
import com.honglu.quickcall.common.api.util.SSLClient;
import com.honglu.quickcall.common.third.shumei.reponse.WordFilterResponse;
import com.honglu.quickcall.common.third.shumei.request.WordFilterRquest;

/**
 * 数美对接第三方接口
 * @author zhaozheyi
 *
 */
public class ShumeiUtils {
	private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
	public final static String SENSITIVEWORDURL = "http://api-sh.fengkongcloud.com/v2/saas/anti_fraud/text";
	
	public static WordFilterResponse sensitiveFilter(WordFilterRquest wfr){
		HashMap<String, Object> paramMap = new HashMap<>();
    	paramMap.put("accessKey", "A8ukd06uSubzLozglNo1");
    	paramMap.put("type", "ZHIBO");
    	paramMap.put("appId", "default");
    	
    	paramMap.put("data", wfr);
    	String json = JSON.toJSONString(paramMap, true);
    	System.out.println(json);
    	String res = doPost(SENSITIVEWORDURL,json);
    	JSONObject jb = JSONObject.parseObject(res);
    	JSONObject detail = JSONObject.parseObject(jb.getString("detail"));
    	WordFilterResponse response = new WordFilterResponse();
    	response.setCode(jb.getString("code"));
    	response.setScore(jb.getString("score"));
    	response.setRiskLevel(jb.getString("riskLevel"));
    	response.setMatchedItem(detail.getString("matchedItem"));
    	response.setFilteredText(detail.getString("filteredText"));
    	System.out.println(res);
    	System.out.println(response);
		return response;
	}
	
	
	
	/* 
  	 * 利用HttpClient进行post请求的工具类 
  	 */  
      private static String doPost(String url, String json){  
      	
      	String returnValue = null;  
          CloseableHttpClient httpClient = HttpClients.createDefault();  
          ResponseHandler<String> responseHandler = new BasicResponseHandler();  
          try{  
              //第一步：创建HttpClient对象  
          	httpClient = new SSLClient();  
          	//httpClient = HttpClients.createDefault(); 
          	
              //第二步：创建httpPost对象  
              HttpPost httpPost = new HttpPost(url);
              
              //第三步：给httpPost设置JSON格式的参数  
              StringEntity requestEntity = new StringEntity(json,"utf-8");  
              requestEntity.setContentEncoding("UTF-8");                
              httpPost.setHeader("Content-type", "application/json");  
              httpPost.setEntity(requestEntity);
              
              RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(1000).setConnectTimeout(1000).build();//设置请求和传输超时时间
              httpPost.setConfig(requestConfig);
              //第四步：发送HttpPost请求，获取返回值  
              returnValue = httpClient.execute(httpPost,responseHandler); //调接口获取返回值时，必须用此方法             
          }  
           catch(Exception e)  
          {  
          	 logger.info("httpcilent发送post请求失败");
          }  
            
          finally {  
             try {  
              httpClient.close();  
          } catch (IOException e) {  
          	logger.info("httpcilent关闭失败");
          }  
          }  
           //第五步：处理返回值  
           return returnValue;     
      } 
      
      public static void main(String[] args) {
    	  sensitiveFilter(new WordFilterRquest("我操你", "12312312312", "123"));
	}
}
