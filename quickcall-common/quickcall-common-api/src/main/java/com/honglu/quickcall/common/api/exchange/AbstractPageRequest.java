package com.honglu.quickcall.common.api.exchange;

/**
 * Created by len.song on 2017-12-10.
 */
public abstract class AbstractPageRequest extends AbstractRequest {
    private Integer pageIndex = 1;              //分页页数
    private Integer pageSize = 20;               //页数大小

    public Integer getPageIndex() {
        return pageIndex<1?1:pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize>100?100:pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
