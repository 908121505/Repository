package com.honglu.quickcall.common.constants;

import java.util.ResourceBundle;

/**
 * Created by len.song on 2017-12-14.
 */
public class PropertiesConstant {

    public final static String QiniuAccessKey = ResourceBundle.getBundle("thirdconfig").getString("QiniuAccessKey");
    public final static String QiniuSecretKey = ResourceBundle.getBundle("thirdconfig").getString("QiniuSecretKey");
    public final static String QiniuPublish = ResourceBundle.getBundle("thirdconfig").getString("QiniuPublish");
    public final static String QiniuLiveRtmp = ResourceBundle.getBundle("thirdconfig").getString("QiniuLiveRtmp");
    public final static String QiniuHubName = ResourceBundle.getBundle("thirdconfig").getString("QiniuHubName");
    public final static Integer ExpireAfterSeconds = Integer.parseInt(ResourceBundle.getBundle("thirdconfig").getString("ExpireAfterSeconds"));
    public final static String QiniuBucket = ResourceBundle.getBundle("thirdconfig").getString("QiniuBucket");
    public final static String QINIUPATHS = ResourceBundle.getBundle("thirdconfig").getString("domain");
    public final static String QINIUIMG = ResourceBundle.getBundle("thirdconfig").getString("QiniuImg");
    public final static String QINIUSEXY = ResourceBundle.getBundle("thirdconfig").getString("QiniuSexy");
}
