package com.honglu.quickcall.user.service.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.criteria.Order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.dubbo.common.json.JSONObject;
import com.alibaba.dubbo.config.annotation.Service;
import com.honglu.quickcall.account.facade.code.AccountBizReturnCode;
import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exception.RemoteException;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.common.api.util.JedisUtil;
import com.honglu.quickcall.common.api.util.ResponseUtils;
import com.honglu.quickcall.common.core.util.Detect;
import com.honglu.quickcall.common.core.util.StringUtil;
import com.honglu.quickcall.user.facade.code.UserBizReturnCode;
import com.honglu.quickcall.user.facade.entity.Customer;
import com.honglu.quickcall.user.facade.entity.CustomerInterest;
import com.honglu.quickcall.user.facade.entity.CustomerOccupation;
import com.honglu.quickcall.user.facade.entity.Interest;
import com.honglu.quickcall.user.facade.entity.Occupation;
import com.honglu.quickcall.user.facade.entity.Orders;
import com.honglu.quickcall.user.facade.entity.Product;
import com.honglu.quickcall.user.facade.entity.SensitivityWord;
import com.honglu.quickcall.user.facade.entity.Skill;
import com.honglu.quickcall.user.facade.entity.in.HomePageLogout;
import com.honglu.quickcall.user.facade.entity.in.PersonHomePage;
import com.honglu.quickcall.user.facade.entity.in.VProductTag;
import com.honglu.quickcall.user.facade.exchange.request.PersonInfoRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveBirthRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveGenderRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveInterestRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveNickNameRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveOccupationRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveSignNameRequest;
import com.honglu.quickcall.user.facade.exchange.request.ShowHomePageLogout;
import com.honglu.quickcall.user.service.dao.CustomerMapper;
import com.honglu.quickcall.user.service.dao.CustomerOccupationMapper;
import com.honglu.quickcall.user.service.dao.FansMapper;
import com.honglu.quickcall.user.service.dao.OrdersMapper;
import com.honglu.quickcall.user.service.dao.ProductMapper;
import com.honglu.quickcall.user.service.dao.SensitivityWordMapper;
import com.honglu.quickcall.user.service.dao.SkillMapper;
import com.honglu.quickcall.user.service.dao.CustomerInterestMapper;
import com.honglu.quickcall.user.service.service.CustomerRedisManagement;
import com.honglu.quickcall.user.service.service.InterestMapper;
import com.honglu.quickcall.user.service.service.OccupationMapper;
import com.honglu.quickcall.user.service.service.PersonInfoService;
import com.honglu.quickcall.user.service.util.JsonParseUtil;
import com.honglu.quickcall.user.service.util.RedisKeyConstants;

import cn.jiguang.commom.utils.StringUtils;

@Service
@Transactional
public class UserInfoServiceImpl implements PersonInfoService {
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
	private ProductMapper productMapper;
	@Autowired
	private FansMapper fansMapper;
	@Autowired
	private SkillMapper skillMapper;
	@Autowired
	private OrdersMapper ordersMapper;
	/**
	 * 中文、英文、数字、下划线校验 4-24位
	 */
	private final static Pattern CH_EN_PATTERN = Pattern.compile("^[\\u4e00-\\u9fa5a-zA-Z]{4,24}+$");
	private final static Pattern ID_PATTERN = Pattern.compile(
			"^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$");
	private static final Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

	/**
	 * @author liuyinkai 查看个人信息
	 */
	@Override
	public CommonResponse queryPersonInfo(PersonInfoRequest params) {
		CommonResponse commonResponse = new CommonResponse();
		PersonHomePage personHomePage = null;
		if (null != (params.getCustomerId()) || null != (params.getOtherId())) {
			throw new RemoteException(UserBizReturnCode.paramError, "参数错误 request.getJson()=" + params.getCustomerId());
		}
		try {
			// 判断是不是查看自己的资料
			if (!params.getCustomerId().equals(params.getOtherId())) {
				personHomePage = queryPersonal(params.getCustomerId());
			} else {
				personHomePage = queryPersonal(params.getOtherId());
			}

			commonResponse.setData(personHomePage);
			commonResponse.setCode(UserBizReturnCode.Success);
			commonResponse.setMessage(UserBizReturnCode.Success.desc());
		} catch (Exception e) {
			throw new RemoteException(UserBizReturnCode.UserNotExist, "用户不存在");
		}

		return commonResponse;
	}

	/**
	 * @Title 查询详细展示信息
	 * @modify liuyinkai
	 * @param accountId
	 * @return outPacket
	 */
	@SuppressWarnings("unchecked")
	public PersonHomePage queryPersonal(Long customerId) {
		Customer customer = customerRedisManagement.getCustomer(customerId);
		// 获取身份证
		String identityID = customer.getCredentialsNum();
		PersonHomePage personHomePage = new PersonHomePage();
		if (customer != null) {
			personHomePage.setCustomerId(customerId);// id
			personHomePage.setNickName(customer.getNickName());// 昵称
			// 判断身份证是否为空，如果又身份证则按找身份证上面的性别
			if (StringUtils.isNotEmpty(identityID)) {
				Matcher m = ID_PATTERN.matcher(identityID);
				if (!m.matches()) {
					throw new RemoteException(UserBizReturnCode.paramError, "身份证参数错误");
				}
				// 判断身份证男女，截取身份证倒数第二位
				String genderStr = identityID.substring(identityID.length() - 2, identityID.length() - 1);
				int genderInt = Integer.parseInt(genderStr);
				if (genderInt % 2 == 0) {
					personHomePage.setSex(0);// 女
				} else {
					personHomePage.setSex(1);// 男
				}
			} else {
				personHomePage.setSex(customer.getSex());// 性别
			}
			personHomePage.setHeadPortraitUrl(customer.getHeadPortraitUrl());// 头像
			personHomePage.setBirthday(customer.getBirthday());// 生日
			// 查询兴趣爱好 by customerId
			List<String> interestList = interestMapper.selectInterestByCustomerId(customerId);
			personHomePage.setInterest(interestList);
			// 查询职业 by accountId
			String occupation = occupationMapper.selectByCustomerId(customerId);
			personHomePage.setOccupation(occupation);
			return personHomePage;
		} else {
			return null;
		}
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

		if (StringUtils.isNotEmpty(newNickname)) {
			if (newNickname.length() > 8) {
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
	 * @param nickName 用户昵称
	 * @return 0 - 通过，1 - 敏感词，2 - 中英文
	 */
	private Integer checkNickName(String nickName) {
		try {
			// 中文、英文、数字、下划线校验 4-24位
			Matcher m = CH_EN_PATTERN.matcher(nickName);
			if (!m.matches()) {
				return 2;
			}
			// 昵称敏感词校验
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
		Integer newGender = params.getGender();
		// 如果newGender 为空或者不等于 0、1 则返回错误
		if (null != newGender && newGender != 0 && newGender != 1) {
			throw new RemoteException(UserBizReturnCode.paramError, "参数错误，修改失败");
		}
		customer.setSex(newGender);
		// 更新性别
		int result = customerMapper.updateGenderByCustomerID(customer);
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
		customer.setBirthday(params.getBirthday());
		// 更新生日
		int result = customerMapper.updateGenderByCustomerID(customer);
		logger.info("=====saveGender,更新数量" + result);
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
		// 获取兴趣,截取
		String interests = params.getInterestId();
		String[] interest = interests.split(",");
		if (null != customer) {
			// 更新customer_interest表
			try {
				for (String str : interest) {
					customerInterest.setInterestId(Integer.parseInt(str));
					customerInterest.setCreateTime(new Date());
					int result = customerInterestMapper.insertSelective(customerInterest);
				}
				commonResponse.setData(customerInterest);
				commonResponse.setCode(UserBizReturnCode.Success);
				commonResponse.setMessage(UserBizReturnCode.Success.desc());
				return commonResponse;
			} catch (Exception e) {
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
		String occupation = params.getOccupationId();
		if (null != customer) {
			// 更新customer_interest表
			try {
				// 存入兴趣ID
				customerOccupation.setOccupationId(Integer.parseInt(occupation));
				// 创建时间
				customerOccupation.setCreateTime(new Date());
				int result = customerOccupationMapper.insertSelective(customerOccupation);
				if (result > 0) {
					commonResponse.setData(customerOccupation);
					commonResponse.setCode(UserBizReturnCode.Success);
					commonResponse.setMessage(UserBizReturnCode.Success.desc());
				}
				return commonResponse;
			} catch (Exception e) {
				throw new BizException(AccountBizReturnCode.JdbcError, "操作数据库异常");
			}
		} else {
			throw new BizException(AccountBizReturnCode.JdbcError, "操作数据库异常");
		}

	}

	/**
	 * 大V主页，普通用户主页（客态）
	 * 
	 * @author liuyinkai
	 * @param params
	 */
	@Override
	public CommonResponse showHomePageLogout(ShowHomePageLogout params) {
		CommonResponse commonResponse = new CommonResponse();
		if (null != params) {
			HomePageLogout homePageLogout = new HomePageLogout();
			// 获取主页所有资料
			HomePageLogout hpl = customerMapper.showHomePageLogout(params.getCustomerId());
			// 获取兴趣名字
			List<String> interestName = interestMapper.selectInterestByCustomerId(params.getCustomerId());
			homePageLogout.setInterestName(interestName);
			// 获取职业名字
			String occupationName = occupationMapper.selectByCustomerId(params.getCustomerId());
			homePageLogout.setOccupationName(occupationName);
			// 判断是否是大V用户，只有拥有上架商品的用户和通过大V认证的用户才会显示大V认证
			int num = productMapper.queryVProductNum(params.getCustomerId());
			if (num > 0 && hpl.getvStatus() == 2) {
				// 可以显示大V图标
				homePageLogout.setvStatus(1);
			} else {
				// 不显示大V图标
				homePageLogout.setvStatus(0);
			}
			// 主播擅长项目
			List<VProductTag> vProductTags = this.queryTag(params.getCustomerId());
			homePageLogout.setvProductTags(vProductTags);
			// 查询粉丝数量
			Long fansNum = fansMapper.queryFansNumByCustomerId(params.getCustomerId());
			homePageLogout.setFansNum(fansNum);
			commonResponse.setData(homePageLogout);
			commonResponse.setCode(UserBizReturnCode.Success);
			commonResponse.setMessage(UserBizReturnCode.Success.desc());
			return commonResponse;
		}
		throw new BizException(AccountBizReturnCode.JdbcError, "操作数据库异常");
	}

	/**
	 * 查询主播产品标签
	 * 
	 * @author liuyinkai
	 * @param customerId
	 * @return
	 */
	public List<VProductTag> queryTag(Long customerId) {
		List<VProductTag> list = new ArrayList<VProductTag>();
		Orders orders = new Orders();
		// 获取标签名称id，价钱，服务时间
		list = productMapper.selectVProductTag(customerId);
		for (VProductTag tag : list) {
			// 获取标签名称
			Product product = productMapper.selectByPrimaryKey(customerId);
			tag.setTagName(product.getName());
			// 获取该产品接单次数(订单完成状态)
			// 封装参数
			orders.setProductId(product.getProductId());
			orders.setSellerId(customerId);
			Long completeNum = ordersMapper.queryCompleteNumByCustomerIdProductId(orders);
			tag.setCompletedOrderNum(completeNum);
		}
		return list;

	}

}
