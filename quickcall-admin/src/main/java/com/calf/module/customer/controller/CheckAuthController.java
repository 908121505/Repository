package com.calf.module.customer.controller;

import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.SearchUtil;
import com.calf.module.customer.entity.Customer;
import com.calf.module.order.entity.Product;
import com.calf.module.order.entity.Skill;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * 认证审核Controller
 *
 * @author duanjun
 * @date 2018-09-24 15:31
 */
@Controller
@RequestMapping("/checkAuth")
public class CheckAuthController implements BaseController<Customer> {
    private static final Logger log = LoggerFactory.getLogger(CheckAuthController.class);

    @Autowired
    private BaseManager baseManager;

    private static final String JSP_PATH = "check_auth/%s";

    /**
     * 身份认证审核页面
     *
     * @return
     */
    @RequestMapping(value = "/identityHome.htm")
    public String identityHome() {
        return String.format(JSP_PATH, "identity_list");
    }

    /**
     * 大V认证审核页面
     *
     * @return
     */
    @RequestMapping(value = "/bigVHome.htm")
    public String bigVHome() {
        return String.format(JSP_PATH, "bigv_list");
    }
    
    /**
     * 大V声音审核页面
     *
     * @return
     */
    @RequestMapping(value = "/bigVVoiceHome.htm")
    public String bigVVoiceHome() {
        return String.format(JSP_PATH, "bigvVoice_list");
    }

    @Override
    public DataTables<Customer> initTable(HttpServletRequest request) {
        Map<String, Object> parameters = SearchUtil.convertorEntitysToMap(request.getParameterMap());
        String sEcho = (String) parameters.get("sEcho");
        List<Customer> banners = baseManager.query(Customer.class, parameters);
        int total = baseManager.get("Customer.queryCount", parameters);
        return new DataTables<>(sEcho, banners, banners.size(), total);
    }

    @Override
    public int saveUpdate(Customer entity) {
        Subject currentUser = SecurityUtils.getSubject();
        entity.setModifyMan(currentUser.getPrincipal().toString());
        if (Objects.equals(entity.getvStatus(), 2)) {
            entity.setType(1);// 大V审核通过后，客户类型变为大V

            // ADUAN 大V审核通过后 -- 插入假数据（后面删除）

            Map<String, Object> params = new HashMap<>();
            params.put("sellerId", entity.getCustomerId());
            List<Product> product = baseManager.query("Product.countSellerProduct", params);
            if(product.size() == 0){
                params = new HashMap<>();
                params.put("skillStatus", "0");
                params.put("iDisplayStart", "0");
                params.put("iDisplayLength", "5");
                List<Skill> skills = baseManager.query("Skill.selectPageList", params);
                for (Skill skill : skills) {
                    Product bean = new Product();
                    bean.setProductId(String.valueOf(UUIDUtils.getId()));
                    bean.setSkillId(skill.getId());
                    bean.setName(skill.getName());
                    bean.setServiceTime(30);
                    bean.setPrice(skill.getMinPrice().add(new BigDecimal(skill.getPriceStep())).divide(new BigDecimal(2)));
                    bean.setPreferentialPrice(new BigDecimal(skill.getPriceStep()));
                    bean.setDiscountRate(new BigDecimal(20));
                    bean.setDiscountPrice(new BigDecimal(skill.getPriceStep()).multiply(new BigDecimal(20)));
                    bean.setType(0);
                    bean.setProductStatus(1);
                    bean.setSellerId(entity.getCustomerId());
                    bean.setProductDescribe("审核通过时自动插入的数据，仅供测试使用");
                    bean.setCreateMan(currentUser.getPrincipal().toString());

                    baseManager.insert("Product.insert", bean);
                }
            }
        }
        if(Objects.equals(entity.getvVoiceStatus(), 4)){
        	entity.setvVoiceUrl(entity.getvVoiceUrlTmp());
        }

        return baseManager.update(entity);
    }

    @Override
    public String home() {
        return null;
    }

    @Override
    public String addAndUpdateHome(Model model, String id) {
        return null;
    }

    @Override
    public int saveAdd(Customer entity) {
        return 0;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public int delete(String id) {
        return 0;
    }
}
