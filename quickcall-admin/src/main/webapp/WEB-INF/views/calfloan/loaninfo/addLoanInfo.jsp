<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }贷款类型信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sysUserForm" name="loaninfoForm"
				action="loaninfo/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">名称<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="hidden" value="${entity.loanInfoId }"
							name="loanInfoId" /> <input type="text" class="form-control"
							id="loaninfo_appName" name="appName" value="${entity.appName }">
						<input type="hidden" class="form-control" name="imageUrl"
							id="loaninfoFile_input" value="${entity.imageUrl }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">副名称<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="loaninfo_viceAppName" name="viceAppName"
							value="${entity.viceAppName }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">类型<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<select class="form-control" id="loaninfo_loanCateId" name="loanCateId">
<!-- 								<option value="0">--请选择--</option> -->
								<c:forEach var="info" items="${infos }">
									<option value="${info.loanCateId }">${info.loanTitle }</option>
								</c:forEach>
							</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">最高额度<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" id="loaninfo_maxMoney"  class="form-control" name="maxMoney"
							value="${entity.maxMoney }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">申请人数<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="number" step="1" class="form-control" name="applicantsNum" id="loaninfo_applicantsNum"
							value="${entity.applicantsNum }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">日还款</label>
					<div class="col-sm-10">
						<input type="number" step="1" class="form-control" name="dayRepayment"
							value="${entity.dayRepayment }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">日利率<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="number" step="0.0001" id="loaninfo_rateMin" class="form-control" name="rateMin"
							value="${entity.rateMin }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">总利息</label>
					<div class="col-sm-10">
						<input type="number" step="0.01" class="form-control" name="allRota"
							value="${entity.allRota }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">贷款下限<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="number" step="1" id="loaninfo_loanRangeStart" class="form-control" name="loanRangeStart"
							value="${entity.loanRangeStart }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">贷款上限<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="number" step="1" id="loaninfo_loanRangeEnd" class="form-control" name="loanRangeEnd"
							value="${entity.loanRangeEnd }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">期限下限<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="number" step="1" id="loaninfo_timeLimitStart" class="form-control" name="timeLimitStart"
							 value="${entity.timeLimitStart }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">期限上限<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="number" step="1" id="loaninfo_timeLimitEnd" class="form-control" name="timeLimitEnd"
							 value="${entity.timeLimitEnd }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">H5链接<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="loaninfo_linkUrlH" name="linkUrlH"
							value="${entity.linkUrlH }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">申请条件<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="loaninfo_appCon" name="appCon"
							value="${entity.appCon }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">审核说明<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="loaninfo_auditDesc" name="auditDesc"
							value="${entity.auditDesc }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">产品介绍<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="loaninfo_productIntr" name="productIntr"
							value="${entity.productIntr }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">放款速度<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="loaninfo_moneyFastest" name="moneyFastest"
							value="${entity.moneyFastest }">
					</div>
				</div>
				<%-- <div class="form-group">
					<label class="col-sm-2 control-label">审核说明</label>
					<div class="col-sm-10">
						<c:forEach var="process" items="${processes }">
							<input type="checkbox"
							name="state" value="${process. }" ${process.processName=='1'?'checked':'' }>${process.processName }
						</c:forEach>
					</div>
				</div> --%>
				<div class="form-group">
					<label class="col-sm-2 control-label">推荐<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<label class="checkbox-inline"> <input type="radio"
							name="isRecommend" value="1" ${entity.isRecommend=='1'?'checked':'' }>
							是
						</label> <label class="checkbox-inline"> <input type="radio"
							name="isRecommend" value="0" ${entity.isRecommend=='0'?'checked':'' }>
							否
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">职业身份<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<label><input type="checkbox" name="profIdentity" ${fn:contains(entity.profIdentity,'1')?'checked':'' } value="1"/>上班族</label>
						<label><input type="checkbox" name="profIdentity" ${fn:contains(entity.profIdentity,'2')?'checked':'' } value="2"/>个体户</label>
						<label><input type="checkbox" name="profIdentity" ${fn:contains(entity.profIdentity,'3')?'checked':'' } value="3"/>学 生</label>
<!-- 						<select class="form-control" id="loaninfo_profIdentity" name="profIdentity"> -->
<!-- 							<option value="1">上班族</option> -->
<!-- 							<option value="2">个体户</option> -->
<!-- 							<option value="3">学 &nbsp;&nbsp;生</option> -->
<!-- 						</select> -->
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">状态<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<label class="checkbox-inline"> <input type="radio"
							name="state" value="1" ${entity.state=='1'?'checked':'' }>
							有效
						</label> <label class="checkbox-inline"> <input type="radio"
							name="state" value="0" ${entity.state=='0'?'checked':'' }>
							无效
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">序号<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="loaninfo_sort" name="sort"
							value="${entity.sort }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">时间复选<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="times" placeholder="多个天数用英文分号;隔开"
							 value="${entity.times }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">备注</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="remark"
							value="${entity.remark }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">图片上传<c:if test="${entity eq null }"><font color="red">&nbsp;*</font></c:if></label>
					<div class="col-sm-10">
						<div class="input-group">
							<input type="file" class="form-control" id="loaninfoFile"
								name="loaninfoFile"> <span class="input-group-btn">
								<button class="btn btn-success" type="button"
									id="uploadloaninfo">上传图片</button>
							</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">图片</label>
					<div class="col-sm-10">
						<div class="col-sm-6">
							<img src="${entity.imageUrl }" alt="暂无图片，点击上传！"
								class="img-rounded" style="max-width: 100%; max-height: 300px;"
								id="loaninfoFile_img">
						</div>
					</div>
				</div>
				<span id="tip" style="color: red;font-size: 14px;margin-left:20px; "></span>
			</form>
		</div>
		<div class="modal-footer">
			<button class="btn btn-default" data-dismiss="modal"
				aria-hidden="true">取消</button>
			<button class="btn btn-primary" data-dismiss="modal">保存</button>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="resources/js/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<script type="text/javascript">
	$("#loaninfo_profIdentity").val('${entity.profIdentity}');
	$("#loaninfo_loanCateId").val('${entity.loanCateId}');
	function check_fun(){
		$("#tip").html("");
		var b = true;
		var sort = $("#loaninfo_sort").val();
		var state = $('input[name="state"]:checked').val();
		var profIdentity = $('input[name="profIdentity"]:checked').val();
		var isRecommend = $('input[name="isRecommend"]:checked').val();
		var moneyFastest = $("#loaninfo_moneyFastest").val();
		var productIntr = $("#loaninfo_productIntr").val();
		var auditDesc = $("#loaninfo_auditDesc").val();
		var appCon = $("#loaninfo_appCon").val();
		var linkUrlH = $("#loaninfo_linkUrlH").val();
		var timeLimitEnd = $("#loaninfo_timeLimitEnd").val();
		var timeLimitStart = $("#loaninfo_timeLimitStart").val();
		var loanRangeEnd = $("#loaninfo_loanRangeEnd").val();
		var loanRangeStart = $("#loaninfo_loanRangeStart").val();
		var rateMin = $("#loaninfo_rateMin").val();
		var applicantsNum = $("#loaninfo_applicantsNum").val();
		var maxMoney = $("#loaninfo_maxMoney").val();
		var loanCateId = $("#loaninfo_loanCateId").val();
		var viceAppName = $("#loaninfo_viceAppName").val();
		var appName = $("#loaninfo_appName").val();
		if('${entity}' == ''){
			var file = document.loaninfoForm.loaninfoFile.value;
			var filepath = $("#loaninfoFile_input").val();
			if(file == "" || filepath == null || filepath == ''){
				$("#tip").html("请上传图片");
				b = false;
			}
		}
		if(sort == null || sort.trim() == ''){
			$("#tip").html("请输入序号");
			b = false;
		}
		if(state == undefined){
			$("#tip").html("请选择状态");
			b = false;
		}
		if(profIdentity == null || profIdentity.trim() == ''){
			$("#tip").html("请选择职业身份");
			b = false;
		}
		if(isRecommend == undefined){
			$("#tip").html("请选择是否推荐");
			b = false;
		}
		if(moneyFastest == null || moneyFastest.trim() == ''){
			$("#tip").html("请输入放款速度");
			b = false;
		}
		if(productIntr == null || productIntr.trim() == ''){
			$("#tip").html("请输入产品介绍");
			b = false;
		}
		if(auditDesc == null || auditDesc.trim() == ''){
			$("#tip").html("请输入审核说明");
			b = false;
		}
		if(appCon == null || appCon.trim() == ''){
			$("#tip").html("请输入申请条件");
			b = false;
		}
		if(linkUrlH == null || linkUrlH.trim() == ''){
			$("#tip").html("请输入H5链接");
			b = false;
		}
		if(parseInt(timeLimitEnd) < parseInt(timeLimitStart)){
			$("#tip").html("期限下限不能大于期限上限");
			b = false;
		}
		if(parseInt(timeLimitEnd) < 0){
			$("#tip").html("期限上限限必须大于等于0");
			b = false;
		}
		if(timeLimitEnd == null || timeLimitEnd.trim() == ''){
			$("#tip").html("请输入期限上限");
			b = false;
		}
		if(parseInt(timeLimitStart) < 0){
			$("#tip").html("期限下限必须大于等于0");
			b = false;
		}
		if(timeLimitStart == null || timeLimitStart.trim() == ''){
			$("#tip").html("请输入期限下限");
			b = false;
		}
		if(parseInt(loanRangeEnd) < parseInt(loanRangeStart)){
			$("#tip").html("贷款下限不能大于贷款上限");
			b = false;
		}
		if(parseInt(loanRangeEnd) < 0){
			$("#tip").html("贷款下限必须大于等于0");
			b = false;
		}
		if(loanRangeEnd == null || loanRangeEnd.trim() == ''){
			$("#tip").html("请输入贷款上限");
			b = false;
		}
		if(parseInt(loanRangeStart) < 0){
			$("#tip").html("贷款下限必须大于等于0");
			b = false;
		}
		if(loanRangeStart == null || loanRangeStart.trim() == ''){
			$("#tip").html("请输入贷款下限");
			b = false;
		}
		if(rateMin >= 1 || rateMin <= 0){
			$("#tip").html("日利率必须大于0且小于1");
			b = false;
		}
		if(rateMin == null || rateMin.trim() == ''){
			$("#tip").html("请输入日利率");
			b = false;
		}
		if(parseInt(applicantsNum) < 0){
			$("#tip").html("申请人数必须大于等于0");
			b = false;
		}
		if(applicantsNum == null || applicantsNum.trim() == ''){
			$("#tip").html("请输入申请人数");
			b = false;
		}
		if(maxMoney == null || maxMoney.trim() == ''){
			$("#tip").html("请输入最高额度");
			b = false;
		}
		if(loanCateId == null || loanCateId.trim() == ''){
			$("#tip").html("请选择类型");
			b = false;
		}
		if(viceAppName == null || viceAppName.trim() == ''){
			$("#tip").html("请输入副名称");
			b = false;
		}
		if(appName == null || appName.trim() == ''){
			$("#tip").html("请输入名称");
			b = false;
		}
		return b;
	}
	$(function() {
		$('#uploadloaninfo').click(function() {
			var file = document.loaninfoForm.loaninfoFile.value;
			if(file == ""){
				$("#tip").html("请选择图片");
				return false;
			}
			$.ajaxFileUpload({
				type : "post",
				dataType : "json",
				fileElementId : 'loaninfoFile',
				url : "upload/loaninfo.htm",
				data : {
					"id" : "${entity.loanInfoId }"
				},
				error : function(data) {
					alert("错误！");
				},
				success : function(data) {
					if (data.result = 'success') {
						console.info(data);
						alert("上传图片成功！");
						$('#loaninfoFile_input').val(data.imgUrl);
						$('#loaninfoFile_img').attr("src", data.imgUrl);
						$("#tip").html("");
					} else {
						alert(data.msg);
					}
				}
			});
		});
	});
</script>