package com.honglu.quickcall.account.web.filter;

import com.alibaba.fastjson.JSONObject;
import com.honglu.quickcall.account.facade.code.AccountBizReturnCode;
import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.util.ConstantUtils;
import com.honglu.quickcall.common.api.util.DES3Utils;
import com.honglu.quickcall.common.api.util.HttpHelper;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.nio.charset.Charset;

/**
 * Created with antnest-platform
 */
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {

	private final byte[] body;

	public BodyReaderHttpServletRequestWrapper(HttpServletRequest request, HttpServletResponse response) throws IOException {
		super(request);
		JSONObject object = null;
		String str = "";
		try {
			String in = HttpHelper.getBodyString(request);
			if (StringUtils.isBlank(in)) {
				HttpHelper.getFailData(response, "参数不能为空");
				throw new BizException(AccountBizReturnCode.paramError);
			}
			if (in.indexOf("%") != -1) {
				str = DES3Utils.decryptMode(URLDecoder.decode(in), ConstantUtils.THREEDES_KEY);
			} else {
				str = DES3Utils.decryptMode(in, ConstantUtils.THREEDES_KEY);
			}

			object = JSONObject.parseObject(str);
			if (!object.containsKey("sign") || !object.containsKey("data")) {
				HttpHelper.getFailData(response, "签名或data参数格式错误");
				throw new BizException(AccountBizReturnCode.paramError);
			}
		} catch (Exception e) {
			HttpHelper.getFailData(response, "参数错误");
			throw new BizException(AccountBizReturnCode.paramError);
		}
		try {
			if (!DES3Utils.checkSign(str, object.getString("sign"))) {
				HttpHelper.getFailData(response, "验签不正确");
				throw new BizException(AccountBizReturnCode.CheckSignError);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		body = object.getString("data").getBytes(Charset.forName("UTF-8"));
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(getInputStream()));
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {

		final ByteArrayInputStream bais = new ByteArrayInputStream(body);

		return new ServletInputStream() {

			@Override
			public int read() throws IOException {
				return bais.read();
			}
		};
	}

}