package com.honglu.quickcall.common.third.shumei;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.honglu.quickcall.common.api.util.HttpClientUtils;
import com.honglu.quickcall.common.api.util.SSLClient;
import com.honglu.quickcall.common.constants.PropertiesConstant;
import com.honglu.quickcall.common.third.shumei.reponse.WordFilterResponse;
import com.honglu.quickcall.common.third.shumei.request.AudioFilterRequest;
import com.honglu.quickcall.common.third.shumei.request.ImageFilterRequest;
import com.honglu.quickcall.common.third.shumei.request.WordFilterRquest;

/**
 * 数美对接第三方接口
 * 
 * @author zhaozheyi
 *
 */
public class ShumeiUtils {
	private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

	/**
	 * 
	 * @param wfr
	 * @return 文字审核
	 */
	public static WordFilterResponse wordSensitiveFilter(WordFilterRquest wfr) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("accessKey", PropertiesConstant.accessKey);
		paramMap.put("appId", PropertiesConstant.appId);
		paramMap.put("type", PropertiesConstant.textType);
		paramMap.put("data", wfr);
		String json = JSON.toJSONString(paramMap, true);
		logger.info("请求数美数据：" + json);
		String res = doPost(PropertiesConstant.SENSITIVEWORDURL, json);
		logger.info("数美返回数据：" + res);
		WordFilterResponse response = new WordFilterResponse();
		if (res != null) {
			JSONObject jb = JSONObject.parseObject(res);
			JSONObject detail = JSONObject.parseObject(jb.getString("detail"));
			response.setCode(jb.getString("code"));
			response.setScore(jb.getString("score"));
			response.setRiskLevel(jb.getString("riskLevel"));
			response.setMatchedItem(detail.getString("matchedItem"));
			response.setFilteredText(detail.getString("filteredText"));
			logger.info(response.toString());
			return response;
		}
		return response;
	}

	/**
	 * 图片过滤
	 * 
	 * @param requestParam
	 */
	public static void imageSensitiveFilter(ImageFilterRequest requestParam) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("accessKey", PropertiesConstant.accessKey);
		paramMap.put("appId", PropertiesConstant.appId);
		paramMap.put("type", PropertiesConstant.IMGType);
		paramMap.put("callback", PropertiesConstant.imgCallbackUrl);
		paramMap.put("data", requestParam);
		String json = JSON.toJSONString(paramMap, true);
		logger.info("图片过滤请求数美数据：" + json);
		String res = doPost(PropertiesConstant.SENSITIVEIMAGEURL, json);
		logger.info("图片过滤数美同步返回数据：" + res);
	}

	public static String AudioSensitiveFilter(AudioFilterRequest requestParam) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("accessKey", PropertiesConstant.accessKey);
		paramMap.put("type", PropertiesConstant.AUDIOTYPE);
		paramMap.put("btid", requestParam.getBtid());
		paramMap.put("callback", PropertiesConstant.audCallbackUrl);
		paramMap.put("data", requestParam);
		String json = JSON.toJSONString(paramMap, true);
		String res = doPost(PropertiesConstant.SENSITIVEAUDIOURL, json);
		if (res != null) {
			JSONObject object = JSON.parseObject(res);
			// 同步返回唯一标示
			String requestId = object.getString("requestId");
			return requestId;
		}
		return null;
	}

	/*
	 * 利用HttpClient进行post请求的工具类
	 */
	private static String doPost(String url, String json) {

		String returnValue = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		try {
			// 第一步：创建HttpClient对象
			httpClient = new SSLClient();
			// httpClient = HttpClients.createDefault();

			// 第二步：创建httpPost对象
			HttpPost httpPost = new HttpPost(url);

			// 第三步：给httpPost设置JSON格式的参数
			StringEntity requestEntity = new StringEntity(json, "utf-8");
			requestEntity.setContentEncoding("UTF-8");
			httpPost.setHeader("Content-type", "application/json");
			httpPost.setEntity(requestEntity);

			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();// 设置请求和传输超时时间
			httpPost.setConfig(requestConfig);
			// 第四步：发送HttpPost请求，获取返回值
			returnValue = httpClient.execute(httpPost, responseHandler); // 调接口获取返回值时，必须用此方法
		} catch (Exception e) {
			logger.error("httpcilent发送post请求失败");
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				logger.info("httpcilent关闭失败");
			}
		}
		// 第五步：处理返回值
		return returnValue;
	}

	public static void main(String[] args) {
		// wordSensitiveFilter(new WordFilterRquest("毛泽东", "12312312312",
		// "123"));
		/*
		 * ImageFilterRequest requestParam = new ImageFilterRequest(); Image
		 * image = new Image(); Image image2 = new Image(); Image image3 = new
		 * Image(); image.setImg(
		 * "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=667028489,1503194141&fm=200&gp=0.jpg"
		 * ); image.setBtId("图片唯一标示"); image3.setBtId("图片唯一标示3"); image2.setImg(
		 * "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1808185461,3849564117&fm=200&gp=0.jpg"
		 * ); image3.setImg(
		 * "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2478918814,2901666318&fm=200&gp=0.jpg"
		 * ); image2.setBtId("图片唯一标示2"); requestParam.setImgs(new
		 * Image[]{image,image2,image3}); requestParam.setTokenId("tokenId");
		 * imageSensitiveFilter(requestParam);
		 */
		String str1 = PropertiesConstant.accessKey;
		System.out.println(str1);
	}
}
