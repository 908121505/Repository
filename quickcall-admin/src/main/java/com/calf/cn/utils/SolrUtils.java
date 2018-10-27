package com.calf.cn.utils;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrException;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**  
 * solr 工具类  
 * Created by wlj
 */  
public class SolrUtils {  
	
	private final static Logger logger = LoggerFactory.getLogger(SolrUtils.class);
	
    /**  
     * solr 服务器访问地址  
     */  
	 private static String url;
	 
	 private static String server;
	 
	 private static Integer connectionTimeout;
	 
	 private static Integer defaltMaxConnectionsPerHost;
	 
	 private static Integer maxTotalConnections;
	 
	 private static Boolean  followRedirects;
	 
	 private static Boolean allowCompression;
	 
	 private static HttpSolrClient httpSolrClient = null;  
	 
	 public SolrUtils() {
		}

    static {
		InputStream is = null;
		try {
			Properties constant = new Properties();
			is = SolrUtils.class.getClassLoader().getResourceAsStream("solr.properties");
			if (is != null) {
				constant.load(is);
			}
			
			url= constant.getProperty("solr.url");
			
			server=constant.getProperty("solr.server");
			
			connectionTimeout=StringUtils.isNotBlank(constant.getProperty("solr.connectionTimeout"))?
					Integer.parseInt(constant.getProperty("solr.connectionTimeout")):100;
					
			defaltMaxConnectionsPerHost=StringUtils.isNotBlank(constant.getProperty("solr.defaltMaxConnectionsPerHost"))?
					Integer.parseInt(constant.getProperty("solr.defaltMaxConnectionsPerHost")):100;
					
			maxTotalConnections	=StringUtils.isNotBlank(constant.getProperty("solr.maxTotalConnections"))?
					Integer.parseInt(constant.getProperty("solr.maxTotalConnections")):100;
					
			followRedirects=Boolean.parseBoolean(constant.getProperty("solr.followRedirects"));
			
			allowCompression=Boolean.parseBoolean(constant.getProperty("solr.allowCompression"));
		} catch (IOException e) {
			logger.error("读取solr配置文件出错", e);
		} catch (Exception e) {
			logger.error("SolrUtils init error", e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error("关闭solr配置文件流出错", e);
				}
			}
		}
	}
    
    /**  
     * 将SolrDocument 转换为Bean  
     * @param record  
     * @param clazz 
     * @return bean  
     */  
    public static Object toBeanExt(SolrDocument record, Class<?> clazz){  
    	Object obj = null;  
    	try {  
    		obj = clazz.newInstance();  
    	} catch (InstantiationException e1) {  
    		e1.printStackTrace();  
    	} catch (IllegalAccessException e1) {  
    		e1.printStackTrace();  
    	}  
    	Field[] fields = clazz.getDeclaredFields();  
    	for(Field field:fields){  
    		Object value = record.get(field.getName());  
    		if(value == null){
    			continue;
    		}
    		try {  
    			BeanUtils.setProperty(obj, field.getName(), value);  
    		} catch (IllegalAccessException e) {  
    			e.printStackTrace();  
    		} catch (InvocationTargetException e) {  
    			e.printStackTrace();  
    		}  
    	}  
    	return obj;  
    }  
    
    /**  
     * @param
     * @return SolrInputDocument  
     */  
    public static SolrInputDocument addFileds(Map<String,Object> map, SolrInputDocument document){  
  
        if(document == null){  
            document = new SolrInputDocument();  
        }  
        Iterator iterator = map.keySet().iterator();  
        while (iterator.hasNext()){  
            String key = iterator.next().toString();  
            document.setField(key,map.get(key));  
        }  
        return document;  
    }  
  
    /**  
     *建立solr链接，获取 HttpSolrClient  
     * @return HttpSolrClient  
     */  
    public static HttpSolrClient connect(){  
  
      if(httpSolrClient == null){
        try{  
            httpSolrClient = new HttpSolrClient.Builder(url+"/"+server).build();  
            httpSolrClient.setParser(new XMLResponseParser());//设定xml文档解析器  
            httpSolrClient.setConnectionTimeout(connectionTimeout);//socket read timeout  
            httpSolrClient.setAllowCompression(allowCompression);  
            httpSolrClient.setMaxTotalConnections(maxTotalConnections);  
            httpSolrClient.setDefaultMaxConnectionsPerHost(defaltMaxConnectionsPerHost);  
            httpSolrClient.setFollowRedirects(followRedirects);  
        }catch (SolrException e){  
        	logger.error("请检查tomcat服务器或端口是否开启!");  
            e.printStackTrace();  
        }  
      }
        return  httpSolrClient;  
    }  
  
    /**  
     * 将SolrDocument 转换为Bean  
     * @param record  
     * @param clazz 
     * @return bean  
     */  
    public static Object toBean(SolrDocument record, Class clazz){  
        Object obj = null;  
        try {  
            obj = clazz.newInstance();  
        } catch (InstantiationException e1) {  
            e1.printStackTrace();  
        } catch (IllegalAccessException e1) {  
            e1.printStackTrace();  
        }  
        Field[] fields = clazz.getDeclaredFields();  
        for(Field field:fields){  
            Object value = record.get(field.getName());  
            try {  
                BeanUtils.setProperty(obj, field.getName(), value);  
            } catch (IllegalAccessException e) {  
                e.printStackTrace();  
            } catch (InvocationTargetException e) {  
                e.printStackTrace();  
            }  
        }  
        return obj;  
    }  
    
    /**  
     * 将实体对象 转换为Map
     * @param object 
     * @return HashMap  
     */  
    public static HashMap<String, Object> beanToMap(Object obj) {   
        HashMap<String, Object> params = new HashMap<String, Object>(0);   
            try {   
                PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();   
                PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);   
                for (int i = 0; i < descriptors.length; i++) {   
                    String name = descriptors[i].getName();   
                    if (!"class".equals(name)) {   
                        params.put(name, propertyUtilsBean.getNestedProperty(obj, name));   
                    }   
                }   
            } catch (Exception e) {   
                e.printStackTrace();   
            }   
            return params;   
    }  
}
