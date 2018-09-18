package com.honglu.quickcall.task.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Notice {
    private Integer id;

    private Integer persionId;

    private Integer adminId;
    @JSONField (format="yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    private Integer state;

    private String title;

    private Integer flag;

    private Integer bdelete;

    private String content;
    private Integer noticeRecordId;
    private String linkUrl;

    public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNoticeRecordId() {
		return noticeRecordId;
	}

	public void setNoticeRecordId(Integer noticeRecordId) {
		this.noticeRecordId = noticeRecordId;
	}

	public Integer getPersionId() {
        return persionId;
    }

    public void setPersionId(Integer persionId) {
        this.persionId = persionId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getBdelete() {
        return bdelete;
    }

    public void setBdelete(Integer bdelete) {
        this.bdelete = bdelete;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}