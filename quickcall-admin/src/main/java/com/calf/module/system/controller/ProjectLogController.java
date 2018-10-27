package com.calf.module.system.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.module.system.entity.ProjectLog;

@Controller
@RequestMapping(value="/system/projectlog")
public class ProjectLogController implements BaseController<ProjectLog> {
	ResourceBundle rb = ResourceBundle.getBundle("common");
	
	@Override
	public String home() {
		// TODO Auto-generated method stub
		return "system/projectlog/ploglist";
	}

	@Override
	public DataTables<ProjectLog> initTable(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String file_path = rb.getString("ht_log_url");
		File file = new File(file_path);
		File[] files = file.listFiles();
		
		List<ProjectLog> aaData = new ArrayList<ProjectLog>();
		for (File f1 : files) {
			ProjectLog log = new ProjectLog(f1.getName(),String.format("%d KB", f1.length()/1024),f1.lastModified());
			aaData.add(log);
		}
		Collections.sort(aaData);
		Integer iTotalDisplayRecords = files.length;
		
		Integer start = Integer.valueOf(request.getParameter("iDisplayStart"));
		Integer size = Integer.valueOf(request.getParameter("iDisplayLength"));
		Integer end = (start + size)>files.length ? files.length : (start + size);
		
		String sEcho = request.getParameter("sEcho");
		return new DataTables<ProjectLog>(sEcho, aaData.subList(start, end), aaData.size(), iTotalDisplayRecords);
	}

	@Override
	public String addAndUpdateHome(Model model, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveAdd(ProjectLog entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveUpdate(ProjectLog entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 日志下载
	 * @param fileName
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/download.htm",method=RequestMethod.GET)
	public void downLogFile(HttpServletRequest request,HttpServletResponse response,String fileName) throws IOException {
		// TODO Auto-generated method stub
		String path = String.format("%s/%s", rb.getString("ht_log_url"),fileName);
		File file = new File(path);
		if(file.exists()){
			InputStream in = new FileInputStream(file);
			byte[] b = new byte[in.available()];  
			in.read(b);  
			in.close();
            
			response.setHeader("Content-Disposition","attachment; filename="+fileName+"");  
			OutputStream out = response.getOutputStream();
			out.write(b);
			out.flush();
			out.close();
		}
	}

	/* (非 Javadoc)
	 * Description:
	 * @see com.calf.cn.controller.BaseController#delete(java.lang.String)
	 */
	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
