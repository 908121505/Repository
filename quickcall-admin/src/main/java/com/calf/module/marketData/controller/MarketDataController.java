package com.calf.module.marketData.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.cn.service.BaseManager;
import com.calf.cn.utils.CalendarUtil;
import com.calf.cn.utils.DateUtil;
import com.calf.cn.utils.SearchUtil;
import com.calf.module.marketData.staticFunc.ExportMarketData;
import com.calf.module.marketData.vo.MarketDataVO;

@Controller
@RequestMapping(value = "/marketData")
public class MarketDataController implements BaseController<MarketDataVO>{
	private static final Logger LOGGER = LoggerFactory.getLogger(MarketDataController.class);
	
    @Autowired
    private BaseManager baseManager;
    
	@Override
	public String home() {
		return "marketData/realTimeDataList";
	}
	
	@RequestMapping(value = "/hishome.htm")
	public String home1() {
		return "marketData/newUserHistoryList";
	}
	
	@RequestMapping(value = "/effhome.htm")
	public String home2() {
		return "marketData/marketEffectList";
	}

	@SuppressWarnings("unchecked")
	@Override
	public DataTables<MarketDataVO> initTable(HttpServletRequest request) {
		Map<String, Object> parameters = SearchUtil.convertorEntitysToMap(request.getParameterMap());
		String sEcho = (String) parameters.get("sEcho");
		List<MarketDataVO> list = getMarketDataList(parameters);
		return new DataTables<>(sEcho, list, list.size(), list.size());
	}
	
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/exportCSV.htm")
	public void ExportCSV(HttpServletRequest request, HttpServletResponse response){
    	Map<String, Object> parameters = SearchUtil.convertorEntitysToMap(request.getParameterMap());
    	List<MarketDataVO> list = getMarketDataList(parameters);
    	ExportMarketData.exportCsv(list, response);
	}
    
    @ResponseBody
    @RequestMapping(value = "/getChannel.htm")
    public List<String> getChannel(){
    	List<String> list = baseManager.query("MarketDataMapper.queryAllChannel", new HashMap<>());
    	for (int i = 0;i < list.size() ; i++) {
			if("".equals(list.get(i))){
				list.set(i, "未知渠道");
			}
		}
    	return list;
    }
    
	private List<MarketDataVO> getMarketDataList(Map<String, Object> params){
		List<MarketDataVO> list = new ArrayList<>();
		HashMap<String,Object> map = new HashMap<>();
    	String type = (String) params.get("type");
		String appChannelName = (String) params.get("appChannelName");
		String sHour = (String) params.get("sHour");
		String eHour = (String) params.get("eHour");
		String sTime = (String) params.get("sTime");
		String eTime = (String) params.get("eTime");
		if(appChannelName != null){
			appChannelName = appChannelName.replaceAll("未知渠道", "");
			String[] chs = appChannelName.split(",");
			map.put("appChannelName", chs);
		}
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		switch(type){
		case "1":
			CalendarUtil.clearTime(start);
			CalendarUtil.setTenMinutes(end);
			if(sHour != null){
				start.add(Calendar.HOUR_OF_DAY, Integer.valueOf(sHour));
			}
			if(eHour != null){
				CalendarUtil.clearTime(end);
				end.add(Calendar.HOUR_OF_DAY, Integer.valueOf(eHour));
			}
			map.put("start", start.getTime());
			map.put("end", end.getTime());
			map.put("date", DateUtil.dateYMDEXT(start.getTime()));
			List<MarketDataVO> result = baseManager.query("MarketDataMapper.queryAll", map);
			list.addAll(result);
			break;
		case "2":
			if(sTime != null && eTime != null){
				start = CalendarUtil.getCalendarByDate(sTime);
				end = CalendarUtil.getCalendarByDate(eTime);
			}else{
				CalendarUtil.clearTime(start);
				CalendarUtil.clearTime(end);
			}
			int diff = CalendarUtil.getDiff(start, end)+1;
			Calendar startHour = (Calendar) start.clone();
			Calendar endHour = (Calendar) start.clone();
			if(sHour != null && eHour != null){
				startHour.add(Calendar.HOUR_OF_DAY, Integer.valueOf(sHour));
				endHour.add(Calendar.HOUR_OF_DAY, Integer.valueOf(eHour));
			}
			else{
				endHour.add(Calendar.DAY_OF_MONTH, 1);
			}
			for(int i=0;i<diff;i++){
				map.put("start", startHour.getTime());
				map.put("end", endHour.getTime());
				map.put("date", DateUtil.dateYMDEXT(startHour.getTime()));
				List<MarketDataVO> res = baseManager.query("MarketDataMapper.queryAll", map);
				list.addAll(res);
				startHour.add(Calendar.DAY_OF_MONTH, 1);
				endHour.add(Calendar.DAY_OF_MONTH, 1);
			}
			break;
		case "3":
			Calendar startAll = CalendarUtil.getCalendarByYear(2018);
			if(sTime != null && eTime != null){
				start = CalendarUtil.getCalendarByDate(sTime);
				end = CalendarUtil.getCalendarByDate(eTime);
			}else{
				CalendarUtil.clearTime(start);
				CalendarUtil.clearTime(end);
			}
			int diff1 = CalendarUtil.getDiff(start, end)+1;
			Calendar endAll = (Calendar) start.clone();
			endAll.add(Calendar.DAY_OF_MONTH, 1);
			map.put("start", startAll.getTime());
			for(int i=0;i<diff1;i++){
				map.put("end", endAll.getTime());
				Calendar tmp = (Calendar) endAll.clone();
				tmp.add(Calendar.DAY_OF_MONTH, -1);
				map.put("date", DateUtil.dateYMDEXT(tmp.getTime()));
				List<MarketDataVO> res = baseManager.query("MarketDataMapper.queryAll", map);
				list.addAll(res);
				endAll.add(Calendar.DAY_OF_MONTH, 1);
			}
			break;
		default:
			break;
		}
		
		return list;
    }
    
	
	@Override
	public String addAndUpdateHome(Model model, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveAdd(MarketDataVO entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveUpdate(MarketDataVO entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
