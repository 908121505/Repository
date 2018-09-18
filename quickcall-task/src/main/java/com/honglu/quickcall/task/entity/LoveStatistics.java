package com.honglu.quickcall.task.entity;

import java.util.Date;

public class LoveStatistics {
    private Integer id;

    private Integer presonid;

    private Date createtime;

    private Integer datelove;

    private Integer weeklove;

    private Integer monthlove;

    private Integer updatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPresonid() {
        return presonid;
    }

    public void setPresonid(Integer presonid) {
        this.presonid = presonid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getDatelove() {
        return datelove;
    }

    public void setDatelove(Integer datelove) {
        this.datelove = datelove;
    }

    public Integer getWeeklove() {
        return weeklove;
    }

    public void setWeeklove(Integer weeklove) {
        this.weeklove = weeklove;
    }

    public Integer getMonthlove() {
        return monthlove;
    }

    public void setMonthlove(Integer monthlove) {
        this.monthlove = monthlove;
    }

    public Integer getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Integer updatetime) {
        this.updatetime = updatetime;
    }
}