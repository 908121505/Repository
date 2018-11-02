package com.calf.cn.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
/**
 * HTTP相关的操作
 * @author bo.li
 * @Date 2011-10-9
 */
public class HttpUtil {

	public static final String CHARACTER_ENCODING = "UTF-8";
	public static final String PATH_SIGN = "/";
	public static final String METHOD_POST = "POST";
	public static final String METHOD_GET = "GET";
	public static final String METHOD_DELETE = "DELETE";
	public static final String METHOD_PUT = "PUT";
	public static final String CONTENT_TYPE = "Content-Type";
	static {
		System.setProperty("sun.net.client.defaultConnectTimeout", "50000");
		System.setProperty("sun.net.client.defaultReadTimeout", "50000");
	}

	/**
	 * 获取客户端IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		if (request == null)
			return "";
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 获取对应ip的物理网卡地址
	 * 
	 * @param ip
	 * @return
	 */
	public String getMACAddress(String ip) {
		String str = "";
		String macAddress = "";
		try {
			Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
			InputStreamReader ir = new InputStreamReader(p.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			for (int i = 1; i < 100; i++) {
				str = input.readLine();
				if (str != null) {
					if (str.indexOf("MAC Address") > 1) {
						macAddress = str.substring(str.indexOf("MAC Address") + 14, str.length());
						break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		return macAddress;
	}

	public static String getRootPath(ServletContext sctx) {
		String rootpath = sctx.getRealPath("/");
		if (rootpath != null) {
			rootpath = rootpath.replaceAll("\\", "/");
		} else {
			rootpath = "/";
		}
		if (!rootpath.endsWith("/")) {
			rootpath = rootpath + "/";
		}
		return rootpath;
	}

	private static String inputStreamToString(InputStream is, String encode) throws Exception {
		StringBuffer buffer = new StringBuffer();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is, encode));
		int ch;
		for (; (ch = rd.read()) > -1;) {
			buffer.append((char) ch);
		}
		rd.close();
		return buffer.toString();
	}

	/**
	 * 发送get请求，获取返回html
	 * 
	 * @param strUrl
	 *            请求地址
	 * @param encode
	 *            页面编码
	 * @return
	 * @throws Exception
	 */
	public static String sendGetRequest(String strUrl, String encode) throws Exception {
		URL newUrl = new URL(strUrl);
		HttpURLConnection hConnect = (HttpURLConnection) newUrl.openConnection();
		InputStream is = hConnect.getInputStream();
		String str = inputStreamToString(is, encode);
		is.close();
		hConnect.disconnect();
		return str;
	}

	/**
	 * 发送delete请求，获取返回html
	 * 
	 * @param strUrl
	 *            请求地址
	 * @param encode
	 *            页面编码
	 * @return
	 * @throws Exception
	 */
	public static String sendDeleteRequest(String requestUrl, String encode) throws Exception {
		URL url = new URL(requestUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(METHOD_DELETE);// 提交模式
		conn.setConnectTimeout(5000);// 连接超时 单位毫秒
		conn.setReadTimeout(5000);// 读取超时 单位毫秒
		conn.setDoInput(true);
		conn.setUseCaches(false);

		InputStream is = conn.getInputStream();
		String str = inputStreamToString(is, encode);
		is.close();
		conn.disconnect();
		return str;
	}

	/**
	 * 发送post请求
	 * 
	 * @param requestUrl
	 *            请求URL地址
	 * @param params
	 *            请求参数 text1=aaa&text2=bbb
	 * @param encode
	 *            请求参数及页面的编码
	 * @return 返回页面返回的html
	 * @throws Exception
	 */
	public static String sendPostRequest(String requestUrl, String params, String encode) throws Exception {
		URL url = new URL(requestUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(METHOD_POST);// 提交模式
		conn.setConnectTimeout(5000);// 连接超时 单位毫秒
		conn.setReadTimeout(5000);// 读取超时 单位毫秒
		conn.setDoOutput(true);// 是否输入参数
		conn.setDoInput(true);
		conn.setUseCaches(false);

		byte[] b = params.toString().getBytes(encode);
		OutputStream os = conn.getOutputStream();
		os.write(b);// 输入参数
		os.flush();
		os.close();

		InputStream is = conn.getInputStream();
		String str = inputStreamToString(is, encode);
		is.close();
		conn.disconnect();
		return str;
	}

	/**
	 * 发送post请求
	 * 
	 * @param requestUrl
	 *            请求URL地址
	 * @param params
	 *            Map类型的参数
	 * @param encode
	 *            请求参数及页面的编码
	 * @return String
	 * @throws Exception
	 */
	public static String sendPostRequest(String requestUrl, Map<String, String> params, String encode) throws Exception {
		StringBuffer paramStr = new StringBuffer("");
		if (params != null && params.size() > 0) {
			for (String key : params.keySet()) {
				paramStr.append(key);
				paramStr.append("=");
				paramStr.append(encode(params.get(key)));
				paramStr.append("&");
			}
		}
		return sendPostRequest(requestUrl, paramStr.toString(), encode);
	}

	/**
	 * 发送put请求
	 * 
	 * @param requestUrl
	 *            请求URL地址
	 * @param params
	 *            请求参数 text1=aaa&text2=bbb
	 * @param encode
	 *            请求参数及页面的编码
	 * @return 返回页面返回的html
	 * @throws Exception
	 */
	public static String sendPutRequest(String requestUrl, String params, String encode) throws Exception {
		URL url = new URL(requestUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(METHOD_PUT);// 提交模式
		conn.setConnectTimeout(5000);// 连接超时 单位毫秒
		conn.setReadTimeout(5000);// 读取超时 单位毫秒
		conn.setDoOutput(true);// 是否输入参数
		conn.setDoInput(true);
		conn.setUseCaches(false);

		byte[] b = params.toString().getBytes(encode);
		OutputStream os = conn.getOutputStream();
		os.write(b);// 输入参数
		os.flush();
		os.close();

		InputStream is = conn.getInputStream();
		String str = inputStreamToString(is, encode);
		is.close();
		conn.disconnect();
		return str;
	}

	/**
	 * 发送带文件上传以及文本域的post请求
	 * 
	 * @param urlString
	 *            post请求地址
	 * @param params
	 *            文本
	 * @param files
	 *            文件
	 * @return 返回的html
	 * @throws Exception
	 */
	public static String sendPostFileRequest(String urlString, Map<String, String> params, Map<String, String> files, String encode) throws Exception {
		// 是否在控制台打印请求参数，方便调用
		boolean printArgs = false;

		// 构送请求http请求参数
		String BOUNDARY = "---------------------------7d4a6d158c9"; // 分隔线
		List<byte[]> headerList = new ArrayList<byte[]>();
		List<byte[]> contentList = new ArrayList<byte[]>();
		byte[] end = ("--" + BOUNDARY + "--\r\n").getBytes();
		int contentLength = 0;
		if (params != null && params.size() > 0) {
			for (String key : params.keySet()) {
				byte[] header = ("--" + BOUNDARY + "\r\nContent-Disposition: form-data;name=\"" + key + "\"\r\n\r\n").getBytes(encode);
				byte[] content = params.get(key).getBytes(encode);
				headerList.add(header);
				contentList.add(content);
				contentLength += header.length + content.length + "\r\n".getBytes().length;
				if (printArgs) {
					System.out.print(new String(header, encode));
					System.out.print(new String(content, encode));
					System.out.print(new String("\r\n".getBytes()));
				}
			}
		}
		if (files != null && files.size() > 0) {
			for (String key : files.keySet()) {
				String filename = files.get(key).substring(files.get(key).lastIndexOf("/") + 1);
				byte[] header = ("--" + BOUNDARY + "\r\nContent-Disposition: form-data; name=\"" + key + "\"; filename=\"" + filename + "\"\r\nContent-Type: multipart/form-data\r\n\r\n")
						.getBytes(encode);
				byte[] content = org.apache.commons.io.FileUtils.readFileToByteArray(new File(files.get(key)));
				headerList.add(header);
				contentList.add(content);
				contentLength += header.length + content.length + "\r\n".getBytes().length;
				if (printArgs) {
					System.out.print(new String(header, encode));
					System.out.print(new String(content));
					System.out.print(new String("\r\n".getBytes()));
				}
			}
		}
		contentLength += end.length;
		if (printArgs) {
			System.out.print(new String(end));
		}

		// 发送http请求
		URL url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(METHOD_POST);
		conn.setConnectTimeout(5000);// 连接超时 单位毫秒
		conn.setReadTimeout(5000);// 读取超时 单位毫秒
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
		conn.setRequestProperty("Content-Length", String.valueOf(contentLength));
		OutputStream os = conn.getOutputStream();
		for (int i = 0; i < headerList.size(); i++) {
			os.write(headerList.get(i));
			os.write(contentList.get(i));
			os.write("\r\n".getBytes());
		}
		os.write(end);
		os.flush();
		os.close();
		// 获取http请求结果
		InputStream is = conn.getInputStream();
		String str = inputStreamToString(is, encode);
		is.close();
		conn.disconnect();
		return str;
	}

	/**
	 * url解码
	 * 
	 * @param str
	 * @return 解码后的字符串,当异常时返回原始字符串。
	 */
	public static String decode(String url) {
		if (url == null) {
			return null;
		}
		try {
			return URLDecoder.decode(url, CHARACTER_ENCODING);
		} catch (UnsupportedEncodingException ex) {
			return url;
		}
	}

	/**
	 * url编码
	 * 
	 * @param str
	 * @return 编码后的字符串,当异常时返回原始字符串。
	 */
	public static String encode(String url) {
		if (url == null) {
			return null;
		}
		try {
			return URLEncoder.encode(url, CHARACTER_ENCODING);
		} catch (UnsupportedEncodingException ex) {
			return url;
		}
	}
	/**
	 * 获取服务端的url 根地址
	 * add by zhuangyq
	 * @param req
	 * @return
	 */
	public static String getServerBaseUrl(HttpServletRequest req){
		if(req==null)
			return "";
		//String ip = req.getHeader("Host");
		String path = req.getContextPath();
		String basePath = !"https".equals(req.getScheme())?"https":req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path+"/";
		return basePath;
	}
	/**
	 * map参数转化为url查询串
	 * add by zhuangyq
	 * @param url 原始url 未带查询参数
	 * @param params 将url参数封装为map对象
	 * @param joinStr 如果有空默认为&
	 * @return 返回完整的url
	 */
	public static String appendQueryString(String url,Map<String, String> params,String joinStr){
		StringBuffer sb = new StringBuffer(url); 
        //设置请求参数 
		joinStr = (joinStr==null||"".equals(joinStr))?"&":joinStr;
        if (params != null) { 
            Iterator<Map.Entry<String, String>> it = params.entrySet().iterator(); 
            if (params.size() > 0) { 
                sb.append("?"); 
                while (it.hasNext()) { 
                    Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next(); 
                    if (it.hasNext())
                    sb.append(entry.getKey() + "=" + entry.getValue() + joinStr); 
                    else 
                    	sb.append(entry.getKey() + "=" + entry.getValue() ); 
                } 
            } 

        }
		return sb.toString();
	}
	public static void main(String[] args) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("ssid", "zte");
		params.put("packCode", "abac");
		String ret = appendQueryString("http://www.test.com",params,null );
		System.out.println(ret);
	}
	
	
	public static String postRequest(NameValuePair[] NameValuePairs, String ip, int prot,
			String operMethod) {
		HttpClient httpClient = new HttpClient();
		HttpMethod method = null;

		try {
			httpClient.getHostConfiguration().setHost(ip, prot, "http");
			PostMethod post = new PostMethod(operMethod);

			post.setRequestBody(NameValuePairs);
			post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "GB2312");
			method = post;

			httpClient.executeMethod(method);
			System.out.println(method.getResponseBodyAsString());
			System.out.println("返回的请求状态:" + method.getStatusCode());
			System.out.println("服务器状态：" + method.getStatusLine());// 打印服务器返回的状态

			return method.getResponseBodyAsString();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		return null;
	}


}