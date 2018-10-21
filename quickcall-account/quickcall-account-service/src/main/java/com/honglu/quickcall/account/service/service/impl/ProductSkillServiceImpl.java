package com.honglu.quickcall.account.service.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.honglu.quickcall.account.facade.entity.CustomerSkill;
import com.honglu.quickcall.account.facade.entity.CustomerSkillExt;
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
import com.honglu.quickcall.account.service.dao.CustomerSkillExtMapper;
import com.honglu.quickcall.account.service.dao.CustomerSkillMapper;
import com.honglu.quickcall.account.service.dao.SkillItemExtMapper;
import com.honglu.quickcall.account.service.dao.SkillMapper;
import com.honglu.quickcall.account.service.service.IProductSkillService;
import com.honglu.quickcall.common.api.util.DateUtils;
import com.honglu.quickcall.common.api.util.JSONUtil;

@Service("productSkillService")
public class ProductSkillServiceImpl implements IProductSkillService {

	@Autowired
	private SkillMapper skillMapper;
	@Autowired
	private CustomerSkillMapper customerSkillMapper;
	@Autowired
	private CustomerSkillExtMapper customerSkillExtMapper;
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
			for (int j = 0; j < 5; j++) {
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
		return infoVO;
	}

	@Override
	public List<FirstPageSkillIteminfoVO> selectPartSkill() {
		return skillMapper.selectPartSkill();
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

	@SuppressWarnings("null")
	public void getNewCustomerSkillInfoVO(CustomerSkill skill, CustomerSkillInfoVO skillVO) {
		int dayIndex = DateUtils.getDayOfWeek();
		HashMap<String, Integer> weekDataMap = new HashMap<String, Integer>();
		String endTimeStr = null;

//		weekDataMap.put("monday", 0);
//		weekDataMap.put("tuesday", 0);
//		weekDataMap.put("wednesday", 0);
//		weekDataMap.put("thursday", 0);
//		weekDataMap.put("friday", 0);
//		weekDataMap.put("saturday", 0);
//		weekDataMap.put("sunday", 0);
		// 当前是周一，则周一之后的都是默认值
		if (dayIndex == 1) {
			weekDataMap.put("monday", skill.getMonday());
		} else if (dayIndex == 2) {
			weekDataMap.put("tuesday", skill.getTuesday());
		} else if (dayIndex == 3) {
			weekDataMap.put("wednesday", skill.getWednesday());
		} else if (dayIndex == 4) {
			weekDataMap.put("thursday", skill.getThursday());
		} else if (dayIndex == 5) {
			weekDataMap.put("friday", skill.getFriday());
		} else if (dayIndex == 6) {
			weekDataMap.put("saturday", skill.getSaturday());
		} else if (dayIndex == 7) {
			weekDataMap.put("sunday", skill.getSunday());
		}
		
		if(StringUtils.isNotBlank(endTimeStr)){
			if(!"2359".equals(endTimeStr)){
				String  startStr =  endTimeStr.substring(0, 2);
				String  endStr =  endTimeStr.substring(2, 4);
				skillVO.setEndServiceTimeStr(startStr + ":"+endStr);
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		String  str = "2359";
		System.out.println(str.substring(0, 2));
		System.out.println(str.substring(2, 4));
	}

	/**
	 * 获取技能可选项
	 * @param skillItemId
	 * @param skillVO
	 */
	public void getSkillExtList(Long skillItemId,CustomerSkillVO skillVO) {
		List<CustomerSkillExtVO> resultList = new ArrayList<CustomerSkillExtVO>();
		List<SkillItemExt> skillItemExtList = skillItemExtMapper.querySkillItemExtList(skillItemId, 1);
		if (CollectionUtils.isEmpty(skillItemExtList)) {
			return;
		}
		HashMap<Integer, List<SkillUnitPriceVO>> itemExtMap = new HashMap<Integer, List<SkillUnitPriceVO>>();
		List<BigDecimal> discontRateList = new ArrayList<BigDecimal>();
		for (SkillItemExt itemExt : skillItemExtList) {
			Integer  type = itemExt.getSkillExtType();
			//需要使用常量进行限制
			if(type == 2){
				discontRateList.add(itemExt.getSkillExtDiscont());
				continue ;
			}
			List<SkillUnitPriceVO> list = null;
			Integer skillExtRange = itemExt.getSkillExtRange();
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
		
		
		skillVO.setDiscontRateList(discontRateList);
		skillVO.setSkillExtList(resultList);
	}
	
	
	public void getOldDataInfo(Long customerSkilId,CustomerSkillVO skillVO) {
		
		
		List<CustomerSkillExt>   skillExtList = customerSkillExtMapper.querySelectInfo(customerSkilId);
		if(CollectionUtils.isEmpty(skillExtList)){
			return  ;
		}
		
		List<Long> oldSkillItemExtIdList = new ArrayList<Long>();
		for (CustomerSkillExt cse : skillExtList) {
			oldSkillItemExtIdList.add(cse.getSkillItemExtId());
		}
		skillVO.setSkillType(2);//TODO  定义常量
		if(skillExtList.size() == 1){
			String  serviceUnit = skillExtList.get(0).getServiceUnit() ;
			skillVO.setOldServiceUnit(serviceUnit);
			if("次".equals(serviceUnit)){//TODO  定义常量
				skillVO.setSkillType(1);//TODO  定义常量
			}
			skillVO.setOldSkillPrice(skillExtList.get(0).getSkillPrice());
		}
		skillVO.setOldSkillItemExtIdList(oldSkillItemExtIdList);
	}

	public CustomerSkillInfoVO querySkillInfoPersonalExt(Long customerId) {

		CustomerSkillInfoVO resultVO = new CustomerSkillInfoVO();

		// 获取用户技能列表信息
		List<CustomerSkill> custSkillList = customerSkillMapper.querySkillInfoPersonal(customerId);
		if (CollectionUtils.isEmpty(custSkillList)) {
			return resultVO;
		}
		Integer receiveStatus = null;
		List<Long> skillIdList = new ArrayList<Long>();
		List<CustomerSkillVO> customerSkillList = new ArrayList<CustomerSkillVO>();
		for (CustomerSkill custSkill : custSkillList) {
			CustomerSkillVO skillVO = new CustomerSkillVO();
			if (receiveStatus == null) {
				receiveStatus = custSkill.getReceiveStatus();
				resultVO.setReceiveStatus(receiveStatus);
				getNewCustomerSkillInfoVO(custSkill, resultVO);
			}

			skillIdList.add(custSkill.getCustomerSkillId());
			skillVO.setCustomerSkillId(custSkill.getCustomerSkillId());
			skillVO.setOldDiscountRate(custSkill.getDiscountRate());
			skillVO.setOldSkillRange(custSkill.getSkillRange());
			getOldDataInfo(custSkill.getCustomerSkillId(), skillVO);
			skillVO.setSkillItemName(custSkill.getSkillName());
			skillVO.setSwitchStatus(custSkill.getSwitchStatus());
			// 根据技能ID获取可选技能信息
			Long skillItemId = custSkill.getSkillItemId();
			getSkillExtList(skillItemId,skillVO);
			skillVO.setSkillItemId(skillItemId);
			customerSkillList.add(skillVO);

		}
		resultVO.setCustomerSkillList(customerSkillList);
		return resultVO;
	}

	@Override
	public CustomerSkillInfoVO querySkillInfoPersonal(Long customerId) {

		CustomerSkillInfoVO resultVO = new CustomerSkillInfoVO();

		HashMap<String, Integer> weekDataMap = new HashMap<String, Integer>();

		List<CustomerSkillVO> resultList = new ArrayList<CustomerSkillVO>();

		List<BigDecimal> discontRateList = new ArrayList<BigDecimal>();
		discontRateList.add(new BigDecimal(7.5));
		discontRateList.add(new BigDecimal(10));
		List<BigDecimal> skillPriceList = new ArrayList<BigDecimal>();
		skillPriceList.add(new BigDecimal(10));
		skillPriceList.add(new BigDecimal(1.5));
		skillPriceList.add(new BigDecimal(3));

		//0：按次     1：半小时/一小时
		for (int i = 0; i < 2; i++) {

			CustomerSkillVO skillVO = new CustomerSkillVO();
			String skillItemName = null;
			String serviceUnit = null;
			if (i == 0) {
				skillItemName = "叫醒";
				serviceUnit = "次";
			} else if (i == 1) {
				skillItemName = "声优聊天";
				serviceUnit = "半小时";
			}
			skillVO.setCustomerSkillId(10000L);
			skillVO.setOldSkillRange(1);
			skillVO.setOldServiceUnit(serviceUnit);
			skillVO.setOldDiscountRate(new BigDecimal(7.5));
			skillVO.setSkillItemId(1000L);
			skillVO.setSkillItemName(skillItemName);
			skillVO.setOldSkillPrice(new BigDecimal(10));
			skillVO.setSwitchStatus(i > 0.5 ? 1 : 0);

			List<CustomerSkillExtVO> skillExtList = new ArrayList<CustomerSkillExtVO>();

			for (int j = 0; j < 3; j++) {

				CustomerSkillExtVO extVO = new CustomerSkillExtVO();
				extVO.setSkillRangeValue(j + 1);

				// 按次算
				List<SkillUnitPriceVO> unitPriceList = new ArrayList<>();
				if (i == 0) {
					SkillUnitPriceVO up = new SkillUnitPriceVO();
					up.setSkillItemExtId(new Long(1111+j));
					up.setUnitName("次");
					up.setUnitPrice(new BigDecimal(11 *(j+1)));
					unitPriceList.add(up);
				} else {
					for (int k = 0; k < 2; k++) {
						SkillUnitPriceVO up = new SkillUnitPriceVO();
						if (k == 0) {
							up.setUnitName("半小时");
							up.setUnitPrice(new BigDecimal(60 * (1 +j)));
							up.setSkillItemExtId(new Long(3333+k));
						} else {
							up.setUnitName("小时");
							up.setUnitPrice(new BigDecimal(100* (1+j)));
							up.setSkillItemExtId(new Long(2222+k));
						}
						unitPriceList.add(up);
					}

				}

				extVO.setUnitPriceList(unitPriceList);
				skillExtList.add(extVO);
			}

			List<Long> oldSkillItemExtIdList = new ArrayList<Long>();
			if(i == 0){
				oldSkillItemExtIdList.add(1111L);
			}else{
				oldSkillItemExtIdList.add(3333L);
				oldSkillItemExtIdList.add(2222L);
				
			}
			skillVO.setOldSkillItemExtIdList(oldSkillItemExtIdList);
			skillVO.setDiscontRateList(discontRateList);
			skillVO.setSkillExtList(skillExtList);
			skillVO.setSkillType(i == 0 ? 1 : 2);
			resultList.add(skillVO);
		}

		weekDataMap.put("monday", 1);
		weekDataMap.put("tuesday", 0);
		weekDataMap.put("wednesday", 1);
		weekDataMap.put("thursday", 0);
		weekDataMap.put("friday", 1);
		weekDataMap.put("saturday", 0);
		weekDataMap.put("sunday", 1);

		resultVO.setReceiveStatus(1);
		resultVO.setCustomerSkillList(resultList);

		resultVO.setEndServiceTimeStr("10:00");
		resultVO.setWeekDataMap(weekDataMap);
		String json = JSONUtil.toJson(resultVO);
		System.out.println("============" + json);
		return resultVO;
	}

	@Override
	public void updateSkillInfoPersonal(SkillUpdateRequest request) {
		
		
		List<CustomerSkillRequestVO>  list = request.getCustomerSkillList();
		if(CollectionUtils.isEmpty(list)){
			return ;
		}
		
		
		for (CustomerSkillRequestVO csrv : list) {
			
			csrv.getCustomerSkillId();
			
		}
		
		

	}

}
