package com.honglu.quickcall.user.service.service.impl;

import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.ResultUtils;
import com.honglu.quickcall.common.api.util.DateUtils;
import com.honglu.quickcall.common.constants.PropertiesConstant;
import com.honglu.quickcall.user.facade.entity.CustomerSkill;
import com.honglu.quickcall.user.facade.entity.ResourceConfig;
import com.honglu.quickcall.user.facade.entity.SkillItem;
import com.honglu.quickcall.user.facade.exchange.request.DaVListBySkillItemIdRequest;
import com.honglu.quickcall.user.facade.exchange.request.FirstPageBigvListRequest;
import com.honglu.quickcall.user.facade.vo.AppHomeBigvListVO;
import com.honglu.quickcall.user.facade.vo.DaVinfoVO;
import com.honglu.quickcall.user.service.dao.CustomerAppearanceMapper;
import com.honglu.quickcall.user.service.dao.CustomerSkillMapper;
import com.honglu.quickcall.user.service.dao.ResourceConfigMapper;
import com.honglu.quickcall.user.service.dao.SkillItemMapper;
import com.honglu.quickcall.user.service.service.QueryBigvListService;
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
    private CustomerAppearanceMapper customerAppearanceMapper;
    @Autowired
    private ResourceConfigMapper resourceConfigMapper;

    @Override
    public CommonResponse queryHomeBigvList(FirstPageBigvListRequest request) {
        List<AppHomeBigvListVO> resultList = new LinkedList<>();

        Integer weekIndex = DateUtils.getDayOfWeek();

        /****** 查询首页6帧资源位数据*****/
        AppHomeBigvListVO recomedBigv = new AppHomeBigvListVO();
        recomedBigv.setSkillItemName("推荐");
        // 默认100为推荐类别ID
        recomedBigv.setSkillItemId(100L);
        List<AppHomeBigvListVO.BigvInfoVO> bigvList = this.queryConfigBigvList(recomedBigv, weekIndex);
        if (bigvList != null && bigvList.size() > 0) {
            recomedBigv.setDaVinfoList(bigvList);
            resultList.add(recomedBigv);
        }

        /******** 查询分类页数据***********/
        // 查询所有分类
        List<SkillItem> skillList = skillItemMapper.selectAllEnabledSkills();
        // 循环封装数据
        for (SkillItem skillItem : skillList) {
            AppHomeBigvListVO bigvListVO = new AppHomeBigvListVO();
            bigvListVO.setSkillItemName(skillItem.getSkillItemName());
            bigvListVO.setSkillItemId(skillItem.getId());
            bigvListVO = this.querySkillItemTypeBigvList(bigvListVO, weekIndex);
            if (bigvListVO == null) {
                LOGGER.warn("首页查询数据 - 【{}】技能未查询到有效大V数据", skillItem.getSkillItemName());
                continue;
            }
            resultList.add(bigvListVO);
        }

        return ResultUtils.resultSuccess(resultList);
    }

    /**
     * 获取首页6帧配置位数据大V列表
     *
     * @param recomedBigv
     * @param weekIndex
     * @return
     */
    private List<AppHomeBigvListVO.BigvInfoVO> queryConfigBigvList(AppHomeBigvListVO recomedBigv, Integer weekIndex) {
        // 查询出资源位的配置信息
        List<ResourceConfig> configs = resourceConfigMapper.selectAllResourceConfig();
        if (configs.isEmpty()) {
            LOGGER.warn("【首页6帧】资源位数据未配置 -- 前端不显示推荐资源位");
            return null;
        }

        // 6帧资源位客户Map
        Map<Integer, CustomerSkill> customerIdMap = new LinkedHashMap<>();
        // 去重 -- 排除已经查询出的客户Id
        List<Long> exCustomerIds = new ArrayList<>();
        // 资源位配置的启用技能
        Map<Integer, List<Long>> configSkillsMap = new HashMap<>();
        // 资源位配置的排除的客户Id
        Map<Integer, List<Long>> resourceExCustomerIdsMap = new HashMap<>();
        // 配置的启用技能
        List<Long> configSkills;
        // 得到随机大V
        CustomerSkill bigv;
        // 循环资源位查询数据
        for (ResourceConfig config : configs) {
            // 查询出资源配置启用的品类
            configSkills = resourceConfigMapper.selectResourceEnableSkills(config.getResourceConfigId());
            if (configSkills.isEmpty()) {
                LOGGER.warn("【首页6帧】资源配置位【{}】,未配置启用的技能品类 -- 跳过该资源位", config.getConfigNum());
                continue;
            }
            configSkillsMap.put(config.getConfigNum(), configSkills);

            // 查询配置位排除的大V
            List<Long> resourceExCustomerIds = resourceConfigMapper.selectResourceExCustomerIds(config.getResourceConfigId());
            resourceExCustomerIdsMap.put(config.getConfigNum(), resourceExCustomerIds);

            // 加上已经被查询出来的客户
            resourceExCustomerIds.addAll(exCustomerIds);

            // 自然推荐
            if (Objects.equals(config.getStrategy(), 1)) {
                // 得到随机大V -- 排除被下单的大V
                bigv = this.getRandomBigv(config.getConfigNum(), configSkills, resourceExCustomerIds, weekIndex, 0);
                if (bigv == null) {
                    LOGGER.warn("【首页6帧】资源配置位【{} - 自然推荐】-【未被下单大V排名】- 未查询到可用的大V数据", config.getConfigNum());
                }
            }
            // 运营推荐
            else {
                // 从推荐池获取大V -- 排除被下单的大V
                bigv = resourceConfigMapper.selectRandomBigvFromResourcePool(config.getResourcePoolId(), configSkills, exCustomerIds, weekIndex, 0);
                if (bigv == null) {
                    LOGGER.warn("【首页6帧】资源配置位【{} - 运营推荐】-【未被下单大V排名】-【资源池】- 未查询到可用的大V数据 - 切换到自然推荐", config.getConfigNum());
                    // 推荐池选取不到切换到自然推荐
                    bigv = this.getRandomBigv(config.getConfigNum(), configSkills, resourceExCustomerIds, weekIndex, 0);
                    // 若自然推荐也找不到 -- 再取消下单状态的限制
                    if (bigv == null) {
                        LOGGER.warn("资源位【{} - 运营推荐】-【未被下单】 - 切换到自然推荐后，又未查询到可用的大V数据，取消未被下单的限制再次从资源池中查询", config.getConfigNum());
                        bigv = resourceConfigMapper.selectRandomBigvFromResourcePool(config.getResourcePoolId(), configSkills, exCustomerIds, weekIndex, null);
                    }
                }
            }

            // 加入排除列表
            if (bigv != null) {
                exCustomerIds.add(bigv.getCustomerId());
            }

            customerIdMap.put(config.getConfigNum(), bigv);
        }

        // 返回前端的大V列表
        List<AppHomeBigvListVO.BigvInfoVO> bigvList = new ArrayList<>();

        AppHomeBigvListVO.BigvInfoVO bigvInfoVo;
        // 再次循环查询过的资源位 -- 补充未查询到大V的资源位 + 最后循环获取基础数据
        for (Integer configNum : customerIdMap.keySet()) {
            bigvInfoVo = null;
            if (customerIdMap.get(configNum) != null) {
                bigvInfoVo = packetBigvInfo(recomedBigv, customerIdMap.get(configNum));
            } else {
                // 获取被排除的客户
                List<Long> resourceExCustomerIds = resourceExCustomerIdsMap.get(configNum);
                resourceExCustomerIds.addAll(exCustomerIds);

                // 若该资源位未查询到数据 -- 则取消技能被下单的限制 -- 再次全部用自然推荐算法查询数据
                bigv = getRandomBigv(configNum, configSkillsMap.get(configNum), resourceExCustomerIds, weekIndex, null);
                if (bigv != null) {
                    // 加入排除列表
                    exCustomerIds.add(bigv.getCustomerId());
                    bigvInfoVo = packetBigvInfo(recomedBigv, bigv);
                }
            }
            if (bigvInfoVo == null) {
                LOGGER.warn("资源位【{}】-最终未查询到大V数据 - 该资源位只能不显示数据了", configNum);
                continue;
            }
            bigvList.add(bigvInfoVo);
        }

        return bigvList;
    }

    /**
     * 查询分类页的现实的大V数据
     *
     * @param bigvListVO
     * @param weekIndex
     * @return
     */
    private AppHomeBigvListVO querySkillItemTypeBigvList(AppHomeBigvListVO bigvListVO, Integer weekIndex) {
        // 根据大V排名查询到数据
        List<CustomerSkill> customerSkillList = resourceConfigMapper.selectRankBigvListBySkillItemId(bigvListVO.getSkillItemId(), weekIndex, 0, 4);
        if (customerSkillList.isEmpty()) {
            return null;
        }
        bigvListVO.setDaVinfoList(packetCustomerSkillList(bigvListVO, customerSkillList));
        return bigvListVO;
    }

    private List<AppHomeBigvListVO.BigvInfoVO> packetCustomerSkillList(AppHomeBigvListVO recomedBigv, List<CustomerSkill> customerSkills) {
        List<AppHomeBigvListVO.BigvInfoVO> recomedBigvList = new ArrayList<>();
        // 封装数据
        for (CustomerSkill customerSkill : customerSkills) {
            recomedBigvList.add(packetBigvInfo(recomedBigv, customerSkill));
        }
        return recomedBigvList;
    }

    /**
     * 得到随机的大V
     *
     * @param configNum
     * @param configSkills
     * @param exCustomerIds
     * @param weekIndex
     * @param skillOrdered  技能是否被【下单】暂用：0=否，其他=不限制
     * @return
     */
    private CustomerSkill getRandomBigv(Integer configNum, List<Long> configSkills, List<Long> exCustomerIds, Integer weekIndex, Integer skillOrdered) {
        // 查询满足条件的大V数量 -- 用于统计百分比 -- 条件：可接单 && 未被下单
        int bigvNum = resourceConfigMapper.countEnabledBigvBySkillRank(configSkills, exCustomerIds, weekIndex, skillOrdered);
        if (bigvNum == 0) {
            return null;
        }

        // 计算百分比
        int beginIndex = cacluRandomLimitBeginIndex(configNum, bigvNum);
        int endIndex = cacluRandomLimitEndIndex(configNum, bigvNum) - beginIndex;

        // 随机根据大V排名查询一条数据
        return resourceConfigMapper.selectEnabledBigvBySkillRank(configSkills, exCustomerIds, weekIndex, beginIndex, endIndex, skillOrdered);
    }

    /**
     * 打包大V信息
     *
     * @param recomedBigv
     * @param customerSkill
     */
    private AppHomeBigvListVO.BigvInfoVO packetBigvInfo(AppHomeBigvListVO recomedBigv, CustomerSkill customerSkill) {
        AppHomeBigvListVO.BigvInfoVO bigv = recomedBigv.new BigvInfoVO();
        bigv.setCustomerId(customerSkill.getCustomerId());
        bigv.setCustomerSkillId(customerSkill.getCustomerSkillId());
        bigv.setSkillBackColor(customerSkill.getSkillHomeBackColor());
        bigv.setNickName(customerSkill.getCustomerNickName());
        bigv.setSex(customerSkill.getCustomerSex());
        if (customerSkill.getCustomerBirthday() != null) {
            bigv.setAge(DateUtils.getAgeByBirthYear(customerSkill.getCustomerBirthday()));
        }
        bigv.setPrice(customerSkill.getDiscountPrice());
        bigv.setUnitName(customerSkill.getServiceUnit());
        bigv.setSkillItemName(customerSkill.getSkillName());
        bigv.setVoiceTime(customerSkill.getSkillVoiceTime());
        bigv.setVoiceUrl(customerSkill.getSkillVoiceUrl());
        // 查询第一张形象照 性别(0=女,1=男)
        List<String> appearanceList = customerAppearanceMapper.queryCustomerAppearance(customerSkill.getCustomerId(), 0, 1);
        if (appearanceList.isEmpty()) {
            bigv.setCoverUrl(Objects.equals(bigv.getSex(), 1)
                    ? PropertiesConstant.DEFAULT_CUSTOMER_APPEARANCE_URL_BOY
                    : PropertiesConstant.DEFAULT_CUSTOMER_APPEARANCE_URL_GIRL);
        } else {
            bigv.setCoverUrl(appearanceList.get(0));
        }
        return bigv;
    }

    private static int cacluRandomLimitBeginIndex(Integer configNum, int bigvNum) {
        // 1-2号资源位使用排序列表前30%的声优内容，3-4号资源位使用30%-60%的声优内容，剩余内容由5-6号资源位使用
        if (configNum == 1 || configNum == 2) {
            return 0;
        }

        if (configNum == 3 || configNum == 4) {
            return (int) Math.floor(bigvNum * 0.3);
        }

        return (int) Math.floor(bigvNum * 0.6);
    }

    private static int cacluRandomLimitEndIndex(Integer configNum, int bigvNum) {
        // 1-2号资源位使用排序列表前30%的声优内容，3-4号资源位使用30%-60%的声优内容，剩余内容由5-6号资源位使用
        if (configNum == 1 || configNum == 2) {
            return (int) Math.ceil(bigvNum * 0.3);
        }

        if (configNum == 3 || configNum == 4) {
            return (int) Math.ceil(bigvNum * 0.6);
        }

        return bigvNum;
    }

    @Override
    public CommonResponse queryClassifyBigvList(DaVListBySkillItemIdRequest request) {
        Long skillItemId = request.getSkillItemId();
        Integer pageIndex = request.getPageIndex();
        Integer pageSize = request.getPageSize();
        Integer start = null;
        //分页参数
        if (pageIndex != null && pageSize != null) {
            start = pageIndex * pageSize;
        }else{
    	//如果不分页只显示100条防止前后台内存报警
        	start = 0;
        	pageSize = 100;
        }
        Integer weekIndex = DateUtils.getDayOfWeek();
        List<DaVinfoVO> daVinfoVOList = new ArrayList<>();
		List<CustomerSkill> customerSkillList = resourceConfigMapper.selectRankBigvListBySkillItemId(skillItemId, weekIndex, start, pageSize);
		//对象转换
        for (CustomerSkill customerSkill : customerSkillList) {
        	DaVinfoVO daVinfoVO = new DaVinfoVO();
        	daVinfoVO.setCustomerId(customerSkill.getCustomerId());
        	daVinfoVO.setAge(DateUtils.getAgeByBirthYear(customerSkill.getCustomerBirthday()));
        	daVinfoVO.setCustomerSkillId(customerSkill.getCustomerSkillId());
        	daVinfoVO.setSkillBackColor(customerSkill.getSkillHomeBackColor());
        	daVinfoVO.setPrice(customerSkill.getDiscountPrice());
        	daVinfoVO.setUnitName(customerSkill.getServiceUnit());
        	daVinfoVO.setVoiceUrl(customerSkill.getSkillVoiceUrl());
        	daVinfoVO.setVoiceTime(customerSkill.getSkillVoiceTime());
        	daVinfoVO.setSkillItemName(customerSkill.getSkillName());
        	daVinfoVO.setNickName(customerSkill.getCustomerNickName());
        	daVinfoVO.setSex(customerSkill.getCustomerSex());
            // 查询第一张形象照 性别(0=女,1=男)
            List<String> appearanceList = customerAppearanceMapper.queryCustomerAppearance(daVinfoVO.getCustomerId(), 0, 1);
            if (appearanceList == null || appearanceList.size() == 0) {
                if (Objects.equals(daVinfoVO.getSex(), 1)) {
                    daVinfoVO.setCoverUrl(PropertiesConstant.DEFAULT_CUSTOMER_APPEARANCE_URL_BOY);
                } else {
                    daVinfoVO.setCoverUrl(PropertiesConstant.DEFAULT_CUSTOMER_APPEARANCE_URL_GIRL);
                }
            } else {
                daVinfoVO.setCoverUrl(appearanceList.get(0));
            }
            daVinfoVOList.add(daVinfoVO);
        }
        return ResultUtils.resultSuccess(daVinfoVOList);
    }

}
