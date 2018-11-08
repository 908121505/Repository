package com.honglu.quickcall.user.service.util;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * HTTP客户端工程类
 * @author SteveGuo
 * @date 2017-12-25 16:40 PM
 */
public class HttpClientFactory {

    /**
     * 创建混合的HttpClient，对http和https都支持
     * @return
     */
    public static CloseableHttpClient createMixHttpClient(){
        SSLConnectionSocketFactory connectionSocketFactory;
        try{
            SSLContext sslcontext = SSLContexts.custom()
                    //忽略掉对服务器端证书的校验
                    .loadTrustMaterial(new TrustStrategy() {
                        @Override
                        public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                            return true;
                        }
                    }).build();
            connectionSocketFactory = new SSLConnectionSocketFactory(sslcontext, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
                .register("https", connectionSocketFactory)
                .register("http", new PlainConnectionSocketFactory())
                .build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        connectionManager.setMaxTotal(100);
        connectionManager.setDefaultMaxPerRoute(20);
        RequestConfig requestConfig = RequestConfig
                .custom()
                .setCookieSpec(CookieSpecs.DEFAULT)
                .setConnectTimeout(5000)
                .build();
        return HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                .build();
    }

}
