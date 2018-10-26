package com.calf.module.appconfig.controller;

import com.calf.cn.aspect.MethodLog;
import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.DateUtil;
import com.calf.cn.utils.SearchUtil;
import com.calf.cn.utils.UUIDUtils;
import com.calf.module.appconfig.entity.Banner;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;


/**
 * APP配置 -- Banner配置Controller
 *
 * @author duanjun
 * @date 2018-09-19 11:34
 */
@Controller
@RequestMapping(value = "/banner")
public class BannerController implements BaseController<Banner> {
    private static final Logger log = LoggerFactory.getLogger(BannerController.class);

    @Autowired
    private BaseManager baseManager;

    private static final String JSP_PATH = "app_config/banner/%s";

    @Override
    public String home() {
        return String.format(JSP_PATH, "list");
    }

    @MethodLog(operType = "获取图片")
    @RequestMapping(value = "/getImg.htm", method = RequestMethod.GET)
    public void getImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> parameters = SearchUtil.convertorEntitysToMap(request.getParameterMap());
        String imgUrl = (String) parameters.get("imgUrl");
        File file = new File(imgUrl);
        System.out.println(imgUrl);
        InputStream is = new FileInputStream(file);
        byte[] be = new byte[1024];
        while (is.read(be) != -1) {
            try {
                response.getOutputStream().write(be);
            } catch (IOException e) {
                log.error("获取图片异常：", e);
            }
        }
    }

    @Override
    public DataTables<Banner> initTable(HttpServletRequest request) {
        Map<String, Object> parameters = SearchUtil.convertorEntitysToMap(request.getParameterMap());
        String title = (String) parameters.get("title");
        String sTime = (String) parameters.get("startTime");
        if (StringUtils.isNotBlank(sTime)) {
            sTime = sTime + " 00:00:00";
            parameters.put("startTime", sTime);
        }
        String eTime = (String) parameters.get("endTime");
        if (StringUtils.isNotBlank(eTime)) {
            eTime = eTime + " 23:59:59";
            parameters.put("endTime", eTime);
        }
        System.out.println(title);
        String sEcho = (String) parameters.get("sEcho");
        List<Banner> banners = baseManager.query(Banner.class, parameters);
        int total = baseManager.get("Banner.queryCount", parameters);
        return new DataTables<>(sEcho, banners, banners.size(), total);
    }

    @Override
    @RequiresPermissions(value = {"banner:add", "banner:update"}, logical = Logical.OR)
    public String addAndUpdateHome(Model model, String bannerId) {
        if (StringUtils.isNotBlank(bannerId)) {
            model.addAttribute("entity", baseManager.get("Banner.getBannerById", new Object[]{bannerId}));
        }
        return String.format(JSP_PATH, "edit");
    }

    @Override
    @MethodLog(operType = "新增")
    public int saveAdd(Banner entity) {
        Subject currentUser = SecurityUtils.getSubject();
        if (StringUtils.isNotBlank(entity.getUrl())) {
            entity.setUrl(entity.getUrl().trim());//去除前后空格
        }

        entity.setCreateMan(currentUser.getPrincipal().toString());
        entity.setModifyMan(currentUser.getPrincipal().toString());
        return baseManager.insert(entity);
    }

    @Override
    @MethodLog(operType = "删除")
    @RequiresPermissions(value = "banner:delete")
    public int delete(Long bannerId) {
        return delBanner(bannerId);
    }

    private int delBanner(Long bannerId) {
        return baseManager.delete(Banner.class, new Object[]{bannerId});
    }

    @Override
    @MethodLog(operType = "修改")
    public int saveUpdate(Banner entity) {
        if (StringUtils.isNotBlank(entity.getUrl())) {
            entity.setUrl(entity.getUrl().trim());//去除前后空格
        }

        Subject currentUser = SecurityUtils.getSubject();
        entity.setModifyMan(currentUser.getPrincipal().toString());
        return baseManager.update(entity);
    }

    @Override
    public int delete(String bannerId) {
        return baseManager.delete(Banner.class, new Object[]{bannerId});
    }

}
