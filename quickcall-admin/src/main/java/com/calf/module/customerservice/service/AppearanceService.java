package com.calf.module.customerservice.service;

import com.calf.cn.entity.DataTables;
import com.calf.module.customerservice.entity.CustomerAppearance;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
/**
 * Created by cp on 2018/10/22.
 */
public interface AppearanceService {
    DataTables<CustomerAppearance> getAppearancePageList(HttpServletRequest request);

    void getAppearanceDetail(Model model, String id);

    int saveUpdate(CustomerAppearance entity);

    int approve(String id);

    int reject(String id);
}
