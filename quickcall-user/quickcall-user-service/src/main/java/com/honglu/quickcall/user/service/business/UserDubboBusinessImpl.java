package com.honglu.quickcall.user.service.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.exception.BaseException;
import com.honglu.quickcall.common.api.exception.BizException;
import com.honglu.quickcall.common.api.exchange.AbstractRequest;
import com.honglu.quickcall.common.api.exchange.CommonResponse;
import com.honglu.quickcall.user.facade.business.UserDubboBusiness;
import com.honglu.quickcall.user.facade.code.UserBizReturnCode;
import com.honglu.quickcall.user.facade.code.UserFunctionType;
import com.honglu.quickcall.user.facade.exchange.request.AddOrCancelFansRequest;
import com.honglu.quickcall.user.facade.exchange.request.CheckAttentionRequest;
import com.honglu.quickcall.user.facade.exchange.request.GetSmsCodeRequest;
import com.honglu.quickcall.user.facade.exchange.request.IsPhoneExistsRequest;
import com.honglu.quickcall.user.facade.exchange.request.PersonInfoRequest;
import com.honglu.quickcall.user.facade.exchange.request.QueryAttentionFansListRequest;
import com.honglu.quickcall.user.facade.exchange.request.QueryInterestListRequest;
import com.honglu.quickcall.user.facade.exchange.request.QueryOccupationListRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveBirthRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveCertificationRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveGenderRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveInterestRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveNickNameRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveOccupationRequest;
import com.honglu.quickcall.user.facade.exchange.request.SaveSignNameRequest;
import com.honglu.quickcall.user.facade.exchange.request.SetHeardUrlRequest;
import com.honglu.quickcall.user.facade.exchange.request.SetPwdRequest;
import com.honglu.quickcall.user.facade.exchange.request.ShowHomePageLogout;
import com.honglu.quickcall.user.facade.exchange.request.UserIdCardInfoRequest;
import com.honglu.quickcall.user.facade.exchange.request.UserLoginRequest;
import com.honglu.quickcall.user.facade.exchange.request.UserRegisterRequest;
import com.honglu.quickcall.user.facade.exchange.request.UserUnreadMessageNumRequest;
import com.honglu.quickcall.user.service.service.CommonPersonService;
import com.honglu.quickcall.user.service.service.PersonInfoService;
import com.honglu.quickcall.user.service.service.UserMessageService;
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

	@Override
	public CommonResponse excute(AbstractRequest request) {
		if (request == null) {
			throw new BizException(BizCode.ParamError, BizCode.ParamError.desc());
		}
		CommonResponse response = new CommonResponse();
		try {
			switch (request.getBizCode()) {
			case UserFunctionType.PersonInfo:// 查看个人信息
				response = personInfoService.queryPersonInfo((PersonInfoRequest) request);
				break;

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
            case UserFunctionType.UNREAD_MESSAGE_NUM:
                response=  userMessageService.queryUserUnreadMessageNum((UserUnreadMessageNumRequest)request);
                break;
            case UserFunctionType.USER_ID_CARD_CERTIFY_INFO:
                response=  commonPersonService.queryUserIdCardCertificationInfo((UserIdCardInfoRequest)request);
                break;
            case UserFunctionType.SAVE_USER_CERTIFY_INFO:
                response=  commonPersonService.saveUserCertificationInfo((SaveCertificationRequest)request);
                break;
			case UserFunctionType.SaveNicknameImage:// 保存昵称和头像
				response = personInfoService.saveNicknameImage((SaveNickNameRequest) request);
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
			case UserFunctionType.QUERY_INTEREST_LIST://查询兴趣列表
				response = personInfoService.queryInterestList((QueryInterestListRequest) request);
				break;
			case UserFunctionType.QUERY_OCCUPATION_LIST://查询职业列表
				response = personInfoService.queryOccupationList((QueryOccupationListRequest) request);
				break;
			case UserFunctionType.QUERY_ATTENDTION_FANS_LIST://查询关注/粉丝列表
				response = personInfoService.queryAttentionFansList((QueryAttentionFansListRequest) request);
				break;
			case UserFunctionType.ADD_OR_UPDATE_FANS://添加/取消关注
				response = personInfoService.addOrCancelFans((AddOrCancelFansRequest) request);
				break;
			case UserFunctionType.CHECK_ATTENTION://判断是否关注对方
				response = personInfoService.checkAttention((CheckAttentionRequest) request);
				break;
			///////////////////////////////////////////////////////////////////////////
			case UserFunctionType.SaveOccupation:// 保存职业
				response = personInfoService.saveOccupation((SaveOccupationRequest) request);
				break;
			case UserFunctionType.ShowHomePageLogout:// 大V主页，普通用户主页（客态）
				response = personInfoService.showHomePageLogout((ShowHomePageLogout) request);
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
