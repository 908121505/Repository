package com.calf.module.export.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.calf.cn.aspect.MethodLog;
import com.calf.cn.utils.JedisUtil;
import com.calf.cn.utils.UUIDUtils;

/**
 * Created by bruce on 2017/5/26.
 */
@Controller
@RequestMapping("/import")
public class ImportDataFileController {

    
    
    
    /**
     * 导入消息记录
     */
    @ResponseBody
    @RequestMapping(value = "/message.htm", method = RequestMethod.POST)
    public Object messageRecord(@RequestParam("file") MultipartFile file) throws IOException {
//
//        Map<String, Object> result = new HashMap<>();
//        result.put("code", "500");
//        List<Message> list = new ArrayList<>();
//        XSSFRow row = null;
//        try {
//            XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
//            XSSFSheet xs = wb.getSheetAt(0);
//            if (xs == null) {
//                return result;
//            }
//            for (int rowNum = 1; rowNum < xs.getPhysicalNumberOfRows(); rowNum++) {
//                row = xs.getRow(rowNum);
//                if ( row.getPhysicalNumberOfCells() != 5){
//                    break;
//                }
//                Message message = new Message();
//                message.setTitle(getValue(row.getCell(0)));
//                message.setContent(getValue(row.getCell(1)));
//                message.setState(Integer.valueOf(getValue(row.getCell(2))));
//                message.setIsRead(Integer.valueOf(getValue(row.getCell(3))));
//                message.setType(Integer.valueOf(getValue(row.getCell(4))));
//                message.setCellPhone(String.valueOf(Double.parseDouble(getValue(row.getCell(5)))));
//                message.setMessageUrl(String.valueOf(getValue(row.getCell(6))));
//                message.setLinkUrlH((getValue(row.getCell(7))));
//                list.add(message);
//            }
//
//        } catch (IOException e) {
//            return result;
//        }
//        result.put("code", "200");
//        return result;
     return null;
//    
    }

    private static String getValue(XSSFCell xssfCell) {
        if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfCell.getBooleanCellValue());
        } else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC) {
            DecimalFormat df = new DecimalFormat("0");
            return String.valueOf(df.format(xssfCell.getNumericCellValue()));
        } else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_STRING) {
            return String.valueOf(xssfCell.getStringCellValue());
        } else {
            return String.valueOf(xssfCell.getStringCellValue());
        }
    }
    private static String getValueExt(XSSFCell xssfCell) {
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
