package com.honglu.quickcall.common.constants;

import java.util.ResourceBundle;

/**
 * 公共配置文件常亮配置
 * Created by len.song on 2017-12-14.
 */
public class PropertiesConstant {
	//*** 配置文件获取源 ***
	public final static ResourceBundle RESOURCE_BUNDLE= ResourceBundle.getBundle("thirdconfig");
	
	
    /**
     * 默认大V形象照
     **/
    public final static String DEFAULT_CUSTOMER_APPEARANCE_URL_BOY = ResourceBundle.getBundle("thirdconfig").getString("DEFAULT_CUSTOMER_APPEARANCE_URL_BOY");
    public final static String DEFAULT_CUSTOMER_APPEARANCE_URL_GIRL = ResourceBundle.getBundle("thirdconfig").getString("DEFAULT_CUSTOMER_APPEARANCE_URL_GIRL");
    
	//敏感词过滤接口地址
    public final static String SENSITIVEWORDURL = RESOURCE_BUNDLE.getString("SENSITIVEWORDURL");
    //图片过滤接口地址
    public final static String SENSITIVEIMAGEURL = RESOURCE_BUNDLE.getString("SENSITIVEIMAGEURL");
    //#权限认证
    public final static String accessKey = RESOURCE_BUNDLE.getString("accessKey");
    //平台业务类型
    public final static String textType = RESOURCE_BUNDLE.getString("textType");
    public final static String IMGType = RESOURCE_BUNDLE.getString("IMGType");

    //应用标识
    public final static String appId = RESOURCE_BUNDLE.getString("appId");
   
    
    /**
     *图片审核回调地址
     */
    public final static String imgCallbackUrl = RESOURCE_BUNDLE.getString("imgCallbackUrl");
    /**
     * 音频过滤地址
     */
    public final static String SENSITIVEAUDIOURL = RESOURCE_BUNDLE.getString("SENSITIVEAUDIOURL");
    /**
     * 音频识别类型
     */
    public final static String AUDIOTYPE = RESOURCE_BUNDLE.getString("AUDIOTYPE");
    /**
     * 音频回调地址
     */
    public final static String audCallbackUrl = RESOURCE_BUNDLE.getString("audCallbackUrl");
    

    
}
