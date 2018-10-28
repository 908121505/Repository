package com.calf.module.export.controller;

import com.calf.cn.utils.JedisUtil;
import com.calf.module.internal.constant.RedisCons;
import com.gexin.fastjson.JSON;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xiangxianjin
 * @date 2018年10月24日 15点53分
 * @descrption: 导入
 */
@Controller
@RequestMapping("/import")
public class ImportDataFileController {
    private final static Logger LOGGER = LoggerFactory.getLogger(ImportDataFileController.class);

    /**
     * 手机格式正则校验
     */
    private static Pattern PHONE_PATTERN = Pattern.compile("^((1[0-9]))\\d{9}$");

    /**
     * 导入消息记录
     */
    @RequestMapping(value = "/message.htm", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> messageRecord(@RequestParam("file") MultipartFile file) throws IOException {
        Map<String, Object> result = new HashMap<>();
        XSSFRow row = null;
        XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
        try {
            XSSFSheet xs = wb.getSheetAt(0);
            if (xs == null) {
                return result;
            }
            Set<String> checkSet =  new HashSet<String>();
            Integer rowNumTotal =  xs.getPhysicalNumberOfRows();
            LOGGER.info("开始导入文件：{}，导入数量：{}", file.getName(), rowNumTotal);
            String errorMessage = "";
            Integer maxNum = 10000;
            if(rowNumTotal > maxNum){
                result.put("code", 2004);
                result.put("message", "导入数量超出限制，支持最大导入数据" + maxNum + "条");
                return result;
            }
            for (int rowNum = 0; rowNum < rowNumTotal; rowNum++) {
                row = xs.getRow(rowNum);
                String phoneNum =  getValue(row.getCell(0));
                Matcher m = PHONE_PATTERN.matcher(phoneNum);
                if(!m.matches()){
                    //手机号码格式不准确
                    String tip = "第" + rowNum + "行手机号码格式不准确，手机号：" + phoneNum + "！";
                    errorMessage += tip;
                    LOGGER.warn(tip);
                    continue;
                }
                if(checkSet.contains(phoneNum)){
                    //手机号码重复
                    String tip = "第" + rowNum + "行存在重复手机号，手机号：" + phoneNum + "，不再重复导入！";
                    errorMessage += tip;
                    LOGGER.warn(tip);
                    continue;
                }
                checkSet.add(phoneNum);
            }
            if(CollectionUtils.isNotEmpty(checkSet)){
                String idStr = UUIDUtils.getUUID();
                String listStr = JSON.toJSONString(checkSet);
                //缓存有效期30天
                JedisUtil.set(RedisCons.ADMIN_MESSAGE_CUSTOMER + idStr, listStr,30*24*60*60);
                //正常
                result.put("code", 200);
                result.put("idStr", idStr);
                result.put("message", StringUtils.isNotBlank(errorMessage)
                        ? errorMessage : "导入成功，共"+checkSet.size()+"条");
            }else{
                //数据为空
                result.put("code", 2002);
                result.put("message", "文件导入数据为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("文件导入发生异常，文件名：{}，错误原因：{}", file.getName(), e.getMessage());
            result.put("code", 2005);
            result.put("message", "文件导入发生异常，错误原因："+e.getMessage());
        }
        return result;
    }

    private static String getValue(XSSFCell xssfCell) {
    	if (xssfCell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
    		return String.valueOf(xssfCell.getBooleanCellValue());
    	} else if (xssfCell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
    		DecimalFormat df = new DecimalFormat("0");
    		return String.valueOf(df.format(xssfCell.getNumericCellValue()));
    	} else if (xssfCell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
    		return String.valueOf(xssfCell.getStringCellValue());
    	} else {
    		return String.valueOf(xssfCell.getStringCellValue());
    	}
    }


}
