package com.calf.module.order.controller;

import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.module.order.impl.SkillItemExtService;
import com.calf.module.order.vo.SkillItemExtVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/skillItemExt")
public class SkillItemExtController implements BaseController<SkillItemExtVo> {

    @Autowired
    private SkillItemExtService skillItemExtService;

    @Override
    public String home() {
        return null;
    }

    @Override
    public DataTables<SkillItemExtVo> initTable(HttpServletRequest request) {
        return skillItemExtService.getSkillItemExtPageList(request);
    }

    @Override
    public String addAndUpdateHome(Model model, String id) {
        return null;
    }

    @Override
    public int saveAdd(SkillItemExtVo entity) {
        return skillItemExtService.saveAdd(entity);
    }




    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public int saveUpdate(SkillItemExtVo entity) {
        return 0;
    }

    @Override
    public int delete(String id) {
        return 0;
    }
}
