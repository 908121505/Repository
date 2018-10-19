package com.honglu.quickcall.account.service.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honglu.quickcall.account.facade.vo.CustomerSkillVO;
import com.honglu.quickcall.account.facade.vo.DaVinfoListVO;
import com.honglu.quickcall.account.facade.vo.DaVinfoVO;
import com.honglu.quickcall.account.facade.vo.FirstPageSkillIteminfoVO;
import com.honglu.quickcall.account.service.dao.CustomerSkillMapper;
import com.honglu.quickcall.account.service.dao.SkillMapper;
import com.honglu.quickcall.account.service.service.IProductSkillService;


@Service("productSkillService")
public class ProductSkillServiceImpl implements IProductSkillService {
	
	@Autowired
	private SkillMapper  skillMapper ;
	@Autowired
	private CustomerSkillMapper   customerSkillMapper;

	@Override
	public DaVinfoListVO getResourceDaVinfoList() {
		DaVinfoListVO  resultVO =  new DaVinfoListVO();
		resultVO.setSkillItemName("大V推荐");
		resultVO.setSkillItemId(100000L);
		List<DaVinfoVO> daVinfoList = new ArrayList<DaVinfoVO>();
		String   coverUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539668863932.png";
		String  bussTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539668863932.png";
		String  categoryTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539668869623.png";
		
		for (int i = 0; i < 6; i++) {
			daVinfoList.add(getDaVinfoVO("半小时",bussTagUrl,categoryTagUrl,coverUrl));
		}
		resultVO.setDaVinfoList(daVinfoList );
		return resultVO;
	}

	@Override
	public List<DaVinfoListVO> getTagDaVinfoList() {
		List<DaVinfoListVO>  resultList =  new ArrayList<DaVinfoListVO>();
		for (int i = 0; i < 4; i++) {
			String  unitName = "半小时" ;
			String  bussTagUrl = "";
			String  categoryTagUrl = "";
			String  coverUrl = "";
			DaVinfoListVO   listVO =  new DaVinfoListVO();
			if(i == 0){
				listVO.setSkillItemName("哄睡");
				unitName = "次";
				coverUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539668863932.png";
				bussTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539668863932.png";
				categoryTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539668869623.png";
			}else if ( i == 1){
				listVO.setSkillItemName("叫醒");
				unitName = "次";
				coverUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539682726634.png";
				bussTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539682726634.png";
				categoryTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539682729263.png";
			}else if ( i == 2){
				listVO.setSkillItemName("声优聊天");
				coverUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539744936508.png";
				bussTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539744936508.png";
				categoryTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539744942470.png";
			}else if ( i == 3){
				listVO.setSkillItemName("情感咨询");
				coverUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539744047494.png";
				bussTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539744047494.png";
				categoryTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539744942470.png";
			}
			listVO.setSkillItemId(1000L);
			List<DaVinfoVO>  daVinfoList  =  new ArrayList<DaVinfoVO>();
			for (int j = 0; j < 5; j++) {
				daVinfoList.add(getDaVinfoVO(unitName,bussTagUrl,categoryTagUrl,coverUrl));
			}
			listVO.setDaVinfoList(daVinfoList);
			resultList.add(listVO);
		}
		
		return resultList;
	}

	
	
	private  DaVinfoVO  getDaVinfoVO(String  unitName,String  bussTagUrl,String  categoryTagUrl,String coverUrl){
		DaVinfoVO  infoVO = new DaVinfoVO();
		infoVO.setAge(18);
		infoVO.setBussTagUrl(bussTagUrl);
		infoVO.setCategoryTagUrl(categoryTagUrl);
		infoVO.setCustomerId(100L);
		infoVO.setNickName("小陈");
		infoVO.setPrice(new BigDecimal(40));
		infoVO.setCustomerSkillId(1000L);
		int rd=Math.random() >0.5?1:0; 
		infoVO.setSex(rd);
		infoVO.setUnitName(unitName);
		infoVO.setCoverUrl(coverUrl);
		infoVO.setCurrencyName(Math.random() >0.5? "币":"元");
		return infoVO;
	}

	@Override
	public List<FirstPageSkillIteminfoVO> selectPartSkill() {
		return skillMapper.selectPartSkill();
	}

	@Override
	public List<DaVinfoVO> getDaVListBySkillId(Long  skillId) {
		String  unitName = "半小时" ;
		List<DaVinfoVO>   listVO =  new ArrayList<DaVinfoVO>();
		String  coverUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539744047494.png";
		String  bussTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539744942470.png";
		String  categoryTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539744942470.png";
		for (int j = 0; j < 5; j++) {
			listVO.add(getDaVinfoVO(unitName,bussTagUrl,categoryTagUrl,coverUrl));
		}
		return  listVO;
	}

	@Override
	public List<CustomerSkillVO> querySkillInfoPersonal(Long customerId) {
		
		List<CustomerSkillVO>   resultList =  new ArrayList<CustomerSkillVO>();
		
		List<BigDecimal> discontRateList = new ArrayList<BigDecimal>();
		discontRateList.add(new BigDecimal(75));
		discontRateList.add(new BigDecimal(75));
		discontRateList.add(new BigDecimal(100));
		List<BigDecimal> skillPriceList = new  ArrayList<BigDecimal>();
		skillPriceList.add(new BigDecimal(10));
		skillPriceList.add(new BigDecimal(15));
		skillPriceList.add(new BigDecimal(30));
		skillPriceList.add(new BigDecimal(66));
		skillPriceList.add(new BigDecimal(88));
		for (int i = 0; i < 4; i++) {
			String skillItemName  = null ;
			String serviceUnit  = null ;
			if(i == 0 ){
				skillItemName = "哄睡";
				serviceUnit = "次";
			}else if(i == 1 ){
				skillItemName = "叫醒";
				serviceUnit = "次";
			}else if(i == 2 ){
				skillItemName = "声优聊天";
				serviceUnit = "半小时";
			}else if(i == 3 ){
				skillItemName = "情感咨询";
				serviceUnit = "小时";
			}
			CustomerSkillVO  skillVO =  new CustomerSkillVO();
			skillVO.setCustomerSkillId(10000L);
			skillVO.setServiceUnit(serviceUnit);
			skillVO.setDiscontRateList(discontRateList );
			skillVO.setDiscountRate(new BigDecimal(70));
			skillVO.setEndServiceTime(new Date());
//			skillVO.setFriday(Math.random() >0.5?1:0);
//			skillVO.setMonday(Math.random() >0.5?1:0);
			skillVO.setReceiveStatus(Math.random() >0.5?1:0);
//			skillVO.setSaturday(Math.random() >0.5?1:0);
			skillVO.setSkillItemId(1000L);
			skillVO.setSkillItemName(skillItemName);
			skillVO.setSkillPrice(new BigDecimal(10));
			skillVO.setSkillPriceList(skillPriceList);
//			skillVO.setSunday(Math.random() >0.5?1:0);
			skillVO.setSwitchStatus(Math.random() >0.5?1:0);
//			skillVO.setThursday(Math.random() >0.5?1:0);
//			skillVO.setTuesday(Math.random() >0.5?1:0);
//			skillVO.setWednesday(Math.random() >0.5?1:0);
			
			HashMap<String, Integer> weekDataMap = new HashMap<String, Integer>();
			
			weekDataMap.put("monday", 1);
			weekDataMap.put("tuesday", 0);
			weekDataMap.put("wednesday", 1);
			weekDataMap.put("thursday", 0);
			weekDataMap.put("friday", 1);
			weekDataMap.put("saturday", 0);
			weekDataMap.put("sunday", 1);
			
			skillVO.setWeekDataMap(weekDataMap );
			resultList.add(skillVO);
		}
		return resultList;
	}
	

}
