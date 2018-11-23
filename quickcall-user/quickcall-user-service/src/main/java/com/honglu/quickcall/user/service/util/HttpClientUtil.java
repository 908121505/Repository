package com.honglu.quickcall.user.service.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;


/**
 * httpClient工具类
 *
 * @author xiangping
 * @date 2018-11-23 15:04
 */
public class HttpClientUtil {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

    // 默认字符集
    private static final String ENCODING = "UTF-8";

    /**
     * post
     * @param url
     * @param headers
     * @param data
     * @param encoding
     * @return
     */
    public static String post(String url,Map<String, String> headers, JSONObject data, String encoding) {
        // 请求返回结果
        String resultJson = null;
        // 创建Client
        CloseableHttpClient client = HttpClients.createDefault();
        // 创建HttpPost对象
        HttpPost httpPost = new HttpPost();

        try {
            // 设置请求地址
            httpPost.setURI(new URI(url));
            // 设置请求头
            if (headers != null) {
                Header[] allHeader = new BasicHeader[headers.size()];
                int i = 0;
                for (Map.Entry<String, String> entry: headers.entrySet()){
                    allHeader[i] = new BasicHeader(entry.getKey(), entry.getValue());
                    i++;
                }
                httpPost.setHeaders(allHeader);
            }
            // 设置实体
            httpPost.setEntity(new StringEntity(JSON.toJSONString(data)));
            // 发送请求,返回响应对象
            CloseableHttpResponse response = client.execute(httpPost);
            // 获取响应状态
            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                // 获取响应结果
                resultJson = EntityUtils.toString(response.getEntity(), encoding);
            } else {
                log.error("响应失败，状态码：" + status);
            }

        } catch (Exception e) {
            log.error("发送post请求失败", e);
        } finally {
            httpPost.releaseConnection();
        }
        return resultJson;
    }

    /**
     * post
     * @param url
     * @param data
     * @return
     */
    public static String post(String url, JSONObject data) {
        // 设置默认请求头
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/json");

        return post(url, headers, data, ENCODING);
    }

    /** 
    * @Title: post
    * @param url 请求地址
    * @param params 请求实体
    * @author wangxy
    * @return String
    * @throws
    */
    public static String post(String url,Map<String,Object> params){
        // 设置默认请求头
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/json");
        // 将map转成json
        JSONObject data = JSONObject.parseObject(JSON.toJSONString(params));
        return post(url,headers,data,ENCODING);
    }

    /**
     * post
     * @param url
     * @param headers
     * @param data
     * @return
     */
    public static String post(String url, Map<String, String> headers, JSONObject data) {
        return post(url, headers, data, ENCODING);
    }

    /** 
    * @Title: post
    * @param url 请求地址
    * @param headers 请求头
    * @param params 请求实体
    * @author wangxy
    * @return String
    * @throws
    */
    public static String post(String url,Map<String,String> headers,Map<String,String> params){
        // 将map转成json
        JSONObject data = JSONObject.parseObject(JSON.toJSONString(params));
        return post(url,headers,data,ENCODING);
    }

    /**
     * get
     * @param url
     * @param params
     * @param encoding
     * @return
     */
    public static String get(String url,Map<String,Object> params,String encoding){
        // 请求结果
        String resultJson = null;
        // 创建client
        CloseableHttpClient client = HttpClients.createDefault();
        // 创建HttpGet
        HttpGet httpGet = new HttpGet();
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            // 封装参数
            if(params != null){
                for (String key : params.keySet()) {
                    builder.addParameter(key, params.get(key).toString());
                }
            }
            URI uri = builder.build();
            // 设置请求地址
            httpGet.setURI(uri);
            // 发送请求，返回响应对象
            CloseableHttpResponse response = client.execute(httpGet);
            // 获取响应状态
            int status = response.getStatusLine().getStatusCode();
            if(status == HttpStatus.SC_OK){
                // 获取响应数据
                resultJson = EntityUtils.toString(response.getEntity(), encoding);
            }else{
                log.error("响应失败，状态码：" + status);
            }
        } catch (Exception e) {
            log.error("发送get请求失败",e);
        } finally {
            httpGet.releaseConnection();
        }
        return resultJson;
    }

    /**
     * get
     * @param url
     * @param params
     * @return
     */
    public static String get(String url,Map<String,Object> params){
        return get(url,params,ENCODING);
    }

    /**
     * get
     * @param url
     * @return
     */
    public static String get(String url){
        return get(url,null,ENCODING);
    }
}