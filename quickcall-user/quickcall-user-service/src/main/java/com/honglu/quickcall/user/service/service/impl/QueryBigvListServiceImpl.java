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

/*

    @Override
    public CommonResponse queryHomeBigvList(FirstPageBigvListRequest request) {
        List<AppHomeBigvListVO> resultList = new LinkedList<>();

        // 查询首页6帧资源位数据
        AppHomeBigvListVO recomedBigv = new AppHomeBigvListVO();
        recomedBigv.setSkillItemName("推荐大V");
        List<CustomerSkill> customerSkills = customerSkillMapper.selectAuditedSkillByPage(null, 0, 6);
        packetCustomerSkillList(recomedBigv, customerSkills);
        resultList.add(recomedBigv);

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

            // 封装技能列表
            packetCustomerSkillList(bigvListVO, customerSkillList);
        }

        return ResultUtils.resultSuccess(resultList);
    }
*/

    /**
     * 查询首页6针资源位大V列表
     *
     * @param customerSkills
     * @return
     */
    private void packetCustomerSkillList(AppHomeBigvListVO recomedBigv, List<CustomerSkill> customerSkills) {
        List<AppHomeBigvListVO.BigvInfoVO> recomedBigvList = new ArrayList<>();
        // 封装数据
        for (CustomerSkill customerSkill : customerSkills) {
            AppHomeBigvListVO.BigvInfoVO bigv = recomedBigv.new BigvInfoVO();
            packetBigvInfo(bigv, customerSkill);
            recomedBigvList.add(bigv);
        }

        recomedBigv.setDaVinfoList(recomedBigvList);
    }


    @Override
    public CommonResponse queryHomeBigvList(FirstPageBigvListRequest request) {
        List<AppHomeBigvListVO> resultList = new LinkedList<>();

        Integer weekIndex = DateUtils.getDayOfWeek();
        String endTimeStr = DateUtils.formatDateHHSS(new Date()).replaceAll(":", "");

        /****** 查询首页6帧资源位数据*****/
        AppHomeBigvListVO recomedBigv = new AppHomeBigvListVO();
        recomedBigv.setSkillItemName("推荐大V");
        List<AppHomeBigvListVO.BigvInfoVO> bigvList = queryConfigBigvList(recomedBigv, weekIndex, endTimeStr);
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
            bigvListVO = querySkillItemTypeBigvList(bigvListVO, weekIndex, endTimeStr);
            if(bigvListVO == null){
                LOGGER.warn("首页查询数据 - 【{}】技能未查询到有效大V数据", skillItem.getSkillItemName());
                continue;
            }
            resultList.add(bigvListVO);
        }

        return ResultUtils.resultSuccess(resultList);
    }

    /**
     * 查询分类页的现实的大V数据
     *
     * @param bigvListVO
     * @param weekIndex
     * @param endTimeStr
     * @return
     */
    private AppHomeBigvListVO querySkillItemTypeBigvList(AppHomeBigvListVO bigvListVO, Integer weekIndex, String endTimeStr) {
        // 根据大V排名查询到数据
        List<CustomerSkill> customerSkillList = resourceConfigMapper.selectRankBigvListBySkillItemId(bigvListVO.getSkillItemId(), weekIndex, endTimeStr);
        if(customerSkillList == null || customerSkillList.size() == 0){
            return null;
        }
        List<AppHomeBigvListVO.BigvInfoVO> bigvList = new ArrayList<>();

        packetCustomerSkillList(bigvListVO, customerSkillList);

        bigvListVO.setDaVinfoList(bigvList);
        return bigvListVO;
    }

    /**
     * 获取首页6帧配置位数据大V列表
     *
     * @param recomedBigv
     * @param weekIndex
     * @param endTimeStr
     * @return
     */
    private List<AppHomeBigvListVO.BigvInfoVO> queryConfigBigvList(AppHomeBigvListVO recomedBigv, Integer weekIndex, String endTimeStr) {
        // 查询出资源位的配置信息
        List<ResourceConfig> configs = resourceConfigMapper.selectAllResourceConfig();
        if (configs == null || configs.size() == 0) {
            LOGGER.warn("资源位数据未配置 -- 前端不显示资源位");
            return null;
        }

        // 6帧资源位客户Map
        Map<Integer, BigvSkillScore> customerIdMap = new LinkedHashMap<>();

        List<Long> exCustomerIds = new ArrayList<>();

        Map<Integer, List<Long>> configSkillsMap = new HashMap<>();
        Map<Integer, List<Long>> resourceExCustomerIdsMap = new HashMap<>();

        // 循环资源位查询数据
        for (ResourceConfig config : configs) {
            // 查询出资源配置启用的品类
            List<Long> configSkills = resourceConfigMapper.selectResourceEnableSkills(config.getResourceConfigId());
            if (configSkills == null || configSkills.size() == 0) {
                LOGGER.warn("6帧资源配置位【{}】,未查询到启用的技能品类 -- 跳过该资源位", config.getConfigNum());
                continue;
            }
            configSkillsMap.put(config.getConfigNum(), configSkills);

            // 查询配置位排除的大V
            List<Long> resourceExCustomerIds = resourceConfigMapper.selectExCustomerIds(config.getResourceConfigId());
            resourceExCustomerIdsMap.put(config.getConfigNum(), resourceExCustomerIds);

            // 加上已经被查询出来的客户
            resourceExCustomerIds.addAll(exCustomerIds);

            // 得到随机大V
            BigvSkillScore bigv;
            // 自然推荐
            if (Objects.equals(config.getStrategy(), 1)) {
                // 得到随机大V
                bigv = getRandomBigv(config.getConfigNum(), configSkills, resourceExCustomerIds, weekIndex, endTimeStr, 0);
                if (bigv == null) {
                    LOGGER.warn("资源位【{} - 自然推荐】-【未被下单】- 未查询到可用的大V数据", config.getConfigNum());
                }
                customerIdMap.put(config.getConfigNum(), bigv);
            }
            // 运营推荐
            else {
                // 从推荐池获取大V
                bigv = getRandomBigvFromPool(config, configSkills, resourceExCustomerIds, weekIndex, endTimeStr, 0);
                if (bigv == null) {
                    LOGGER.warn("资源位【{} - 运营推荐】-【未被下单】-【资源池】- 未查询到可用的大V数据 - 切换到自然推荐", config.getConfigNum());
                    // 推荐池选取不到切换到自然推荐
                    bigv = getRandomBigv(config.getConfigNum(), configSkills, resourceExCustomerIds, weekIndex, endTimeStr, 0);
                    // 若自然推荐也找不到 -- 再取消下单状态的限制
                    if (bigv == null) {
                        LOGGER.warn("资源位【{} - 运营推荐】-【未被下单】 - 切换到自然推荐后，又未查询到可用的大V数据，取消未被下单的限制再次从资源池中查询", config.getConfigNum());
                        bigv = getRandomBigvFromPool(config, configSkills, resourceExCustomerIds, weekIndex, endTimeStr, null);
                    }
                }
            }

            // 加入排除列表
            if (bigv != null) {
                exCustomerIds.add(customerIdMap.get(config.getConfigNum()).getCustomerId());
            }

            customerIdMap.put(config.getConfigNum(), bigv);
        }

        // 返回前端的大V列表
        List<AppHomeBigvListVO.BigvInfoVO> bigvList = new ArrayList<>();

        // 再次循环查询过的资源位 -- 补充未查询到大V的资源位 + 最后循环获取基础数据
        for (Integer configNum : customerIdMap.keySet()) {
            AppHomeBigvListVO.BigvInfoVO bigvInfoVo = null;
            if (customerIdMap.get(configNum) != null) {
                bigvInfoVo = getBigvByCustomerSkillId(recomedBigv, customerIdMap.get(configNum).getCustomerSkillId());
            } else {
                // 获取被排除的客户
                List<Long> resourceExCustomerIds = resourceExCustomerIdsMap.get(configNum);
                resourceExCustomerIds.addAll(exCustomerIds);

                // 若该资源位未查询到数据 -- 则取消技能被下单的限制 -- 再次全部用自然推荐算法查询数据
                BigvSkillScore bigv = getRandomBigv(configNum, configSkillsMap.get(configNum), resourceExCustomerIds, weekIndex, endTimeStr, null);
                if (bigv != null) {
                    // 加入排除列表
                    exCustomerIds.add(bigv.getCustomerId());
                    bigvInfoVo = getBigvByCustomerSkillId(recomedBigv, customerIdMap.get(configNum).getCustomerSkillId());
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
     * 得到随机的大V
     *
     * @param configNum
     * @param configSkills
     * @param exCustomerIds
     * @param weekIndex
     * @param endTimeStr
     * @param skillOrdered  技能是否被【下单】暂用：0=否，其他=不限制
     * @return
     */
    private BigvSkillScore getRandomBigv(Integer configNum, List<Long> configSkills, List<Long> exCustomerIds, Integer weekIndex, String endTimeStr, Integer skillOrdered) {
        // 查询满足条件的大V数量 -- 用于统计百分比 -- 条件：可接单 && 未被下单
        int bigvNum = resourceConfigMapper.countEnabledBigvAndSkillRankData(configSkills, exCustomerIds, weekIndex, endTimeStr, skillOrdered);
        if (bigvNum == 0) {
            return null;
        }

        // 计算百分比
        int beginIndex = cacluRandomLimitBeginIndex(bigvNum, configNum);
        int endIndex = cacluRandomLimitEndIndex(bigvNum, configNum) - beginIndex;

        // 随机根据大V排名查询一条数据
        BigvSkillScore bigv = resourceConfigMapper.selectEnabledBigvAndSkillRankData(configSkills, exCustomerIds, weekIndex, endTimeStr, beginIndex, endIndex, skillOrdered);
        return bigv;
    }

    /**
     * 从推荐池获取大V
     *
     * @param config
     * @param configSkills
     * @param exCustomerIds
     * @param weekIndex
     * @param endTimeStr
     * @param skillOrdered  技能是否被【下单】暂用：0=否，其他=不限制
     * @return
     */
    private BigvSkillScore getRandomBigvFromPool(ResourceConfig config, List<Long> configSkills, List<Long> exCustomerIds, Integer weekIndex, String endTimeStr, Integer skillOrdered) {
        // 随机从推荐池满足条件的大V -- 用于统计百分比 -- 条件：可接单 && 未被下单
        BigvSkillScore bigv = resourceConfigMapper.selectRandomBigvFromResourcePool(config.getResourcePoolId(), configSkills, exCustomerIds, weekIndex, endTimeStr, skillOrdered);
        return bigv;
    }

    /**
     * 根据客户技能ID获取返回前端的大V数据
     *
     * @param recomedBigv
     * @param customerSkillId
     * @return
     */
    private AppHomeBigvListVO.BigvInfoVO getBigvByCustomerSkillId(AppHomeBigvListVO recomedBigv, Long customerSkillId) {
        CustomerSkill customerSkill = customerSkillMapper.selectBigvInfoVoByCustomerSkillId(customerSkillId);
        if (customerSkill == null) {
            return null;
        }
        AppHomeBigvListVO.BigvInfoVO bigv = recomedBigv.new BigvInfoVO();
        packetBigvInfo(bigv, customerSkill);
        return bigv;
    }

    /**
     * 打包大V信息
     *
     * @param bigv
     * @param customerSkill
     */
    private void packetBigvInfo(AppHomeBigvListVO.BigvInfoVO bigv, CustomerSkill customerSkill) {
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
    }

    private int cacluRandomLimitBeginIndex(Integer configNum, int bigvNum) {
        // 1-2号资源位使用排序列表前30%的声优内容，3-4号资源位使用30%-60%的声优内容，剩余内容由5-6号资源位使用
        if (configNum == 1 || configNum == 2) {
            return 0;
        }

        if (configNum == 3 || configNum == 4) {
            return (int) Math.floor(bigvNum * 0.3);
        }

        return (int) Math.floor(bigvNum * 0.6);
    }

    private int cacluRandomLimitEndIndex(Integer configNum, int bigvNum) {
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
