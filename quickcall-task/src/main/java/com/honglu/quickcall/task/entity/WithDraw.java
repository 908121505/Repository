package com.honglu.quickcall.task.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class WithDraw  {
	private Integer id;

    private String onceid;
    private Integer pid;

    private BigDecimal money;
    @JSONField (format="yyyy-MM-dd HH:mm:ss")
    private Date createdate;
    @JSONField (format="yyyy-MM-dd HH:mm:ss")
    private Date handledate;

    private Integer state;

    private String batchno;

    private Integer adminid;

    private BigDecimal platformCost;

    private Integer dealwith;
    private Integer stream_state;
	private Integer w_type;
	
	public Integer getW_type() {
		return w_type;
	}

	public void setW_type(Integer w_type) {
		this.w_type = w_type;
	
	}

    public Integer getStream_state() {
		return stream_state;
	}

	public void setStream_state(Integer stream_state) {
		this.stream_state = stream_state;
	}

	public WithDraw( String onceid, Integer pid, BigDecimal money, Date createdate,
			Integer state, BigDecimal platformCost, Integer dealwith) {
		this.onceid = onceid;
		this.pid = pid;
		this.money = money;
		this.createdate = createdate;
		this.state = state;
		this.platformCost = platformCost;
		this.dealwith = dealwith;
	}
    
    public WithDraw(Integer id, String onceid, Integer pid, BigDecimal money, Date createdate, Date handledate,
			Integer state, String batchno, Integer adminid, BigDecimal platformCost, Integer dealwith) {
		super();
		this.id = id;
		this.onceid = onceid;
		this.pid = pid;
		this.money = money;
		this.createdate = createdate;
		this.handledate = handledate;
		this.state = state;
		this.batchno = batchno;
		this.adminid = adminid;
		this.platformCost = platformCost;
		this.dealwith = dealwith;
	}
    
    

	public WithDraw() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOnceid() {
		return onceid;
	}

	public void setOnceid(String onceid) {
		this.onceid = onceid;
	}

	public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getHandledate() {
        return handledate;
    }

    public void setHandledate(Date handledate) {
        this.handledate = handledate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getBatchno() {
        return batchno;
    }

    public void setBatchno(String batchno) {
        this.batchno = batchno == null ? null : batchno.trim();
    }

    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }

    public BigDecimal getPlatformCost() {
        return platformCost;
    }

    public void setPlatformCost(BigDecimal platformCost) {
        this.platformCost = platformCost;
    }

    public Integer getDealwith() {
        return dealwith;
    }

    public void setDealwith(Integer dealwith) {
        this.dealwith = dealwith;
    }
}