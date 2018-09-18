package com.honglu.quickcall.common.third.weixin.service;


import java.util.ResourceBundle;

import org.springframework.stereotype.Service;

import com.honglu.quickcall.common.third.weixin.httpclient.AccessToken;
import com.honglu.quickcall.common.third.weixin.httpclient.HttpClients;
import com.honglu.quickcall.common.third.weixin.httpclient.PostParameter;
import com.honglu.quickcall.common.third.weixin.models.User;
import com.honglu.quickcall.common.third.weixin.util.Config;
import com.honglu.quickcall.common.third.weixin.util.JSONObject;
import com.qq.connect.utils.RandomStatusGenerator;



/**
 * 微信第三方登录
 * 第一步：授权，获取code
 * 第二步：通过code,获取accessToken，openid
 * 第三步:通过accessToken、openid获取用户信息
 * @author GunnyZeng
 *
 */
public class WeiXinOAuthApi {
	private static String appid =   ResourceBundle.getBundle("thirdconfig").getString("weixin_appid");
	private static String secret =   ResourceBundle.getBundle("thirdconfig").getString("weixin_secret");
	private static String getUserInfoURL =   ResourceBundle.getBundle("thirdconfig").getString("weixin_userinfpApiUrl");
	private static String accessTokenURL =   ResourceBundle.getBundle("thirdconfig").getString("weixin_access_token_URL");
	static HttpClients client  = new HttpClients();

	public String authorize() {/*
		String state = RandomStatusGenerator.getUniqueState();
        if(scope != null && !scope.equals(""))
        	 return (new StringBuilder()).append(authorizeURL).append("?appid=").append(appid).append("&redirect_uri=").append(redirect_URI).append("&response_type=").append("code").append("&state=").append(state).append("&scope=").append(scope).append("#wechat_redirect").toString();
        else
            return (new StringBuilder()).append(authorizeURL).append("?appid=").append(appid).append("&redirect_uri=").append(redirect_URI).append("&response_type=").append("code").append("&state=").append(state).append("#wechat_redirect").toString();
    
	*/
		return null;
		
	}

	public static AccessToken getOpenID(Object parame) throws Exception {
		 String code = (String) parame;
		 AccessToken accessToken = getAccessToken(code);
		 String openID=null;
         User user =  new User();
         /*if (accessToken==null) {
             throw new Exception("授权失败！");
         } else {
              openID = accessToken.getUserUid();*/
            /* //获取用户信息
             JSONObject json = showUser(accessToken);
             
             if (json!=null&&json.keys().hasNext()) {
                 user.setId(openID);
                 user.setAvatarLarge(json.getString("headimgurl"));
                 user.setNickName(json.getString("nickname"));
                 user.setGender(json.getString("sex"));
                 user.setCity(json.getString("city"));
                 user.setProvince(json.getString("province"));
                 user.setSource("微信");
                 
             } else {
            	 throw new Exception("很抱歉，我们没能正确获取到您的信息! ");
             }*/
         
         return accessToken;
	}
	
	/**
	 * 获取AccessToken
	 * @param code
	 * @return
	 * @throws Exception 
	 */
	private static AccessToken getAccessToken(String code) throws Exception{
		return new AccessToken(client.post(
				accessTokenURL,
				new PostParameter[] {
						new PostParameter("appid", appid),
						new PostParameter("secret",secret),
						new PostParameter("grant_type", "authorization_code"),
						new PostParameter("code", code),
						}, false, null),"openid");
		
		//return new AccessToken(client.get(accessTokenURL+"?appid="+appid+"&secret="+secret+"&grant_type=authorization_code&code="+code),);
		//return new AccessToken(client.get(accessTokenURL+"?appid="+appid+"&secret="+secret+"&grant_type=authorization_code&code="+code, "openid"),"openid");
		
	}
	/**
	 * 授权后获取用户json数据
	 * @param accessToken
	 * @return
	 * @throws Exception 
	 */
	 private static JSONObject showUser(AccessToken accessToken) throws Exception
		    {
		        return client.get(getUserInfoURL, new PostParameter[] {
		            new PostParameter("openid", accessToken.getUserUid()), 
		            new PostParameter("access_token", accessToken.getAccessToken())
		        },accessToken.getAccessToken()).asJSONObject();
		    }


}
