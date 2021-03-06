package com.honglu.quickcall.user.service.business;

import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.exception.BaseException;
import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.business.UserDubboBusiness;
import com.honglu.quickcall.user.facade.code.UserBizReturnCode;
import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.request.*;
import com.honglu.quickcall.user.facade.exchange.request.editprofile.*;
import com.honglu.quickcall.user.service.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service("User.UserDubboBusiness")
public class UserDubboBusinessImpl implements UserDubboBusiness {

	private static final Logger logger = LoggerFactory.getLogger(UserDubboBusinessImpl.class);

	@Autowired
	private CommonPersonService commonPersonService;
	@Autowired
	private PersonInfoService personInfoService;
	@Autowired
	private UserMessageService userMessageService;
	@Autowired
	private EditProfileService editProfileService;
	@Autowired
	private BlacklistService blacklistService;
	@Autowired
	private DelateService delateService;
	@Autowired
	private FeedBackService feedBackService;
	@Autowired
	private InternalMessageService internalMessageService;
	@Autowired
	private QueryBigvListService queryHomeBigvListService;

	@Autowired
	private AppVersionManageService appVersionManageService;
	@Autowired
	private DeviceWhitelistService deviceWhitelistService;
	@Autowired
	private AttentionService attentionService;
	@Autowired
	private CustomerVisitService customerVisitService;
	@Autowired
	private ScoreRankService scoreRankService;

	@Autowired
	private WeiXinService weiXinService;

	@Autowired
	DeviceInfoService deviceInfoService;

	@Override
	public CommonResponse excute(AbstractRequest request) {
		if (request == null) {
			throw new BizException(BizCode.ParamError, BizCode.ParamError.desc());
		}
		CommonResponse response = new CommonResponse();
		try {
			switch (request.getBizCode()) {
			case UserFunctionType.CheckPhone:
				response = commonPersonService.regUserExist((IsPhoneExistsRequest) request);
				break;
			case UserFunctionType.register:
				response = commonPersonService.register((UserRegisterRequest) request);
				break;
			case UserFunctionType.login:
				response = commonPersonService.login((UserLoginRequest) request);
				break;
			case UserFunctionType.setpwd:
				response = commonPersonService.setpwd((SetPwdRequest) request);
				break;
			case UserFunctionType.setHeardUrl:
				response = commonPersonService.setHeardUrl((SetHeardUrlRequest) request);
				break;
			case UserFunctionType.getSmsCode:
				response = commonPersonService.getSmsCode((GetSmsCodeRequest) request);
				break;
			/** 查询首页大V列表 **/
			case UserFunctionType.QUERY_FIRST_PAGE_BIGV_LIST:
				response = queryHomeBigvListService.queryHomeBigvList((FirstPageBigvListRequest) request);
				break;
			case UserFunctionType.UNREAD_MESSAGE_NUM:
				response = userMessageService.queryUserUnreadMessageNum((UserUnreadMessageNumRequest) request);
				break;
			case UserFunctionType.USER_ID_CARD_CERTIFY_INFO:
				response = commonPersonService.queryUserIdCardCertificationInfo((UserIdCardInfoRequest) request);
				break;
			case UserFunctionType.SAVE_USER_CERTIFY_INFO:
				response = commonPersonService.saveUserCertificationInfo((SaveCertificationRequest) request);
				break;
			case UserFunctionType.SAVE_DV_VOICE_INFO:
				response = commonPersonService.saveDvVoiceInfo((SaveDvVoiceRequest) request);
				break;
			case UserFunctionType.SaveNicknameImage:// 保存昵称和头像
				response = personInfoService.saveNicknameImage((SaveNickNameRequest) request);
				break;
			case UserFunctionType.SEARCH_PERSON_LIST:// 首页搜索用户列表
				response = personInfoService.searchPerson((SearchPersonRequest) request);
				break;
			case UserFunctionType.SaveGender:// 保存性别
				response = personInfoService.saveGender((SaveGenderRequest) request);
				break;
			case UserFunctionType.SaveSignName:// 保存签名
				response = personInfoService.saveSignName((SaveSignNameRequest) request);
				break;
			case UserFunctionType.SaveBirthday:// 保存生日
				response = personInfoService.saveBirthday((SaveBirthRequest) request);
				break;
			case UserFunctionType.SaveInterest:// 保存兴趣
				response = personInfoService.saveInterest((SaveInterestRequest) request);
				break;
			///////////////////////////////////////////////////////////////////////////
			case UserFunctionType.QUERY_INTEREST_LIST:// 查询兴趣列表
				response = personInfoService.queryInterestList((QueryInterestListRequest) request);
				break;
			case UserFunctionType.QUERY_OCCUPATION_LIST:// 查询职业列表
				response = personInfoService.queryOccupationList((QueryOccupationListRequest) request);
				break;
			case UserFunctionType.QUERY_ATTENDTION_FANS_LIST:// 查询关注/粉丝列表
				response = personInfoService.queryAttentionFansList((QueryAttentionFansListRequest) request);
				break;
			case UserFunctionType.ADD_OR_UPDATE_FANS:// 添加/取消关注
				response = personInfoService.addOrCancelFans((AddOrCancelFansRequest) request);
				break;
			case UserFunctionType.CHECK_ATTENTION:// 判断是否关注对方
				response = personInfoService.checkAttention((CheckAttentionRequest) request);
				break;
			case UserFunctionType.CHECK_EACH_ATTENTION:// 判断是否互相关注对方
				response = personInfoService.checkEachAttention((CheckEachAttentionRequest) request);
				break;
			///////////////////////////////////////////////////////////////////////////
			case UserFunctionType.SaveOccupation:// 保存职业
				response = personInfoService.saveOccupation((SaveOccupationRequest) request);
				break;
			case UserFunctionType.readAttention:
				response = personInfoService.readAttention((ReadAttentionRequest) request);
				break;
			case UserFunctionType.QUERY_MY_SKILL:// 查询我的技能列表
				response = personInfoService.queryMySkill((queryMyskillRequest) request);
				break;
			case UserFunctionType.SAVE_DV_SKILL_AUDIT:
				response = personInfoService.saveCustomerSkillCertify((SaveSkillAuditRequest) request);
				break;
			case UserFunctionType.updateNickname:
				response = editProfileService.updateNickName((UpdateNickNameReq) request);
				break;
			case UserFunctionType.updateHeadPortrait:
				response = editProfileService.updateHeadPortrait((UpdateHeadPortraitReq) request);
				break;
			case UserFunctionType.updateSignName:
				response = editProfileService.updateSignName((UpdateSignNameReq) request);
				break;
			case UserFunctionType.updateStarSign:
				response = editProfileService.updateStarSign((UpdateStarSignReq) request);
				break;
			case UserFunctionType.updateAppearance:
				response = editProfileService.updateAppearance((UpdateAppearanceReq) request);
				break;
			case UserFunctionType.queryInterestList:
				response = editProfileService.queryInterestList((QueryInterestListReq) request);
				break;
			case UserFunctionType.updateInterest:
				response = editProfileService.updateInterest((UpdateInterestReq) request);
				break;
			case UserFunctionType.removeAppearance:
				response = editProfileService.removeAppearance((RemoveAppearanceReq) request);
				break;
			// 个人中心
			case UserFunctionType.CUSTOMER_CENTER:
				response = personInfoService.queryCustomerCenter((CustomerCenterRequest) request);
				break;
			// 客户主页
			case UserFunctionType.CUSTOMER_HOME:
				response = personInfoService.queryCustomerHome((CustomerHomeRequest) request);
				break;
			// 客户等级
			case UserFunctionType.CUSTOMER_LEVEL:
				response = personInfoService.queryCustomerLevel((CustomerLevelRequest) request);
				break;
			case UserFunctionType.updateVoiceIdentificationCard:
				response = editProfileService.updateVoiceIdentificationCard((UpdateVoiceIdentificationCardReq) request);
				break;
			case UserFunctionType.removeVoiceIdentificationCard:
				response = editProfileService.removeVoiceIdentificationCard((RemoveVoiceIdentificationCardReq) request);
				break;
			case UserFunctionType.bindVXorQQ:
				response = commonPersonService.bindVXorQQ((BindVXorQQRequest) request);
				break;
			case UserFunctionType.removeBlacklist:
				response = blacklistService.removeBlacklist((RemoveBlacklistReq) request);
				break;
			case UserFunctionType.queryBlacklist:
				response = blacklistService.queryBlacklist((QueryBlacklistReq) request);
				break;
			case UserFunctionType.saveBlacklist:
				response = blacklistService.saveBlacklist((SaveBlacklistReq) request);
				break;
			case UserFunctionType.updateGender:
				response = editProfileService.updateGender((UpdateGenderReq) request);
				break;
			case UserFunctionType.updateBirthday:
				response = editProfileService.updateBirthday((UpdateBirthdayReq) request);
				break;
			case UserFunctionType.queryUserEditInfo:
				response = editProfileService.queryUserEditInfo((QueryUserEditInfoReq) request);
				break;
			case UserFunctionType.getAllDelate:
				response = delateService.getAllDelatesExcludeOther();
				break;
			case UserFunctionType.insertDelate:
				response = delateService.insertDelate((DelateInsertRequest) request);
				break;

			case UserFunctionType.NoReadAttentionCount:
				response = personInfoService.noReadAttentionCount((NoReadAttentionCountRequest) request);
				break;
			case UserFunctionType.insertFeedBack:
				response = feedBackService.insertFeedBack((FeedBackInsertRequest) request);
				break;
			case UserFunctionType.INTERNAL_MESSAGE:
				response = internalMessageService.queryMessages((InternalMessageRequest) request);
				break;
			case UserFunctionType.AddSystemUser:
				response = commonPersonService.addSystemUser((AddSystemUserRequest) request);
				break;
			case UserFunctionType.QUERY_DV_LIST_BY_TYPE:
				response = queryHomeBigvListService.queryClassifyBigvList((DaVListBySkillItemIdRequest) request);
				break;
			case UserFunctionType.appVersionManage:
				response = appVersionManageService.findAppVersionInfo((AppVersionManageRequest) request);
				break;
			case UserFunctionType.AddBookingMessage:
				response = userMessageService.saveBookingMessage((BookingMessageSaveRequest) request);
				break;
			case UserFunctionType.QueryBookingMessage:
				response = userMessageService.queryBookingMessage((BookingMessageQueryRequest) request);
				break;
			case UserFunctionType.IsBigVidentity:
				response = personInfoService.isBigVidentity((IsBigVidentityRequest) request);
				break;
			case UserFunctionType.loginOut:
				response = commonPersonService.loginOut((LoginOutRequest) request);
				break;
			case UserFunctionType.CUSYOMER_MESSAGE_SETTING:
				response = userMessageService.queryCustomerMessageSetting((CustomerMessageSettingQueryRequest) request);
				break;
			case UserFunctionType.ADD_CUSYOMER_MESSAGE_SETTING:
				response = userMessageService.saveCustomerMessageSetting((CustomerMsgSettingRequest) request);
				break;
			case UserFunctionType.CANCEL_ATTENTION:
				response = attentionService.cancelAttention((AttentionCancelRequest) request);
				break;
			/******** 初始化大V评分排名数据 *********/
			case UserFunctionType.INIT_BIGV_SCORE_RANK_DATA:
				response = scoreRankService.initBigvScoreRankData();
				break;
			case UserFunctionType.queryDeviceWhitelist:
				response = deviceWhitelistService.queryDeviceWhitelist((QueryDeviceWhitelistReq) request);
				break;
			case UserFunctionType.saveDeviceWhitelist:
				response = deviceWhitelistService.saveDeviceWhitelist((SaveDeviceWhitelistReq) request);
				break;
			case UserFunctionType.searchPersonByPhone:
				response = commonPersonService.searchPersonByPhone((SearchPersonByPhoneRequest) request);
				break;
			case UserFunctionType.Recent_Visit_List:
				response = customerVisitService.queryRecentVisitList((RecentVisitRequest)request);
				break;
			case UserFunctionType.Set_Visit_Read:
				response = customerVisitService.setVisitRead((SetVisitReadRequest)request);
				break;
			case UserFunctionType.CUSTOMER_APPLY_BIGV:
				response = personInfoService.submitCustomerApplyBigv((CustomerApplyBigvRequest)request);
				break;
			case UserFunctionType.WEIXIN:
				response = weiXinService.getOpenId((WeiXinRequest) request);
				break;
			case UserFunctionType.ADD_UPDATE_DEVICE_INFO:
				response = deviceInfoService.saveOrUpdateDeviceInfo((DeviceInfoRequest) request);
				break;
			default:
				throw new BizException(UserBizReturnCode.BizFunctionTypeNotMatch,
						UserBizReturnCode.BizFunctionTypeNotMatch.desc());
			}
		} catch (BaseException e) {
			logger.error("接口编码为：" + request.getBizCode() + "异常：" + e.getMessage(), e);
			response.setCode(e.getCode());
			response.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("接口编码为：" + request.getBizCode() + "异常：" + e.getMessage(), e);
			response.setCode(UserBizReturnCode.Unknown);
			response.setMessage(e.getMessage() == null ? e + "" : e.getMessage() + e);
		}
		logger.info("返回结果{}", response);
		return response;
	}
}
