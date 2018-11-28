package com.honglu.quickcall.account.test;

import com.honglu.quickcall.account.service.bussService.BarrageMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.honglu.quickcall.common.api.util.HttpClientUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by len.song on 2018-02-07.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class BarrageMessageTest {
    @Autowired
    private BarrageMessageService barrageMessageService;


	@Test
	public void lpushMessageTest() {
		barrageMessageService.lpushMessage(1810252051103631364L);
		barrageMessageService.lpushMessage(1810252051103631364L);
		barrageMessageService.lpushMessage(1810252136459163176L);
		barrageMessageService.lpushMessage(1810252136459163176L);
		barrageMessageService.lpushMessage(1810252155095321673L);
		barrageMessageService.lpushMessage(1810252155095321673L);
		barrageMessageService.lpushMessage(1810252155095321673L);
		barrageMessageService.lpushMessage(1810252155095321673L);
		barrageMessageService.lpushMessage(1810252155095321673L);
		barrageMessageService.lpushMessage(1810252155095321673L);
		barrageMessageService.lpushMessage(1810252155095321673L);
		barrageMessageService.lpushMessage(1810252155095321673L);
		barrageMessageService.lpushMessage(1810252155095321673L);
		barrageMessageService.lpushMessage(1810252155095321673L);
		barrageMessageService.lpushMessage(1810252155095321673L);
		barrageMessageService.lpushMessage(1810252155095321673L);
		barrageMessageService.lpushMessage(1810252155095321673L);
	}

	@Test
	public void whoTest() {
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
