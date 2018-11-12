package com.honglu.quickcall.account.service.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.honglu.quickcall.account.facade.constants.OrderSkillConstants;
import com.honglu.quickcall.account.facade.entity.CustomerSkill;
import com.honglu.quickcall.account.facade.entity.SkillItem;
import com.honglu.quickcall.account.facade.entity.SkillItemExt;
import com.honglu.quickcall.account.facade.exchange.request.SkillUpdateRequest;
import com.honglu.quickcall.account.facade.vo.CustomerSkillExtVO;
import com.honglu.quickcall.account.facade.vo.CustomerSkillInfoVO;
import com.honglu.quickcall.account.facade.vo.CustomerSkillRequestVO;
import com.honglu.quickcall.account.facade.vo.CustomerSkillVO;
import com.honglu.quickcall.account.facade.vo.DaVinfoListVO;
import com.honglu.quickcall.account.facade.vo.DaVinfoVO;
import com.honglu.quickcall.account.facade.vo.FirstPageSkillIteminfoVO;
import com.honglu.quickcall.account.facade.vo.SkillUnitPriceVO;
import com.honglu.quickcall.account.service.dao.CustomerSkillMapper;
import com.honglu.quickcall.account.service.dao.SkillItemExtMapper;
import com.honglu.quickcall.account.service.dao.SkillItemMapper;
import com.honglu.quickcall.account.service.service.IProductSkillService;
import com.honglu.quickcall.common.api.util.DateUtils;
import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.common.api.util.RedisKeyConstants;
import com.honglu.quickcall.producer.facade.business.DataDuriedPointBusiness;
import com.honglu.quickcall.producer.facade.req.databury.DataBuriedPointOrderButtonReq;

@Service("productSkillService")
public class ProductSkillServiceImpl implements IProductSkillService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductSkillServiceImpl.class);


    @Autowired
    private DataDuriedPointBusiness dataDuriedPointBusiness;

//	private  static final  Integer  WEEK_INDEX_DEFAULT = 0 ;
	public  static final  String  ENDTIME_STR_24 = "2400" ;
	public  static final  String  ENDTIME_STR_00 = "0000" ;
	private  static  final  String  START_TIME_KEY =  "startTime";
	private  static  final  String  END_TIME_KEY =  "endTime";


	
	@Autowired
	private SkillItemMapper skillItemMapper;
	@Autowired
	private CustomerSkillMapper customerSkillMapper;

	@Autowired
	private SkillItemExtMapper skillItemExtMapper;

	@Override
	public DaVinfoListVO getResourceDaVinfoList() {
		DaVinfoListVO resultVO = new DaVinfoListVO();
		resultVO.setSkillItemName("大V推荐");
		resultVO.setSkillItemId(100000L);
		List<DaVinfoVO> daVinfoList = new ArrayList<DaVinfoVO>();
		String coverUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539668863932.png";
		String bussTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539668863932.png";
		String categoryTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539668869623.png";
		for (int i = 0; i < 6; i++) {
			daVinfoList.add(getDaVinfoVO("半小时", bussTagUrl, categoryTagUrl, coverUrl));
		}
		resultVO.setDaVinfoList(daVinfoList);
		return resultVO;
	}

	@Override
	public List<DaVinfoListVO> getTagDaVinfoList() {
		List<DaVinfoListVO> resultList = new ArrayList<DaVinfoListVO>();
		for (int i = 0; i < 4; i++) {
			String unitName = "半小时";
			String bussTagUrl = "";
			String categoryTagUrl = "";
			String coverUrl = "";
			DaVinfoListVO listVO = new DaVinfoListVO();
			if (i == 0) {
				listVO.setSkillItemName("哄睡");
				unitName = "次";
				coverUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539668863932.png";
				bussTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539668863932.png";
				categoryTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539668869623.png";
			} else if (i == 1) {
				listVO.setSkillItemName("叫醒");
				unitName = "次";
				coverUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539682726634.png";
				bussTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539682726634.png";
				categoryTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539682729263.png";
			} else if (i == 2) {
				listVO.setSkillItemName("声优聊天");
				coverUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539744936508.png";
				bussTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539744936508.png";
				categoryTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539744942470.png";
			} else if (i == 3) {
				listVO.setSkillItemName("情感咨询");
				coverUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539744047494.png";
				bussTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539744047494.png";
				categoryTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539744942470.png";
			}
			listVO.setSkillItemId(1000L);
			List<DaVinfoVO> daVinfoList = new ArrayList<DaVinfoVO>();
			for (int j = 0; j < 4; j++) {
				daVinfoList.add(getDaVinfoVO(unitName, bussTagUrl, categoryTagUrl, coverUrl));
			}
			listVO.setDaVinfoList(daVinfoList);
			resultList.add(listVO);
		}

		return resultList;
	}

	private DaVinfoVO getDaVinfoVO(String unitName, String bussTagUrl, String categoryTagUrl, String coverUrl) {
		DaVinfoVO infoVO = new DaVinfoVO();
		infoVO.setAge(18);
		infoVO.setBussTagUrl(bussTagUrl);
		infoVO.setCategoryTagUrl(categoryTagUrl);
		infoVO.setCustomerId(100L);
		infoVO.setNickName("小陈");
		infoVO.setPrice(new BigDecimal(40));
		infoVO.setCustomerSkillId(1000L);
		int rd = Math.random() > 0.5 ? 1 : 0;
		infoVO.setSex(rd);
		infoVO.setUnitName(unitName);
		infoVO.setCoverUrl(coverUrl);
		infoVO.setCurrencyName(Math.random() > 0.5 ? "币" : "元");
		infoVO.setSkillItemName("哄睡");
		infoVO.setVoiceTime(new BigDecimal(10));
		infoVO.setVoiceUrl("http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/user/audio/51d8d1dbace54c41a56208864c1c9284.mp3");
		return infoVO;
	}

	@Override
	public List<FirstPageSkillIteminfoVO> selectPartSkill() {
		return customerSkillMapper.selectPartSkill();
	}

	@Override
	public List<DaVinfoVO> getDaVListBySkillId(Long skillId) {
		String unitName = "半小时";
		List<DaVinfoVO> listVO = new ArrayList<DaVinfoVO>();
		String coverUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539744047494.png";
		String bussTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539744942470.png";
		String categoryTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539744942470.png";
		for (int j = 0; j < 5; j++) {
			listVO.add(getDaVinfoVO(unitName, bussTagUrl, categoryTagUrl, coverUrl));
		}
		return listVO;
	}

	public Integer getNewCustomerSkillInfoVO(CustomerSkill skill, CustomerSkillInfoVO skillVO) {
		HashMap<String, Integer> weekDataMap = new HashMap<String, Integer>();
//		String endTimeStr = skill.getEndTimeStr();
		Integer sunday = skill.getSunday();
		Integer saturday = skill.getSaturday();
		Integer tuesday = skill.getTuesday();
		Integer wednesday = skill.getWednesday();
		Integer thursday = skill.getThursday();
		Integer friday = skill.getFriday();
		Integer monday = skill.getMonday();
		weekDataMap.put("tuesday", skill.getTuesday());
		weekDataMap.put("monday", skill.getMonday());
		weekDataMap.put("wednesday", skill.getWednesday());
		weekDataMap.put("thursday", skill.getThursday());
		weekDataMap.put("friday", skill.getFriday());
		weekDataMap.put("saturday", skill.getSaturday());
		weekDataMap.put("sunday", skill.getSunday());
		skillVO.setWeekDataMap(weekDataMap);
		return sunday+saturday+tuesday+wednesday+thursday +friday +monday;
		
		
		
		/**
		 * 为了兼容ios和Android约定如下：
		 * 结束时间入参：0000   2400    落库：2400   判断时采用2359进行判断时间
		 * 
		 * 查询：库中数据2400   同意返回前端00:00
		 * 
		 */
//		if(StringUtils.isNotBlank(endTimeStr)){
//			if(ENDTIME_STR_24.equals(endTimeStr)){
//				skillVO.setEndServiceTimeStr("00:00");
//			}else{
//				String  startStr =  endTimeStr.substring(0, 2);
//				String  endStr =  endTimeStr.substring(2, 4);
//				skillVO.setEndServiceTimeStr(startStr + ":"+endStr);
//			}
//		}
		
		/*if(StringUtils.isNotBlank(endTimeStr)){
			if(ENDTIME_STR_24.equals(endTimeStr)){
				skillVO.setEndServiceTimeStr("00:00");
			}else{
				String  startStr =  endTimeStr.substring(0, 2);
				String  endStr =  endTimeStr.substring(2, 4);
				skillVO.setEndServiceTimeStr(startStr + ":"+endStr);
			}
		}*/
		
	}
	
	


	/**
	 * 获取技能可选项
	 * @param skillItemId
	 * @param skillVO
	 */
	public void getSkillExtList(Long skillItemId,CustomerSkillVO skillVO) {
		List<CustomerSkillExtVO> resultList = new ArrayList<CustomerSkillExtVO>();
		List<SkillItemExt> skillItemExtList = skillItemExtMapper.querySkillItemExtList(skillItemId, 1,1);
		if (CollectionUtils.isEmpty(skillItemExtList)) {
			return;
		}
		HashMap<Integer, List<SkillUnitPriceVO>> itemExtMap = new HashMap<Integer, List<SkillUnitPriceVO>>();
		
		Integer  firstRange = null ;
		for (SkillItemExt itemExt : skillItemExtList) {
			//需要使用常量进行限制
			List<SkillUnitPriceVO> list = null;
			Integer skillExtRange = itemExt.getSkillExtRange();
			if(firstRange == null){
				firstRange =itemExt.getSkillExtRange();
			}
			if (itemExtMap.containsKey(skillExtRange)) {
				list = itemExtMap.get(skillExtRange);
			} else {
				list = new ArrayList<SkillUnitPriceVO>();
			}
			SkillUnitPriceVO unitPriceVO = new SkillUnitPriceVO();

			unitPriceVO.setSkillItemExtId(itemExt.getId());
			unitPriceVO.setUnitName(itemExt.getSkillExtUnit());
			unitPriceVO.setUnitPrice(itemExt.getSkillExtPrice());
			list.add(unitPriceVO);
			itemExtMap.put(skillExtRange, list);
		}
		
		
		

		for (Map.Entry<Integer, List<SkillUnitPriceVO>> entry : itemExtMap.entrySet()) {
			CustomerSkillExtVO extVO = new CustomerSkillExtVO();
			extVO.setSkillRangeValue(entry.getKey());
			extVO.setUnitPriceList(entry.getValue());
			resultList.add(extVO);
		}
		
		skillVO.setSkillExtList(resultList);
		String  oldServiceUnit = skillVO.getOldServiceUnit();
		if(StringUtils.isBlank(oldServiceUnit) ){
			List<SkillUnitPriceVO>  list =  itemExtMap.get(1);
			skillVO.setOldServiceUnit(list.get(0).getUnitName());
		}
		
		
		Integer  oldSkillRange = skillVO.getOldSkillRange();
		if(oldSkillRange == null){
			skillVO.setOldSkillRange(firstRange);
		}
		
		Long  skillItemExtId  = skillVO.getOldSkillItemExtId();
		if(skillItemExtId == null){
			List<SkillUnitPriceVO>  list =  itemExtMap.get(1);
			skillVO.setOldSkillPrice(list.get(0).getUnitPrice());
			skillVO.setOldSkillItemExtId(list.get(0).getSkillItemExtId());
		}
		
		
		BigDecimal  skillPrice =  skillVO.getOldSkillPrice();
		if(skillPrice == null){
			List<SkillUnitPriceVO>  list =  itemExtMap.get(1);
			skillVO.setOldSkillPrice(list.get(0).getUnitPrice());
		}
		
		List<BigDecimal> discontRateList = new ArrayList<BigDecimal>();
		List<SkillItemExt> skillItemExtDiscountList = skillItemExtMapper.querySkillItemExtDiscountList(skillItemId, 1,2);
		if(!CollectionUtils.isEmpty(skillItemExtDiscountList)){
			for (SkillItemExt skillItemExt : skillItemExtDiscountList) {
				discontRateList.add(skillItemExt.getSkillExtDiscont());
			}
			skillVO.setDiscontRateList(discontRateList);
			BigDecimal  oldDiscountRate = skillVO.getOldDiscountRate();
			if(oldDiscountRate == null){
				skillVO.setOldDiscountRate(skillItemExtDiscountList.get(0).getSkillExtDiscont());
			}else{
				if(oldDiscountRate.compareTo(BigDecimal.ZERO) == 0){
					skillVO.setOldDiscountRate(skillItemExtDiscountList.get(0).getSkillExtDiscont());
				}
			}
			
		}
		
		
		
		
		
	}
	
	

	
	private static final  Integer  WEEK_INDEX_DEFAULT = 0;

	@Override
	public CustomerSkillInfoVO querySkillInfoPersonal(Long customerId) {

		CustomerSkillInfoVO resultVO = new CustomerSkillInfoVO();
		// 获取用户技能列表信息
		List<CustomerSkill> custSkillList = customerSkillMapper.querySkillInfoPersonal(customerId);
		if (CollectionUtils.isEmpty(custSkillList)) {
			resultVO.setAutoReceiveStatus(0);
			resultVO.setCustomerSkillList(null);
			resultVO.setEndServiceTimeStr("23:59");
			resultVO.setReceiveStatus(0);
			resultVO.setStartServiceTimeStr("00:00");
			HashMap<String, Integer> weekDataMap = new HashMap<String, Integer>();
			weekDataMap.put("tuesday", WEEK_INDEX_DEFAULT);
			weekDataMap.put("monday", WEEK_INDEX_DEFAULT);
			weekDataMap.put("wednesday", WEEK_INDEX_DEFAULT);
			weekDataMap.put("thursday", WEEK_INDEX_DEFAULT);
			weekDataMap.put("friday", WEEK_INDEX_DEFAULT);
			weekDataMap.put("saturday", WEEK_INDEX_DEFAULT);
			weekDataMap.put("sunday", WEEK_INDEX_DEFAULT);
			resultVO.setWeekDataMap(weekDataMap );
			return resultVO;
		}
		
		//自动接单开始时间
		String   startServiceTimeStr = "" ;
		//自动接单结束时间
		String   endServiceTimeStr = "";
		//接单设置开关
		Integer receiveStatus = null;
		//自动接单开关
		Integer autoReceiveStatus = null ;
		
		Integer  sumIndex = null ;
		
		List<Long> skillIdList = new ArrayList<Long>();
		List<CustomerSkillVO> customerSkillList = new ArrayList<CustomerSkillVO>();
		for (CustomerSkill custSkill : custSkillList) {
			CustomerSkillVO skillVO = new CustomerSkillVO();
			if (receiveStatus == null) {
				receiveStatus = custSkill.getReceiveStatus();
				resultVO.setReceiveStatus(receiveStatus);
				sumIndex = getNewCustomerSkillInfoVO(custSkill, resultVO);
			}
			
			if(autoReceiveStatus == null){
				autoReceiveStatus = custSkill.getAutoReceiveStatus();
			}
			skillIdList.add(custSkill.getCustomerSkillId());
			skillVO.setCustomerSkillId(custSkill.getCustomerSkillId());
			skillVO.setOldDiscountRate(custSkill.getDiscountRate());
			skillVO.setOldSkillRange(custSkill.getSkillRange());
			skillVO.setOldSkillItemExtId(custSkill.getSkillItemExtId());
			skillVO.setOldServiceUnit(custSkill.getServiceUnit());
			skillVO.setSkillItemName(custSkill.getSkillName());
			skillVO.setSwitchStatus(custSkill.getSwitchStatus());
			skillVO.setOldSkillPrice(custSkill.getDiscountPrice());
			skillVO.setSkillType(custSkill.getSkillType());
			// 根据技能ID获取可选技能信息
			Long skillItemId = custSkill.getSkillItemId();
			getSkillExtList(skillItemId,skillVO);
			
			//去时间字符串
			//返回给客户端自动接单开始时间和结束时间
			if(StringUtils.isBlank(startServiceTimeStr)){
				if(sumIndex > 0){
					String  startStr = custSkill.getStartTimeStr();
					if(StringUtils.isNotBlank(startStr) && startStr.length() == 4){
						startServiceTimeStr = startStr.substring(0,2)+":"+startStr.substring(2,4);
					}
				}else{
					Date  appointStartTime = custSkill.getAppointStartTime();
					if(appointStartTime != null){
						startServiceTimeStr = DateUtils.getDateHHMMTime(appointStartTime);
					}
				}
			}
			if(StringUtils.isBlank(endServiceTimeStr)){
				if(sumIndex > 0){
					String  endStr = custSkill.getEndTimeStr();
					if(StringUtils.isNotBlank(endStr) && endStr.length() == 4){
						endServiceTimeStr = endStr.substring(0,2)+":"+endStr.substring(2,4);
					}
				}else{
					Date  appointEndTime = custSkill.getAppointEndTime();
					if(appointEndTime != null){
						endServiceTimeStr = DateUtils.getDateHHMMTime(appointEndTime);
					}
				}
			}
			
			skillVO.setSkillItemId(skillItemId);
			customerSkillList.add(skillVO);

		}
		//自动接单开关为空，则给默认值
		if(autoReceiveStatus == null){
			autoReceiveStatus = 0;
		}
		resultVO.setAutoReceiveStatus(autoReceiveStatus);
		//默认值
		if(StringUtils.isBlank(startServiceTimeStr)){
			startServiceTimeStr = "00:00";
		}
		//默认值
		resultVO.setStartServiceTimeStr(startServiceTimeStr);
		if(StringUtils.isBlank(endServiceTimeStr)){
			endServiceTimeStr = "23:59";
		}
		resultVO.setEndServiceTimeStr(endServiceTimeStr);
		//返回自动接单开关
		//需要回显原来选择的结束时间
		resultVO.setCustomerSkillList(customerSkillList);
		return resultVO;
	}


	
	

	public   Map<String, Date>   getAppointEndTime(String  startTimeStr,String  endTimeStr){

		//
		Map<String, Date>  resultMap = new HashMap<String,Date>();
		if(StringUtils.isBlank(startTimeStr) || startTimeStr.length() < 4){
			startTimeStr = "0000";
		}
		if(StringUtils.isBlank(endTimeStr) || endTimeStr.length() < 4){
			endTimeStr = "2359";
		}
		
		
		
		Integer  startTimeIndex =  Integer.valueOf(startTimeStr);
		
		//当前时间
		Calendar  cal =  Calendar.getInstance();
		Integer  currHour= cal.get(Calendar.HOUR_OF_DAY);
		Integer  currMinute = cal.get(Calendar.MINUTE);
		
		String  currTimeStr =  (currHour < 10 ? "0"+currHour :currHour+"")+ (currMinute < 10  ? "0"+currMinute :currMinute +"" );
		Integer  currTimeIndex =  Integer.valueOf(currTimeStr);
		
		Integer   selectHourIndex = Integer.valueOf(startTimeStr.substring(0, 2));
		Integer   selectMinuteIndex = Integer.valueOf(startTimeStr.substring(2, 4));
		
		
		Date  appointEndTime = null ;//预约结束时间
		Date  appointStartTime = null ;//预约开始时间
		
		//选中的结束时间在当前时间之后
		if(startTimeIndex >=  currTimeIndex ){
			cal.set(Calendar.HOUR_OF_DAY, selectHourIndex);
			cal.set(Calendar.MINUTE, selectMinuteIndex);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			appointStartTime = cal.getTime();
		}else{
			//选中的结束时间在当前时间只前
			//结束时间向后推1天
			cal.add(Calendar.DAY_OF_YEAR, 1);
			cal.set(Calendar.HOUR_OF_DAY, selectHourIndex);
			cal.set(Calendar.MINUTE, selectMinuteIndex);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			//计算结束时间
			appointStartTime =cal.getTime(); 
		}
		
		if(StringUtils.isBlank(endTimeStr)){
			appointEndTime = DateUtils.getDayEndTime(appointStartTime);
		}else{
			//结束字符串不为空，则需要根究结束字符串进行计算
			Integer   endTimeHour = Integer.valueOf(endTimeStr.substring(0, 2));//结束时间小时
			Integer   endTimeMinute = Integer.valueOf(endTimeStr.substring(2, 4));//结束时间分钟
			
			cal =  Calendar.getInstance();
			cal.setTime(appointStartTime);
			cal.set(Calendar.HOUR_OF_DAY, endTimeHour);
			cal.set(Calendar.MINUTE, endTimeMinute);
			cal.set(Calendar.SECOND, 59);
			cal.set(Calendar.MILLISECOND, 0);
			appointEndTime =  cal.getTime();
		}
		resultMap.put(START_TIME_KEY, appointStartTime);
		resultMap.put(END_TIME_KEY, appointEndTime);
		return resultMap;
	}
	
	
	
	
	/**
	 * 为了兼容ios和Android约定如下：
	 * 结束时间入参：0000   2400    落库：2400   判断时采用2359进行判断时间
	 */
	

//	private  static final  Integer  WEEK_INDEX_DEFAULT = 0 ;


	@Override
	public void updateSkillInfoPersonal(SkillUpdateRequest request) {
		
		
		List<CustomerSkillRequestVO>  list = request.getCustomerSkillList();
		if(CollectionUtils.isEmpty(list)){
			return ;
		}
		
		Integer  autoReceiveStatus = request.getAutoReceiveStatus();
		Date  appointStartTime =  null;
		Date  appointEndTime = null ;
		
		if(autoReceiveStatus == 1){
			String  startTimeStr = request.getStartServiceTimeStr();
			String  endTimeStr = request.getEndServiceTimeStr();
			//根据结束时间获取预约结束时间
			
			Map<String, Date>  dateMap = getAppointEndTime(startTimeStr,endTimeStr);
			appointStartTime = dateMap.get(START_TIME_KEY);
			appointEndTime =dateMap.get(END_TIME_KEY);
		}
		
		
		Integer  receiveStatus = request.getReceiveStatus();
		Integer sunday = request.getSunday();
		Integer saturday = request.getSaturday();
		Integer tuesday = request.getTuesday();
		Integer wednesday = request.getWednesday();
		Integer thursday = request.getThursday();
		Integer friday = request.getFriday();
		Integer monday = request.getMonday();
		//首先判断是否选中了重复选项
		Integer  sumIndex = sunday+saturday+tuesday+wednesday+thursday +friday +monday;
		//sumIndex  > 0 说明选择了重复
		String startTimeStrUpdate = null ;
		String endTimeStrUpdate = null ;
		if(sumIndex > 0){
			//根据开始时间计算开始结束字符串
			if(appointStartTime != null){
				startTimeStrUpdate = DateUtils.formatDateHHMM(appointStartTime);
			}
			if(appointEndTime != null){
				endTimeStrUpdate = DateUtils.formatDateHHMM(appointEndTime);
			}
		}
		Long  customerId = request.getCustomerId();
		List<CustomerSkill>   updateList = new ArrayList<>();
		Date  currTime = new Date();
		for (CustomerSkillRequestVO csrv : list) {
			CustomerSkill custSkill = new CustomerSkill();
			custSkill.setCustomerId(customerId);
			custSkill.setCustomerSkillId(csrv.getCustomerSkillId());
			Long  skillItemId = csrv.getSkillItemId();
			//根据技能ID获取技能信息
			SkillItem  skillItem = skillItemMapper.selectByPrimaryKey(skillItemId);
			if(skillItem != null){
				custSkill.setSkillName(skillItem.getSkillItemName());
			}
			
			//获取价格
			Long  skillItemExtId = csrv.getSkillItemExtId();
			SkillItemExt  skillItemExt =  skillItemExtMapper.selectByPrimaryKey(skillItemExtId);
			BigDecimal  skillExtPrice = skillItemExt.getSkillExtPrice() ;
			custSkill.setSkillPrice(skillExtPrice);
			//计算折扣价格
			BigDecimal  discountRate = csrv.getDiscountRate();
			BigDecimal   discountPrice = skillExtPrice.multiply(discountRate.divide(new BigDecimal(10))) ;
			//对价格进行取整
			discountPrice = discountPrice.setScale(0, BigDecimal.ROUND_HALF_UP);
			custSkill.setDiscountPrice(discountPrice);
			custSkill.setSkillRange(skillItemExt.getSkillExtRange());
			custSkill.setSkillItemExtId(skillItemExtId);
			custSkill.setServiceUnit(skillItemExt.getSkillExtUnit());
			custSkill.setDiscountRate(discountRate);
			custSkill.setSkillItemId(skillItemId);
			custSkill.setSwitchStatus(csrv.getSwitchStatus());
			custSkill.setMonday(monday);
			custSkill.setTuesday(tuesday);
			custSkill.setWednesday(wednesday);
			custSkill.setFriday(friday);
			custSkill.setSunday(sunday);
			custSkill.setThursday(thursday);
			custSkill.setSaturday(saturday);
			custSkill.setModifyTime(currTime);
			custSkill.setReceiveStatus(receiveStatus);
			custSkill.setAutoReceiveStatus(request.getAutoReceiveStatus());
//			custSkill.setEndTimeStr(endTimeStr);
			
			if(sumIndex > 0){
				//大于0说明选择了重复选项
				custSkill.setStartTimeStr(startTimeStrUpdate);
				custSkill.setEndTimeStr(endTimeStrUpdate);
			}else{
				//==0说明没有选择重复选项
				//设置开始时间和结束时间
				custSkill.setAppointStartTime(appointStartTime);
				custSkill.setAppointEndTime(appointEndTime);
			}
			
			updateList.add(custSkill);
		}
		//在技能审核的时候已经初始化用户技能信息
		customerSkillMapper.batchUpdate(updateList);
		//接单设置埋点
		DataBuriedPointOrderButtonReq req = new DataBuriedPointOrderButtonReq();
		req.setButtonexecution_time(DateUtils.formatDate(currTime));
		req.setOrderbutton_status(receiveStatus +"");
		req.setUser_id(customerId +"");
		try {
			dataDuriedPointBusiness.buryOrderButtonData(req );
		} catch (Exception e1) {
			LOGGER.error("接单设置埋点发生异常，异常信息：",e1);
		}
		
		//更新bigv_score表
//		try {
//			customerSkillMapper.updateBigvScore(customerId, receiveStatus);
//		} catch (Exception e) {
//			LOGGER.error("更新用户状态发生异常，异常信息：",e);
//		}
		

	}

	/**默认超时时间为25小时*/
	private static  final  int   TIMEOUT_SECONDS = 90000;
	
	@Override
	public Integer checkReceiveSwitch(Long customerId) {
		String  currTimeStr = DateUtils.formatDateReachDate(new Date());
		
		String key = RedisKeyConstants.ACCOUNT_RECEIVE_NO_NX  + currTimeStr+customerId ;
		//首先判断当天是否有过请求
		long  result = JedisUtil.setnx(key , "1", TIMEOUT_SECONDS);
		//result 返回1表示第一次请求
		if(result == 1){
			//其次判断接单开关是否开启
			Integer  receiveStatus = customerSkillMapper.queryReceiveStatusByCustomerId(customerId);
			return receiveStatus;
		}
		return 2;
		
		
	}

	@Override
	public void openReceiveSwitch(Long customerId) {
		//开启开关
		Integer  receiveStatus = customerSkillMapper.queryReceiveStatusByCustomerId(customerId);
		if(receiveStatus != null){
			if(OrderSkillConstants.RECEIVE_CLOSE == receiveStatus){
				customerSkillMapper.openReceiveSwitch(customerId,OrderSkillConstants.RECEIVE_OPEN);
			}
		}
		
		
	}

}
