package com.honglu.quickcall.common.third.weixin.httpclient;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.PartBase;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import com.honglu.quickcall.common.third.weixin.util.JSONException;






/**
 * @author sinaWeibo
 * 
 */
public class HttpClients implements java.io.Serializable {
	protected Logger logger;//日志
	protected HttpClient httpClient;//httpclient实例

	private static final long serialVersionUID = -176092625883595547L;
	private static final int OK 				   = 200;// OK: Success!
	private static final int NOT_MODIFIED 		   = 304;// Not Modified: There was no new data to return.
	private static final int BAD_REQUEST 		   = 400;// Bad Request: The request was invalid.  An accompanying error message will explain why. This is the status code will be returned during rate limiting.
	private static final int NOT_AUTHORIZED 	   = 401;// Not Authorized: Authentication credentials were missing or incorrect.
	private static final int FORBIDDEN 			   = 403;// Forbidden: The request is understood, but it has been refused.  An accompanying error message will explain why.
	private static final int NOT_FOUND             = 404;// Not Found: The URI requested is invalid or the resource requested, such as a user, does not exists.
	private static final int NOT_ACCEPTABLE        = 406;// Not Acceptable: Returned by the Search API when an invalid format is specified in the request.
	private static final int INTERNAL_SERVER_ERROR = 500;// Internal Server Error: Something is broken.  Please post to the group so the Weibo team can investigate.
	private static final int BAD_GATEWAY           = 502;// Bad Gateway: Weibo is down or being upgraded.
	private static final int SERVICE_UNAVAILABLE   = 503;// Service Unavailable: The Weibo servers are up, but overloaded with requests. Try again later. The search and trend methods use this to indicate when you are being rate limited.

	private String proxyHost = Configuration.getProxyHost();
	private int proxyPort = Configuration.getProxyPort();
	private String proxyAuthUser = Configuration.getProxyUser();
	private String proxyAuthPassword = Configuration.getProxyPassword();

	public String getProxyHost() {
		return proxyHost;
	}

	/**
	 * Sets proxy host. System property -Dsinat4j.http.proxyHost or
	 * http.proxyHost overrides this attribute.
	 * 
	 * @param proxyHost
	 */
	public void setProxyHost(String proxyHost) {
		this.proxyHost = Configuration.getProxyHost(proxyHost);
	}

	public int getProxyPort() {
		return proxyPort;
	}

	/**
	 * Sets proxy port. System property -Dsinat4j.http.proxyPort or
	 * -Dhttp.proxyPort overrides this attribute.
	 * 
	 * @param proxyPort
	 */
	public void setProxyPort(int proxyPort) {
		this.proxyPort = Configuration.getProxyPort(proxyPort);
	}

	public String getProxyAuthUser() {
		return proxyAuthUser;
	}

	/**
	 * Sets proxy authentication user. System property -Dsinat4j.http.proxyUser
	 * overrides this attribute.
	 * 
	 * @param proxyAuthUser
	 */
	public void setProxyAuthUser(String proxyAuthUser) {
		this.proxyAuthUser = Configuration.getProxyUser(proxyAuthUser);
	}

	public String getProxyAuthPassword() {
		return proxyAuthPassword;
	}

	/**
	 * Sets proxy authentication password. System property
	 * -Dsinat4j.http.proxyPassword overrides this attribute.
	 * 
	 * @param proxyAuthPassword
	 */
	public void setProxyAuthPassword(String proxyAuthPassword) {
		this.proxyAuthPassword = Configuration
				.getProxyPassword(proxyAuthPassword);
	}

	private final static boolean DEBUG = Configuration.getDebug();
	static Logger log = Logger.getLogger(HttpClients.class.getName());
	
	org.apache.commons.httpclient.HttpClient client = null;

	private MultiThreadedHttpConnectionManager connectionManager;
	private int maxSize;

	public HttpClients() {
		this(150, 30000, 30000, 1024 * 1024);
	}

	public HttpClients(int maxConPerHost, int conTimeOutMs, int soTimeOutMs,
			int maxSize) {
		connectionManager = new MultiThreadedHttpConnectionManager();
		HttpConnectionManagerParams params = connectionManager.getParams();
		params.setDefaultMaxConnectionsPerHost(maxConPerHost);
		params.setConnectionTimeout(conTimeOutMs);
		params.setSoTimeout(soTimeOutMs);

		HttpClientParams clientParams = new HttpClientParams();
		// 忽略cookie 避免 Cookie rejected 警告
		clientParams.setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
		client = new org.apache.commons.httpclient.HttpClient(clientParams,
				connectionManager);
		Protocol myhttps = new Protocol("https", new MySSLSocketFactory(), 443);
		Protocol.registerProtocol("https", myhttps);
		this.maxSize = maxSize;
		// 支持proxy
		if (proxyHost != null && !proxyHost.equals("")) {
			client.getHostConfiguration().setProxy(proxyHost, proxyPort);
			client.getParams().setAuthenticationPreemptive(true);
			if (proxyAuthUser != null && !proxyAuthUser.equals("")) {
				client.getState().setProxyCredentials(
						AuthScope.ANY,
						new UsernamePasswordCredentials(proxyAuthUser,
								proxyAuthPassword));
				log("Proxy AuthUser: " + proxyAuthUser);
				log("Proxy AuthPassword: " + proxyAuthPassword);
			}
		}
	}

	/**
	 * log调试
	 * 
	 */
	private static void log(String message) {
		if (DEBUG) {
			log.debug(message);
		}
	}

	/**
	 * 处理http getmethod 请求
	 * 
	 */

	public Response get(String url, String token) throws Exception {

		return get(url, new PostParameter[0], token);

	}

	public Response get(String url, PostParameter[] params, String token)
			throws Exception {
		log("Request:");
		log("GET:" + url);
		if (null != params && params.length > 0) {
			String encodedParams = HttpClients.encodeParameters(params);
			if (-1 == url.indexOf("?")) {
				url += "?" + encodedParams;
			} else {
				url += "&" + encodedParams;
			}
		}
		GetMethod getmethod = new GetMethod(url);
		return httpRequest(getmethod, token);

	}

	public Response get(String url, PostParameter[] params, Paging paging, String token)
			throws Exception {
		if (null != paging) {
			List<PostParameter> pagingParams = new ArrayList<PostParameter>(4);
			if (-1 != paging.getMaxId()) {
				pagingParams.add(new PostParameter("max_id", String
						.valueOf(paging.getMaxId())));
			}
			if (-1 != paging.getSinceId()) {
				pagingParams.add(new PostParameter("since_id", String
						.valueOf(paging.getSinceId())));
			}
			if (-1 != paging.getPage()) {
				pagingParams.add(new PostParameter("page", String
						.valueOf(paging.getPage())));
			}
			if (-1 != paging.getCount()) {
				if (-1 != url.indexOf("search")) {
					// search api takes "rpp"
					pagingParams.add(new PostParameter("rpp", String
							.valueOf(paging.getCount())));
				} else {
					pagingParams.add(new PostParameter("count", String
							.valueOf(paging.getCount())));
				}
			}
			PostParameter[] newparams = null;
			PostParameter[] arrayPagingParams = pagingParams
					.toArray(new PostParameter[pagingParams.size()]);
			if (null != params) {
				newparams = new PostParameter[params.length
						+ pagingParams.size()];
				System.arraycopy(params, 0, newparams, 0, params.length);
				System.arraycopy(arrayPagingParams, 0, newparams,
						params.length, pagingParams.size());
			} else {
				if (0 != arrayPagingParams.length) {
					String encodedParams = HttpClients
							.encodeParameters(arrayPagingParams);
					if (-1 != url.indexOf("?")) {
						url += "&" + encodedParams;
					} else {
						url += "?" + encodedParams;
					}
				}
			}
			return get(url, newparams, token);
		} else {
			return get(url, params, token);
		}
	}

	/**
	 * 处理http deletemethod请求
	 */

	public Response delete(String url, PostParameter[] params, String token)
			throws Exception {
		if (0 != params.length) {
			String encodedParams = HttpClients.encodeParameters(params);
			if (-1 == url.indexOf("?")) {
				url += "?" + encodedParams;
			} else {
				url += "&" + encodedParams;
			}
		}
		DeleteMethod deleteMethod = new DeleteMethod(url);
		return httpRequest(deleteMethod, token);

	}

	/**
	 * 处理http post请求
	 * 
	 */

	public Response post(String url, PostParameter[] params, String token)
			throws Exception {
		return post(url, params, true, token);

	}

	public Response post(String url, PostParameter[] params,
			Boolean WithTokenHeader, String token) throws Exception {
		log("Request:");
		log("POST" + url);
		PostMethod postMethod = new PostMethod(url);
		for (int i = 0; i < params.length; i++) {
			postMethod.addParameter(params[i].getName(), params[i].getValue());
		}
		HttpMethodParams param = postMethod.getParams();
		param.setContentCharset("UTF-8");
		return httpRequest(postMethod, WithTokenHeader, token);
	}

	/**
	 * 支持multipart方式上传图片
	 * 
	 */
	public Response multPartURL(String url, PostParameter[] params,
			ImageItem item, String token) throws Exception {
		PostMethod postMethod = new PostMethod(url);
		try {
			Part[] parts = null;
			if (params == null) {
				parts = new Part[1];
			} else {
				parts = new Part[params.length + 1];
			}
			if (params != null) {
				int i = 0;
				for (PostParameter entry : params) {
					parts[i++] = new StringPart(entry.getName(),
							(String) entry.getValue());
				}
				parts[parts.length - 1] = new ByteArrayPart(item.getContent(),
						item.getName(), item.getContentType());
			}
			postMethod.setRequestEntity(new MultipartRequestEntity(parts,
					postMethod.getParams()));
			
			return httpRequest(postMethod, token);

		} catch (Exception ex) {
			throw new Exception(ex.getMessage(), ex);
		}
	}

	public Response multPartURL(String fileParamName, String url,
			PostParameter[] params, File file, boolean authenticated, String token)
			throws Exception {
		PostMethod postMethod = new PostMethod(url);
		try {
			Part[] parts = null;
			if (params == null) {
				parts = new Part[1];
			} else {
				parts = new Part[params.length + 1];
			}
			if (params != null) {
				int i = 0;
				for (PostParameter entry : params) {
					parts[i++] = new StringPart(entry.getName(),
							(String) entry.getValue());
				}
			}
			FilePart filePart = new FilePart(fileParamName, file.getName(),
					file, new MimetypesFileTypeMap().getContentType(file),
					"UTF-8");
			filePart.setTransferEncoding("binary");
			parts[parts.length - 1] = filePart;

			postMethod.setRequestEntity(new MultipartRequestEntity(parts,
					postMethod.getParams()));
			return httpRequest(postMethod, token);
		} catch (Exception ex) {
			throw new Exception(ex.getMessage(), ex);
		}
	}

	public Response httpRequest(HttpMethod method, String token) throws Exception {
		return httpRequest(method, true, token);
	}

	public Response httpRequest(HttpMethod method, Boolean WithTokenHeader, String token)
			throws Exception {
		InetAddress ipaddr;
		int responseCode = -1;
		try {
			ipaddr = InetAddress.getLocalHost();
			List<Header> headers = new ArrayList<Header>();
			if (WithTokenHeader) {
				if (token == null) {
					throw new IllegalStateException("Oauth2 token is not set!");
				}
				headers.add(new Header("Authorization", "OAuth2 " + token));
				headers.add(new Header("API-RemoteIP", ipaddr.getHostAddress()));
				client.getHostConfiguration().getParams()
						.setParameter("http.default-headers", headers);
				for (Header hd : headers) {
					log(hd.getName() + ": " + hd.getValue());
				}
			}

			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(3, false));
			client.executeMethod(method);
			Header[] resHeader = method.getResponseHeaders();
			responseCode = method.getStatusCode();
			log("Response:");
			log("https StatusCode:" + String.valueOf(responseCode));

			for (Header header : resHeader) {
				log(header.getName() + ":" + header.getValue());
			}
			Response response = new Response();
			response.setResponseAsString(method.getResponseBodyAsString());
			log(response.toString() + "\n");

			if (responseCode != OK)
			{
				try {
					throw new Exception(getCause(responseCode));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			return response;

		} catch (IOException ioe) {
			throw new Exception(ioe.getMessage(), ioe);
		} finally {
			method.releaseConnection();
		}

	}

	/*
	 * 对parameters进行encode处理
	 */
	public static String encodeParameters(PostParameter[] postParams) {
		StringBuffer buf = new StringBuffer();
		for (int j = 0; j < postParams.length; j++) {
			if (j != 0) {
				buf.append("&");
			}
			try {
				buf.append(URLEncoder.encode(postParams[j].getName(), "UTF-8"))
						.append("=")
						.append(URLEncoder.encode(postParams[j].getValue(),
								"UTF-8"));
			} catch (java.io.UnsupportedEncodingException neverHappen) {
			}
		}
		return buf.toString();
	}

	private static class ByteArrayPart extends PartBase {
		private byte[] mData;
		private String mName;

		public ByteArrayPart(byte[] data, String name, String type)
				throws IOException {
			super(name, type, "UTF-8", "binary");
			mName = name;
			mData = data;
		}

		protected void sendData(OutputStream out) throws IOException {
			out.write(mData);
		}

		protected long lengthOfData() throws IOException {
			return mData.length;
		}

		protected void sendDispositionHeader(OutputStream out)
				throws IOException {
			super.sendDispositionHeader(out);
			StringBuilder buf = new StringBuilder();
			buf.append("; filename=\"").append(mName).append("\"");
			out.write(buf.toString().getBytes());
		}
	}

	private static String getCause(int statusCode) {
		String cause = null;
		switch (statusCode) {
		case NOT_MODIFIED:
			break;
		case BAD_REQUEST:
			cause = "The request was invalid.  An accompanying error message will explain why. This is the status code will be returned during rate limiting.";
			break;
		case NOT_AUTHORIZED:
			cause = "Authentication credentials were missing or incorrect.";
			break;
		case FORBIDDEN:
			cause = "The request is understood, but it has been refused.  An accompanying error message will explain why.";
			break;
		case NOT_FOUND:
			cause = "The URI requested is invalid or the resource requested, such as a user, does not exists.";
			break;
		case NOT_ACCEPTABLE:
			cause = "Returned by the Search API when an invalid format is specified in the request.";
			break;
		case INTERNAL_SERVER_ERROR:
			cause = "Something is broken.  Please post to the group so the Weibo team can investigate.";
			break;
		case BAD_GATEWAY:
			cause = "Weibo is down or being upgraded.";
			break;
		case SERVICE_UNAVAILABLE:
			cause = "Service Unavailable: The Weibo servers are up, but overloaded with requests. Try again later. The search and trend methods use this to indicate when you are being rate limited.";
			break;
		default:
			cause = "";
		}
		return statusCode + ":" + cause;
	}
	//get获取内容
	public Response getByurl(String url,String charset) throws Exception{
		String html=null;
		HttpGet httpGet=null;
		httpGet=new HttpGet(url);
		initHttpGet(httpGet, charset);
		HttpResponse response=httpClient.execute(httpGet);
		int responseCode = response.getStatusLine().getStatusCode();
		if (responseCode != OK)
		{
			try {
				throw new Exception(getCause(responseCode));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		httpGet.releaseConnection();
		return 	(Response) response;
	}
	//post获取内容
	public Response getBypost(String url,Map<String,String> params,String charset) throws Exception {
		String html=null;
		HttpPost httpPost=null;
		
		httpPost=new HttpPost(url);
		initHttpPost(httpPost, charset);
		List formParams = new ArrayList();
		for (Object key:params.keySet()) {
			   formParams.add(new BasicNameValuePair((String)key, (String)params.get((String)key)));
	    }
		UrlEncodedFormEntity  paramsEntity = new UrlEncodedFormEntity(formParams,charset);
		httpPost.setEntity(paramsEntity);
		HttpResponse response=httpClient.execute(httpPost);
		int responseCode = response.getStatusLine().getStatusCode();
		if (responseCode != OK)
		{
			try {
				throw new Exception(getCause(responseCode));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		httpPost.releaseConnection();
		return 	(Response) response;
	}
	

	public void initHttpGet(HttpGet httpGet, String charSet) {
		httpGet.addHeader("Accept", "*/*");  
		httpGet.addHeader("Accept-Charset", charSet);  
		httpGet.addHeader("Accept-Encoding", "gzip");  
		httpGet.addHeader("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3"); 
		httpGet.addHeader("Connection", "keep-alive");
		httpGet.addHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) "
		  		+ "AppleWebKit/537.22 (KHTML, like Gecko) "
		  		+ "Chrome/25.0.1364.160 Safari/537.22");  
	}

	public void initHttpPost(HttpPost httpPost, String charSet) {
		httpPost.addHeader("Accept", "*/*");  
		httpPost.addHeader("Accept-Charset", charSet);  
		httpPost.addHeader("Accept-Encoding", "gzip");  
		httpPost.addHeader("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3"); 
		httpPost.addHeader("Connection", "keep-alive");
		httpPost.addHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) "
		  		+ "AppleWebKit/537.22 (KHTML, like Gecko) "
		  		+ "Chrome/25.0.1364.160 Safari/537.22");  
		//如果参数中包含中文
		httpPost.addHeader("Content-Type","application/x-www-form-urlencoded;charset="+charSet);  

	}

	
	
}
