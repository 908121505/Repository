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
     * 后台编辑器上传的文件（暂不知哪儿用了，先定义着）
     */
    public static final String EDITOR_FILE = ROOT_PATH + "editor";
    /**
     * APP展示Banner的图片存放路径
     */
    public static final String APP_BANNER = ROOT_PATH + "banner";
    /**
     * APP展示技能的图片存放路径
     */
    public static final String APP_SKILL = ROOT_PATH + "skill";
    /**
     * 用户身份认证身份证图片存放路径
     **/
    public static final String IDENTITY_AUTH_ID_CARD_IMAGE = ROOT_PATH + "user/idcard";
    /**
     * 大V认证介绍录音文件存放路径
     **/
    public static final String BIG_V_INTRODUCE_AUDIO = ROOT_PATH + "user/audio";
    /**
     * 用户上传头像存储的路径
     **/
    public static final String USER_UPLOAD_HEAD_IMG = ROOT_PATH + "user/headimg";
    /**
     * 随机用户上传头像存储的路径
     **/
    public static final String FADE_USER_UPLOAD_HEAD_IMG = ROOT_PATH + "fadecustomer/headimg";
    /**
     * 大V技能认证声音文件存放路径
     */
    public static final String CUSTOMER_SKILL_CERTIFY_AUDIO = ROOT_PATH + "user/skillVoice";
    /**
     * 用户形象照文件存放路径
     */
    public static final String USER_APPEARANCE_DIR = ROOT_PATH + "user/appearance";
    /**
     * 用户声鉴卡文件存放路径
     */
    public static final String USER_VOICE_IDENTIFICATION_CARD_DIR = ROOT_PATH + "user/voiceIdentificationCard";
    /**
     * 弹窗广告图片存放路径
     */
    public static final String APP_ADVERTISEMENT = ROOT_PATH + "advertisement";
}
