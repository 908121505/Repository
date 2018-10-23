package com.calf.module.order.impl;

import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.SearchUtil;
import com.calf.module.common.impl.CommonUtilService;
import com.calf.module.order.entity.SkillItemExt;
import com.calf.module.order.vo.SkillItemExtVo;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service("skillItemExtService")
public class SkillItemExtService {

    @Autowired
    private BaseManager baseManager;

    @Autowired
    private CommonUtilService commonUtilService;


    @SuppressWarnings("unchecked")
	public DataTables<SkillItemExtVo> getSkillItemExtPageList(HttpServletRequest request) {
        HashMap<String, Object> parameters = (HashMap<String, Object>) SearchUtil.convertorEntitysToMap(request.getParameterMap());

        List<SkillItemExtVo> skillList = baseManager.query("SkillItemExt.selectPageList", parameters);

        String sEcho = (String) parameters.get("sEcho");
        int total = baseManager.get("SkillItemExt.slectCount", parameters);
        return new DataTables<SkillItemExtVo>(sEcho, skillList, skillList.size(), total);
    }

    public int saveAdd(SkillItemExtVo source) {
        Long id = source.getSkillItemId();

        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put("skillItemId", source.getSkillItemId());

        List<SkillItemExt> skillItemExts = baseManager.query("SkillItemExt.getSkillItemExtBySkillItemId", paramMap);
        List<SkillItemExt> needAddSkillItemExt = getNeedAddSkillItemExts(source,skillItemExts);

        for (SkillItemExt skillItemExt : needAddSkillItemExt) {
            baseManager.insert(skillItemExt);
        }

        return 0;

    /*    SkillItemExt target = new SkillItemExt();
        BeanUtils.copyProperties(source,target);
        target.setCreateTime(new Date());
        target.setCreateMan(commonUtilService.getCurrUser());
        target.setId(UUIDUtils.getId());
        target.setSkillExtStatus(0);
        baseManager.insert(target);*/

    }

    public int saveDiscountSkillItemExt(SkillItemExtVo vo){
        SkillItemExt ext = new SkillItemExt();
        BeanUtils.copyProperties(vo,ext);
        ext.setId(UUIDUtils.getId());
        ext.setSkillExtType(2);
        ext.setCreateTime(new Date());
        ext.setCreateMan(commonUtilService.getCurrUser());
        ext.setSkillExtStatus(0);
        baseManager.insert(ext);
        return 0;
    }

    public int savaUpdate(SkillItemExtVo vo) {
        return 0;
    }

    public int delete(String id) {
        return 0;
    }

    public int delete(Long id) {
        return 0;
    }

    private List<SkillItemExt> getNeedAddSkillItemExts(SkillItemExtVo source, List<SkillItemExt> skillItemExts) {
        List<SkillItemExt> needAddSkillItemExt = new LinkedList<>();
        Set<SkillItemExt> oldSkillItemExts = new HashSet<>(skillItemExts);
        Set<SkillItemExt> rangeSkillItemExts = new HashSet<>();
        Set<SkillItemExt> skillExtUnitCollection = new HashSet<>();
        if (source.getSkillExtUnit() != null) {
            for (SkillItemExt skillItemExt : skillItemExts) {
                SkillItemExt ext = new SkillItemExt();
                ext.setId(UUIDUtils.getId());
                ext.setSkillExtStatus(1);
                ext.setCreateMan(commonUtilService.getCurrUser());
                ext.setCreateTime(new Date());
                ext.setSkillExtRange(skillItemExt.getSkillExtRange());
                ext.setSkillExtType(1);
                ext.setSkillItemId(skillItemExt.getSkillItemId());
                ext.setSkillExtUnit(source.getSkillExtUnit());
                if (oldSkillItemExts.contains(ext)){
                    continue;
                }
                oldSkillItemExts.add(ext);
                rangeSkillItemExts.add(ext);
            }
        }
        needAddSkillItemExt.addAll(rangeSkillItemExts);
        if (source.getSkillExtRange() != null) {
            for (SkillItemExt skillItemExt : skillItemExts) {
                SkillItemExt ext = new SkillItemExt();
                ext.setId(UUIDUtils.getId());
                ext.setSkillExtStatus(1);
                ext.setCreateMan(commonUtilService.getCurrUser());
                ext.setCreateTime(new Date());
                ext.setSkillExtRange(source.getSkillExtRange());
                ext.setSkillExtType(1);
                ext.setSkillItemId(skillItemExt.getSkillItemId());
                ext.setSkillExtUnit(skillItemExt.getSkillExtUnit());
                if (oldSkillItemExts.contains(ext)){
                    continue;
                }
                skillExtUnitCollection.add(ext);
                oldSkillItemExts.add(ext);
            }
        }
        needAddSkillItemExt.addAll(skillExtUnitCollection);
        if (source.getSkillExtRange() != null && source.getSkillExtUnit() != null) {
            SkillItemExt ext = new SkillItemExt();
            ext.setId(UUIDUtils.getId());
            ext.setSkillExtStatus(1);
            ext.setCreateMan(commonUtilService.getCurrUser());
            ext.setCreateTime(new Date());
            ext.setSkillExtRange(source.getSkillExtRange());
            ext.setSkillExtType(1);
            ext.setSkillItemId(source.getSkillItemId());
            ext.setSkillExtUnit(source.getSkillExtUnit());
            if(!oldSkillItemExts.contains(ext)){
                needAddSkillItemExt.add(ext);
            }

        }

        return needAddSkillItemExt;
    }


}
