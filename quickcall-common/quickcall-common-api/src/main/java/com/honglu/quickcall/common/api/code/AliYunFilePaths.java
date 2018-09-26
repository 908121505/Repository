package com.honglu.quickcall.common.api.code;

/**
 * 阿里云上传文件路径定义类
 *
 * @author duanjun
 * @date 2018-09-26 17:06
 */
public class AliYunFilePaths {
    /**
     * 根路径
     */
    private static final String ROOT_PATH = "voice/";
    /**
     * 用户身份认证身份证图片存放路径
     **/
    public static final String IDENTITY_AUTH_ID_CARD_IMAGE = ROOT_PATH + "user/idcard";
    /**
     * 大V认证介绍录音文件存放路径
     **/
    public static final String BIG_V_INTRODUCE_AUDIO = "user/audio";
    /**
     * 用户上传头像存储的路径
     **/
    public static final String USER_UPLOAD_HEAD_IMG = "user/headimg";
}
