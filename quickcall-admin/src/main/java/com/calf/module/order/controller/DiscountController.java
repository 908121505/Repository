package com.calf.module.order.controller;


import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.SearchUtil;
import com.calf.module.customer.entity.CustomerVo;
import com.calf.module.order.entity.Discount;
import com.calf.module.order.entity.SkillItemVo;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import shaded.org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/discount")
public class DiscountController implements BaseController<Discount> {

    @Autowired
    private BaseManager baseManager;

    @Override
    public String home() {
        return "order/discountList";
    }

    @Override
    public DataTables<Discount> initTable(HttpServletRequest request) {
        Map<String, Object> parameters = SearchUtil.convertorEntitysToMap(request.getParameterMap());
        String sEcho = (String) parameters.get("sEcho");
        List<Discount> list = baseManager.query("Discount.selectPageList", parameters);
        int total = baseManager.get("Discount.slectCount", parameters);
        return new DataTables<>(sEcho, list, list.size(), total);
    }

    @Override
    public String addAndUpdateHome(Model model, String id) {
        System.out.println(id);
        if(StringUtils.isNotBlank(id)){
            Map<String,Object> map = new HashMap<>();
            map.put("id",id);
            //skillItemService.getSkillItemDetail(model,id);
           Discount discounts= baseManager.get("Discount.selectDiscountById",map);
           model.addAttribute("entity",discounts);
        }
        return  "order/discountAdd";
    }

    @Override
    public int saveAdd(Discount entity) {
        entity.setId(UUIDUtils.getId()+"");
        Map<String,Object> param = new HashMap<>();
        //BigDecimal divide = entity.getDiscount().divide(new BigDecimal("100")).setScale(1);
        param.put("id",entity.getId());
        BigDecimal divide = entity.getDiscount().divide(new BigDecimal("100")).setScale(1);
        param.put("discount",divide);
        param.put("skillExtStatus",entity.getSkillExtStatus());
        param.put("skillItemId",entity.getSkillItemId());
        baseManager.insert("Discount.insertDiscount",param);
        return 0;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public int saveUpdate(Discount entity) {
        Map<String,Object> param = new HashMap<>();
        param.put("id",entity.getId());
        BigDecimal divide = entity.getDiscount().divide(new BigDecimal("100")).setScale(1);
        param.put("discount",divide);
        param.put("skillExtStatus",entity.getSkillExtStatus());
        param.put("skillItemId",entity.getSkillItemId());
        baseManager.update("Discount.updateDiscount",param);

        return 0;
    }

    @Override
    public int delete(String id) {
        return 0;
    }

    @RequestMapping(value = "getAllSkillName",method = RequestMethod.POST)
    @ResponseBody
    public List<SkillItemVo> getAllSkillName(){
        Map<String,Object> param = new HashMap<>();
        List<SkillItemVo> skillNames = baseManager.query("Discount.slelectAllSkillName",param);
        return skillNames;

    }

}
