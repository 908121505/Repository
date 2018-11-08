package com.honglu.quickcall.user.service.service.impl;

import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.ResultUtils;
import com.honglu.quickcall.user.facade.exchange.request.WeiXinRequest;
import com.honglu.quickcall.user.facade.vo.SearchPersonByPhoneVO;
import com.honglu.quickcall.user.service.dao.CustomerMapper;
import com.honglu.quickcall.user.service.service.WeiXinService;
import com.honglu.quickcall.user.service.util.HttpUtils;
import com.honglu.quickcall.user.service.util.SnsAccessToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiangping
 * @date 2018-11-08 21:12
 */
@Service
public class WeiXinServiceImpl implements WeiXinService {

    private String openid_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={0}&secret={1}&code={2}&grant_type=authorization_code";
    private String appId = "wxf17e5960e8fdc170";
    private String appSecret = "6e5568d478f041679a88732f75b5edd4";

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public CommonResponse getOpenId(WeiXinRequest params) {
        String url = MessageFormat.format(openid_url, appId, appSecret, params.getCode());
        SnsAccessToken msg = HttpUtils.doGet(url, SnsAccessToken.class);
        Map<String,String> map = new HashMap<>();
        map.put("openid",msg.getOpenid());
        if (StringUtils.isNotBlank(params.getPhone())) {
            List<SearchPersonByPhoneVO> customers = customerMapper.queryPersonByPhone(Long.parseLong(params.getPhone()));
            if (customers!=null&&customers.size()>0){
                map.put("customer_id",String.valueOf(customers.get(0).getCustomerId()));
                map.put("nickname",customers.get(0).getNickName());
            }
        }
        return ResultUtils.resultSuccess(map);
    }
}
