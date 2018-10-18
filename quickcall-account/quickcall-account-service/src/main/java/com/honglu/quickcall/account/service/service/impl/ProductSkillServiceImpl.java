package com.honglu.quickcall.account.service.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honglu.quickcall.account.facade.vo.DaVinfoListVO;
import com.honglu.quickcall.account.facade.vo.DaVinfoVO;
import com.honglu.quickcall.account.facade.vo.FirstPageSkillinfoVO;
import com.honglu.quickcall.account.service.dao.SkillMapper;
import com.honglu.quickcall.account.service.service.IProductSkillService;


@Service("productSkillService")
public class ProductSkillServiceImpl implements IProductSkillService {
	
	@Autowired
	private SkillMapper  skillMapper ;

	@Override
	public DaVinfoListVO getResourceDaVinfoList() {
		DaVinfoListVO  resultVO =  new DaVinfoListVO();
		resultVO.setSkillName("大V推荐");
		resultVO.setSkillId(100000L);
		List<DaVinfoVO> daVinfoList = new ArrayList<DaVinfoVO>();
		String  bussTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539668863932.png";
		String  categoryTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539668869623.png";
		for (int i = 0; i < 6; i++) {
			daVinfoList.add(getDaVinfoVO("半小时",bussTagUrl,categoryTagUrl));
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
			DaVinfoListVO   listVO =  new DaVinfoListVO();
			if(i == 0){
				listVO.setSkillName("哄睡");
				unitName = "次";
				bussTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539668863932.png";
				categoryTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539668869623.png";
			}else if ( i == 1){
				listVO.setSkillName("叫醒");
				unitName = "次";
				bussTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539682726634.png";
				categoryTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539682729263.png";
			}else if ( i == 2){
				listVO.setSkillName("声优聊天");
				bussTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539744936508.png";
				categoryTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539744942470.png";
			}else if ( i == 3){
				listVO.setSkillName("情感咨询");
				bussTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539744047494.png";
				categoryTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539744942470.png";
			}
			listVO.setSkillId(1000L);
			List<DaVinfoVO>  daVinfoList  =  new ArrayList<DaVinfoVO>();
			for (int j = 0; j < 5; j++) {
				daVinfoList.add(getDaVinfoVO(unitName,bussTagUrl,categoryTagUrl));
			}
			listVO.setDaVinfoList(daVinfoList);
			resultList.add(listVO);
		}
		
		return resultList;
	}

	
	
	private  DaVinfoVO  getDaVinfoVO(String  unitName,String  bussTagUrl,String  categoryTagUrl){
		DaVinfoVO  infoVO = new DaVinfoVO();
		infoVO.setAge(18);
		infoVO.setBussTagUrl(bussTagUrl);
		infoVO.setCategoryTagUrl(categoryTagUrl);
		infoVO.setCustomerId(100L);
		infoVO.setNickName("小陈");
		infoVO.setPrice(new BigDecimal(40));
		infoVO.setProductId(1000L);
		int rd=Math.random() >0.5?1:0; 
		infoVO.setSex(rd);
		infoVO.setUnitName(unitName);
		return infoVO;
	}

	@Override
	public List<FirstPageSkillinfoVO> selectPartSkill() {
		return skillMapper.selectPartSkill();
	}

	@Override
	public List<DaVinfoVO> getDaVListBySkillId(Long  skillId) {
		String  unitName = "半小时" ;
		List<DaVinfoVO>   listVO =  new ArrayList<DaVinfoVO>();
		String  bussTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539744047494.png";
		String  categoryTagUrl = "http://test-guanjia.oss-cn-shanghai.aliyuncs.com/voice/skill/1539744942470.png";
		for (int j = 0; j < 5; j++) {
			listVO.add(getDaVinfoVO(unitName,bussTagUrl,categoryTagUrl));
		}
		return  listVO;
	}
	

}
