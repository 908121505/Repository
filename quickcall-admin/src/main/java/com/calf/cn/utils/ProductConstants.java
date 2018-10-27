package com.calf.cn.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * Copyright © 2018 www.xiaoniu.com All rights reserved.
 * 
 * 功能描述：产品常用常量
 * @Package: com.calf.cn.utils 
 * @author: chenliuguang   
 * @date: 2018年5月22日 下午5:35:38
 */
public class ProductConstants {
	
	
	
	public  static List<String>  prodRoleCodeList(){
		List<String>  list =  new ArrayList<String>();
		list.add("1001");
		list.add("1002");
		list.add("1003");
		list.add("1014");
		list.add("1015");
		return list ;
	}
	public  static Map<Integer, String>  prodStatusMap(){
		Map<Integer, String>  prodStatusMap = new  HashMap<Integer, String>();
		prodStatusMap.put(PROD_STATUS_ONLINE, "已上线");
		prodStatusMap.put(PROD_STATUS_OFFLINE, "已下线");
		prodStatusMap.put(PROD_STATUS_WAITING_ORDERLINE, "待预约上线");
		prodStatusMap.put(PROD_STATUS_ORDERLINE, "预约上线");
		prodStatusMap.put(PROD_STATUS_WAITING_ONLINE, "待上线");
		prodStatusMap.put(PROD_STATUS_WAITING_OFFLINE, "待下线");
		return prodStatusMap ;
	}
	
	
	
	public static    Map<Integer, String>  auditStatusMap (){
		Map<Integer, String>  auditStatusMap = new  HashMap<Integer, String>();
		auditStatusMap.put(PROD_AUDIT_STATUS_WAITING, "待审核");
		auditStatusMap.put(PROD_AUDIT_STATUS_PASS, "审核通过");
		auditStatusMap.put(PROD_AUDIT_STATUS_REFUSE, "审核未通过");
		return auditStatusMap;
	}
	
	public static final  Integer  LOG_TYPE_APPROVE = 1 ;
	public static final  Integer  LOG_TYPE_SAVEUPDATE = 2 ;
	public static final  Integer  LOG_TYPE_JOBUPDATE = 3 ;
	
	
	
	
	
//	审核类型1：上线审核通过 2：上线审核拒绝 3：下线审核通过 4：下线审核拒绝 5：预约上线审核通过 6：预约上线审核拒绝
	/**审核类型1：上线审核通过*/
	public static final Integer PAT_PASS_ONLINE = 1 ;
	
	/**审核类型3：下线审核通过 */
	public static final Integer PAT_PASS_OFFLINE = 3 ;
	/**审核类型5：预约上线审核通过*/
	public static final Integer PAT_PASS_ORDERLINE = 5 ;
	/**审核类型 2：上线审核拒绝*/
	public static final Integer PAT_REFUSE_ONLINE = 2 ;
	/**审核类型4：下线审核拒绝 */
	public static final Integer PAT_REFUSE_OFFLINE = 4 ;
	/**审核类型 6：预约上线审核拒绝*/
	public static final Integer PAT_REFUSE_ORDERLINE = 6 ;
	
	
	
	/**操作类型：1上线操作*/
	public static final Integer OPERATE_TYPE_ONLINE = 1 ;
	/**操作类型：2下线操作*/
	public static final Integer OPERATE_TYPE_OFFLINE = 2 ;
	/**操作类型：3预约上线操作*/
	public static final Integer OPERATE_TYPE_ORDER = 3 ;
	/**操作类型：4不影响状态修改*/
	public static final Integer OPERATE_TYPE_PARTUPDATE = 4 ;
	
	
	
	
	
	/**返回前端操作类型：上线+预约上线*/
	public static final Integer OPERATE_ONLINE_ORDER = 1 ;
	/**返回前端操作类型：下线*/
	public static final Integer OPERATE_OFFLINE = 2 ;
	
	
	
	/**已上线*/
	public static final Integer PROD_STATUS_ONLINE = 1 ;
	/**已下线*/
	public static final Integer PROD_STATUS_OFFLINE = 2 ;
	/**待预约上线*/
	public static final Integer PROD_STATUS_WAITING_ORDERLINE = 30 ;
	/**预约上线*/
	public static final Integer PROD_STATUS_ORDERLINE = 3 ;
	/**待上线*/
	public static final Integer PROD_STATUS_WAITING_ONLINE = 4 ;
	/**待下线*/
	public static final Integer PROD_STATUS_WAITING_OFFLINE = 5 ;
	

	
	
	
	/**待审核*/
	public static final Integer PROD_AUDIT_STATUS_WAITING = 1 ;
	/**审核通过*/
	public static final Integer PROD_AUDIT_STATUS_PASS = 2 ;
	/**审核未通过*/
	public static final Integer PROD_AUDIT_STATUS_REFUSE = 3 ;
	
	
	/**审核标识 1：立即上线  2：根据上线时间上线  3：立即下线  4：根据下线时间下线*/
	/**审核标识1  立即上线*/
	public static final Integer PROD_AUDIT_FLAG_ONLINE_INSTANCE = 1 ;
	
	/**审核标识2  根据上线时间上线*/
	public static final Integer PROD_AUDIT_FLAG_BY_ONLINETIME = 2 ;
	
	/**审核标识3：立即下线*/
	public static final Integer PROD_AUDIT_FLAG_OFFLINE_INSTANCE = 3 ;
	
	/**审核标识 4:根据下线时间下线*/
	public static final Integer PROD_AUDIT_FLAG_BY_OFFLINETIME = 4 ;
	
	/**审核标识 5:预约上线审核*/
	public static final Integer PROD_AUDIT_FLAG_APPROVE_ONLINE = 5 ;
	/**审核标识 6:根据上线时间上线且根据下线时间下线*/
	public static final Integer PROD_AUDIT_FLAG_ONLINE_OFFLINE = 6 ;
	
	/**审核标识 14:立即上线且根据下线时间下线*/
	public static final Integer PROD_AUDIT_FLAG_INSTANCE_ONLINE_AUTO_OFFLINE = 14 ;
	
	
	
	
	
	
	

    
    /**10:00*/
    public static final String CLOCK_10 ="10:00";
    /**14:00*/
    public static final String CLOCK_14 ="14:00";
    /**16:00*/
    public static final String CLOCK_16 ="16:00";
    /**20:00*/
    public static final String CLOCK_20 ="20:00";
    /**10*/
    public static final int HOUR_10 =10;
    /**14*/
    public static final int HOUR_14 =14;
    /**16*/
    public static final int HOUR_16 =16;
    /**20*/
    public static final int HOUR_20 =20;
    
    
    public static final HashMap<String, Integer>  map = new HashMap<String, Integer>();
    public static final HashMap<String, Long>  seasonTypeMap = new HashMap<String, Long>();
    
    static{
    	map.put(CLOCK_10, HOUR_10);
    	map.put(CLOCK_14, HOUR_14);
    	map.put(CLOCK_16, HOUR_16);
    	map.put(CLOCK_20, HOUR_20);
    	seasonTypeMap.put(CLOCK_10, 1L);
    	seasonTypeMap.put(CLOCK_14, 2L);
    	seasonTypeMap.put(CLOCK_16, 3L);
    	seasonTypeMap.put(CLOCK_20, 4L);
    }
    
  /**重复提交问题*/
  public static final Integer  REPEAT_SUBMIT_FLAG = 888;
  /**提交类型1：审核拒绝*/
  public static final Integer  SUBMIT_TYPE_REJECT = 1 ;
  /**提交类型2：审核通过*/
  public static final Integer  SUBMIT_TYPE_PASS = 2 ;
  /**提交类型3：保存*/
  public static final Integer  SUBMIT_TYPE_SAVE = 3 ;
  
  
  
  /**推荐类型1：推荐*/
  public static final Integer  RECOMMEND_YES = 1 ;
  /**推荐类型2：不推荐*/
  public static final Integer  RECOMMEND_NO = 2 ;
  
  
  
  
  
  
  
  
}
