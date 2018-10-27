package com.honglu.quickcall.common.third.mob;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ResourceBundle;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.collections.bag.SynchronizedSortedBag;

public class MobUtil {
	private static String appkey =   ResourceBundle.getBundle("thirdconfig").getString("mob_appkey");
	private static String zone =   ResourceBundle.getBundle("thirdconfig").getString("mob_zone");
	public static void main(String[] args) throws Exception {
		//String result = requestData("https://webapi.sms.mob.com/sms/sendmsg", 
		//"appkey=22d9856cab3af&zone=86&phone=18503377523");
		//System.out.println(result);
	//String result = requestData("https://webapi.sms.mob.com/sms/checkcode", 
				//"appkey=22d9856cab3af&zone=86&phone=18217583747&code=5222");
			//	System.out.println(result);
		/*MobMsg mob=new MobMsg();
		mob.setTel("18217583747");
		mob.setType(1);
		String result = requestData(mob);
		System.out.println(x);*/
      		
	}
	 
	/**
	 * 发起https 请求
	 * @param address
	 * @param m
	 * @return
	 */
	public  static String requestData(MobMsg mob){
		HttpURLConnection conn = null;
		try {
			// Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager(){
				public X509Certificate[] getAcceptedIssuers(){return null;}
				public void checkClientTrusted(X509Certificate[] certs, String authType){}
				public void checkServerTrusted(X509Certificate[] certs, String authType){}
			}};
	 
			// Install the all-trusting trust manager
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new SecureRandom());
	 
			//ip host verify
			HostnameVerifier hv = new HostnameVerifier() {
				 public boolean verify(String urlHostName, SSLSession session) {
				 return urlHostName.equals(session.getPeerHost());
				 }
			};
	 
			//set ip host verify
			HttpsURLConnection.setDefaultHostnameVerifier(hv);
	 
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			URL url=null;
			String params="appkey="+appkey+"&zone="+zone+"&phone="+mob.getTel();
			if(mob.getType()==1) {
			 url = new URL("https://webapi.sms.mob.com/sms/sendmsg");
			 
			}else {
			 url = new URL("https://webapi.sms.mob.com/sms/checkcode");
			 params+="&code="+mob.getCode();
			}
			
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");// POST
			conn.setConnectTimeout(3000);
			conn.setReadTimeout(3000);
			// set params ;post params 
			if (params!=null) {
				conn.setDoOutput(true);
				DataOutputStream out = new DataOutputStream(conn.getOutputStream());
				out.write(params.getBytes(Charset.forName("UTF-8")));
				out.flush();
				out.close();
			}
			conn.connect();
			//get result 
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				String result = inputStream2String(conn.getInputStream());
				return result;
			} else {
				System.out.println(conn.getResponseCode() + " "+ conn.getResponseMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.disconnect();
		}
		return null;
	}
	
	
	 static  String   inputStream2String   (InputStream   in)   throws   IOException   { 
        StringBuffer   out   =   new   StringBuffer(); 
        byte[]   b   =   new   byte[4096]; 
        for   (int   n;   (n   =   in.read(b))   !=   -1;)   { 
                out.append(new   String(b,   0,   n)); 
        } 
        return   out.toString(); 
} 
}
