package com.calf.module.resource.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
//import com.calf.cn.utils.PropertiesUtil;
import com.calf.cn.utils.SearchUtil;
import com.calf.module.common.impl.CommonUtilService;
import com.calf.module.resource.entity.ResourcePoolCustomer;
import com.calf.module.resource.entity.ResourcePool;
import com.calf.module.resource.vo.ResourcePoolVo;
import com.github.pagehelper.util.StringUtil;
import com.honglu.quickcall.account.facade.constants.OrderSkillConstants;
import com.honglu.quickcall.common.core.util.UUIDUtils;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

//资源池服务
@Service("resourcePoolService")
public class ResourcePoolService{
    private static final Logger logger = LoggerFactory.getLogger(ResourcePoolService.class);
    @Autowired
    private BaseManager baseManager;

    @Autowired
    private CommonUtilService commonUtilService ;

    public DataTables<ResourcePoolVo> getResourcePoolPageList(HttpServletRequest request) {
        HashMap<String,Object> parameters = (HashMap<String, Object>) SearchUtil.convertorEntitysToMap(request.getParameterMap());
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("resourceName", parameters.get("resourceName"));
        paramMap.put("status", 1);
        paramMap.put("iDisplayStart", parameters.get("iDisplayStart"));
        paramMap.put("iDisplayLength", parameters.get("iDisplayLength"));
        List<ResourcePoolVo> poolList = baseManager.query("ResourcePool.selectPageList", paramMap);

        String sEcho = (String) parameters.get("sEcho");
        int total = baseManager.get("ResourcePool.selectCount", paramMap);

        for(int i=0;i<poolList.size();i++){
            ResourcePoolVo rpo = poolList.get(i);
            String id = rpo.getId();
            Map<String, Integer> map = getOnlineAndInOrderCount(id);
            Integer onlineNum = map.get("online");
            Integer inOrdernum = map.get("inOrder");
            rpo.setSoundOnline(onlineNum);
            rpo.setSoundOrder(inOrdernum);
            rpo.setSoundRemaining(onlineNum-inOrdernum);
        }
        return new DataTables<ResourcePoolVo>(sEcho, poolList, poolList.size(), total);
    }

    /**
     *  查询资源池里的在线声优,已接单声优
     * @param resourcePoolId 资源池ID
     * @return
     */
    public Map<String,Integer> getOnlineAndInOrderCount(String resourcePoolId){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("resource_pool_id", resourcePoolId);
        List<ResourcePoolCustomer> crpList = baseManager.query("ResourcePoolCustomer.selectByResourcePoolId", paramMap);
        List<String> clist = new ArrayList<String>();
        Integer online = 0;
        Integer inOrder = 0;
        if(crpList!=null&&crpList.size()>0){
            for (int i = 0; i < crpList.size(); i++) {
                String cid = crpList.get(i).getCustomerId();
                clist.add(cid);
            }
            Map<String, Object> paramMapA = new HashMap<String, Object>();
            paramMapA.put("clist",clist);
            online = baseManager.get("ResourcePool.queryOnlineCount", paramMapA);

            List<Integer> statusList = new ArrayList<Integer>();
            statusList.add(OrderSkillConstants.ORDER_STATUS_WAITING_START);//待开始
            statusList.add(OrderSkillConstants.ORDER_STATUS_WAITING_START_DA_APPAY_START_SERVICE);//大V发起开始服务
            statusList.add(OrderSkillConstants.ORDER_STATUS_GOING_USER_ACCEPCT);//进行中
            statusList.add(OrderSkillConstants.ORDER_STATUS_GOING_DAV_APPAY_FINISH);//进行中（大V发起完成服务）
            //statusList.add(OrderSkillConstants.ORDER_STATUS_WAITING_START);//待开始
            Map<String, Object> paramMapB = new HashMap<String, Object>();
            paramMapB.put("clist",clist);
            paramMapB.put("statusList",statusList);
            inOrder = baseManager.get("ResourcePool.queryInOrderCount", paramMapB);
        }
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("online",online);//在线声优
        map.put("inOrder",inOrder);//已接单声优
        return map;
    }

    public void getResourcePoolDetail(Model model, String id){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        ResourcePoolVo rp = baseManager.get("ResourcePool.selectByPrimaryKey", paramMap);

        Map<String, Object> paramMapA = new HashMap<String, Object>();
        paramMapA.put("resource_pool_id", id);
        List<ResourcePoolCustomer> crpList = baseManager.query("ResourcePoolCustomer.selectByResourcePoolId", paramMapA);
        String uidsStr = "";
        for (int i = 0; i < crpList.size(); i++) {
            uidsStr += crpList.get(i).getAppId()+"\n";
        }
        if (uidsStr.endsWith("\n")) {
            uidsStr = uidsStr.substring(0,uidsStr.length() - 1);
        }
        rp.setSoundTotalUIDStr(uidsStr);

        model.addAttribute("entity", rp);
    }

    public int saveAdd(ResourcePoolVo entity) {
        ResourcePool rp = new ResourcePool();
        try{
            BeanUtils.copyProperties(rp,entity);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        Long id = UUIDUtils.getId();
        rp.setId(id+"");
        rp.setStatus(1);//有效
        rp.setCreateMan(commonUtilService.getCurrUser());
        rp.setCreateTime(new Date());

        //存UID,去重
        String[] uids = entity.getSoundTotalUIDStr().split("\n");
        List list = Arrays.asList(uids);
        Set set = new HashSet(list);
        String [] appIds = (String [])set.toArray(new String[0]);

        int num = 0;//计数appIds
        List<String> errrorList = new ArrayList<String>();
        for (int i = 0; i < appIds.length; i++) {
            ResourcePoolCustomer crp = new ResourcePoolCustomer();
            String uid = appIds[i];
            HashMap<String,Object> map = new HashMap<String, Object>();
            map.put("app_id", uid);
            String customerId = baseManager.get("ResourcePool.getExistCustomerId",map);
            if(StringUtil.isNotEmpty(customerId)){
                crp.setResourcePoolId(id+"");
                crp.setCustomerId(customerId);
                crp.setAppId(uid);
                crp.setStatus(1);
                crp.setCreateMan(commonUtilService.getCurrUser());
                crp.setCreateTime(new Date());
                baseManager.insert("ResourcePoolCustomer.insertCustResourcePool",crp);
                num++;
            }else{
                errrorList.add(uid);
            }
        }
        logger.debug("saveAdd错误的录入主播UID个数:"+errrorList.size());
        for(String str : errrorList){
            logger.debug("saveAdd错误的录入主播UID:"+str);
        }

        rp.setSoundTotal(num);
        baseManager.insert(rp);
        return 0;
    }

    public int saveUpdate(ResourcePoolVo entity) {
        ResourcePool rp = new ResourcePool();
        try{
            BeanUtils.copyProperties(rp,entity);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        rp.setStatus(1);
        rp.setModifyMan(commonUtilService.getCurrUser());
        rp.setModifyTime(new Date());

        //先删除再插入resource_pool_customer
        baseManager.delete("ResourcePoolCustomer.deleteByResourcePoolId",new Object[]{rp.getId()});

        //存UID,去重
        String[] uids = entity.getSoundTotalUIDStr().split("\n");
        List list = Arrays.asList(uids);
        Set set = new HashSet(list);
        String [] appIds = (String [])set.toArray(new String[0]);

        int num = 0;//计数appIds
        List<String> errrorList = new ArrayList<String>();
        for (int i = 0; i < appIds.length; i++) {
            ResourcePoolCustomer crp = new ResourcePoolCustomer();
            String uid = appIds[i];
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("app_id", uid);
            String customerId = baseManager.get("ResourcePool.getExistCustomerId", map);
            if (StringUtil.isNotEmpty(customerId)) {
                crp.setResourcePoolId(rp.getId() + "");
                crp.setCustomerId(customerId);
                crp.setAppId(uid);
                crp.setStatus(1);
                crp.setCreateMan(commonUtilService.getCurrUser());
                crp.setCreateTime(new Date());
                baseManager.insert("ResourcePoolCustomer.insertCustResourcePool", crp);
                num++;
            }else{
                errrorList.add(uid);
            }
        }
        logger.debug("saveUpdate错误的录入主播UID个数:"+errrorList.size());
        for(String str : errrorList){
            logger.debug("saveUpdate错误的录入主播UID:"+str);
        }

        rp.setSoundTotal(num);
        baseManager.update("ResourcePool.updateByPrimaryKey",object2Map(rp));
        return 0;
    }

    public int delete(String id){
        /*baseManager.delete(ResourcePool.class,new Object[]{Long.valueOf(id)});*/
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("modifyMan",commonUtilService.getCurrUser());
        map.put("modifyTime",new Date());
        map.put("resource_pool_id", id);
        map.put("status", 0);
        baseManager.update("ResourcePool.updateStatusDel",map);
        baseManager.update("ResourcePoolCustomer.updateStatusDel",map);
        return 0;
    }

    public int delete(Long id){//here
        /*baseManager.delete(ResourcePool.class,new Object[]{id});*/
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("modifyMan",commonUtilService.getCurrUser());
        map.put("modifyTime",new Date());
        map.put("resource_pool_id", id);
        map.put("status", 0);
        baseManager.update("ResourcePool.updateStatusDel",map);
        baseManager.update("ResourcePoolCustomer.updateStatusDel",map);
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
