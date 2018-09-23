package com.honglu.quickcall.common.api.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtils {
	private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
    public static String sendJsonPost(String url, String params) {
        String jsonString = "";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //设置POST请求头
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");
        StringEntity entity = new StringEntity(params, "UTF-8");
        httpPost.setEntity(entity);

        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                jsonString = EntityUtils.toString(responseEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonString;
    }
    
    
    
	
  	
  	/* 
  	 * 利用HttpClient进行post请求的工具类 
  	 */  
      public static String doPost(String url, String json){  
      	
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
              
              //RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(2000).build();//设置请求和传输超时时间
              //httpPost.setConfig(requestConfig);
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
      
      /* 
  	 * 利用HttpClient进行post请求的工具类 
  	 * application/x-www-form-urlencoded
  	 */  
      public static String doPostForm(String url, String json){  
      	
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
              httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");  
              httpPost.setEntity(requestEntity);
              
              //RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(2000).build();//设置请求和传输超时时间
              //httpPost.setConfig(requestConfig);
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
      
      
      
      /* 
       * 利用HttpClient进行get请求的工具类 
       */  
      public static String doGet(String url,String charset){  
          if(null == charset){  
              charset = "utf-8";  
          }  
          HttpClient httpClient = null;  
          HttpGet httpGet= null;  
          String result = null;  
            
          try {  
              httpClient = new SSLClient();  
              httpGet = new HttpGet(url);  
                
              HttpResponse response = httpClient.execute(httpGet);  
              if(response != null){  
                  HttpEntity resEntity = response.getEntity();  
                  if(resEntity != null){  
                      result = EntityUtils.toString(resEntity,charset);  
                  }  
              }  
          } catch (Exception e) {  
          	logger.info("httpcilent发送get请求失败");
          }  
          
          return result;  
      }  

    
    
    
    
    
    
    
    
    
    
}
