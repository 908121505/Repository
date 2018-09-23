package com.calf.module.order.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.calf.cn.controller.BaseController;
import com.calf.cn.entity.DataTables;
import com.calf.module.order.impl.ProductService;
import com.calf.module.order.vo.ProductVO;

import shaded.org.apache.commons.lang3.StringUtils;

@Controller
@RequestMapping("/product")
public class ProductController implements BaseController<ProductVO>{

	@Autowired
	private ProductService  productService;
	
	@Override
	public String home() {
		return "order/productList";
	}

	@Override
	public DataTables<ProductVO> initTable(HttpServletRequest request) {
		return productService.getProductPageList(request);
	}

	@Override
	public String addAndUpdateHome(Model model, String id) {
		if(StringUtils.isNotBlank(id)){
			productService.getProductDetail(model,id);
		}
		return  "order/productAdd";
	}

	@Override
	public int saveAdd(ProductVO entity) {
		return productService.saveAdd(entity);
	}

	@Override
	public int delete(Long id) {
		return 0;
	}

	@Override
	public int saveUpdate(ProductVO entity) {
		return productService.saveUpdate(entity);
	}

	@Override
	public int delete(String id) {
		return 0;
	}

	
}