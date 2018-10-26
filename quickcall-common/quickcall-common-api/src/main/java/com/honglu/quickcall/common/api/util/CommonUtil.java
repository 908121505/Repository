package com.honglu.quickcall.common.api.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: 公共工具类
 *
 * @author chenpeng
 * @date 2018/10/18 18:25
 */
public class CommonUtil {

    private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);
    /**
     * 字符不包括中文
     */
    private final static Pattern EN_COUNT_PATTERN = Pattern.compile("[a-zA-Z\\d~`!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？_]*");
    /**
     * 字符包括中文
     */
    private final static Pattern CH_EN_PATTERN = Pattern.compile("[\\u4e00-\\u9fa5a-zA-Z\\d`!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？_]*");
    /**
     * 中文
     */
    private final static Pattern CH_PATTERN = Pattern.compile("[\\u4e00-\\u9fa5]");


    /**
     * 昵称规则校验
     */
    public static Boolean checkNickName(String nickName) {
        Boolean ifPass = false;
        try {
            //包含中文个数
            int count = 0;
            char[] c = nickName.toCharArray();
            int nickNameLength = c.length;
            for (int i = 0; i < nickNameLength; i++) {
                Matcher m = CH_PATTERN.matcher(String.valueOf(c[i]));
                if (m.matches()) {
                    count++;
                }
            }
            if (count == 0) {
                Matcher m = EN_COUNT_PATTERN.matcher(nickName);
                if (!m.matches()) {
                    return ifPass;
                }
                if (nickNameLength < 4 || nickNameLength > 20) {
                    return ifPass;
                }

            } else {
                Matcher m = CH_EN_PATTERN.matcher(nickName);
                if (!m.matches()) {
                    return ifPass;
                }
                int actLength = count + nickNameLength;
                if (actLength < 4 || actLength > 20) {
                    return ifPass;
                }
            }

        } catch (Exception e) {
            logger.error("用户昵称格式校验异常！异常信息:{}", e.getMessage(), e);
            e.printStackTrace();
            return ifPass;
        }

        ifPass = true;
        return ifPass;
    }

    /**
     * 个性签名字符格式规则校验
     */
    public static Boolean checkSignName(String signName) {
        Boolean ifPass = false;
        try {
            int count = 0;
            char[] c = signName.toCharArray();
            int signNameLength = c.length;
            for (int i = 0; i < signNameLength; i++) {
                Matcher m = CH_PATTERN.matcher(String.valueOf(c[i]));
                if (m.matches()) {
                    count++;
                }
            }
            if (count == 0) {
                Matcher m = EN_COUNT_PATTERN.matcher(signName);
                if (!m.matches()) {
                    return ifPass;
                }
                if (signNameLength > 20) {
                    return ifPass;
                }

            } else {
                Matcher m = CH_EN_PATTERN.matcher(signName);
                if (!m.matches()) {
                    return ifPass;
                }
                int actLength = count + signNameLength;
                if (actLength > 20) {
                    return ifPass;
                }
            }

        } catch (Exception e) {
            logger.error("个性签名字符格式校验异常！异常信息:{}", e.getMessage(), e);
            e.printStackTrace();
            return ifPass;
        }

        ifPass = true;
        return ifPass;
    }

}
