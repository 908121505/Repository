package com.honglu.quickcall.account.test;

import java.util.HashMap;
import java.util.Map;

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

		String params = "orderId=c6a5360b4fcd45e39d09d1ca9433a1d3&type=2";
		String res = HttpClientUtils.doPostForm("http://testfinance.51huihuahua.com/financevoice/voice/payOrderQuery",
				params);
		if (res != null) {
			Map maps = JSON.parseObject(res);
			if (maps.get("code").equals("200")) {
				res = JSON.parseObject(maps.get("data").toString()).get("tradeStatus").toString();
			}
		}
	}
}
