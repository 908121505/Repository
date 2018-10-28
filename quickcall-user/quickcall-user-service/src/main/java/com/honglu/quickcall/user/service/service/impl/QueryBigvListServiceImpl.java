package com.honglu.quickcall.user.service.service.impl;

import com.honglu.quickcall.account.facade.business.IAccountOrderService;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.ResultUtils;
import com.honglu.quickcall.common.api.util.DateUtils;
import com.honglu.quickcall.common.constants.PropertiesConstant;
import com.honglu.quickcall.user.facade.entity.BigvSkillScore;
import com.honglu.quickcall.user.facade.entity.CustomerSkill;
import com.honglu.quickcall.user.facade.entity.ResourceConfig;
import com.honglu.quickcall.user.facade.entity.SkillItem;
import com.honglu.quickcall.user.facade.exchange.request.DaVListBySkillItemIdRequest;
import com.honglu.quickcall.user.facade.exchange.request.FirstPageBigvListRequest;
import com.honglu.quickcall.user.facade.vo.AppHomeBigvListVO;
import com.honglu.quickcall.user.facade.vo.DaVinfoVO;
import com.honglu.quickcall.user.service.dao.*;
import com.honglu.quickcall.user.service.service.QueryBigvListService;
import com.honglu.quickcall.user.service.util.CountAge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        List<SkillItem> skillList = skillItemMapper.selectAllEnabledSkills();
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
            // 查询第一张形象照 性别(0=女,1=男)
            List<String> appearanceList = customerAppearanceMapper.queryCustomerAuditedAppearance(customerSkill.getCustomerId(), 0);
            if (appearanceList == null || appearanceList.size() == 0) {
                if (Objects.equals(bigv.getSex(), 1)) {
                    bigv.setCoverUrl(PropertiesConstant.DEFAULT_CUSTOMER_APPEARANCE_URL_BOY);
                } else {
                    bigv.setCoverUrl(PropertiesConstant.DEFAULT_CUSTOMER_APPEARANCE_URL_GIRL);
                }
            } else {
                bigv.setCoverUrl(appearanceList.get(0));
            }

            recomedBigvList.add(bigv);
        }

        recomedBigv.setDaVinfoList(recomedBigvList);
        return recomedBigv;
    }


    public CommonResponse queryHomeBigvList() {
        List<AppHomeBigvListVO> resultList = new LinkedList<>();

        /****** 查询首页6帧资源位数据*****/
        AppHomeBigvListVO recomedBigv = new AppHomeBigvListVO();
        recomedBigv.setSkillItemName("推荐大V");
        List<AppHomeBigvListVO.BigvInfoVO> bigvList = queryConfigBigvList();
        if (bigvList != null && bigvList.size() > 0) {
            recomedBigv.setDaVinfoList(bigvList);
            resultList.add(recomedBigv);
        }

        /******** 查询分类页数据***********/
        // 查询所有分类
        List<SkillItem> skillList = skillItemMapper.selectAllEnabledSkills();


        return ResultUtils.resultSuccess(resultList);
    }


    /**
     * 存储客户技能ID -- 用户去重
     **/
    private List<String> customerSkillId = new ArrayList<>();

    /**
     * 获取首页6帧配置位数据大V列表
     *
     * @return
     */
    private List<AppHomeBigvListVO.BigvInfoVO> queryConfigBigvList() {
        // 查询出资源位的配置信息
        List<ResourceConfig> configs = resourceConfigMapper.selectAllResourceConfig();
        if (configs == null || configs.size() == 0) {
            LOGGER.warn("资源位数据未配置 -- 前端不显示资源位");
            return null;
        }

        // 6帧资源位客户Map
        Map<Integer, Long> customerIdMap = new LinkedHashMap<>();

        // 循环资源位查询数据
        for (ResourceConfig config : configs) {
            // 查询出资源配置启用的品类
            List<Long> configSkills = resourceConfigMapper.selectResourceEnableSkills(config.getResourceConfigId());
            if (configSkills == null || configSkills.size() == 0) {
                LOGGER.warn("6帧资源配置位【{}】,未查询到启用的技能品类 -- 跳过该资源位", config.getConfigNum());
                continue;
            }

            // 自然推荐
            if (Objects.equals(config.getStrategy(), 1)) {
                // 得到随机大V
                customerIdMap.put(config.getConfigNum(), getRandomBigv(config, configSkills, customerIdMap));
            }
            // 运营推荐
            else {

            }
        }

        // 查询有效大V总数
        int bigvCount = bigvScoreMapper.countValidBigvCount();

        // 查询资源位所有启用的技能
        List<String> configAllSkills = resourceConfigMapper.selectAllEnableSkills();

        // 查询所有的技能分内列表
        List<SkillItem> skillList = skillItemMapper.selectAllEnabledSkills();

        // 查询大V + 技能综合排名表
//        List<CustomerSkill> bigvList = resourceConfigMapper.selectEnabledBigvAndSkillRankData(skillList.size() * 4 + 6);


        return null;
    }

    /**
     * 得到随机的大V
     *
     * @param config
     * @param configSkills
     * @param customerIdMap
     * @return
     */
    private Long getRandomBigv(ResourceConfig config, List<Long> configSkills, Map<Integer, Long> customerIdMap) {
        Integer weekIndex = DateUtils.getDayOfWeek();
        String endTimeStr = DateUtils.formatDateHHSS(new Date()).replaceAll(":", "");

        List<Long> customerIds = new ArrayList<>();
        for (Long customerId : customerIdMap.values()) {
            customerIds.add(customerId);
        }
        if(customerIds.size() == 0){
            customerIds = null;
        }

        // 查询满足条件的大V数量 -- 用于统计百分比 -- 条件：可接单 && 未被下单
        int bigvNum = resourceConfigMapper.countEnabledBigvAndSkillRankData(configSkills, customerIds, weekIndex, endTimeStr);

        // 查询该资源配置的大V列表排名
        List<BigvSkillScore> bigvList = resourceConfigMapper.selectEnabledBigvAndSkillRankData(configSkills, customerIds, weekIndex, endTimeStr);
        if (bigvList == null && bigvList.size() == 0) {
            LOGGER.warn("资源位【{}】未查询到可用的大V数据 -- 跳过不显示该资源位", config.getConfigNum());
            return null;
        }
        if (bigvList.size() == 1) {
            return bigvList.get(0).getCustomerId();
        }


        // 获取未被预约大V的总数
        Long randomCustomerSkillId = getRandomCustomerSkillId(bigvList, config.getConfigNum());


        // 获取随机大V的定位置
        int point = getRandomBigvPoint(config.getConfigNum());

        // 判断大V是否可接单
//        accountOrderService.checkReceiveOrderByCustomerSkillId(bean.getCustomerSkillId());

        return 111L;
    }

    /**
     * 获取一个随机的客户技能项ID
     *
     * @param bigvList
     * @param configNum
     * @return
     */
    private Long getRandomCustomerSkillId(List<BigvSkillScore> bigvList, Integer configNum) {
        int startIndex = 0;
        int endIndex = bigvList.size();

        // 1-2号资源位使用排序列表前30%的声优内容，3-4号资源位使用30%-60%的声优内容，剩余内容由5-6号资源位使用
        if (configNum == 1 || configNum == 2) {
            endIndex = (int) Math.floor(bigvList.size() * 0.3);
        }

        // 获取未被预约的大V总数
//      int notOrderedNum = getNotOrderedNum(bigvList);

        return null;
    }

    public static void main(String[] args) {
        System.out.println(Math.floor(3 * 0.3));
    }


    /**
     * @param configNum
     * @return
     */
    private int getRandomRange(Integer configNum) {
        if (configNum == 1 || configNum == 2) {
        }
        return 0;
    }

    /**
     * 得到随机大V的定位置
     *
     * @param configNum
     * @return
     */
    private int getRandomBigvPoint(Integer configNum) {

        return 0;
    }

    //获取分类大V列表
    @Override
    public CommonResponse queryClassifyBigvList(DaVListBySkillItemIdRequest request) {
        Long skillItemId = request.getSkillItemId();
        Integer pageIndex = request.getPageIndex();
        Integer pageSize = request.getPageSize();
        Integer start = null;
        if (pageIndex != null && pageSize != null) {
            start = pageIndex * pageSize;
        }
        List<DaVinfoVO> daVinfoVOList = customerSkillMapper.queryCustomerListBySkillItem(skillItemId, start, pageSize);
        for (DaVinfoVO daVinfoVO : daVinfoVOList) {
            // 查询第一张形象照 性别(0=女,1=男)
            List<String> appearanceList = customerAppearanceMapper.queryCustomerAuditedAppearance(daVinfoVO.getCustomerId(), 0);
            if (appearanceList == null || appearanceList.size() == 0) {
                if (Objects.equals(daVinfoVO.getSex(), 1)) {
                    daVinfoVO.setCoverUrl(PropertiesConstant.DEFAULT_CUSTOMER_APPEARANCE_URL_BOY);
                } else {
                    daVinfoVO.setCoverUrl(PropertiesConstant.DEFAULT_CUSTOMER_APPEARANCE_URL_GIRL);
                }
            } else {
                daVinfoVO.setCoverUrl(appearanceList.get(0));
            }
        }
        return ResultUtils.resultSuccess(daVinfoVOList);
    }
}
