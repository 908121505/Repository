package com.calf.module.export.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.DateUtil;
import com.calf.cn.utils.ProductConstants;
import com.calf.cn.utils.RadomUtil;
import com.calf.cn.utils.RegExpUtil;
import com.calf.cn.utils.SearchUtil;


/**
 * 导出文件
 * 
 * @author guixin
 *
 */
@Controller
@RequestMapping("/export")
public class ExportDataFileController {
	private final static Logger LOGGER = LoggerFactory.getLogger(ExportDataFileController.class);

	@Autowired
	private BaseManager baseManager;

	@RequestMapping(value = "/home.htm", method = RequestMethod.GET)
	public String home() {
		System.out.println(1);
		return "export/exportfile";
	}

	
	/**
	 * 导出 市场三合一报表
	 * 
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/marketThree.htm", method = RequestMethod.GET)
	public void marketThreePlay(HttpServletResponse response, HttpServletRequest request) throws IOException {

		HSSFWorkbook wb = new HSSFWorkbook();
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/OCTET-STREAM");

			String filedisplay = "市场三合一报表.xls";
			/*
			 * filedisplay = URLEncoder.encode(filedisplay, "UTF-8");
			 * response.addHeader("Content-Disposition", "attachment;filename="
			 * + filedisplay);
			 */
			response.addHeader("Content-Disposition",
					"attachment;filename=" + new String(filedisplay.getBytes(), "iso-8859-1"));
			HSSFSheet sheet = wb.createSheet("市场三合一报表");
			sheet.setColumnWidth(0, 8000);
			HSSFFont font = wb.createFont();
			font.setFontName("宋体");
			font.setFontHeightInPoints((short) 36);

			HSSFRow row = sheet.createRow(0);

			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment((short) 2);
			HSSFCell cell1 = row.createCell(0);
			cell1.setCellValue("序号");
			HSSFCell cell2 = row.createCell(1);
			cell2.setCellValue("日期");
			HSSFCell cell5 = row.createCell(2);
			cell5.setCellValue("注册渠道");
			HSSFCell cell7 = row.createCell(3);
			cell7.setCellValue("注册数");
			HSSFCell cell6 = row.createCell(4);
			cell6.setCellValue("登录数");
			HSSFCell cell8 = row.createCell(5);
			cell8.setCellValue("申请人数");
			HSSFCell cell9 = row.createCell(6);
			cell9.setCellValue("申请商户数");

			Map<String, Object> paramMap = SearchUtil.convertorEntitysToMap(request.getParameterMap());

			// 识别 排序参数
			String sortVal = String.valueOf(paramMap.get("mDataProp_" + String.valueOf(paramMap.get("iSortCol_0"))));
			String sort = String.valueOf(paramMap.get("sSortDir_0"));
            if(StringUtils.isBlank(sort) || "null".equalsIgnoreCase(sort)){
            	sort = "desc";
            }
			List<Map<String, Object>> mapList = null;
			paramMap.put("exportflag", 1);// 导出标记
			String pageType = String.valueOf(paramMap.get("pageType"));
			String appChannelName = paramMap.get("appChannelName") == null ? ""
					: paramMap.get("appChannelName").toString();
			if (StringUtils.isNotBlank(appChannelName)) {
				appChannelName = appChannelName.replaceAll(",{1,}", ",");
				String filterString = "\\[](){}^$?*+.|";
				paramMap.put("appChannelName", RegExpUtil
						.filterRegExpString(StringUtils.strip(appChannelName, ","), filterString).replace(",", "|"));
			}
			paramMap.put("orderByParam", sortVal);
			paramMap.put("orderByType", sort);
			if ("byCurrent".equalsIgnoreCase(pageType)) {

				mapList = baseManager.query("Account.queryLoginAndRegAndApplyNumByCurrent", paramMap);
			} else if ("byDate".equalsIgnoreCase(pageType)) {
				mapList = baseManager.query("Account.queryLoginAndRegAndApplyNumByDate", paramMap);

			} else {

				mapList = baseManager.query("Account.queryLoginAndRegAndApplyNumByTotal", paramMap);
			}
			for (int i = 0; i < mapList.size(); i++) {
				row = sheet.createRow(i + 1);
				Map<String, Object> map = mapList.get(i);
				row.createCell(0).setCellValue((i + 1) + "");
				row.createCell(1).setCellValue(map.get("createDate") == null ? "--" : map.get("createDate") + "");
				row.createCell(2)
						.setCellValue(map.get("appChannelName") == null ? "--" : map.get("appChannelName") + "");
				row.createCell(3).setCellValue(map.get("regCount") == null ? "--" : map.get("regCount") + "");
				row.createCell(4).setCellValue(map.get("loginCount") == null ? "--" : map.get("loginCount") + "");
				row.createCell(5)
						.setCellValue(map.get("applyPersonNum") == null ? "--" : map.get("applyPersonNum") + "");
				row.createCell(6)
						.setCellValue(map.get("applyProductNum") == null ? "--" : map.get("applyProductNum") + "");
			}

			try {
				OutputStream out = response.getOutputStream();
				wb.write(out);
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			wb.close();
		}

	}

}
