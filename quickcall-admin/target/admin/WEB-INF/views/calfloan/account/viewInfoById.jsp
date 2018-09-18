<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-dialog">
	<div class="modal-content">


		<style>
* {
	margin: 0;
	padding: 0;
}

body {
	margin: 50px;
	font-size: 12px;
	color: #666;
}

li {
	list-style: none;
}

.list {
	height: 2950px;
	padding: 10px;
	font-size: 14px;
	line-height: 24px;
	border: 1px #cfedff solid;
	border-top: 0;
}

.tab {
	height: 40px;
	border: 1px #cfedff solid;
	border-bottom: 0;
	background: url(images/title.gif) repeat-x;
}

.tab ul {
	margin: 0;
	padding: 0;
}

.tab li {
width:20%;
	float: left;
	height: 70px;
	line-height: 70px;
	text-align: center;
	border-right: 1px #ebf7ff solid;
	cursor: pointer;
}

.tab li.now {

	color: #5299c4;
	background: #fff;
	font-weight: bold;
}

.block {
	display: block;
}
</style>
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">用户信息详情</h3>
		</div>
		<div class="modal-body">

			<div class="tab">
				<ul>
					<li id="one1" onmouseover="setTab('one',1,5)" class="now">用户上传信息</li>
					<li id="one2" onmouseover="setTab('one',2,5)" class="now">face++报告</li>
					<li id="one3" onmouseover="setTab('one',3,5)" class="now">芝麻信用</li>
					<li id="one4" onmouseover="setTab('one',4,5)" class="now">同盾</li>
					<li id="one5" onmouseover="setTab('one',5,5)" class="now">聚信立</li>
				</ul>
			</div>
			 <div class="list">
				<div id="one_con1" style="display: none;"> 
							<form class="form-horizontal" method="post" id="sysUserForm"
				name="bannerForm" action="" role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">用户id</label>
					<div class="col-sm-10">
						<input type="hidden" value="${entity.gfCreditCardId }"
							name="gfCreditCardId" /> <input type="text"
							class="form-control required" disabled="disabled" id="customerId"
							name="customerId" value="${entity.customerId }"> <input
							type="hidden" class="form-control" name="image"
							id="bannerFile_input" value="">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">客户名称</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="name"
							name="name" disabled="disabled" value="${entity.name }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">用户标签</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" disabled="disabled"
							id="userLabel" name="userLabel" value="${entity.userLabel }"><br>

					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">手机号码</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="phoneNum"
							name="phoneNum" disabled="disabled" value="${entity.phoneNum }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">身份证号码</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="idCode"
							name="idCode" disabled="disabled" value="${entity.idCode }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">身份证图片</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="idCardUrl"
							name="idCardUrl" disabled="disabled" value="${entity.idCardUrl }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">性别</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="gender"
							name="gender" disabled="disabled" value="${entity.gender }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">年龄</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="age"
							name="age" disabled="disabled" value="${entity.age }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">民族</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="minzu"
							name="minzu" disabled="disabled" value="${entity.minzu }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">现居地址</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control"
							id="addressDetail" name="addressDetail" disabled="disabled"
							value="${entity.addressDetail }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">房产</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="isHouse"
							name="isHouse" disabled="disabled" value="${entity.isHouse }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">车产</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="isCar"
							name="isCar" disabled="disabled" value="${entity.isCar }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">毕业学校</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="schoolName"
							name="schoolName" disabled="disabled"
							value="${entity.schoolName }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">学历</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="xueLi"
							name="xueLi" disabled="disabled" value="${entity.xueLi }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">入学时间</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="inSchool"
							name="inSchool" disabled="disabled" value="${entity.inSchool }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">毕业时间</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="outSchool"
							name="outSchool" disabled="disabled" value="${entity.outSchool }">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">公司名称</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="companyName"
							name="companyName" disabled="disabled"
							value="${entity.companyName }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">月收入</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="moneyIncome"
							name="moneyIncome" disabled="disabled"
							value="${entity.moneyIncome }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">月收入流水账单</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="moneyBill"
							name="moneyBill" disabled="disabled" value="${entity.moneyBill }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">公积金</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="accFund"
							name="accFund" disabled="disabled" value="${entity.accFund }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">公积金认证</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="isConfirmAcc"
							name="isConfirmAcc" disabled="disabled"
							value="${entity.isConfirmAcc }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">社保</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control"
							id="socialSecurity" name="socialSecurity" disabled="disabled"
							value="${entity.socialSecurity }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">社保认证</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control"
							id="isSocialSecurity" name="isSocialSecurity" disabled="disabled"
							value="${entity.isSocialSecurity }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">工作年限</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="workYear"
							name="workYear" disabled="disabled" value="${entity.workYear }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">银行卡号</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="bankCardNo"
							name="bankCardNo" disabled="disabled"
							value="${entity.bankCardNo }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">婚姻状况</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="ifMarriage"
							name="ifMarriage" disabled="disabled"
							value="${entity.ifMarriage }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">紧急联系人1</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control"
							id="emergencyContact1" name="emergencyContact1"
							disabled="disabled" value="${entity.emergencyContact1 }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">紧急联系人2</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control"
							id="emergencyContact2" name="emergencyContact2"
							disabled="disabled" value="${entity.emergencyContact2 }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">紧急联系人3</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control"
							id="emergencyContact3" name="emergencyContact3"
							disabled="disabled" value="${entity.emergencyContact3 }">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">淘宝账号</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="taoBaoNo"
							name="taoBaoNo" disabled="disabled" value="${entity.taoBaoNo }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">消费测评</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="taoBaoNoMea"
							name="taoBaoNoMea" disabled="disabled"
							value="${entity.taoBaoNoMea }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">京东账号</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="jingDong"
							name="jingDong" disabled="disabled" value="${entity.jingDong }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">消费测评</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="jingDongMea"
							name="jingDongMea" disabled="disabled"
							value="${entity.jingDongMea }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">邮箱</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="email"
							name="email" disabled="disabled" value="${entity.email }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">QQ号码</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="qqNo"
							name="qqNo" disabled="disabled" value="${entity.qqNo }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">微信号码</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="weiXinNo"
							name="weiXinNo" disabled="disabled" value="${entity.weiXinNo }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">芝麻分数</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="zhiMaFen"
							name="zhiMaFen" disabled="disabled" value="${entity.zhiMaFen }">
					</div>
				</div>


			</form>
				</div> 
				<div id="one_con2" style="display: none;">
					<form class="form-horizontal" method="post" id="sysUserForm"
				name="bannerForm" action="" role="form">
			
				<div class="form-group">
					<label class="col-sm-2 control-label">人脸识别质量评分</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="shiBieFen"
							name="shiBieFen" disabled="disabled" value="${entity.shiBieFen}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">活体照片</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="huoCardUrl"
							name="huoCardUrl" disabled="disabled" value="${entity.huoCardUrl }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">姓名</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="name"
							name="name" disabled="disabled" value="${entity.name }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">民族</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="minzu"
							name="minzu" disabled="disabled" value="${entity.minzu }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">性别</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="gender"
							name="gender" disabled="disabled" value="${entity.gender }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">出生</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="chuShen"
							name="chuShen" disabled="disabled" value="${entity.chuShen }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">住址</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="addressDetail"
							name="addressDetail" disabled="disabled" value="${entity.addressDetail }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">公民身份证号码</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="idCode"
							name="idCode" disabled="disabled" value="${entity.idCode }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">签发机关</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="qianFanJ"
							name="qianFanJ" disabled="disabled" value="${entity.qianFanJ }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">有效期限</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="youXiaoQ"
							name="youXiaoQ" disabled="disabled" value="${entity.youXiaoQ }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">身份证正面照</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="fontPic"
							name="fontPic" disabled="disabled" value="${entity.fontPic }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">身份证反面照</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="backPic"
							name="backPic" disabled="disabled" value="${entity.backPic }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">综合结果</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="zengHeJ"
							name="zengHeJ" disabled="disabled" value="${entity.zengHeJ }">
					</div>
				</div>
				

			
			</form>
				</div>
				<div id="one_con3" style="display: none;">
					<form class="form-horizontal" method="post" id="sysUserForm"
				name="bannerForm" action="" role="form">
				
				<div class="form-group">
					<label class="col-sm-2 control-label">欺诈关注清单业务号</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="qinZY"
							name="qinZY" disabled="disabled" value="${entity.qinZY }">
					</div>
				</div>
			
				<div class="form-group">
					<label class="col-sm-2 control-label">是否命中</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="IsNotM"
							name="IsNotM" disabled="disabled" value="${entity.IsNotM }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Riskcode列表</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="Riskcode"
							name="Riskcode" disabled="disabled" value="${entity.Riskcode }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">芝麻信用业务号</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="zhiMaY"
							name="zhiMaY" disabled="disabled" value="${entity.zhiMaY }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">芝麻信用评分</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="zhiMaFen"
							name="zhiMaFen" disabled="disabled" value="${entity.zhiMaFen }">
					</div>
				</div>   
			

			
			</form>
				</div>
				<div id="one_con4" style="display: none;">
					<form class="form-horizontal" method="post" id="sysUserForm"
				name="bannerForm" action="" role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">审核报告编号</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="shenHB"
							name="shenHB" disabled="disabled" value="${entity.shenHB }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">申请编号</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="shenQB"
							name="shenQB" disabled="disabled" value="${entity.shenQB }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">报告生成时间</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="reportTime"
							name="reportTime" disabled="disabled" value="${entity.reportTime }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">申请借款日期</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="loanDate"
							name="loanDate" disabled="disabled" value="${entity.loanDate }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">姓名</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="name"
							name="name" disabled="disabled" value="${entity.name }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">手机号码</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="phoneNum"
							name="phoneNum" disabled="disabled" value="${entity.phoneNum }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">身份证号</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="idCode"
							name="idCode" disabled="disabled" value="${entity.idCode }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">身份证所属地</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="shenFenSS"
							name="shenFenSS" disabled="disabled" value="${entity.shenFenSS }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">手机所属地</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="shoujiSS"
							name="shoujiSS" disabled="disabled" value="${entity.shoujiSS }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">IP所属地</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="ipSS"
							name="ipSS" disabled="disabled" value="${entity.ipSS }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">wifi所属地</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="wifiSS"
							name="wifiSS" disabled="disabled" value="${entity.wifiSS }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">基站所属地</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="weiXinNo"
							name="weiXinNo" disabled="disabled" value="${entity.weiXinNo }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">银行卡所属地</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="bankSS"
							name="bankSS" disabled="disabled" value="${entity.bankSS }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">检查项</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="itemName"
							name="itemName" disabled="disabled" value="${entity.itemName }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">风险等级</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="riskLevel"
							name="riskLevel" disabled="disabled" value="${entity.riskLevel }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">备注</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="remark"
							name="remark" disabled="disabled" value="${entity.remark }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">服务费名称</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="fuWuM"
							name="fuWuM" disabled="disabled" value="${entity.fuWuM }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">校验参数</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="xiaoYC"
							name="xiaoYC" disabled="disabled" value="${entity.xiaoYC }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">调用结果</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="diaoCJ"
							name="diaoCJ" disabled="disabled" value="${entity.diaoCJ }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">计费情况</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="jiFeQ"
							name="jiFeQ" disabled="disabled" value="${entity.jiFeQ }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">被执行人姓名</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="zhiXM"
							name="zhiXM" disabled="disabled" value="${entity.zhiXM }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">性别</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="gender"
							name="gender" disabled="disabled" value="${entity.gender }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">年龄</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="age"
							name="age" disabled="disabled" value="${entity.age }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">执行法院</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="zhixingFY"
							name="zhixingFY" disabled="disabled" value="${entity.zhixingFY }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">省份</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="shengFEN"
							name="shengFEN" disabled="disabled" value="${entity.shengFEN }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">执行依据文号</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="yijuWH"
							name="yijuWH" disabled="disabled" value="${entity.yijuWH }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">立案时间</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="lianSJ"
							name="lianSJ" disabled="disabled" value="${entity.lianSJ }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">案号</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="caseNumber"
							name="caseNumber" disabled="disabled" value="${entity.caseNumber }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">被执行人的履行性质</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="xinzhiL"
							name="xinzhiL" disabled="disabled" value="${entity.xinzhiL }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">失信被执行人行为具体情形</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="discreditDetail"
							name="discreditDetail" disabled="disabled" value="${entity.discreditDetail }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">被执行人姓名</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="Kname"
							name="Kname" disabled="disabled" value="${entity.Kname }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">执行法院</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="courtName"
							name="courtName" disabled="disabled" value="${entity.courtName }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">立案时间</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="filingTime"
							name="filingTime" disabled="disabled" value="${entity.filingTime }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">执行标的</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="executionNumber"
							name="executionNumber" disabled="disabled" value="${entity.executionNumber }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">执行状态</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="executionStatus"
							name="executionStatus" disabled="disabled" value="${entity.executionStatus }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">做生效法律文书确定的义务</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="duty"
							name="duty" disabled="disabled" value="${entity.duty }">
					</div>
				</div>
			</form>
				</div>
				<div id="one_con5" style="display: none;">
					<form class="form-horizontal" method="post" id="sysUserForm"
				name="bannerForm" action="" role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">姓名</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="name"
							name="name" disabled="disabled" value="${entity.name }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">身份证号码有效</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="shenfenYX"
							name="shenfenYX" disabled="disabled" value="${entity.shenfenYX }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">身份证归属地</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="shenFenSS"
							name="shenFenSS" disabled="disabled" value="${entity.shenFenSS }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">手机号码归属地</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="shoujiSS"
							name="shoujiSS" disabled="disabled" value="${entity.shoujiSS }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">手机所属运营商</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="shoujiSY"
							name="shoujiSY" disabled="disabled" value="${entity.shoujiSY }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">身份证号码</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="idCode"
							name="idCode" disabled="disabled" value="${entity.idCode }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">手机号码</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="phoneNum"
							name="phoneNum" disabled="disabled" value="${entity.phoneNum }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">年龄</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="age"
							name="age" disabled="disabled" value="${entity.age }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">性别</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="gender"
							name="gender" disabled="disabled" value="${entity.gender }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">注册App数量</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="appCount"
							name="appCount" disabled="disabled" value="${entity.appCount }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">App类型</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="appType"
							name="appType" disabled="disabled" value="${entity.appType }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">注册数量</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="appCount"
							name="appCount" disabled="disabled" value="${entity.appCount }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">黑中介分数</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="heiFEN"
							name="heiFEN" disabled="disabled" value="${entity.heiFEN }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">直接联系人在黑名单数量</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="heiCount"
							name="heiCount" disabled="disabled" value="${entity.heiCount }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">直接联系人总数</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="lianCount"
							name="lianCount" disabled="disabled" value="${entity.lianCount }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">间接联系人在黑名单数量</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="heiCount"
							name="heiCount" disabled="disabled" value="${entity.heiCount }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">引起黑名单的直接联系人数量</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="heiCount"
							name="heiCount" disabled="disabled" value="${entity.heiCount }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">引起黑名单的直接联系人占比</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="heiBL"
							name="heiBL" disabled="disabled" value="${entity.heiBL }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">被标记的黑名单分类</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="heiCL"
							name="heiCL" disabled="disabled" value="${entity.heiCL }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">手机和姓名是否在黑名单</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="heiMD"
							name="heiMD" disabled="disabled" value="${entity.heiMD }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">身份证姓名黑名单更新时间</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="Hcreate"
							name="Hcreate" disabled="disabled" value="${entity.Hcreate }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">身份证和姓名是否在黑名单</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="HmingD"
							name="HmingD" disabled="disabled" value="${entity.HmingD }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">姓名和电话黑名单更新信息</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="HmingXX"
							name="HmingXX" disabled="disabled" value="${entity.HmingXX }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">名称</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="name"
							name="name" disabled="disabled" value="${entity.name }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">地址</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="addressDetail"
							name="addressDetail" disabled="disabled" value="${entity.addressDetail }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">累计借入本金</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="leiJB"
							name="leiJB" disabled="disabled" value="${entity.leiJB }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">性别</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="gender"
							name="gender" disabled="disabled" value="${entity.gender }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">累计已还金额</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="yiHJ"
							name="yiHJ" disabled="disabled" value="${entity.yiHJ }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">最大逾期天数</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="zuiTS"
							name="zuiTS" disabled="disabled" value="${entity.zuiTS }">
					</div>
				</div>
					<div class="form-group">
					<label class="col-sm-2 control-label">信贷逾期次数</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="discreditTimes"
							name="discreditTimes" disabled="disabled" value="${entity.discreditTimes }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">查询日期</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="chaXUnR"
							name="chaXUnR" disabled="disabled" value="${entity.chaXUnR }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">规则的类型</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="Ktype"
							name="Ktype" disabled="disabled" value="${entity.Ktype }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">机构类型</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="jgType"
							name="jgType" disabled="disabled" value="${entity.jgType }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">是否是本机构查询</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="IsNotCHU"
							name="IsNotCHU" disabled="disabled" value="${entity.IsNotCHU }">
					</div>
				</div>
					<div class="form-group">
					<label class="col-sm-2 control-label">检查项编号</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="itemId"
							name="itemId" disabled="disabled" value="${entity.itemId }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">最后使用时间</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="createTime"
							name="createTime" disabled="disabled" value="${entity.createTime }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">姓名</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="name"
							name="name" disabled="disabled" value="${entity.name }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">身份证号</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="idCode"
							name="idCode" disabled="disabled" value="${entity.idCode }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">机构类型</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="jiGType"
							name="jiGType" disabled="disabled" value="${entity.jiGType }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">手机号码</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id=phoneNum
							name="phoneNum" disabled="disabled" value="${entity.phoneNum }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">运营商名称</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="yunYN"
							name="yunYN" disabled="disabled" value="${entity.yunYN }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">归属地</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="addressDetail"
							name="addressDetail" disabled="disabled" value="${entity.addressDetail }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">机构类型</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="jiGType"
							name="jiGType" disabled="disabled" value="${entity.jiGType }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">风险类型</label>
					<div class="col-sm-10">
						<input type="text" step="1" class="form-control" id="fraudType"
							name="fraudType" disabled="disabled" value="${entity.fraudType }">
					</div>
				</div>
				
			</form>
				</div>	
					
		<!-- 	</div> -->
			<br />
			<br />
			<br />

		
		</div>

		<div class="modal-footer">
			<button class="btn btn-default" data-dismiss="modal"
				aria-hidden="true">关闭</button>

		</div>


	</div>
</div>
<script type="text/javascript"
	src="resources/js/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<script type="text/javascript">
	function check_fun() {
		$("#tip").html("");
		var b = true;
		return b;
	}

	function setTab(name, num, n) {
		for (i = 1; i <= n; i++) {
			var menu = document.getElementById(name + i);
			var con = document.getElementById(name + "_" + "con" + i);
			menu.className = i == num ? "now" : "";
			con.style.display = i == num ? "block" : "none";
		}
	}
</script>