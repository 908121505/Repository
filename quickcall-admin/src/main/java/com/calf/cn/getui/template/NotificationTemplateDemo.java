package com.calf.cn.getui.template;

import com.gexin.rp.sdk.template.NotificationTemplate;

public class NotificationTemplateDemo {

	private static String appId = "RJd8wYCZVCAgySAp8AKqF";
    private static String appKey = "9W3d8Qw9IR8Gg8OluBV9b8";
	public static final String masterSecret = "n2VPl6ob7PAFI0VV90DHGA";   
	public static final String CID = "2d05990fb49fab630874a882da1f03c4";
	
	public static NotificationTemplate notificationTemplateDemo(String appId, String appkey) {
	    NotificationTemplate template = new NotificationTemplate();
	    // 设置APPID与APPKEY
	    template.setAppId(appId);
	    template.setAppkey(appkey);
	    // 设置通知栏标题与内容
	    template.setTitle("请输入通知栏标题");
	    template.setText("请输入通知栏内容");
	    // 配置通知栏图标
	    template.setLogo("icon.png");
	    // 配置通知栏网络图标
	    template.setLogoUrl("");
	    // 设置通知是否响铃，震动，或者可清除
	    template.setIsRing(true);
	    template.setIsVibrate(true);
	    template.setIsClearable(true);
	    // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
	    template.setTransmissionType(1);
	    template.setTransmissionContent("请输入您要透传的内容");
	    // 设置定时展示时间
	    // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
	    return template;
	}
	
	public static void main(String[] args) {
		notificationTemplateDemo(appId, appKey);
	}
	
}
