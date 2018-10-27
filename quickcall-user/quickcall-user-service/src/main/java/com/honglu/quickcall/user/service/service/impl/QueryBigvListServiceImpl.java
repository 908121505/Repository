package com.honglu.quickcall.user.service.service.impl;

import com.honglu.quickcall.account.facade.business.IAccountOrderService;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.ResultUtils;
import com.honglu.quickcall.common.constants.PropertiesConstant;
import com.honglu.quickcall.user.facade.entity.BigvSkillScore;
import com.honglu.quickcall.user.facade.entity.CustomerSkill;
import com.honglu.quickcall.user.facade.entity.ResourceConfig;
import com.honglu.quickcall.user.facade.entity.SkillItem;
import com.honglu.quickcall.user.facade.entity.example.BigvScoreExample;
import com.honglu.quickcall.user.facade.exchange.request.FirstPageBigvListRequest;
import com.honglu.quickcall.user.facade.vo.AppHomeBigvListVO;
import com.honglu.quickcall.user.service.dao.*;
import com.honglu.quickcall.user.service.service.QueryBigvListService;
import com.honglu.quickcall.user.service.util.CountAge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 查询大V列表Service实现类
 *
 * @author duanjun
 * @date 2018-10-25 14:24
 */
@Service
public class QueryBigvListServiceImpl implements QueryBigvListService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerRedisManagementImpl.class);

    @Autowired
    private SkillItemMapper skillItemMapper;
    @Autowired
    private CustomerSkillMapper customerSkillMapper;
    @Autowired
    private CustomerAppearanceMapper customerAppearanceMapper;
    @Autowired
    private ResourceConfigMapper resourceConfigMapper;
    @Autowired
    private IAccountOrderService accountOrderService;
    @Autowired
    private BigvScoreMapper bigvScoreMapper;
    @Autowired
    private BigvSkillScoreMapper bigvSkillScoreMapper;

    @Override
    public CommonResponse queryHomeBigvList(FirstPageBigvListRequest request) {
        List<AppHomeBigvListVO> resultList = new LinkedList<>();

        // 查询首页6帧资源位数据
        AppHomeBigvListVO recomedBigv = new AppHomeBigvListVO();
        recomedBigv.setSkillItemName("推荐大V");
        List<CustomerSkill> customerSkills = customerSkillMapper.selectAuditedSkillByPage(null, 0, 6);
        resultList.add(getBigvList(recomedBigv, customerSkills));

        // 查询所有分类
        List<SkillItem> skillList = skillItemMapper.selectAllSkill();
        if (skillList == null || skillList.size() == 0) {
            return ResultUtils.resultSuccess(resultList);
        }
        // 循环封装数据
        for (SkillItem skillItem : skillList) {
            // 查询数据
            List<CustomerSkill> customerSkillList = customerSkillMapper.selectAuditedSkillByPage(skillItem.getId(), 0, 4);
            if (customerSkillList.size() == 0) {
                continue;
            }
            AppHomeBigvListVO bigvListVO = new AppHomeBigvListVO();
            bigvListVO.setSkillItemName(skillItem.getSkillItemName());
            bigvListVO.setSkillItemId(skillItem.getId());

            resultList.add(getBigvList(bigvListVO, customerSkillList));
        }

        return ResultUtils.resultSuccess(resultList);
    }

    /**
     * 查询首页6针资源位大V列表
     *
     * @param customerSkills
     * @return
     */
    private AppHomeBigvListVO getBigvList(AppHomeBigvListVO recomedBigv, List<CustomerSkill> customerSkills) {
        List<AppHomeBigvListVO.BigvInfoVO> recomedBigvList = new ArrayList<>();
        // 封装数据
        for (CustomerSkill customerSkill : customerSkills) {
            AppHomeBigvListVO.BigvInfoVO bigv = recomedBigv.new BigvInfoVO();
            bigv.setCustomerId(customerSkill.getCustomerId());
            bigv.setCustomerSkillId(customerSkill.getCustomerSkillId());
            bigv.setSkillBackColor(customerSkill.getSkillHomeBackColor());
            bigv.setNickName(customerSkill.getCustomerNickName());
            bigv.setSex(customerSkill.getCustomerSex());
            if (customerSkill.getCustomerBirthday() != null) {
                bigv.setAge(CountAge.getAgeByBirth(customerSkill.getCustomerBirthday()));
            }
            bigv.setPrice(customerSkill.getDiscountPrice());
            bigv.setUnitName(customerSkill.getServiceUnit());
            bigv.setSkillItemName(customerSkill.getSkillName());
            bigv.setVoiceTime(customerSkill.getSkillVoiceTime());
            bigv.setVoiceUrl(customerSkill.getSkillVoiceUrl());
            // 查询第一张形象照
            List<String> appearanceList = customerAppearanceMapper.queryCustomerAuditedAppearance(customerSkill.getCustomerId(), 0);
            bigv.setCoverUrl(appearanceList.isEmpty() ? PropertiesConstant.DEFAULT_CUSTOMER_APPEARANCE_URL : appearanceList.get(0));

            recomedBigvList.add(bigv);
        }

        recomedBigv.setDaVinfoList(recomedBigvList);
        return recomedBigv;
    }


    public CommonResponse queryBigvList() {
        // 查询出资源位的配置信息
        List<ResourceConfig> configs = resourceConfigMapper.selectAllResourceConfig();
        if (configs == null || configs.size() == 0) {
            return ResultUtils.resultDataNotExist("资源位数据未配置");
        }

        // 查询有效大V总数
        int bigvCount = bigvScoreMapper.countValidBigvCount();

        // 循环资源位查询数据
        for (ResourceConfig config : configs) {
            // 自然推荐
            if (Objects.equals(config.getStrategy(), 1)) {
                // 得到随机大V
                Long customerId = getRandomBigv(bigvCount, config);

            }
            // 运营推荐
            else {

            }
        }


        return null;
    }

    /**
     * 得到随机的大V
     *
     * @param bigvCount
     * @param config
     * @return
     */
    private Long getRandomBigv(int bigvCount, ResourceConfig config) {
        // 获取随机大V的定位置
        int point = getRandomBigvPoint(bigvCount, config.getConfigNum());

        // 判断大V是否可接单
//        accountOrderService.checkReceiveOrderByCustomerSkillId(bean.getCustomerSkillId());

        return 111L;
    }

    /**
     * 得到随机大V的定位置
     *
     * @param bigvCount
     * @param configNum
     * @return
     */
    private int getRandomBigvPoint(int bigvCount, Integer configNum) {

        return 0;
    }
}
