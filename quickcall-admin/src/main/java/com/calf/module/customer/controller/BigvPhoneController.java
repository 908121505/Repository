package com.calf.module.customer.controller;

import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.SearchUtil;
import com.calf.module.customer.entity.BigvPhone;
import com.calf.module.customer.entity.Customer;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 大V手机号Controller （相关手机号注册时，自动升级为大V身份）
 *
 * @author duanjun
 * @date 2018-11-03 11:04
 */
@Controller
@RequestMapping("/bigvPhone")
public class BigvPhoneController implements BaseController<BigvPhone> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FadeCustomerController.class);

    @Autowired
    private BaseManager baseManager;

    private static final String JSP_PATH = "customer/bigv_phone/%s";

    @Override
    public String home() {
        return String.format(JSP_PATH, "list");
    }

    @Override
    public DataTables<BigvPhone> initTable(HttpServletRequest request) {
        Map<String, Object> parameters = SearchUtil.convertorEntitysToMap(request.getParameterMap());
        String sEcho = (String) parameters.get("sEcho");
        List<BigvPhone> list = baseManager.query(BigvPhone.class, parameters);
        int total = baseManager.get("BigvPhone.queryCount", parameters);
        return new DataTables<>(sEcho, list, list.size(), total);
    }

    @Override
    public String addAndUpdateHome(Model model, String id) {
        if (StringUtils.isNotBlank(id)) {
            model.addAttribute("entity", baseManager.get("BigvPhone.selectByPrimaryKey", new Object[]{Integer.valueOf(id)}));
        }
        return String.format(JSP_PATH, "edit");
    }

    @Override
    public int saveAdd(BigvPhone entity) {
        Subject currentUser = SecurityUtils.getSubject();
        entity.setCreateMan(currentUser.getPrincipal().toString());
        entity.setModifyMan(currentUser.getPrincipal().toString());
        return baseManager.insert(entity);
    }


    @Override
    public int delete(Long id) {
        return baseManager.delete(BigvPhone.class, new Object[]{id.intValue()});
    }

    @Override
    public int saveUpdate(BigvPhone entity) {
        Subject currentUser = SecurityUtils.getSubject();
        entity.setModifyMan(currentUser.getPrincipal().toString());
        return baseManager.update(entity);
    }

    @Override
    public int delete(String id) {
        return baseManager.delete(BigvPhone.class, new Object[]{Integer.valueOf(id)});
    }


    /**
     * 校验数据
     * @param entity
     * @return
     */
    @RequestMapping(value = "/checkData.htm", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> checkData(BigvPhone entity) {
        Map<String, String> result = new HashMap<>();
        result.put("code", "1");

        // 查询该手机号
        Map<String, Object> params = new HashMap<>();
        params.put("id", entity.getId());
        params.put("phone", entity.getPhone());
        int total = baseManager.get("BigvPhone.checkData", params);
        if(total > 0){
            result.put("msg", "手机号【" + entity.getPhone() + "】已经存在");
            return result;
        }

        result.put("code", "0");
        return result;
    }
    /**
     * 升级为大V
     * @param entity
     * @return
     */
    @RequestMapping(value = "/updateToBigv.htm", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> updateToBigv(BigvPhone entity) {
        LOGGER.info("更新白名单中的手机号客户【{}】为大V---------", entity.getPhone());
        Map<String, String> result = new HashMap<>();
        result.put("code", "1");

        if(entity.getPhone() == null){
            result.put("msg", "手机号已能为空");
            return result;
        }

        // 查询客户ID
        String customerId = baseManager.get("Customer.selectCustomerIdByPhone", new Object[]{entity.getPhone()});
        if(StringUtils.isBlank(customerId)){
            result.put("msg", "客户不存在");
            return result;
        }

        // 更新客户为大V
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setvStatus(2);
        baseManager.update("Customer.update", customer);

        result.put("code", "0");
        return result;
    }
}
