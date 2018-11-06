package com.honglu.quickcall.common.constants;

import java.util.ResourceBundle;

/**
 * 公共配置文件常亮配置
 * Created by len.song on 2017-12-14.
 */
public class PropertiesConstant {
    /*** 配置文件获取源 ***/
    public final static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("thirdconfig");

    /**
     * 默认大V形象照
     **/
    public final static String DEFAULT_CUSTOMER_APPEARANCE_URL_BOY = ResourceBundle.getBundle("thirdconfig").getString("DEFAULT_CUSTOMER_APPEARANCE_URL_BOY");
    public final static String DEFAULT_CUSTOMER_APPEARANCE_URL_GIRL = ResourceBundle.getBundle("thirdconfig").getString("DEFAULT_CUSTOMER_APPEARANCE_URL_GIRL");


}
