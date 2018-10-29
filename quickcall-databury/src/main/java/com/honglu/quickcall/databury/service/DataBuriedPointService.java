package com.honglu.quickcall.databury.service;

import com.alibaba.fastjson.JSON;
import com.honglu.quickcall.databury.enums.EventEnums;
import com.honglu.quickcall.databury.exception.DataBuriedPointException;
import com.honglu.quickcall.databury.util.BuryiedPointDataConvertor;
import com.honglu.quickcall.databury.util.BuryiedPointUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
 * 数据埋点处理
 * @author xiangping
 * @date 2018-10-28 22:33
 */
@Service("dataBuriedPointService")
public class DataBuriedPointService {

    private static final Logger logger = LoggerFactory.getLogger(DataBuriedPointService.class);

    /**
     * Web 浏览页面
     *
     * @param map
     * @return
     * @throws DataBuriedPointException
     */
    public void saveWebBrowseThePageData(Map<String, Object> map) throws DataBuriedPointException {
        logger.info("组装--Web浏览页面--数据--参数为:{}", JSON.toJSONString(map));
        if(map == null){
            throw new DataBuriedPointException("神策埋点-Web浏览页面-消费的mq参数为空");
        }
        Map<String,Object> event = new HashMap<>(10);
        event.put("is_first_time",map.get("is_first_time"));
        event.put("title",map.get("title"));
        event.put("url",map.get("url"));
        event.put("url_path",map.get("url_path"));
        event.put("referrer_host",map.get("referrer_host"));
        event.put("referrer",map.get("referrer"));
        event.put("utm_campaign",map.get("utm_campaign"));
        event.put("utm_medium",map.get("utm_medium"));
        event.put("utm_term",map.get("utm_term"));
        event.put("utm_content",map.get("utm_content"));

        Map<String,Object> data = BuryiedPointDataConvertor.newInstanceEvent(EventEnums.EVENT_PageView.getValue(),(String)map.get("accountId"),event);
        logger.info("组装数据完成-开始埋点--数据={}", JSON.toJSONString(data));

        BuryiedPointUtil.buryData(map);
    }

    /**
     * Web 浏览页面
     *
     * @param map
     * @return
     * @throws DataBuriedPointException
     */
    public void saveWebClickData(Map<String, Object> map) throws DataBuriedPointException {
        logger.info("组装--Web元素点击--数据--参数为:{}", JSON.toJSONString(map));
        if(map == null){
            throw new DataBuriedPointException("神策埋点-Web浏览页面-消费的mq参数为空");
        }
        Map<String,Object> event = new HashMap<>(10);
        event.put("element_id",map.get("element_id"));
        event.put("element_content",map.get("element_content"));
        event.put("element_name",map.get("element_name"));
        event.put("element_class_name",map.get("element_class_name"));
        event.put("element_type",map.get("element_type"));
        event.put("element_selector",map.get("element_selector"));
        event.put("element_target_url",map.get("element_target_url"));
        event.put("url",map.get("url"));
        event.put("title",map.get("title"));
        event.put("url_path",map.get("url_path"));

        Map<String,Object> data = BuryiedPointDataConvertor.newInstanceEvent(EventEnums.EVENT_WebClick.getValue(),(String)map.get("accountId"),event);
        logger.info("组装数据完成-开始埋点--数据={}", JSON.toJSONString(data));

        BuryiedPointUtil.buryData(map);
    }
}
