package com.honglu.quickcall.account.facade.vo;

import java.io.Serializable;

/**
 * 弹幕消息页面对象
 *
 * @author xiangping
 * @date 2018-10-28 9:51
 */
public class PopWindowVO implements Serializable {

    private static final long serialVersionUID = -7160838423061020263L;
    /**
     * 资源路径
     **/
    private String sourceUrl;
    /**
     * 用户头像
     **/
    private String headPortraitUrl;
    /**
     * 是否弹窗
     */
    private boolean showWindow;

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getHeadPortraitUrl() {
        return headPortraitUrl;
    }

    public void setHeadPortraitUrl(String headPortraitUrl) {
        this.headPortraitUrl = headPortraitUrl;
    }

    public boolean isShowWindow() {
        return showWindow;
    }

    public void setShowWindow(boolean showWindow) {
        this.showWindow = showWindow;
    }
}
