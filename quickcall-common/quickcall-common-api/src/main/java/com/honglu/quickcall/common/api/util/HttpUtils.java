package com.honglu.quickcall.common.api.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;




public class HttpUtils {

	private final static Logger log = LoggerFactory.getLogger(HttpUtils.class);
	/** 发送http请求超时时间 */
	private static int timeout = Integer.valueOf(60000);

	/**
	 * 
	 * 在HttpServletRequest获取提交数据
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static String getRequestMsg(HttpServletRequest request) throws IOException {
		request.setCharacterEncoding("UTF-8");
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			InputStream is = request.getInputStream();
			byte[] buffer = new byte[256];

			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}

			byte[] readBytes = bos.toByteArray();
			return new String(readBytes, "UTF-8");
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		}
	}
	/**
	 * 把请求参数解析到MAP中
	 * @param <T>
	 * @param hsr
	 * @return
	 * 			TreeMap<String ,Object>
	 * @throws IOException 
	 */
	public static <T> T getReqJsonT(HttpServletRequest hsr, Class<T> t) throws IOException {
		String jsonStr = getRequestMsg(hsr);
		if(jsonStr == null || jsonStr.isEmpty()){
			return null;
		}
		return JSONObject.toJavaObject(JSONObject.parseObject(jsonStr), t);
	}	
	/**
	 * 把请求参数解析到MAP中
	 * @param <T>
	 * @param hsr
	 * @return
	 * 			TreeMap<String ,Object>
	 * @throws IOException 
	 */
	public static <T> T getReqT(HttpServletRequest hsr, Class<T> t) throws IOException {
		String jsonStr = getRequestMsg(hsr);
		if(jsonStr == null || jsonStr.isEmpty()){
			return null;
		}
		Gson gson = new Gson();
		return gson.fromJson(jsonStr, t);
	}
	
	/**
	 * 将收到的数据放入到map
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static TreeMap<String, String> getReq2Map(HttpServletRequest request) {
		TreeMap<String, String> treeMap = new TreeMap<>();
		Enumeration<String> names = request.getParameterNames();
		log.info("getReq2Map()....................." + names);
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			treeMap.put(name, request.getParameter(name));
		}
		return treeMap;
	}
	
	/**
	 * 
	 * 在HttpServletRequest获取提交数据
	 * 穿过的的参数中已是UTF-8的编码 则不需要重新编码
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static String getRequestMsgUTF(HttpServletRequest request) throws IOException {
		request.setCharacterEncoding("UTF-8");
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			InputStream is = request.getInputStream();
			byte[] buffer = new byte[256];

			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}

			byte[] readBytes = bos.toByteArray();
			return new String(readBytes);
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		}
	}
	

	/**
	 * 
	 * servlet中发送响应数据
	 * 
	 * @param response
	 * @param msg
	 * @return
	 * @throws IOException
	 */
	public static void sendResponse(HttpServletResponse response, String msg) throws IOException {

		OutputStream os = null;
		try {
			byte[] data = msg.getBytes("UTF-8");
			response.setContentLength(data.length);
			response.setCharacterEncoding("UTF-8");
			response.setHeader("HTTP-Version", "HTTP/1.1");
			os = response.getOutputStream();
			os.write(data);
			os.flush();
		} catch (IOException e) {

			throw new IOException(e);
		}
	}

	/**
	 * 发送http post请求，编码方式UTF-8
	 * 
	 * @param data
	 * @return
	 * @throws MsgsendException
	 */
	public static String sendPost(String url, String data) throws Exception {

		OutputStream outputStream = null;
		HttpURLConnection conn = null;

		try {
			URL remoteUrl = new URL(url);
			conn = (HttpURLConnection) remoteUrl.openConnection();

			conn.setDoOutput(true);
			conn.setDoInput(true);

			conn.setRequestMethod("POST");
			conn.setUseCaches(false);
			conn.setConnectTimeout(timeout);
			conn.setReadTimeout(timeout);

			// 发送数据
			byte[] datas = data.getBytes("UTF-8");
			outputStream = conn.getOutputStream();
			outputStream.write(datas, 0, datas.length);
			outputStream.flush();
			log.info("http发送报文:" + data);
			// 读取返回数据
			InputStream inputStream = conn.getInputStream();

			String res = null;
			try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
				byte[] buffer = new byte[256];
				int len = 0;
				while ((len = inputStream.read(buffer)) != -1) {
					bos.write(buffer, 0, len);
				}
				byte[] readBytes = bos.toByteArray();
				res = new String(readBytes, "UTF-8");
			}

			log.info("http post 收到响应数据:" + res);
			return res;
		} catch (MalformedURLException e) {
			log.error("发送http post异常", e);
			throw new Exception(e.getMessage());
		} catch (IOException e) {
			log.error("发送http post异常", e);
			throw new Exception(e.getMessage());
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					log.error("发送http post异常", e);
				}
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
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
			log.error("发送GET请求出现异常", e);
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

	/**
	 * 时间转换机制 yyyy-MM-dd HH:mm:ss 24小时制
	 * 
	 * @param str
	 * @return
	 */
	public static Date stringToDateYYYYMMDD(String str) {
		try {
			if (null == str || "".equals(str)) {
				return null;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = sdf.parse(str);
			return date;
		} catch (Exception e) {
			log.error("处理请求数据异常");
			e.printStackTrace();
		}
		return null;
	}
}
