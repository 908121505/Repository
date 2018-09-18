package com.honglu.quickcall.user.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.honglu.quickcall.common.api.util.HttpHelper;
import com.honglu.quickcall.common.core.util.Detect;

public class AuthSignFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(AuthSignFilter.class);
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        
        String originHeader=httpServletRequest.getHeader("Origin") ;
		 if (Detect.notEmpty(originHeader)) {
			 httpServletResponse.setHeader("Access-Control-Allow-Origin",  originHeader);
			 httpServletResponse.setHeader("Access-Control-Allow-Methods","POST");
			 httpServletResponse.setHeader("Access-Control-Allow-Headers","x-requested-with,Content-Type");
			 httpServletResponse.setHeader("XDomainRequestAllowed","1");
		 }
		 
		 
        //传文件的时候不加密
        String enctype = request.getContentType();  //multipart/form-data
        String requestURI = httpServletRequest.getRequestURI();
        logger.info("===========requestURI="+requestURI);
        
        if (StringUtils.isNotBlank(enctype) && enctype.contains("multipart/form-data")) {
            logger.info(" =============================== usercenterweb.AuthSignFilter.doFilter.multipart/form-data ===============================");
            chain.doFilter(request, response);
        }else if ("/userCenter/user/give_present".equals(requestURI)) {
            // 防止流读取一次后就没有了, 所以需要将流继续写出去
            ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(httpServletRequest, httpServletResponse);
            String body = HttpHelper.getBodyString(requestWrapper);
            @SuppressWarnings("unused")
            JSONObject json = JSONObject.parseObject(body);
            chain.doFilter(requestWrapper, response);
        } else {
            chain.doFilter(request, response);
        }

    }


    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }


}
