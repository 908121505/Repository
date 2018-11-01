package com.honglu.quickcall.gateway.core.interceptor;

import com.honglu.quickcall.gateway.core.configure.AppAccount;
import com.honglu.quickcall.gateway.core.configure.AppAccountProperties;
import com.xping.framework.api.Header;
import com.xping.framework.api.PlatformException;
import com.xping.framework.api.ResultCodeEnum;
import com.xping.framework.core.signature.SignatureUtils;
import com.xping.framework.core.util.StringUtils;
import com.xping.framework.web.http.HeaderHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 签名拦截器
 *
 * @author guoqiang
 * @date 2018-08-15 11:11 AM
 */
@Component
public class SignatureInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LogManager.getLogger(SignatureInterceptor.class);

    private static final List<String> IGNORE_PROPERTIES;

    static {
        IGNORE_PROPERTIES = new ArrayList<>();
        IGNORE_PROPERTIES.add(StringUtils.convertDelimited2Hump(Header.NameEnum.CUSTOMER_ID.getName(), "-"));
        IGNORE_PROPERTIES.add(StringUtils.convertDelimited2Hump(Header.NameEnum.ACCESS_TOKEN.getName(), "-"));
    }

    @Autowired
    private AppAccountProperties appAccountProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HeaderHelper.checkHeader();
        Integer requestAgent = HeaderHelper.getRequestAgent();
        Header.RequestAgentEnum requestAgentEnum = Header.RequestAgentEnum.resolve(requestAgent);
        if (null == requestAgentEnum) {
            LOGGER.error("APP传进来的requestAgent:{}不在允许服务端允许范围内", HeaderHelper.getRequestAgent());
            throw new PlatformException(ResultCodeEnum.ILLEGAL_REQUEST_AGENT);
        }
        String key = requestAgentEnum.name().toLowerCase() + "-" + HeaderHelper.getAppName() + "-" + HeaderHelper.getAppVersion();
        AppAccount appAccount = appAccountProperties.getAppAcount(key);
        if (null == appAccount || StringUtils.isBlank(appAccount.getAppId())
                || StringUtils.isBlank(appAccount.getAppSecret())) {
            throw new PlatformException(ResultCodeEnum.NONE_APP_ACCOUNT_DATA);
        }
        if (!appAccount.getAppId().equals(HeaderHelper.getAppId())) {
            LOGGER.error("APP传进来的appId：{}与服务端appId：{}不一致", HeaderHelper.getAppId(), appAccount.getAppId());
            throw new PlatformException(ResultCodeEnum.ILLEGAL_APP_ID);
        }
        String stringToSign = HeaderHelper.getAppId() + HeaderHelper.getTimestamp();
        String targetSign = SignatureUtils.hashByHmacSHA256(stringToSign, appAccount.getAppSecret());
        if (!targetSign.equals(HeaderHelper.getSign())) {
            LOGGER.error("校验AppId：{}，时间戳：{}的签名：{}失败，服务器生成的签名是：{}", HeaderHelper.getAppId(),
                    HeaderHelper.getTimestamp(), HeaderHelper.getSign(), targetSign);
            throw new PlatformException(ResultCodeEnum.ILLEGAL_SIGNATURE);
        }
        return true;
    }
}
