package com.calf.module.order.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.SearchUtil;
import com.calf.module.order.vo.SkillPriceLevelVO;
import com.honglu.quickcall.account.facade.entity.SkillItemExt;
import com.honglu.quickcall.common.core.util.UUIDUtils;

@Service("skillPriceLevelService")
public class SkillPriceLevelService {
    @Autowired
    private BaseManager baseManager;

    @Autowired
    private SkillItemService skillItemService;


    /**
     * 获得列表
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
	public DataTables<SkillPriceLevelVO> getSkillPriceLevelPageList(HttpServletRequest request) {
        HashMap<String, Object> parameters = (HashMap<String, Object>) SearchUtil.convertorEntitysToMap(request.getParameterMap());

        List<SkillPriceLevelVO> skillList = baseManager.query("SkillItemExt.selectPriceLevelPage", parameters);

        String sEcho = (String) parameters.get("sEcho");
        int total = baseManager.get("SkillItemExt.selectPriceLevelCount", parameters);
        return new DataTables<SkillPriceLevelVO>(sEcho, skillList, skillList.size(), total);
    }
    
    /**
     * 或者单条详细信息
     * @param model
     * @param skillItemId
     * @param priceLevel
     */
    public void getSkillPriceLevelDetail(Model model, String skillItemId,String priceLevel){
    	HashMap<String, Object> paramMap = new HashMap<>();
    	paramMap.put("skillItemId", skillItemId);
    	paramMap.put("priceLevel", priceLevel);
    	SkillPriceLevelVO spl = baseManager.get("SkillItemExt.selectPriceLevelPage",  paramMap);
    	List<Map<String, Object>> mapList = skillItemService.getAllSkillItem();
    	model.addAttribute("entity",spl);
    	model.addAttribute("itemList",mapList);
    }

	/**
	 * 删除该等级
	 * @param skillItemId
	 * @param priceLevel
	 * @return
	 */
	public int deleteAll(String skillItemId, String priceLevel) {
		HashMap<String, Object> paramMap = new HashMap<>();
    	paramMap.put("skillItemId", skillItemId);
    	paramMap.put("priceLevel", priceLevel);
		return baseManager.delete("SkillItemExt.deletePriceLevel", new Object[]{skillItemId,priceLevel});
	}
	
	/**
	 * 新增等级阶梯
	 * @param entity
	 * @return
	 */
	public int insertPriceLevel(SkillPriceLevelVO entity){
		//半小时记录
		SkillItemExt sie1 = new SkillItemExt();
		sie1.setSkillItemId(Long.valueOf(entity.getSkillItemId()));
		sie1.setId(UUIDUtils.getId());
		sie1.setSkillExtType(1);
		sie1.setSkillExtUnit("半小时");
		sie1.setSkillExtRange(entity.getPriceLevel());
		sie1.setSkillExtPrice(entity.getHalfPrice());
		sie1.setSkillExtThreshold(entity.getLevelThreshold());
		sie1.setSkillExtStatus(entity.getLevelStatus()>>2);
		int n = baseManager.insert("SkillItemExt.insertSelective", sie1);
		//一小时记录
		SkillItemExt sie2 = new SkillItemExt();
		sie2.setSkillItemId(Long.valueOf(entity.getSkillItemId()));
		sie2.setId(UUIDUtils.getId());
		sie2.setSkillExtType(1);
		sie2.setSkillExtUnit("一小时");
		sie2.setSkillExtRange(entity.getPriceLevel());
		sie2.setSkillExtPrice(entity.getOnePrice());
		sie2.setSkillExtThreshold(entity.getLevelThreshold());
		sie2.setSkillExtStatus((entity.getLevelStatus()>>1)&1);
		int n1 = baseManager.insert("SkillItemExt.insertSelective", sie2);
		//一次记录
		SkillItemExt sie3 = new SkillItemExt();
		sie3.setSkillItemId(Long.valueOf(entity.getSkillItemId()));
		sie3.setId(UUIDUtils.getId());
		sie3.setSkillExtType(1);
		sie3.setSkillExtUnit("次");
		sie3.setSkillExtRange(entity.getPriceLevel());
		sie3.setSkillExtPrice(entity.getTimePrice());
		sie3.setSkillExtThreshold(entity.getLevelThreshold());
		sie3.setSkillExtStatus(entity.getLevelStatus()&1);
		int n2 = baseManager.insert("SkillItemExt.insertSelective", sie3);
		return n+n1+n2;
	}
	
	/**
	 * 更新该等级
	 * @param entity
	 * @return
	 */
	public int updatePriceLevel(SkillPriceLevelVO entity){
		//半小时记录
		SkillItemExt sie1 = new SkillItemExt();
		sie1.setSkillItemId(Long.valueOf(entity.getSkillItemId()));
		sie1.setId(UUIDUtils.getId());
		sie1.setSkillExtType(1);
		sie1.setSkillExtUnit("半小时");
		sie1.setSkillExtRange(entity.getPriceLevel());
		sie1.setSkillExtPrice(entity.getHalfPrice());
		sie1.setSkillExtThreshold(entity.getLevelThreshold());
		sie1.setSkillExtStatus(entity.getLevelStatus()>>2);
		int n = baseManager.insert("SkillItemExt.updateSelective", sie1);
		//一小时记录
		SkillItemExt sie2 = new SkillItemExt();
		sie2.setSkillItemId(Long.valueOf(entity.getSkillItemId()));
		sie2.setId(UUIDUtils.getId());
		sie2.setSkillExtType(1);
		sie2.setSkillExtUnit("一小时");
		sie2.setSkillExtRange(entity.getPriceLevel());
		sie2.setSkillExtPrice(entity.getOnePrice());
		sie2.setSkillExtThreshold(entity.getLevelThreshold());
		sie2.setSkillExtStatus((entity.getLevelStatus()>>1)&1);
		int n1 = baseManager.insert("SkillItemExt.updateSelective", sie2);
		//一次记录
		SkillItemExt sie3 = new SkillItemExt();
		sie3.setSkillItemId(Long.valueOf(entity.getSkillItemId()));
		sie3.setId(UUIDUtils.getId());
		sie3.setSkillExtType(1);
		sie3.setSkillExtUnit("次");
		sie3.setSkillExtRange(entity.getPriceLevel());
		sie3.setSkillExtPrice(entity.getTimePrice());
		sie3.setSkillExtThreshold(entity.getLevelThreshold());
		sie3.setSkillExtStatus(entity.getLevelStatus()&1);
		int n2 = baseManager.insert("SkillItemExt.updateSelective", sie3);
		return n+n1+n2;
	} 
	
	@SuppressWarnings("unchecked")
	public int getPriceLevelExists(HttpServletRequest request){
		 HashMap<String, Object> parameters = (HashMap<String, Object>) SearchUtil.convertorEntitysToMap(request.getParameterMap());
		 int count = baseManager.get("SkillItemExt.selectThisLevelCount", parameters);
		 return count;
	}
}
