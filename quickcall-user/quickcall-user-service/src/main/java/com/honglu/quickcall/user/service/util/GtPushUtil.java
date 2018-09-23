package com.honglu.quickcall.user.service.util;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.notify.Notify;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.base.payload.MultiMedia;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * <p>
 * 功能描述：个推
 *
 * @Package: com.xulu.loancenter.biz.util
 * @author: chenliuguang
 * @date: 2018年6月6日 下午9:45:04
 */
public class GtPushUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(GtPushUtil.class);

    private static final String APP_ID = "t1BeE1l2yo6dTuR28TQkd7";
    private static final String APP_KEY = "ymbJGmANDy54CCoTbXpmw";
    private static final String MASTER_SECRET = "KtldFuXwEa8EGGCdloiSS1";
    private static final String HOST = "http://sdk.open.api.igexin.com/apiex.htm";
    private static final String LINK_URL = "xulugj://www.xulu.com/native_new?a_name=preview.PreviewActivity&i_name=MakeAnAppointmentVC&need_login=1";

    /**
     * 向客户端推送消息
     *
     * @param clientIdList 设备cid列表
     * @param content      消息内容
     * @param title        消息标题
     */
    public static void pushMessage(List<String> clientIdList, String content, String title) {
        // 配置返回每个用户返回用户状态，可选
        System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
        IGtPush push = new IGtPush(HOST, APP_KEY, MASTER_SECRET);
        // 通知透传模板
        TransmissionTemplate template = pushMessageTemplate(content, title, LINK_URL);
        ListMessage message = new ListMessage();
        // 设置消息离线，并设置离线时间
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 1000 * 3600);
        message.setData(template);

        List<Target> targets = new ArrayList<>();
        int totalSize = clientIdList.size();
        boolean minFlag = false;
        if (totalSize <= 1000) {
            minFlag = true;
        }

        LOGGER.info("批量推送APP消息业务开始执行：{}", clientIdList.size());
        for (int i = 1; i <= clientIdList.size(); i++) {
            String cid = clientIdList.get(i - 1);
            Target target1 = new Target();
            target1.setAppId(APP_ID);
            target1.setClientId(cid);
            targets.add(target1);
            if (minFlag) {
                if (totalSize == i) {
                    LOGGER.info("=============本批次推送成功==================");
                    try {
                        String taskId = push.getContentId(message);
                        IPushResult ret = push.pushMessageToList(taskId, targets);
                        LOGGER.info("推送APP消息返回信息：{}", ret.getResponse().toString());
                    } catch (Exception e) {
                        LOGGER.error("==========本批次推送发送异常，异常信息：", e);
                    }
                    targets = new ArrayList<>();
                }
            } else {
                if (i % 1000 == 0) {
                    LOGGER.info("=============本批次推送成功==================");
                    try {
                        String taskId = push.getContentId(message);
                        IPushResult ret = push.pushMessageToList(taskId, targets);
                        LOGGER.info("推送APP消息返回信息：{}", ret.getResponse().toString());
                    } catch (Exception e) {
                        LOGGER.error("==========本批次推送发送异常，异常信息：", e);
                    }
                    targets = new ArrayList<>();
                } else {
                    if (totalSize == i) {
                        LOGGER.info("=============本批次推送成功==================");
                        try {
                            String taskId = push.getContentId(message);
                            IPushResult ret = push.pushMessageToList(taskId, targets);
                            LOGGER.info("推送APP消息返回信息：{}", ret.getResponse().toString());
                        } catch (Exception e) {
                            LOGGER.error("==========本批次推送发送异常，异常信息：", e);
                        }
                        targets = new ArrayList<>();
                    }
                }
            }
        }
    }

    /**
     * 组装模板{"target":"3","isTransparent":"1","isRing":"1","title":"XXX","alert":"XXX",
     * "linkUrl":"xulugj://www.xulu.com/native_new?a_name=preview.PreviewActivity&i_name=MakeAnAppointmentVC&need_login=1"
     * }
     *
     * @param content 消息内容
     * @param title   消息标题
     * @return
     */
    private static TransmissionTemplate pushMessageTemplate(String content, String title, String linkUrl) {
        TransmissionTemplate template = new TransmissionTemplate();
        // 设置APPID与APPKEY
        template.setAppId(APP_ID);
        template.setAppkey(APP_KEY);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(2);
        ObjectMapper json = new ObjectMapper();
        Map<String, String> map = new HashMap<>();
        map.put("target", "4");
        map.put("isTransparent", "1");
        map.put("isRing", "1");
        map.put("title", title);
        map.put("alert", content);
        map.put("linkUrl", linkUrl);
        String jsonparams = "";
        try {
            jsonparams = json.writeValueAsString(map);
        } catch (IOException e) {
            LOGGER.error("个推参数拼接异常，异常信息", e);
        }
        template.setTransmissionContent(jsonparams);//透传内容
        APNPayload payload = new APNPayload();
        // 在已有数字基础上加1显示，设置为-1时，在已有数字上减1显示，设置为数字时，显示指定数字
        payload.setAutoBadge("+1");
        // payload.setContentAvailable(1);
        payload.setSound("default");
        payload.setCategory("$由客户端定义");
        // 简单模式APNPayload.SimpleMsg
        // payload.setAlertMsg(new APNPayload.SimpleAlertMsg("hello"));

        // 字典模式使用APNPayload.DictionaryAlertMsg
        payload.setAlertMsg(getDictionaryAlertMsg(content, title));

        // 添加多媒体资源
        payload.addMultiMedia(new MultiMedia().setResType(MultiMedia.MediaType.video)
                .setResUrl("http://ol5mrj259.bkt.clouddn.com/test2.mp4").setOnlyWifi(true));

        template.setAPNInfo(payload);

        Notify notify = new Notify();
        notify.setContent(content);
        notify.setPayload(jsonparams);
        notify.setUrl(linkUrl);
        notify.setTitle(title);
        template.set3rdNotifyInfo(notify);

        return template;
    }


    /**
     * 字典模式值设置body 和 title即可，否则ISO低版本不支持
     *
     * @param body
     * @param title
     * @return
     */
    private static APNPayload.DictionaryAlertMsg getDictionaryAlertMsg(String body, String title) {
        APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
        alertMsg.setBody(body);
//		alertMsg.setActionLocKey("ActionLockey");
//		alertMsg.setLocKey("LocKey");
//		alertMsg.addLocArg("loc-args");
//		alertMsg.setLaunchImage("launch-image");
        // iOS8.2以上版本支持
        alertMsg.setTitle(title);
//		alertMsg.setTitleLocKey("TitleLocKey");
//		alertMsg.addTitleLocArg("TitleLocArg");
        return alertMsg;
    }

    public static void main(String[] args) {
        System.out.println(UUIDUtils.getUUID());

//		List<String> clientIdList =new  ArrayList<String>();
//		clientIdList.add("cde7d0a13a65bd77dc5aad9fe2112a09");
//		clientIdList.add("845975b8717db271dec57050299c8f36");
//		pushMessage(clientIdList , "11", "22");
    }

}
