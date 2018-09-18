package com.honglu.quickcall.common.api.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientUtils {
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
}
