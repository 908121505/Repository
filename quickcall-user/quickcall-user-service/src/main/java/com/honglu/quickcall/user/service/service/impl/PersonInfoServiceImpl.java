package com.honglu.quickcall.user.service.service.impl;

import cn.jiguang.commom.utils.StringUtils;
import com.honglu.quickcall.account.facade.business.IAccountOrderService;
import com.honglu.quickcall.account.facade.code.AccountBizReturnCode;
import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exception.RemoteException;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.exchange.ResultUtils;
import com.honglu.quickcall.common.api.util.DateUtils;
import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.common.api.util.RedisKeyConstants;
import com.honglu.quickcall.common.constants.PropertiesConstant;
import com.honglu.quickcall.common.core.util.Detect;
import com.honglu.quickcall.common.core.util.StringUtil;
import com.honglu.quickcall.common.core.util.UUIDUtils;
import com.honglu.quickcall.common.third.rongyun.models.CodeSuccessReslut;
import com.honglu.quickcall.common.third.rongyun.util.RongYunUtil;
import com.honglu.quickcall.user.facade.code.UserBizReturnCode;
import com.honglu.quickcall.user.facade.constants.ScoreRankConstants;
import com.honglu.quickcall.user.facade.constants.UserBizConstants;
import com.honglu.quickcall.user.facade.entity.*;
import com.honglu.quickcall.user.facade.entity.example.AppShareConfigExample;
import com.honglu.quickcall.user.facade.exchange.request.*;
import com.honglu.quickcall.user.facade.vo.*;
import com.honglu.quickcall.user.service.dao.*;
import com.honglu.quickcall.user.service.service.CustomerRedisManagement;
import com.honglu.quickcall.user.service.service.PersonInfoService;
import com.honglu.quickcall.user.service.util.JsonParseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class PersonInfoServiceImpl implements PersonInfoService {
	@Autowired
	private CustomerRedisManagement customerRedisManagement;
	@Autowired
	private InterestMapper interestMapper;
	@Autowired
	private OccupationMapper occupationMapper;
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private SensitivityWordMapper sensitivityWordMapper;
	@Autowired
	private CustomerInterestMapper customerInterestMapper;
	@Autowired
	private CustomerOccupationMapper customerOccupationMapper;
	@Autowired
	private FansMapper fansMapper;
	@Autowired
	private SkillItemMapper skillItemMapper;
	@Autowired
	private CustomerSkillMapper customerSkillMapper;
	@Autowired
	private CustomerSkillCertifyMapper customerSkillCertifyMapper;
	@Autowired
	private CustomerAppearanceMapper customerAppearanceMapper;
	@Autowired
	private IAccountOrderService accountOrderService;
	@Autowired
	private AppShareConfigMapper appShareConfigMapper;
	@Autowired
	private BlacklistMapper blacklistMapper;
	@Autowired
	private CustomerVisitMapper customerVisitMapper;
	@Autowired
	private BigvSkillScoreMapper bigvSkillScoreMapper;

	/**
	 * 中文、英文、数字、下划线校验 4-24位
	 */
	private final static Pattern CH_EN_PATTERN = Pattern.compile("^[\\u4e00-\\u9fa5a-z\\d_]{4,24}$");
	private static final Logger logger = LoggerFactory.getLogger(PersonInfoServiceImpl.class);

	/**
	 * 首页搜索用户
	 *
	 * @param params
	 * @return
	 */
	@Override
	public CommonResponse searchPerson(SearchPersonRequest params) {
		CommonResponse commonResponse = new CommonResponse();
		String keyword = params.getKeyword();
		if (StringUtil.isBlank(keyword)) {
			throw new BizException(UserBizReturnCode.paramError, "搜索关键字不能为空");
		}
		keyword = keyword.replaceAll("%","\\\\%");
		Pattern pattern = Pattern.compile("[0-9]{8,10}");
		Long currentCustomer = params.getCustomerId();
		List<SearchPersonListVO> customerList = null;
		// 匹配搜索关键字是模糊搜索还是精准搜索
		if (pattern.matcher(keyword).matches()) {
			// 精准搜索
			customerList = customerMapper.selectPreciseSearch(keyword, currentCustomer);
		} else {
			// 模糊搜索
			customerList = customerMapper.selectFuzzySearch(keyword, currentCustomer, params.getPageIndex(),
					params.getPageSize());
		}

		logger.info("用户编号为：" + currentCustomer + "搜索关键字:" + keyword + " 成功");
		commonResponse.setData(customerList);
		commonResponse.setCode(UserBizReturnCode.Success);
		commonResponse.setMessage(UserBizReturnCode.Success.desc());
		return commonResponse;
	}

	/**
	 * 保存昵称和头像
	 *
	 * @author liuyinkai
	 * @param params
	 */
	@Override
	public CommonResponse saveNicknameImage(SaveNickNameRequest params) {
		CommonResponse commonResponse = new CommonResponse();
		Customer customer = customerMapper.selectByPrimaryKey(params.getCustomerId());
		String newNickname = params.getNickName();

		if(customer == null){
			return ResultUtils.result(BizCode.CustomerNotExist);
		}
		if (StringUtils.isNotEmpty(newNickname)) {
			if (newNickname.length() > 24) {
				throw new RemoteException(UserBizReturnCode.paramError, "您的昵称超出长度！");
			}
			String oldNickname = customer.getNickName();
			customer.setNickName(newNickname);
			if (newNickname.equals(oldNickname)) {

			} else {
				// 中文、英文、数字、下划线校验 4-24位
				Integer check = 2;
				// 敏感词
				Integer checkDetail = 1;
				Integer checkResult = checkNickName(newNickname);
				if (check.equals(checkResult)) {
					throw new RemoteException(UserBizReturnCode.paramError, "用户名不符合规则");
				} else if (checkDetail.equals(checkResult)) {
					throw new RemoteException(UserBizReturnCode.nickNameSensitive, "您输入的昵称包含敏感字，请重新输入！");
				}
			}
		}
		if (StringUtils.isNotEmpty(params.getHead_portrait_url())) {
			customer.setHeadPortraitUrl(params.getHead_portrait_url());
		}
		int result = customerMapper.updateByPrimaryKeySelective(customer);
		logger.info("=====saveNickName,更新数量" + result);
		if (result > 0) {

			// 刷新融云用户信息
			CodeSuccessReslut reslut = RongYunUtil.refreshUser(String.valueOf(customer.getCustomerId()),
					customer.getNickName(), customer.getHeadPortraitUrl());
			// 刷新失败
			// if (reslut.getCode() != 200) {
			// logger.error("刷新融云用户信息失败，用户id为：" + String.valueOf(customer.getCustomerId()));
			// } else {
			// logger.info("刷新融云用户信息成功！");
			// }

			JedisUtil.set(RedisKeyConstants.USER_CUSTOMER_INFO + params.getCustomerId(),
					JsonParseUtil.castToJson(customer));
			commonResponse.setData(customer);
			commonResponse.setCode(UserBizReturnCode.Success);
			commonResponse.setMessage(UserBizReturnCode.Success.desc());
			return commonResponse;
		} else {
			throw new RemoteException(UserBizReturnCode.paramError, "参数错误，修改失败");
		}
	}

	/**
	 * 昵称规则校验
	 *
	 * @modify liuyinkai
	 * @param nickName
	 *            用户昵称
	 * @return 0 - 通过，1 - 敏感词，2 - 中英文
	 */
	private Integer checkNickName(String nickName) {
		try {
			// 中文、英文、数字、下划线校验 4-24位
			Matcher m = CH_EN_PATTERN.matcher(nickName);
			if (!m.matches()) {
				return 2;
			}
			// 昵称敏感词校验R
			List<SensitivityWord> sensitivityList = sensitivityWordMapper.querySensitiveName();
			if (Detect.notEmpty(sensitivityList)) {
				for (SensitivityWord obj : sensitivityList) {
					if (nickName.contains(obj.getContent())) {
						logger.info("昵称包含敏感词！");
						return 1;
					}
				}
			}
		} catch (Exception e) {
			logger.error("用户修改昵称校验异常！异常信息:{}", e.getMessage(), e);
			e.printStackTrace();
			return 1;
		}
		return 0;
	}

	/**
	 * 保存性别
	 *
	 * @author liuyinkai
	 */
	@Override
	public CommonResponse saveGender(SaveGenderRequest params) {
		CommonResponse commonResponse = new CommonResponse();
		// 取账号信息并存redis
		Customer customer = customerRedisManagement.getCustomer(params.getCustomerId());
		if(customer == null){
			return ResultUtils.result(BizCode.CustomerNotExist);
		}
		Integer newGender = params.getGender();
		// 如果newGender 为空或者不等于 0、1 则返回错误
		if (null != newGender && newGender != 0 && newGender != 1) {
			throw new RemoteException(UserBizReturnCode.paramError, "参数错误，修改失败");
		}
		customer.setSex(newGender);
		// 更新性别
		int result = customerMapper.updateByPrimaryKeySelective(customer);
		if (result > 0) {
			JedisUtil.set(RedisKeyConstants.USER_CUSTOMER_INFO + params.getCustomerId(),
					JsonParseUtil.castToJson(customer));
			commonResponse.setData(customer);
			commonResponse.setCode(UserBizReturnCode.Success);
			commonResponse.setMessage(UserBizReturnCode.Success.desc());
			return commonResponse;
		}
		return null;
	}

	/**
	 * 保存签名
	 *
	 * @author liuyinkai
	 */
	@Override
	public CommonResponse saveSignName(SaveSignNameRequest params) {
		CommonResponse commonResponse = new CommonResponse();
		Customer customer = customerMapper.selectByPrimaryKey(params.getCustomerId());
		if(customer == null){
			return ResultUtils.result(BizCode.CustomerNotExist);
		}
		// 获取新签名
		String newSign = params.getSignName();
		// if (StringUtils.isNotEmpty(newSign)) {
		// 获取旧签名
		String oldSign = customer.getSignName();
		customer.setSignName(newSign);
		if (newSign.equals(oldSign)) {

		} else {
			if (StringUtils.isNotEmpty(newSign)) {
				if (newSign.length() > 200) {
					throw new RemoteException(UserBizReturnCode.paramError, "您的签名超出长度！");
				}
				List<SensitivityWord> sensitivityList = sensitivityWordMapper.querySensitiveName();
				for (SensitivityWord sensitiveWord : sensitivityList) {
					if (StringUtils.isNotEmpty(newSign) && newSign.contains(sensitiveWord.getContent())) {
						throw new RemoteException(UserBizReturnCode.nickNameSensitive, "您输入的签名包含敏感字，请重新输入！");
					}
				}
			}
		}
		int result = customerMapper.updateByPrimaryKeySelective(customer);
		logger.info("=====saveNickName,更新数量" + result);
		if (result > 0) {
			JedisUtil.set(RedisKeyConstants.USER_CUSTOMER_INFO + params.getCustomerId(),
					JsonParseUtil.castToJson(customer));
			commonResponse.setData(customer);
			commonResponse.setCode(UserBizReturnCode.Success);
			commonResponse.setMessage(UserBizReturnCode.Success.desc());
			return commonResponse;
		} else {
			throw new RemoteException(UserBizReturnCode.paramError, "参数错误，修改失败");
		}

	}

	/**
	 * 保存生日
	 *
	 * @author liuyinkai
	 */
	@Override
	public CommonResponse saveBirthday(SaveBirthRequest params) {
		CommonResponse commonResponse = new CommonResponse();
		Customer customer = customerRedisManagement.getCustomer(params.getCustomerId());
		if(customer == null){
			return ResultUtils.result(BizCode.CustomerNotExist);
		}
		customer.setBirthday(params.getBirthday());// 生日
		customer.setStarSign(params.getStarSign());// 星座
		try {
			// 更新生日
			int result = customerMapper.updateByPrimaryKeySelective(customer);

			if (result > 0) {
				JedisUtil.set(RedisKeyConstants.USER_CUSTOMER_INFO + params.getCustomerId(),
						JsonParseUtil.castToJson(customer));
				commonResponse.setData(customer);
				commonResponse.setCode(UserBizReturnCode.Success);
				commonResponse.setMessage(UserBizReturnCode.Success.desc());
				return commonResponse;
			}
			logger.info("=====saveGender,更新数量" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 保存兴趣
	 *
	 * @author liuyinkai
	 */
	@Override
	@Transactional
	public CommonResponse saveInterest(SaveInterestRequest params) {
		CommonResponse commonResponse = new CommonResponse();
		CustomerInterest customerInterest = new CustomerInterest();
		customerInterest.setCustomerId(params.getCustomerId());

		Customer customer = customerRedisManagement.getCustomer(params.getCustomerId());
		if(customer == null){
			return ResultUtils.result(BizCode.CustomerNotExist);
		}
		// 获取兴趣,截取
		String interests = params.getInterestId();
		String[] interest = interests.split(",");
		if (null != customer) {
			// 更新customer_interest表
			try {
				// 如果customer_interest中间表有该用户，先删除该用户在此表的数据
				customerInterestMapper.deleteByCustomerId(params.getCustomerId());
				for (String str : interest) {
					customerInterest.setInterestId(Integer.parseInt(str));
					// customerInterest.setCreateTime(new Date());
					// 判断是否有重复数据
					// int num = customerInterestMapper.selectRepetitiveData(customerInterest);
					// if (num>0) {
					// //更新
					// customerInterestMapper.updateByCustomerIdSelective(customerInterest);
					// } else {
					// 插入
					customerInterestMapper.insertSelective(customerInterest);
					// }
				}
				commonResponse.setData(customerInterest);
				commonResponse.setCode(UserBizReturnCode.Success);
				commonResponse.setMessage(UserBizReturnCode.Success.desc());
				return commonResponse;
			} catch (Exception e) {
				e.printStackTrace();
				throw new BizException(AccountBizReturnCode.JdbcError, "操作数据库异常");
			}
		} else {
			throw new BizException(AccountBizReturnCode.JdbcError, "操作数据库异常");
		}
	}

	/**
	 * 保存职业
	 *
	 * @author liuyinkai
	 */
	@Override
	public CommonResponse saveOccupation(SaveOccupationRequest params) {
		CommonResponse commonResponse = new CommonResponse();
		CustomerOccupation customerOccupation = new CustomerOccupation();
		// 先存入用户id
		customerOccupation.setCustomerId(params.getCustomerId());

		Customer customer = customerRedisManagement.getCustomer(params.getCustomerId());
		// 获取职业
		int occupation = params.getOccupationId();
		if (null != customer) {
			// 更新customer_interest表
			try {
				// 存入职业ID
				// customerOccupation.setOccupationId(occupation);
				// 创建时间
				// customerOccupation.setCreateTime(new Date());
				// 查看重复数量
				int num = customerOccupationMapper.findRepetitveData(params.getCustomerId());
				if (num > 0) {
					// 更新
					// 存入职业ID
					customerOccupation.setOccupationId(occupation);
					customerOccupation.setModifyTime(new Date());
					// 更新修改时间
					/* int nn = */customerOccupationMapper.updateByCustomerIdSelective(customerOccupation);
				} else {
					// 存入职业ID
					customerOccupation.setOccupationId(occupation);
					// 插入
					/* int result = */customerOccupationMapper.insertSelective(customerOccupation);
				}
				commonResponse.setData(customerOccupation);
				commonResponse.setCode(UserBizReturnCode.Success);
				commonResponse.setMessage(UserBizReturnCode.Success.desc());
			} catch (Exception e) {
				e.printStackTrace();
				throw new BizException(AccountBizReturnCode.JdbcError, "操作数据库异常");
			}
			return commonResponse;
		} else {
			return ResultUtils.result(BizCode.CustomerNotExist);
		}

	}

	@Override
	public CommonResponse queryInterestList(QueryInterestListRequest request) {
		CommonResponse commonResponse = new CommonResponse();
		try {
			List<InterestVO> interestList = interestMapper.selectInterestList();
			commonResponse.setData(interestList);
			commonResponse.setCode(UserBizReturnCode.Success);
			commonResponse.setMessage(UserBizReturnCode.Success.desc());
		} catch (Exception e) {
			logger.error("查询异常，异常信息：", e);
			// throw new RemoteException(UserBizReturnCode.UserNotExist, "用户不存在");
		}

		return commonResponse;
	}

	@Override
	public CommonResponse queryOccupationList(QueryOccupationListRequest request) {
		CommonResponse commonResponse = new CommonResponse();
		try {
			List<OccupationVO> interestList = occupationMapper.selectOccupationList();
			commonResponse.setData(interestList);
			commonResponse.setCode(UserBizReturnCode.Success);
			commonResponse.setMessage(UserBizReturnCode.Success.desc());
		} catch (Exception e) {
			logger.error("查询异常，异常信息：", e);
			// throw new RemoteException(UserBizReturnCode.UserNotExist, "用户不存在");
		}

		return commonResponse;
	}

	@Override
	public CommonResponse queryAttentionFansList(QueryAttentionFansListRequest request) {
		CommonResponse commonResponse = new CommonResponse();
		try {
			List<AttentionFansVO> resultList = new ArrayList<AttentionFansVO>();

			Long customerId = request.getCustomerId();
			Customer customer = customerMapper.selectByPrimaryKey(customerId);
			if(customer == null){
				return ResultUtils.result(BizCode.CustomerNotExist);
			}
			Integer type = request.getType();
			if (UserBizConstants.QUERY_ATTENTION_LIST_TYPE == type) {
				// 查询关注列表
				resultList = fansMapper.queryAttentionListByCustomerId(customerId,
						UserBizConstants.ATTENTION_STATUS_ATTENED);
			} else if (UserBizConstants.QUERY_FANS_LIST_TYPE == type) {
				// 查询粉丝列表
				resultList = fansMapper.queryFansIdListByCustomerId(customerId,
						UserBizConstants.ATTENTION_STATUS_ATTENED);
			}
			commonResponse.setData(resultList);
			commonResponse.setCode(UserBizReturnCode.Success);
			commonResponse.setMessage(UserBizReturnCode.Success.desc());
		} catch (Exception e) {
			logger.error("查询异常，异常信息：", e);
			// throw new RemoteException(UserBizReturnCode.UserNotExist, "用户不存在");
		}

		return commonResponse;
	}

	@Override
	public CommonResponse addOrCancelFans(AddOrCancelFansRequest request) {
		if (request == null || request.getFansId() == null || request.getAttendedId() == null) {
			throw new BizException(UserBizReturnCode.paramError, "参数异常");
		}

		CommonResponse commonResponse = new CommonResponse();

		try {

			Long fansId = request.getFansId();
			Long attendedId = request.getAttendedId();
			Integer type = request.getType();

			Fans fans = fansMapper.queryFans(fansId, attendedId);

			if (UserBizConstants.ATTENTION_TYPE_ADD == type) {
				// 添加关注
				if (fans == null) {
					// 添加关注
					Fans fan = new Fans();
					fan.setId(UUIDUtils.getId());
					fan.setAttentionState(UserBizConstants.ATTENTION_STATUS_ATTENED);
					fan.setFansId(fansId);
					fan.setAnchorId(attendedId);
					fansMapper.insert(fan);
				} else {
					// 更改状态为关注
					Integer attentionStatus = fans.getAttentionState();
					if (UserBizConstants.ATTENTION_STATUS_UN_ATTENED == attentionStatus) {
						Fans record = new Fans();
						record.setId(fans.getId());
						record.setAttentionState(UserBizConstants.ATTENTION_STATUS_ATTENED);
						fansMapper.updateByPrimaryKey(record);
					}
				}
			} else if (UserBizConstants.ATTENTION_TYPE_CANCEL == type) {
				// 取消关注
				if (fans != null) {
					Integer attentionStatus = fans.getAttentionState();
					if (UserBizConstants.ATTENTION_STATUS_ATTENED == attentionStatus) {
						// 更改状态
						Fans record = new Fans();
						record.setId(fans.getId());
						record.setAttentionState(UserBizConstants.ATTENTION_STATUS_UN_ATTENED);
						fansMapper.updateByPrimaryKey(record);
					}
				}

			}

			commonResponse.setData("00000");
			commonResponse.setCode(UserBizReturnCode.Success);
			commonResponse.setMessage(UserBizReturnCode.Success.desc());
		} catch (Exception e) {
			logger.error("查询异常，异常信息：", e);
			// throw new RemoteException(UserBizReturnCode.UserNotExist, "用户不存在");
		}

		return commonResponse;
	}

	@Override
	public CommonResponse checkAttention(CheckAttentionRequest request) {
		CommonResponse commonResponse = new CommonResponse();
		return commonResponse;
	}

	@Override
	public CommonResponse checkEachAttention(CheckEachAttentionRequest request) {
		Customer customer = customerMapper.selectByPrimaryKey(request.getAttendedId());
		if(customer == null){
			return ResultUtils.result(BizCode.CustomerNotExist);
		}
		// 查询关注状态
		int n = fansMapper.queryIsFollow(request.getFansId(), request.getAttendedId());
		int n1 = fansMapper.queryIsFollow(request.getAttendedId(), request.getFansId());
		// 双方关注状态算出互相关注状态
		int status = n & n1;
		status = status == 0 ? 0 : 1;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("attentionStatus", n1 == 0 ? 0 : 1);
		map.put("eachAttentionStatus", status);
		logger.info("查询 FansId=" + request.getFansId() + ";AttendedId=" + request.getAttendedId() + ";互相关注状态成功");
		return ResultUtils.resultSuccess(map);
	}

	@Override
	public CommonResponse readAttention(ReadAttentionRequest params) {
		Customer customer = customerMapper.selectByPrimaryKey(params.getCustomerId());
		if(customer == null){
			return ResultUtils.result(BizCode.CustomerNotExist);
		}
		if (params.getCustomerId() == null) {
			throw new BizException(BizCode.ParamError, "用戶Id 不能为空");
		}
		fansMapper.updateReadAttention(params.getCustomerId());
		return ResultUtils.resultSuccess();
	}

	@Override
	public CommonResponse queryMySkill(queryMyskillRequest params) {
		if (params.getCustomerId() == null) {
			throw new BizException(BizCode.ParamError, "用戶Id 不能为空");
		}
		Customer customer = customerMapper.selectByPrimaryKey(params.getCustomerId());
		if(customer == null){
			return ResultUtils.result(BizCode.CustomerNotExist);
		}
		CommonResponse commonResponse = new CommonResponse();
		List<SkillItem> skillList = skillItemMapper.selectAllSkill();
		List<CustomerSkillCertify> skillReviewList = customerSkillCertifyMapper
				.selectAllSkillByCustomer(params.getCustomerId());
		// 已经解锁的技能列表
		List<MySkillVO> haveSkill = new ArrayList<MySkillVO>();
		// 未解锁的技能列表
		List<MySkillVO> noHaveSkill = new ArrayList<MySkillVO>();
		boolean flag;
		Map<String, Object> map = new HashMap<String, Object>();
		// 区分解锁和不解锁的技能
		for (SkillItem skill : skillList) {
			flag = true;
			MySkillVO mySkillVO = new MySkillVO();
			mySkillVO.setName(skill.getSkillItemName());
			mySkillVO.setImageUrl(skill.getUnlockIcon());
			mySkillVO.setSkillId(skill.getId());
			mySkillVO.setSkillStatus(skill.getSkillStatus());
			for (CustomerSkillCertify skillReview : skillReviewList) {
				// 判断认证的技能
				if (skill.getId().equals(skillReview.getSkillItemId())) {
					mySkillVO.setAuditStatus(skillReview.getAuditStatus());
					// 是否已经审核过
					if (skillReview.getIsAudited() == 1) {
						// 审核状态不是待审核状态
						if (skillReview.getAuditStatus() != 1) {
							mySkillVO.setSkillVoiceUrl(skillReview.getSkillVoiceUrl());
							mySkillVO.setSkillVoiceTime(
									skillReview.getSkillVoiceTime().setScale(0, BigDecimal.ROUND_UP).intValue());
						}
						haveSkill.add(mySkillVO);
						flag = false;
						skillReviewList.remove(skillReview);
						break;
					}
				}
			}
			if (flag) {
				mySkillVO.setImageUrl(skill.getLockIcon());
				noHaveSkill.add(mySkillVO);
			}
		}

		logger.info("用户编号为：" + params.getCustomerId() + "查询我的技能成功");
		map.put("unlockList", haveSkill);
		map.put("lockList", noHaveSkill);
		commonResponse.setData(map);
		commonResponse.setCode(UserBizReturnCode.Success);
		commonResponse.setMessage(UserBizReturnCode.Success.desc());
		return commonResponse;
	}

	@Override
	public CommonResponse saveCustomerSkillCertify(SaveSkillAuditRequest params) {
		CommonResponse commonResponse = new CommonResponse();
		Customer customer = customerMapper.selectByPrimaryKey(params.getCustomerId());
		if(customer == null){
			return ResultUtils.result(BizCode.CustomerNotExist);
		}
		CustomerSkillCertify certifyNow = customerSkillCertifyMapper.selectSkillCertifyId(params.getCustomerId(),
				params.getSkillItemId());

		CustomerSkillCertify csc = new CustomerSkillCertify();
		csc.setCustomerId(params.getCustomerId());
		csc.setSkillItemId(params.getSkillItemId());
		csc.setSkillVoiceUrlTmp(params.getSkillVoiceUrl());
		csc.setSkillVoiceTimeTmp(params.getSkillVoiceTime());
		csc.setAuditStatus(UserBizConstants.SKILL_CERTIFY_STATUS_AUDIT);
		// 更改的数量
		int n;
		if (certifyNow != null) {
			// 如果是审核中不能进入本方法
			if (certifyNow.getAuditStatus() == UserBizConstants.SKILL_CERTIFY_STATUS_AUDIT) {
				commonResponse.setCode(UserBizReturnCode.skillCertifyError);
				commonResponse.setMessage(UserBizReturnCode.skillCertifyError.desc());
				return commonResponse;
			}
			// 更新操作
			csc.setCertifyId(certifyNow.getCertifyId());
			n = customerSkillCertifyMapper.updateEntity(csc);
		} else {
			// 插入操作
			csc.setCertifyId(UUIDUtils.getId());
			n = customerSkillCertifyMapper.saveEntity(csc);
		}
		if (n > 0) {
			commonResponse.setCode(UserBizReturnCode.Success);
			commonResponse.setMessage(UserBizReturnCode.Success.desc());
		} else {
			throw new BizException(AccountBizReturnCode.JdbcError, "操作数据库异常");
		}
		logger.info("上传认证信息成功,customerId=" + params.getCustomerId() + ";skillItemId=" + params.getSkillItemId());
		return commonResponse;
	}

	@Override
	public CommonResponse queryCustomerCenter(CustomerCenterRequest request) {
		Customer customer = customerMapper.selectByPrimaryKey(request.getCustomerId());
		if (customer == null) {
			return ResultUtils.result(BizCode.CustomerNotExist);
		}
		CustomerCenterVO customerCenterVO = new CustomerCenterVO();
		customerCenterVO.setCustomerId(request.getCustomerId());
		customerCenterVO.setCustomerAppId(customer.getAppId());
		customerCenterVO.setNickName(customer.getNickName());
		customerCenterVO.setHeadPortraitUrl(customer.getHeadPortraitUrl());
		customerCenterVO.setSex(customer.getSex());
		if (customer.getBirthday() != null) {
			customerCenterVO.setAge(DateUtils.getAgeByBirthYear(customer.getBirthday()));
		}
		customerCenterVO.setCustomerLevel(customer.getCustomerLevel());
		customerCenterVO.setSignName(customer.getSignName());
		customerCenterVO.setIdentityStatus(customer.getIdentityStatus());
		customerCenterVO.setvStatus(customer.getvStatus());

		// 查询关注数
		customerCenterVO.setAttentionNum(fansMapper.queryAttentionNumByCustomerId(request.getCustomerId()));

		// 查询粉丝数
		customerCenterVO.setFansNum(fansMapper.queryFansNumByCustomerId(request.getCustomerId()));
		
		// 查询被访问数量
		customerCenterVO.setVisitNum(customerVisitMapper.selectUnreadCountByCustomerId(request.getCustomerId()));

		// 查询充值、提现金额
		Map<String, BigDecimal> customerMoney = customerMapper.queryCustomerAccountMoney(request.getCustomerId());
		if (customerMoney != null) {
			customerCenterVO.setRechargeAmounts(customerMoney.get("rechargeAmounts"));
			customerCenterVO.setWithdrawAmounts(customerMoney.get("withdrawAmounts"));
		}
		return ResultUtils.resultSuccess(customerCenterVO);
	}

	@Override
	public CommonResponse queryCustomerHome(CustomerHomeRequest request) {
		Customer viewCustomer = customerMapper.selectByPrimaryKey(request.getViewCustomerId());
		if (viewCustomer == null) {
			return ResultUtils.result(BizCode.CustomerNotExist);
		}

		CustomerHomeVO customerHomeVO = new CustomerHomeVO();
		customerHomeVO.setLoginCustomerId(request.getLoginCustomerId());
		customerHomeVO.setViewCustomerId(request.getViewCustomerId());
		customerHomeVO.setCustomerAppId(viewCustomer.getAppId());
		customerHomeVO.setNickName(viewCustomer.getNickName());
		customerHomeVO.setHeadPortraitUrl(viewCustomer.getHeadPortraitUrl());
		customerHomeVO.setSex(viewCustomer.getSex());
		if (viewCustomer.getBirthday() != null) {
			customerHomeVO.setAge(DateUtils.getAgeByBirthYear(viewCustomer.getBirthday()));
		}
		customerHomeVO.setCustomerLevel(viewCustomer.getCustomerLevel());
		customerHomeVO.setSignName(viewCustomer.getSignName());
		customerHomeVO.setStarSign(viewCustomer.getStarSign());
		customerHomeVO.setIdentityStatus(viewCustomer.getIdentityStatus());
		customerHomeVO.setvStatus(viewCustomer.getvStatus());

		// 查询登录用户是否关注被查看的用户 --> 传入了登录用户 && 不是查看自己
		if (request.getLoginCustomerId() != null
				&& !Objects.equals(request.getLoginCustomerId(), request.getViewCustomerId())) {
			int idFollow = fansMapper.queryIsFollow(request.getViewCustomerId(), request.getLoginCustomerId());
			customerHomeVO.setAttentionStatus(Objects.equals(idFollow, 1) ? 1 : 0);
		}

		// 查询粉丝数
		customerHomeVO.setFansNum(fansMapper.queryFansNumByCustomerId(request.getViewCustomerId()));

		// 查看自己的标志
		boolean viewMyselfFlag = Objects.equals(request.getLoginCustomerId(), request.getViewCustomerId());

		// 查询用户形象照列表 性别(0=女,1=男)
		List<String> appearanceList = customerAppearanceMapper
				.queryCustomerAppearance(request.getViewCustomerId(), 0, viewMyselfFlag ? 0 : 1);
		if (appearanceList.isEmpty()) {
			customerHomeVO.setAppearanceUrlList(Objects.equals(customerHomeVO.getSex(), 1)
					? Arrays.asList(PropertiesConstant.DEFAULT_CUSTOMER_APPEARANCE_URL_BOY)
					: Arrays.asList(PropertiesConstant.DEFAULT_CUSTOMER_APPEARANCE_URL_GIRL));
		} else {
			customerHomeVO.setAppearanceUrlList(appearanceList);
		}

		// 查询用户兴趣
		customerHomeVO.setInterestList(customerInterestMapper.queryCustomerInterestList(request.getViewCustomerId()));

		// 查询声鉴卡
		List<String> soundGuideCard = customerAppearanceMapper
				.queryCustomerAppearance(request.getViewCustomerId(), 2, viewMyselfFlag ? 0 : 1);
		customerHomeVO.setSoundGuideCard(soundGuideCard.isEmpty() ? null : soundGuideCard.get(0));

		// 查询分享信息
		AppShareConfigExample shareExample = new AppShareConfigExample();
		shareExample.createCriteria().andTypeEqualTo(1);
		shareExample.setOrderByClause("modify_time desc");
		List<AppShareConfig> shareList = appShareConfigMapper.selectByExample(shareExample);
		if (shareList != null && shareList.size() > 0) {
			AppShareConfig share = shareList.get(0);
			customerHomeVO.setShareTitle(share.getTitle());
			customerHomeVO.setShareContent(share.getContent());
			customerHomeVO.setShareIconUrl(share.getIconUrl());
			customerHomeVO.setShareLinkUrl(
					String.format(share.getLinkUrl(), request.getViewCustomerId(), request.getLoginCustomerId()));
		}

		// 查询用户技能 -- 条件是大V
		List<CustomerHomeVO.CustomerSkill> skillList = new ArrayList<>();
		if (Objects.equals(viewCustomer.getvStatus(), 2)) {
			List<CustomerSkill> customerSkills = customerSkillMapper
					.selectCustomerAuditedSkill(request.getViewCustomerId());
			for (CustomerSkill bean : customerSkills) {
				CustomerHomeVO.CustomerSkill customerSkill = customerHomeVO.new CustomerSkill();
				customerSkill.setCustomerSkillId(bean.getCustomerSkillId());
				customerSkill.setSkillId(bean.getSkillItemId());
				customerSkill.setSkillName(bean.getSkillName());
				customerSkill.setSkillBackColor(bean.getSkillBackColor());
				customerSkill.setSkillFontColor(bean.getSkillFontColor());
				customerSkill.setSkillVoiceUrl(bean.getSkillVoiceUrl());
				customerSkill.setSkillVoiceTime(bean.getSkillVoiceTime());

				// 价格显示折扣后的价格
				customerSkill.setSkillPrice(bean.getDiscountPrice());
				customerSkill.setServiceUnit(bean.getServiceUnit());
				// 查询技能评价标签
				customerSkill.setCustomerLabel(customerSkillMapper
						.selectCustomerSkillHotLabel(request.getViewCustomerId(), bean.getSkillItemId()));

				// 查询技能声量
				customerSkill.setSkillVolume(ScoreRankConstants.formatSkillScore(bigvSkillScoreMapper.selectBigvScoreValue(bean.getCustomerSkillId())));

				// 判断是否可下单
				if (Objects.equals(request.getLoginCustomerId(), request.getViewCustomerId())) {
					customerSkill.setCanOrder(0); // 自己看自己的个人主页时 -- 直接返回 0=不可接单
				} else {
					customerSkill.setCanOrder(
							accountOrderService.checkReceiveOrderByCustomerSkillId(bean.getCustomerSkillId()));
				}
				// 查询技能订单数-wq
				customerSkill.setSkillOrderNo(customerSkillMapper.selectSkillOrderNo(bean.getCustomerSkillId()));
				skillList.add(customerSkill);
			}
		}
		customerHomeVO.setSkillList(skillList);

		// 黑名单状态
		if (request.getLoginCustomerId() != null){
			customerHomeVO.setBackStatus(blacklistMapper.judgeCustomerIfBacked(
					request.getLoginCustomerId(), request.getViewCustomerId()));
		}

		return ResultUtils.resultSuccess(customerHomeVO);
	}

	@Override
	public CommonResponse queryCustomerLevel(CustomerLevelRequest request) {
		Customer customer = customerMapper.selectByPrimaryKey(request.getCustomerId());
		if (customer == null) {
			return ResultUtils.result(BizCode.CustomerNotExist);
		}
		CustomerLevelVO customerLevelVO = new CustomerLevelVO();
		customerLevelVO.setCustomerId(request.getCustomerId());
		customerLevelVO.setCustomerAppId(customer.getAppId());
		customerLevelVO.setNickName(customer.getNickName());
		customerLevelVO.setvStatus(customer.getvStatus());
		customerLevelVO.setHeadPortraitUrl(customer.getHeadPortraitUrl());
		customerLevelVO.setCustomerLevel(customer.getCustomerLevel());
		customerLevelVO.setCustomerExperience(customer.getCumulateExperience());

		// 查询当前等级经验值
		Integer currentLevelExperience = customerMapper.getGradeExperienceByLevelNo(customer.getCustomerLevel());
		customerLevelVO.setCurrentLevelExperience(currentLevelExperience);
		// 查询下一等级经验值
		Integer nextLevelExperience = customerMapper.getGradeExperienceByLevelNo(customer.getCustomerLevel() + 1);
		customerLevelVO.setNextLevelExperience(nextLevelExperience);

		// 距离下一级所需经验值
		if (nextLevelExperience != null) {
			customerLevelVO.setNeedExperienceNum(nextLevelExperience - customer.getCumulateExperience());
		}

		// 查询等级特权 ADUAN -- 一期不做

		// 查询如何升级 ADUAN -- 一期不做

		return ResultUtils.resultSuccess(customerLevelVO);
	}

	@Override
	public CommonResponse noReadAttentionCount(NoReadAttentionCountRequest params) {
		if (params.getCustomerId() == null) {
			throw new BizException(BizCode.ParamError, "用戶Id 不能为空");
		}
		Customer customer = customerMapper.selectByPrimaryKey(params.getCustomerId());
		if(customer == null){
			return ResultUtils.result(BizCode.CustomerNotExist);
		}
		int row = fansMapper.queryNoReadFansNumByCustomerId(params.getCustomerId());
		return ResultUtils.resultSuccess(row);
	}

	@Override
	public CommonResponse isBigVidentity(IsBigVidentityRequest request) {
		Customer customer = customerMapper.selectByPrimaryKey(request.getCustomerId());
		if(customer == null){
			return ResultUtils.result(BizCode.CustomerNotExist);
		}
		if (customer != null && customer.getvStatus() == 2 && customer.getIdentityStatus() == 2) {
			return ResultUtils.resultSuccess(1);
		}

		return ResultUtils.resultSuccess(0);
	}

	@Override
	public CommonResponse submitCustomerApplyBigv(CustomerApplyBigvRequest request) {
		Customer customer = customerMapper.selectByPrimaryKey(request.getCustomerId());
		if (customer == null) {
			return ResultUtils.result(BizCode.CustomerNotExist);
		}
		if(Objects.equals(customer.getvStatus(), 2)){
			return ResultUtils.resultDataStateError("您已经是声优了，不需要申请哟");
		}
		CustomerApplyBigv applyBigv = new CustomerApplyBigv();
		applyBigv.setApplyId(UUIDUtils.getId());
		applyBigv.setCustomerId(request.getCustomerId());
		applyBigv.setApplyTime(new Date());

		if(customerMapper.insertApplyBigvData(applyBigv) == 0){
			return ResultUtils.resultDuplicateOperation("您已申请过，请勿重复提交");
		}
		return ResultUtils.resultSuccess();
	}
}
