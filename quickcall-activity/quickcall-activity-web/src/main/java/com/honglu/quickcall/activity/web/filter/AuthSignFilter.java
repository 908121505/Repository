package com.honglu.quickcall.activity.web.filter;

import com.alibaba.fastjson.JSONObject;
import com.honglu.quickcall.common.api.util.HttpHelper;
import com.honglu.quickcall.common.core.util.Detect;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthSignFilter implements Filter {

    private static final Logger logger = Logger.getLogger(AuthSignFilter.class);

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
		 
		 
        /*//传文件的时候不加密
        String enctype = request.getContentType();  //multipart/form-data
        String requestURI = httpServletRequest.getRequestURI();
        logger.info("===========requestURI="+requestURI);
        
        if (StringUtils.isNotBlank(enctype) && enctype.contains("multipart/form-data")) {
            logger.info(" =============================== communitycenterweb.AuthSignFilter.doFilter.multipart/form-data ===============================");
            chain.doFilter(request, response);
        }else if ("/communityCenter/articleloan/articleTypeDetail".equals(requestURI)|| "/communityCenter/articleCommentPraiseloan/articleCommentPraiseTypeCancelH5".equals(requestURI)
                || "/communityCenter/articleloan/articlePublishType".equals(requestURI) || "/communityCenter/articleloan/articleDetailsTypeList".equals(requestURI)
                || "/communityCenter/articlePraiseloan/articlePraiseTypeCancel".equals(requestURI) ||
                "/communityCenter/articlePostloan/articlePostQueryDetailTypeThumbsH5".equals(requestURI)||
                "/communityCenter/alignment/connection".equals(requestURI)||
                "/communityCenter/userCommunity/opinionFeedback".equals(requestURI)
        		) {
            chain.doFilter(request, response);
        } else {
            // 防止流读取一次后就没有了, 所以需要将流继续写出去
            ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(httpServletRequest, httpServletResponse);
            String body = HttpHelper.getBodyString(requestWrapper);
            @SuppressWarnings("unused")
            JSONObject json = JSONObject.parseObject(body);
            chain.doFilter(requestWrapper, response);
        }*/


        chain.doFilter(request, response);
    }


    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }


}
