package com.calf.module.activity.controller;

import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.module.activity.entity.Ticket;
import com.calf.module.activity.service.TicketService;
import com.calf.module.appconfig.entity.Occupation;
import com.calf.module.customerservice.controller.AppearanceController;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: 优惠券
 *
 * @author chenpeng
 * @date 2018/10/30 14:24
 */
@Controller
@RequestMapping(value = "/ticket")
public class TicketController  implements BaseController<Ticket> {
    private static final Logger logger = LoggerFactory.getLogger(AppearanceController.class);

    @Autowired
    private TicketService ticketService;
    @Autowired
    private BaseManager baseManager;

    @Override
    public String home() {
        return "activity/ticket/ticketList";
    }

    @Override
    public DataTables<Ticket> initTable(HttpServletRequest request) {
        return ticketService.getTicketPageList(request);
    }

    @Override
    public String addAndUpdateHome(Model model, String id) {
        if(StringUtils.isNotBlank(id) && !"0".equals(id)){
            ticketService.getTicketDetail(model,id);
        }
        return  "app_config/advertisement/updateTicket";
    }

    @Override
    public int saveAdd(Ticket entity) {
        return ticketService.saveAdd(entity);
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public int saveUpdate(Ticket entity) {
        return ticketService.saveUpdate(entity);
    }

    @Override
    public int delete(String id){return baseManager.delete(Occupation.class, new Object[]{id});
    }

    /**
     * 删除禁用(逻辑删除)
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/disable")
    public int disable(String id){
        return ticketService.disable(id);
    }


}
