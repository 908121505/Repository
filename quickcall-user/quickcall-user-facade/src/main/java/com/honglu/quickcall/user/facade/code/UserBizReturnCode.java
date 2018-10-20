package com.honglu.quickcall.user.facade.code;

import com.honglu.quickcall.common.api.code.AbstractEnum;
import com.honglu.quickcall.common.api.code.BizCode;
import com.honglu.quickcall.common.api.code.MyServiceCode;
import com.honglu.quickcall.common.api.code.ServiceCode;

/**
 * Created by len.song on 2017-12-07.
 * 异常错误编码
 */
public class UserBizReturnCode extends BizCode {
    //异常编码(501 - 999)


    public static final BizCode UserNotExist=new UserBizReturnCode("UserNotExist","501",MyServiceCode.USER,"用户不存在");
    public static final BizCode PwdError=new UserBizReturnCode("PwdError","502",MyServiceCode.USER,"登录密码错误");
    public static final BizCode findPwdError=new UserBizReturnCode("findPwdError","503",MyServiceCode.USER,"密码修改失败");

    public static final BizCode deviceNoError=new UserBizReturnCode("deviceNoError","504",MyServiceCode.USER,"设备号以注册两个账号");
    public static final BizCode RegisterSaveRongYunError =new UserBizReturnCode("RegisterSaveRongYunError","505",MyServiceCode.USER,"用户保存融云信息失败");

    public static final BizCode EruptClick = new UserBizReturnCode("EruptClick","506", MyServiceCode.USER,"点击过快");
    public static final BizCode CodeOvertime = new UserBizReturnCode("CodeOvertime","507", MyServiceCode.USER,"验证码超时");
    public static final BizCode CodeError = new UserBizReturnCode("CodeError","508", MyServiceCode.USER,"验证码错误");


    public static final BizCode exceedError=new UserBizReturnCode("exceedError","537",MyServiceCode.USER,"超出数量");
    public static final BizCode emptyError=new UserBizReturnCode("emptyError","538",MyServiceCode.USER,"数据为空");

    public static final BizCode UserIdIsNotNull=new UserBizReturnCode("UserIdIsNotNull","510",MyServiceCode.USER,"用户编号不能为空");
    public static final BizCode NoVipPermission=new UserBizReturnCode("NoVipPermission","511",MyServiceCode.USER,"没有VIP权限");
    public static final BizCode VipCommentOutOfLength=new UserBizReturnCode("VipCommentOutOfLength","512",MyServiceCode.USER,"vip视频评论超出了长度限制");
    public static final BizCode VipSOFF =new UserBizReturnCode("VipSOFF","513",MyServiceCode.USER,"vip视频已经解锁");
    public static final BizCode WeixinLoginError=new UserBizReturnCode("WeixinLoginError","514",MyServiceCode.USER,"微信授权登录失败");

    public static final BizCode IsExist =new UserBizReturnCode("IsExist","520",MyServiceCode.USER,"绑定关系已存在");
    public static final BizCode NOExistError=new UserBizReturnCode("NOExistError","521",MyServiceCode.USER,"绑定关系不已存在");

   

    public static final BizCode paramError=new UserBizReturnCode("paramError","535",MyServiceCode.USER,"参数错误");
    public static final BizCode jdbcError=new UserBizReturnCode("jdbcError","536",MyServiceCode.USER,"操作数据库异常");
    public static final BizCode closeError=new UserBizReturnCode("closeError","539",MyServiceCode.USER,"用户状态关闭中");

    public static final BizCode nickNameSensitive=new UserBizReturnCode("nickNameSensitive","540",MyServiceCode.USER,"昵称包含非法内容");
    public static final BizCode signSensitive=new UserBizReturnCode("signSensitive","541",MyServiceCode.USER,"个人介绍包含非法内容");
    
    public static final BizCode userIsExists=new UserBizReturnCode("userIsExists","542",MyServiceCode.USER,"绑定账户已存在");
    
    public static final BizCode skillCertifyError=new UserBizReturnCode("skillCertifyError","551",MyServiceCode.USER,"审核中不能上传声音");
    
    public static final BizCode CheckSignError = new UserBizReturnCode("CheckSignError","997", MyServiceCode.USER,"请求参数验签不正确");
    public static final BizCode BizFunctionTypeNotMatch=new UserBizReturnCode("BizFunctionTypeNotMatch","998",MyServiceCode.USER,"接口功能编码不匹配");
    public static final BizCode Unknown = new UserBizReturnCode("Unknown","999", MyServiceCode.USER,"未知异常");



    public UserBizReturnCode() {

    }

    public UserBizReturnCode(String name, String code, ServiceCode serviceCode, String desc) {
        super(name, code, serviceCode, desc);
    }

    @Override
    protected Class<? extends AbstractEnum> getEnumType() {
        return UserBizReturnCode.class;
    }
}
