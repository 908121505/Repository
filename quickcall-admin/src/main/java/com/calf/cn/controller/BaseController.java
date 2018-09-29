package com.calf.cn.controller;

import com.calf.cn.entity.DataTables;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 控制类基础接口，实线增删改查统一
 *
 * @author zhoujian
 */
public interface BaseController<T> {

    /**
     * 跳转到初始化页面
     *
     * @return
     */
    @RequestMapping(value = "/home.htm")
    String home();

    /**
     * 初始化表格数据
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/initTable.htm")
    DataTables<T> initTable(HttpServletRequest request);

    /**
     * 跳转增加和删除页面
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/addAndUpdateHome.htm", method = RequestMethod.GET)
    String addAndUpdateHome(Model model, String id);

    /**
     * 增加记录
     *
     * @param entity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveInsert.htm", method = RequestMethod.POST)
    int saveAdd(T entity);

    /**
     * 删除记录
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete.htm", method = RequestMethod.POST)
    int delete(Long id);

    /**
     * 修改记录
     *
     * @param entity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveUpdate.htm", method = RequestMethod.POST)
    int saveUpdate(T entity);

    /**
     * 删除记录
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/del.htm", method = RequestMethod.POST)
    int delete(String id);

}
