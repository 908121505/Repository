package com.honglu.quickcall.user.service.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * 封装HTTP请求
 * @author SteveGuo
 * @date 2017-12-25 16:40 PM
 */
public class HttpUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);

    private static CloseableHttpClient httpClient = HttpClientFactory.createMixHttpClient();

    /**
     * 发送GET请求
     * @param url
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T doGet(String url, Class<T> clazz){
        CloseableHttpResponse response = null;
        try{
            HttpGet get = new HttpGet(url);
            get.setHeader("Accept-Encoding", "gzip");
            LOGGER.debug("GET请求的url:{}", url);
            response = httpClient.execute(get);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if(statusCode == HttpStatus.SC_OK){
//                T result = JSONUtils.parseObject(EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8.name()), clazz);
                String entityStr = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8.name());
                LOGGER.info("返回的数据为:{}",entityStr);
                T result = JSON.parseObject(entityStr, clazz);
                LOGGER.debug("GET响应结果:{}", JSONUtils.toJSONString(result));
                return result;
            }
            String reasonPhrase = statusLine.getReasonPhrase();
            throw new RuntimeException(String.format("GET响应状态码:%1$d，结果：%2$s", statusCode, reasonPhrase));
        }catch (Exception ex){
            ex.printStackTrace();
            LOGGER.error("GET出现异常：{}", ex.getMessage());
            throw new RuntimeException(ex);
        }finally {
            HttpClientUtils.closeQuietly(response);
        }
    }

    /**
     * 发送POST请求
     * @param url
     * @param body
     * @param contentType
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T doPost(String url, String body, String contentType, Class<T> clazz){
        CloseableHttpResponse response = null;
        try{
            HttpPost post = new HttpPost(url);
            StringEntity stringEntity = new StringEntity(body, StandardCharsets.UTF_8.name());
            stringEntity.setContentType(contentType);
            post.setEntity(stringEntity);
            LOGGER.debug("POST请求的url:{}，参数：{}", url, stringEntity);
            response = httpClient.execute(post);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if(statusCode == HttpStatus.SC_OK){
                T result;
                String entity = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8.name());
                if(clazz.getName().equals("java.lang.String")) {
                    result = (T)entity;
                } else {
                    result = JSONUtils.parseObject(entity, clazz);
                }
                LOGGER.debug("POST响应结果:{}", JSONUtils.toJSONString(result));
                return result;
            }
            String reasonPhrase = statusLine.getReasonPhrase();
            throw new RuntimeException(String.format("POST响应状态码:%1$d，结果：%2$s", statusCode, reasonPhrase));
        }catch (Exception ex){
            ex.printStackTrace();
            LOGGER.error("POST出现异常：{}", ex.getMessage());
            throw new RuntimeException(ex);
        } finally {
            HttpClientUtils.closeQuietly(response);
        }
    }

    /**
     * 上传图片到服务器
     * @param url
     * @param in
     * @param fileName
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T uploadImage(String url, InputStream in, String fileName, Class<T> clazz) {
        CloseableHttpResponse response = null;
        try{
            HttpPost post = new HttpPost(url);
            HttpEntity httpEntity = MultipartEntityBuilder.create()
                    .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .addBinaryBody("media", IOUtils.toByteArray(in), ContentType.DEFAULT_BINARY, fileName)
                    .build();
            post.setEntity(httpEntity);
            LOGGER.debug("上传图片的url:{}，参数：{}", url, httpEntity);
            response = httpClient.execute(post);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if(statusCode == HttpStatus.SC_OK){
                T result = JSONUtils.parseObject(EntityUtils.toString(response.getEntity(), StandardCharsets
                        .UTF_8.name()), clazz);
                LOGGER.debug("上传图片应结果:{}", result);
                return result;
            }
            String reasonPhrase = statusLine.getReasonPhrase();
            throw new RuntimeException(String.format("POST响应状态码:%1$d，结果：%2$s", statusCode, reasonPhrase));
        }catch (Exception ex){
            ex.printStackTrace();
            LOGGER.error("POST出现异常：{}", ex.getMessage());
            throw new RuntimeException(ex);
        } finally {
            HttpClientUtils.closeQuietly(response);
        }
    }

    /**
     * 下载图片
     * @param url
     * @param basePath
     * @param dirPath
     * @return
     */
    public static String downloadImage(String url, String basePath, String dirPath){
        CloseableHttpResponse response = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try{
            HttpGet get = new HttpGet(url);
            LOGGER.debug("下载图片的url:{}", url);
            response = httpClient.execute(get);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if(statusCode == HttpStatus.SC_OK){
                String canonicalPath = getCanonicalPath(basePath, dirPath);
                String imageName = getImageName(response.getHeaders("Content-disposition"));
                String filePath = canonicalPath + imageName;
                LOGGER.debug("服务器图片存储路径：{}", filePath);
                fos = new FileOutputStream(new File(filePath));
                bos = new BufferedOutputStream(fos);
                IOUtils.copy(IOUtils.toBufferedInputStream(response.getEntity().getContent()), bos);
                return dirPath + File.separator + imageName;
            }
            String reasonPhrase = statusLine.getReasonPhrase();
            throw new RuntimeException(String.format("GET请求下载多媒体文件:%1$d，结果：%2$s", statusCode, reasonPhrase));
        }catch (Exception ex){
            ex.printStackTrace();
            LOGGER.error("GET请求下载多媒体文件出现异常：{}", ex.getMessage());
            throw new RuntimeException(ex);
        }finally {
            HttpClientUtils.closeQuietly(response);
            IOUtils.closeQuietly(bos);
            IOUtils.closeQuietly(fos);
        }
    }

    /**
     * 下载图片
     * @param url
     * @return
     */
    public static InputStream downloadImageInputStream(String url){
        CloseableHttpResponse response = null;
        try{
            HttpGet get = new HttpGet(url);
            LOGGER.debug("下载图片的url:{}", url);
            response = httpClient.execute(get);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if(statusCode == HttpStatus.SC_OK){
                return response.getEntity().getContent();
            }
            String reasonPhrase = statusLine.getReasonPhrase();
            throw new RuntimeException(String.format("GET请求下载多媒体文件:%1$d，结果：%2$s", statusCode, reasonPhrase));
        }catch (Exception ex){
            ex.printStackTrace();
            LOGGER.error("GET请求下载多媒体文件出现异常：{}", ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    private static String getCanonicalPath(String basePath, String dirPath){
        if(StringUtils.isBlank(dirPath)) {
            dirPath = "temp/";
        }
        String canonicalPath = basePath + dirPath + File.separator;
        File fileDirectory = new File(canonicalPath);
        if(!fileDirectory.exists() || !fileDirectory.isDirectory()) {
            fileDirectory.mkdirs();
        }
        return canonicalPath;
    }

    private static String getImageName(Header...headers) {
        String fileName = "";
        if(null != headers && headers.length > 0){
            String disposition = headers[0].getValue();
            if(StringUtils.isNotBlank(disposition)) {
                fileName = disposition.substring(disposition.indexOf("=") + 2, disposition.length() - 1);
            }
        }else {
            fileName = UUID.randomUUID().toString().concat(".jpg");
        }
        return fileName;
    }



    public static String sendGet(String url, String params) {
        String result = "";
        InputStream inputStream = null;
        try {
            String urlNameString = url;
            if (params != null && (!"".equals(params))) {
                urlNameString = url + "?" + params;
            }

            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();

            // 定义 BufferedReader输入流来读取URL的响应
            inputStream = connection.getInputStream();

            try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                byte[] buffer = new byte[256];
                int len = 0;
                while ((len = inputStream.read(buffer)) != -1) {
                    bos.write(buffer, 0, len);
                }
                byte[] readBytes = bos.toByteArray();
                result = new String(readBytes, "UTF-8");
            }
        } catch (Exception e) {
            LOGGER.error("发送GET请求出现异常", e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
}
