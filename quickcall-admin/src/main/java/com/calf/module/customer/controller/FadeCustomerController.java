package com.calf.module.customer.controller;

import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.module.customer.entity.FadeCustomer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

/**
 * 随机用户管理模块
 *
 * @author duanjun
 * @date 2018-10-17 17:13
 */
public class FadeCustomerController implements BaseController<FadeCustomer> {
    private static final Logger log = LoggerFactory.getLogger(FadeCustomerController.class);

    @Autowired
    private BaseManager baseManager;

    private static final String JSP_PATH = "customer/fade_customer/%s";

    @Override
    public String home() {
        return null;
    }

    @Override
    public DataTables<FadeCustomer> initTable(HttpServletRequest request) {
        return null;
    }

    @Override
    public String addAndUpdateHome(Model model, String id) {
        return null;
    }

    @Override
    public int saveAdd(FadeCustomer entity) {
        return 0;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public int saveUpdate(FadeCustomer entity) {
        return 0;
    }

    @Override
    public int delete(String id) {
        return 0;
    }
}
