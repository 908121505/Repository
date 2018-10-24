package com.calf.module.resource.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.SearchUtil;
import com.calf.module.common.impl.CommonUtilService;
import com.calf.module.resource.entity.ResourcePool;
import com.calf.module.resource.vo.ResourcePoolVo;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

//资源池服务
@Service("resourcePoolService")
public class ResourcePoolService {

    @Autowired
    private BaseManager baseManager;

    @Autowired
    private CommonUtilService commonUtilService ;

    public DataTables<ResourcePoolVo> getResourcePoolPageList(HttpServletRequest request) {
        HashMap<String,Object> parameters = (HashMap<String, Object>) SearchUtil.convertorEntitysToMap(request.getParameterMap());
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("resourceName", parameters.get("resourceName"));
        paramMap.put("status", "1");
        paramMap.put("iDisplayStart", parameters.get("iDisplayStart"));
        paramMap.put("iDisplayLength", parameters.get("iDisplayLength"));
        List<ResourcePoolVo> poolList = baseManager.query("ResourcePool.selectPageList", paramMap);

        String sEcho = (String) parameters.get("sEcho");
        int total = baseManager.get("ResourcePool.selectCount", paramMap);

        //test
        /*List<ResourcePoolVo> poolList = new ArrayList<ResourcePoolVo>();
        ResourcePoolVo a = new ResourcePoolVo();
        a.setId("1");
        a.setResourceName("哈哈");
        a.setSoundTotal(50);
        a.setSoundTotalUIDStr("a,b,c,d");
        a.setStatus(1);
        a.setActiveStatus(0);
        ResourcePoolVo b = new ResourcePoolVo();
        b.setId("2");
        b.setResourceName("好看");
        b.setSoundTotal(500);
        b.setSoundTotalUIDStr("ss,dd,ff,gg");
        b.setStatus(1);
        b.setActiveStatus(1);
        poolList.add(a);
        poolList.add(b);
        int total = 100;
        HashMap<String,Object> parameters = (HashMap<String, Object>) SearchUtil.convertorEntitysToMap(request.getParameterMap());
        String sEcho = (String) parameters.get("sEcho");*/
        //test
        for(int i=0;i<poolList.size();i++){
            ResourcePoolVo rpo = poolList.get(i);
            String id = rpo.getId();
            //int onlineNum = AAsevver.getOnlineCount(id);
            //int inOrdernum = AAsevver.getInOrderCount(id);
            int onlineNum = 234;
            int inOrdernum = 123;
            rpo.setSoundOnline(onlineNum);
            rpo.setSoundOrder(inOrdernum);
            rpo.setSoundRemaining(onlineNum-inOrdernum);
        }
        return new DataTables<ResourcePoolVo>(sEcho, poolList, poolList.size(), total);
    }

    public void getResourcePoolDetail(Model model, String id){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        ResourcePool rp = baseManager.get("ResourcePool.selectByPrimaryKey", paramMap);

        //test
        /*ResourcePool rp = new ResourcePool();
        rp.setId("2");
        rp.setResourceName("好看");
        rp.setSoundTotal(500);
        rp.setSoundTotalUIDStr("qq,\r\nww,\r\nrr,\r\ntt");
        rp.setStatus(1);*/

        //ResourcePool rpA = null;
        //model.addAttribute("entity", rp);
        //test
        model.addAttribute("entity", rp);
    }

    public int saveAdd(ResourcePoolVo entity) {
        ResourcePool rp = new ResourcePool();
        BeanUtils.copyProperties(entity, rp);
        Long id = UUIDUtils.getId();
        rp.setId(id+"");
        rp.setStatus(1);//有效
        rp.setCreateMan(commonUtilService.getCurrUser());
        rp.setCreateTime(new Date());
        baseManager.insert(rp);
        return 0;
    }

    public int saveUpdate(ResourcePoolVo entity) {
        //System.out.print(entity.getSoundTotalUIDStr());
        ResourcePool rp = new ResourcePool();
        BeanUtils.copyProperties(entity, rp);
        rp.setModifyMan(commonUtilService.getCurrUser());
        rp.setModifyTime(new Date());
        //baseManager.update("ResourcePool.updateByPrimaryKey",object2Map(rp));
        baseManager.update(rp);
        return 0;
    }

    public int delete(String id){
        /*baseManager.delete(ResourcePool.class,new Object[]{Long.valueOf(id)});*/
        return 0;
    }

    public int delete(Long id){
        /*baseManager.delete(ResourcePool.class,new Object[]{id});*/
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

}
