package com.honglu.quickcall.user.service.service.impl;

import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.ResultUtils;
import com.honglu.quickcall.common.constants.PropertiesConstant;
import com.honglu.quickcall.user.facade.entity.CustomerSkill;
import com.honglu.quickcall.user.facade.entity.SkillItem;
import com.honglu.quickcall.user.facade.exchange.request.FirstPageBigvListRequest;
import com.honglu.quickcall.user.facade.vo.AppHomeBigvListVO;
import com.honglu.quickcall.user.service.dao.CustomerAppearanceMapper;
import com.honglu.quickcall.user.service.dao.CustomerSkillMapper;
import com.honglu.quickcall.user.service.dao.SkillItemMapper;
import com.honglu.quickcall.user.service.service.QueryHomeBigvListService;
import com.honglu.quickcall.user.service.util.CountAge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 查询首页大V列表Service实现类
 *
 * @author duanjun
 * @date 2018-10-25 14:24
 */
@Service
public class QueryHomeBigvListServiceImpl implements QueryHomeBigvListService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerRedisManagementImpl.class);

    @Autowired
    private SkillItemMapper skillItemMapper;
    @Autowired
    private CustomerSkillMapper customerSkillMapper;
    @Autowired
    private CustomerAppearanceMapper customerAppearanceMapper;

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
            List<CustomerSkill> customerSkillList = customerSkillMapper.selectAuditedSkillByPage(skillItem.getId(), 0, skillList.size() * 4);
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
            bigv.setSkillBackColor(customerSkill.getSkillBackColor());
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
}
