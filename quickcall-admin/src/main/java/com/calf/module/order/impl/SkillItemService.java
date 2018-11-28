package com.calf.module.order.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.SearchUtil;
import com.calf.module.common.impl.CommonUtilService;
import com.calf.module.order.entity.SkillItem;
import com.calf.module.order.vo.SkillItemVo;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * 技能项的服务
 */
@Service("skillItemService")
public class SkillItemService {

    @Autowired
    private BaseManager baseManager;

    @Autowired
    private CommonUtilService commonUtilService ;

    @SuppressWarnings("unchecked")
    public DataTables<SkillItemVo> getSkillPageList(HttpServletRequest request) {
		HashMap<String,Object> parameters = (HashMap<String, Object>) SearchUtil.convertorEntitysToMap(request.getParameterMap());
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("name", parameters.get("name"));
        paramMap.put("skillStatus", parameters.get("skillStatus"));
        paramMap.put("iDisplayStart", parameters.get("iDisplayStart"));
        paramMap.put("iDisplayLength", parameters.get("iDisplayLength"));
        List<SkillItemVo> skillList = baseManager.query("SkillItem.selectPageList", paramMap);
        String sEcho = (String) parameters.get("sEcho");
        int total = baseManager.get("SkillItem.slectCount", paramMap);
        return new DataTables<SkillItemVo>(sEcho, skillList, skillList.size(), total);
    }

    public void getSkillItemDetail(Model model, String id){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        SkillItem skill = baseManager.get("SkillItem.selectByPrimaryKey",  paramMap);
        model.addAttribute("entity", skill);
    }

    public int saveAdd(SkillItemVo entity) {
        SkillItem  skillItem =  new SkillItem();
        BeanUtils.copyProperties(entity, skillItem);
        Long  id = UUIDUtils.getId();
        skillItem.setId(id+"");
        skillItem.setCreateMan(commonUtilService.getCurrUser());
        skillItem.setSkillStatus(0);//有效
        skillItem.setCreateTime(new Date());
        baseManager.insert(skillItem);
        return 0;
    }

    public int saveUpdate(SkillItemVo entity) {
        SkillItem  skillItem =  new SkillItem();
        BeanUtils.copyProperties(entity, skillItem);
        skillItem.setModifyMan(commonUtilService.getCurrUser());
        skillItem.setModifyTime(new Date());
        baseManager.update("SkillItem.updateByPrimaryKeySelective",object2Map(skillItem));
        //更新customer_skill表中
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("skillName", entity.getSkillItemName());
        paramMap.put("skillItemId", entity.getId());
        
		baseManager.update("CustomerSkill.updateSkillItemNameBySkillItemId",paramMap );
        
        return 0;
    }

    public int delete(String id){
        baseManager.delete(SkillItem.class,new Object[]{Long.valueOf(id)});
        return 0;
    }

    public int delete(Long id){
        baseManager.delete(SkillItem.class,new Object[]{id});
        return 0;
    }

    private Map<String, Object> object2Map(Object object){
        JSONObject jsonObject = (JSONObject) JSON.toJSON(object);
        Set<Map.Entry<String,Object>> entrySet = jsonObject.entrySet();
        Map<String, Object> map=new HashMap<String,Object>();
        for (Map.Entry<String, Object> entry : entrySet) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }
    
    public List<Map<String,Object>> getAllSkillItem(){
    	 List<Map<String, Object>> skillList = baseManager.query("SkillItem.selectAllSkillItem", null);
    	 return skillList;
    }
}
