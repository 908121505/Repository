package com.calf.module.internal.controller;

import com.aliyun.oss.OSSClient;
import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.MD5Utils;
import com.calf.cn.utils.SFtpUtil;
import com.calf.cn.utils.SearchUtil;
import com.calf.module.common.entity.FileUpload;
import com.calf.module.internal.entity.Message;
import com.calf.module.internal.service.InternalService;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import com.honglu.quickcall.common.third.OSS.OSSUtil;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author xiangxianjin
 * @date 2018年10月24日 22点14分
 * @description: 站内信消息查询
 */
@Controller
@RequestMapping(value = "/internal")
public class InternalController implements BaseController<Message> {
    private static final Logger LOGGER = LoggerFactory.getLogger(InternalController.class);

    @Autowired
    private BaseManager baseManager;

    @Autowired
    private InternalService internalService;

    /**
     * 跳转到初始化页面
     *
     * @return
     */
    @Override
    public String home() {
        return "internal/messageList";
    }

    /**
     * 初始化表格数据
     *
     * @param request
     * @return
     */
    @Override
    public DataTables<Message> initTable(HttpServletRequest request) {
        Map<String, Object> parameters = SearchUtil.convertorEntitysToMap(request.getParameterMap());
        String sEcho = (String) parameters.get("sEcho");
        List<Message> list = baseManager.query("MessageMapper.selectMessage", parameters);
        int total = baseManager.get("MessageMapper.queryCount", parameters);
        return new DataTables<>(sEcho, list, list.size(), total);
    }

    /**
     * 跳转增加和删除页面
     *
     * @param model
     * @param id
     * @return
     */
    @Override
    public String addAndUpdateHome(Model model, String id) {
        LOGGER.info("查询消息记录，消息编号：{}", id);
        if (StringUtils.isNotBlank(id)) {
            model.addAttribute("entity", baseManager.get("MessageMapper.selectByPrimaryKey", new Object[]{id}));
        }
        return "internal/messageEdit";
    }

    /**
     * 增加记录
     *
     * @param entity
     * @return
     */
    @Override
    public int saveAdd(Message entity) {
        Subject currentUser = SecurityUtils.getSubject();
        entity.setCreateMan(currentUser.getPrincipal().toString());
        entity.setModifyMan(currentUser.getPrincipal().toString());
        entity.setCreateTime(new Date());
        entity.setModifyTime(new Date());

        entity.setMessageId(String.valueOf(UUIDUtils.getId()));
        entity.setTitle(entity.getTitle().trim());
        entity.setH5Url(entity.getH5Url().trim());
        return internalService.addMessage(entity);
    }

    /**
     * 删除记录
     *
     * @param id
     * @return
     */
    @Override
    public int delete(Long id) {
        LOGGER.info("删除消息记录，消息编号：{}", id);
        return baseManager.delete("MessageMapper.deleteByPrimaryKey", new Object[]{id});
    }

    /**
     * 修改记录
     *
     * @param entity
     * @return
     */
    @Override
    public int saveUpdate(Message entity) {
        Subject currentUser = SecurityUtils.getSubject();
        entity.setModifyMan(currentUser.getPrincipal().toString());
        entity.setModifyTime(new Date());

        entity.setTitle(entity.getTitle().trim());
        entity.setH5Url(entity.getH5Url().trim());
        return internalService.updateMessage(entity);
    }

    /**
     * 删除记录
     *
     * @param id
     * @return
     */
    @Override
    public int delete(String id) {
        LOGGER.info("删除消息记录，消息编号：{}", id);
        return baseManager.delete("MessageMapper.deleteByPrimaryKey", new Object[]{id});
    }

    /**
     * 背景图片上传
     *
     * @param id
     * @param file
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("/backgroundImg.htm")
    @ResponseBody
    public FileUpload fadeCustomerHeadImg(String imgFolder, @RequestParam("messageFile") MultipartFile file,
                                          HttpServletRequest request) throws IOException {
        FileUpload upload = new FileUpload("error", "背景图片上传失败", null);

        if (file == null) {
            upload.setMsg("请选择一个文件上传！");
            return upload;
        }
        try {
            CommonsMultipartFile cf = (CommonsMultipartFile) file;
            DiskFileItem fi = (DiskFileItem) cf.getFileItem();
            String md5Str = MD5Utils.getMD5(fi.getStoreLocation());
            String fileName = System.currentTimeMillis() + "."
                    + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

            //阿里云客户端
            OSSClient ossClient = OSSUtil.getOSSClient();
            //上传
            boolean flag = OSSUtil.uploadInputStreamObject2OSS(ossClient, file.getInputStream(), fileName, imgFolder);
            if (flag) {
                String imageUrl = SFtpUtil.ossUrl + "/" + imgFolder + "/" + fileName;
                upload = new FileUpload("success", "上传成功", imageUrl);
                upload.setMd5Str(md5Str);
            }
        } catch (Exception e) {
            LOGGER.error("上传图片失败：", e);
        }
        return upload;
    }
}
