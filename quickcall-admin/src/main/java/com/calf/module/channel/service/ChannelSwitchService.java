package com.calf.module.channel.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.SearchUtil;
import com.calf.module.channel.entity.ChannelSwitch;
import com.calf.module.common.impl.CommonUtilService;
//import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

//import com.honglu.quickcall.common.core.util.UUIDUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/**
 * 渠道开关Service
 * @author wq
 * @date 2018-11-07
 */
@Service
public class ChannelSwitchService {
    //private static final Logger logger = LoggerFactory.getLogger(ChannelSwitchService.class);

    @Autowired
    private BaseManager baseManager;

    @Autowired
    private CommonUtilService commonUtilService ;

    /**
     * 分页查询，初始化页面
     * @param request
     * @return
     */
    public DataTables<ChannelSwitch> getChannelSwitchPageList(HttpServletRequest request) {
        HashMap<String,Object> parameters = (HashMap<String, Object>) SearchUtil.convertorEntitysToMap(request.getParameterMap());
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("channelStr", parameters.get("channelStr"));
        paramMap.put("versionStr", parameters.get("versionStr"));
        paramMap.put("statusStr", parameters.get("statusStr"));
        paramMap.put("iDisplayStart", parameters.get("iDisplayStart"));
        paramMap.put("iDisplayLength", parameters.get("iDisplayLength"));
        List<ChannelSwitch> list = baseManager.query("ChannelSwitch.selectPageList", paramMap);

        String sEcho = (String) parameters.get("sEcho");
        int total = baseManager.get("ChannelSwitch.selectCount", paramMap);

        return new DataTables<ChannelSwitch>(sEcho, list, list.size(), total);
    }

    /**
     * 根据ID查询-编辑页面
     * @param model
     * @param id
     */
    public void getChannelSwitchDetail(Model model, String id){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        ChannelSwitch entity = baseManager.get("ChannelSwitch.selectByPrimaryKey", paramMap);
        model.addAttribute("entity", entity);
    }

    /**
     * 保存新增
     * @param entity
     * @return
     */
    public int saveAdd(ChannelSwitch entity) {
        ChannelSwitch cs = new ChannelSwitch();
        /*try{
            BeanUtils.copyProperties(entity,cs);
        }
        catch(Exception e){
            e.printStackTrace();
        }*/
        BeanUtils.copyProperties(entity, cs);
        cs.setCreateMan(commonUtilService.getCurrUser());
        cs.setCreateTime(new Date());
        //logger.debug("saveAdd");

        //baseManager.insert(cs);
        baseManager.insert("ChannelSwitch.insertSelective",object2Map(cs));
        return 0;
    }

    /**
     * 保存更新
     * @param entity
     * @return
     */
    public int saveUpdate(ChannelSwitch entity) {
        ChannelSwitch cs = new ChannelSwitch();
        /*try{
            BeanUtils.copyProperties(cs,entity);
        }
        catch(Exception e){
            e.printStackTrace();
        }*/
        BeanUtils.copyProperties(entity, cs);
        cs.setModifyMan(commonUtilService.getCurrUser());
        cs.setModifyTime(new Date());

        //logger.debug("saveUpdate");
        baseManager.update("ChannelSwitch.updateByPrimaryKeySelective",object2Map(cs));
        return 0;
    }

    public int delete(String id){
        baseManager.delete(ChannelSwitch.class,new Object[]{Long.valueOf(id)});

        return 0;
    }

    public int delete(Long id){//here
        baseManager.delete(ChannelSwitch.class,new Object[]{id});

        return 0;
    }

    /**
     * 转换方法
     * @param object
     * @return
     */
    private Map<String, Object> object2Map(Object object){
        JSONObject jsonObject = (JSONObject) JSON.toJSON(object);
        Set<Map.Entry<String,Object>> entrySet = jsonObject.entrySet();
        Map<String, Object> map=new HashMap<String,Object>();
        for (Map.Entry<String, Object> entry : entrySet) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }

}
