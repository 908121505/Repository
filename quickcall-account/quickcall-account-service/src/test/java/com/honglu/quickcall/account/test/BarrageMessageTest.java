package com.honglu.quickcall.account.test;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.honglu.quickcall.common.api.util.HttpClientUtils;

/**
 * Created by len.song on 2018-02-07.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class BarrageMessageTest {

	@Test
	public void lpushMessageTest() {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId", "66c6e6cb5f064ff2bef5d651faec9be1");

		String json = JSON.toJSONString(paramMap, true);
		String params = "orderId=403be86602cb4ad0b5043623a03ea5c9";
		String res = HttpClientUtils
				.doPostForm("http://testfinance.51huihuahua.com/financevoice/voice/aliPayOrderQuery", params);

		System.out.println(res);
	}
}
