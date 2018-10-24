package com.calf.module.customer.controller;

import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.SearchUtil;
import com.calf.module.customer.entity.Customer;
import com.calf.module.customer.entity.CustomerVo;
import com.calf.module.customer.entity.FadeCustomer;
import com.calf.module.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.testng.util.Strings;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customer")
public class CustomerController implements BaseController<CustomerVo> {

    @Autowired
    private BaseManager baseManager;

    @Autowired
    private CustomerService customerService;

    @Override
    public String home() {
        return "customer/customer/customerList";
    }

    @Override
    public DataTables<CustomerVo> initTable(HttpServletRequest request) {
        Map<String, Object> parameters = SearchUtil.convertorEntitysToMap(request.getParameterMap());
        String sEcho = (String) parameters.get("sEcho");
        List<CustomerVo> list = baseManager.query("Customer.selectCustomVo", parameters);
        int total = baseManager.get("Customer.queryCountVo", parameters);
        return new DataTables<>(sEcho, list, list.size(), total);

    }

    @Override
    public String addAndUpdateHome(Model model, String id) {
        if (Strings.isNotNullAndNotEmpty(id)) {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("customerId", id);
            CustomerVo vo = baseManager.get("Customer.selectVoByPrimaryKey", parameters);
            model.addAttribute("entity", vo);
        }
        return "customer/customer/manageCustomerList";
    }

    @Override
    public int saveAdd(CustomerVo entity) {
        return 0;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public int saveUpdate(CustomerVo entity) {
        customerService.updateCustomer(entity);
        return 0;
    }

    @Override
    public int delete(String id) {
        return 0;
    }

}
