<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }ICON信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sysUserForm" name="bannerForm"
				action="icon/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">ID</label>
					<div class="col-sm-10">
						<input type="text" class="form-control required" value="${entity.zoneId }" readonly = "readonly"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">icon名<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="hidden" value="${entity.zoneId }" name="zoneId" /> 
						<input type="text" class="form-control required" id="zoneNames" name="zoneName"
							value="${entity.zoneName }">
							
					    <input type="hidden"
							class="form-control" name="labelBackgroundImg" id="bannerFile_input"
							value="${entity.labelBackgroundImg }">
						<input type="hidden"
							class="form-control" name="backgroundPicture" id="backgroundPicture"
							value="${entity.backgroundPicture }">
						<input type="hidden"
							class="form-control" name="dynamicPicture" id="dynamicPicture"
							value="${entity.dynamicPicture }">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">匹配用户</label>
					<div class="col-sm-10">
				    	<select class="form-control" id="matchTheUser" name="matchTheUser">
						     <option value="">请选择</option>
						      <c:forEach var="ul" items="${userLabelList}">
						         <option value="${ul.labelCode}"   ${entity.matchTheUser eq ul.labelCode?'selected="selected"':''} >${ul.labelName}</option>
						      </c:forEach>
						</select>
						
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">链接<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="linkUrlH" name="linkUrlH"
							value="${entity.linkUrlH }"><br>
							<font color="red">
							(注：如果是跳转到专区,链接如下示列：xulugj://www.xulu.com/jump?need_login=0&url=http://test01gjh5.xnsudai.com/html/activity/makeMoney/prefecture.html?zoneId=603496443e72424e9147bbb5d2bff0be&zoneName=至尊精英
							&nbsp;&nbsp;&nbsp;(http://test01gjh5.xnsudai.com)测试域名,  (http://gjh5.mydkguanjia.com) 生产域名 专区编号和名称到专区管理界面获取
							</font>
							<!-- <font color="red">(注：如果是跳转到APP产品详情页URL传入产品ID，产品ID从产品管理-产品详情页面获取，格式如下：productId=abd815646d8a4544b7c454d5e77a063b )</font> -->
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">状态<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<label class="checkbox-inline"> <input type="radio"
							name="state" value="1" ${entity.state=='1'?'checked':'' }>
							开启
						</label> <label class="checkbox-inline"> <input type="radio"
							name="state" value="0" ${entity.state=='0'?'checked':'' }>
							关闭
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">权重<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control required" id="zoneType" name="zoneType"
							value="${entity.zoneType }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">类型<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
				    	<select class="form-control" id="homeType" name="homeType">
				    		<option value="6" ${entity.homeType==6?'selected':'' }>首页</option>
						    <option value="5" ${entity.homeType==5?'selected':'' }>工具</option>
						     <option value="9" ${entity.homeType==9?'selected':'' }>工具新icon</option>
							<option value="8" ${entity.homeType==8?'selected':'' }>底部icon</option>
							<option value="10" ${entity.homeType==10?'selected':'' }>活动（必下款）内部icon</option>
						</select>
					</div>
				</div>

				<div class="form-group" id="toolTypeDiv" ${(entity.homeType!='5')?'style="display: none"':''}>
					<label class="col-sm-2 control-label">工具类型<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<select class="form-control" id="toolType" name="toolType" >
							<%-- <option value="0" ${(entity.toolType!='1' && entity.toolType!='2')?'selected':''}></option> --%>
							<option value="1" ${entity.toolType=='1'?'selected':'' }>管家工具</option>
							<option value="2" ${entity.toolType=='2'?'selected':'' }>惠花花工具</option>
						</select>
					</div>
				</div>
				<div class="form-group" id="iconLabelDiv">
					<label class="col-sm-2 control-label">工具新icon标签</label>
					<div class="col-sm-10">
						<input type="text" class="form-control required" id="iconLabel" name="iconLabel"
							value="${entity.iconLabel }">
					</div>
				</div>
				<div class="form-group" id="isProductDiv">
					<label class="col-sm-2 control-label">是否为商户<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						
                            <select class="form-control" id="isProduct" name="isProduct" >
							<option value="0" ${entity.isProduct=='0'?'selected':'' }>否</option>
							<option value="1" ${entity.isProduct=='1'?'selected':'' }>是</option>
						</select>							
					</div>
				</div>
				
				

				<!-- 作用版本号 -->
				<div class="form-group" id="actionVersionDiv">
					<label class="col-sm-2 control-label">作用版本</label>

					<div class="col-sm-10">
						<div style="float: left; width: 40%">
							<select class="form-control" id="versionCompareType" name="versionCompareType" >
								<option value="0" ${entity.versionCompareType =='0'?'selected':'' }>所有版本</option>
								<option value="1" ${entity.versionCompareType =='1'?'selected':'' }>大于</option>
								<option value="2" ${entity.versionCompareType =='2'?'selected':'' }>小于</option>
								<option value="3" ${entity.versionCompareType =='3'?'selected':'' }>等于</option>
								<option value="4" ${entity.versionCompareType =='4'?'selected':'' }>大于等于</option>
								<option value="5" ${entity.versionCompareType =='5'?'selected':'' }>小于等于</option>
							</select>
						</div>

						<div style="float: right" >
							<input type="text" class="form-control required" id="actionVersion" name="actionVersion"
								   value="${entity.actionVersion}" placeholder="请输入版本号" >
							<font color="red">
								(格式类型：xx.yy.zz 例如 4.3.0)
							</font>
						</div>

					</div>
				</div>
				<!-- 作用设备号 -->
				<div class="form-group" id="actionDeviceTypeDiv">
					<label class="col-sm-2 control-label">作用设备类型<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<select class="form-control" id="actionDeviceType" name="actionDeviceType" >
							<option value="0" ${entity.actionDeviceType =='0'?'selected':'' }>不限设备</option>
							<option value="1" ${entity.actionDeviceType =='1'?'selected':'' }>安卓</option>
							<option value="2" ${entity.actionDeviceType =='2'?'selected':'' }>ios</option>
						</select>
					</div>
				</div>
				
					<div class="form-group">
					<label class="col-sm-2 control-label">开始时间<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" id="banner_startTime" class="form-control" autocomplete="off"
							onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'1950-01-01',maxDate:'#F{$dp.$D(\'banner_endTime\')}'})"
							value="${entity.startTime }" name="startTime" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">结束时间<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" id="banner_endTime" class="form-control" autocomplete="off"
							onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'banner_startTime\')}',maxDate:'2050-12-31'})"
							value="${entity.endTime }" name="endTime" />
					</div>
				</div>
				
				

				<div class="form-group">
					<label class="col-sm-2 control-label">跳转类型<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
				    	<select class="form-control" id="jumpType" name="jumpType">
				    		<option value="1" ${entity.jumpType=='1'?'selected':'' } >H5</option>
						    <option value="2" ${entity.jumpType=='2'?'selected':'' } >内页</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">字体颜色<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control required" id="labelLcolor" name="labelLcolor"
							value="${entity.labelLcolor }">
					</div>
				</div>
				
					<div class="form-group">
					<label class="col-sm-2 control-label">字体大小<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control required" id="labelFontSize" name="labelFontSize"
							value="${entity.labelFontSize }">
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
					<label class="col-sm-2 control-label">用户标签ID(用户身份需要)</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="matchTheUser"
							value="${entity.matchTheUser }">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">图片上传<c:if test="${entity eq null }"><font color="red">&nbsp;*</font></c:if>
					</label>
					
					<div class="col-sm-10">
						<div class="input-group">
							<input type="file" class="form-control" id="appversionFile"
								name="appversionFile"> <span class="input-group-btn">
								<button class="btn btn-success" type="button" id="uploadBanner">上传图片</button>
							</span>
						</div>
					</div>
				</div>
		      <div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">背景图片</label>
					<div class="col-sm-10">
						<div class="col-sm-6">
							<img src="${entity.labelBackgroundImg }" alt="暂无图片，点击上传！" class="img-rounded"
								style="max-width: 100%; max-height: 300px;" id="bannerFile_img">
						</div>
					</div>
				</div>
				<span id="tip" style="color: red;font-size: 14px;margin-left:20px; "></span>
				
				
				<div  id="img" ${(entity.homeType!='8')?'style="display: none"':''}>
					<div class="form-group">
						<label class="col-sm-2 control-label">图片2上传<c:if test="${entity eq null }"><font color="red">&nbsp;*</font></c:if>
						</label>
						
						<div class="col-sm-10">
							<div class="input-group">
								<input type="file" class="form-control" id="backgroundimg"
									name="backgroundimg"> <span class="input-group-btn">
									<button class="btn btn-success" type="button" id="uploadBannerTwo">上传图片</button>
								</span>
							</div>
						</div>
					</div>
			      <div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">背景图片</label>
						<div class="col-sm-10">
							<div class="col-sm-6">
								<img src="${entity.backgroundPicture }" alt="暂无图片，点击上传！" class="img-rounded"
									style="max-width: 100%; max-height: 300px;" id="zoneFileimg">
							</div>
						</div>
					</div>
					<span id="twotip" style="color: red;font-size: 14px;margin-left:20px; "></span>
				</div>
				
				
				<!-- 动图上传开始---------------------------------->
				<%-- <c:if test="${empty  entity   ||  entity.homeType =='6'}"> --%>
				<div class="form-group"  id = "dynamicImgDiv">
					<label class="col-sm-2 control-label">动图上传</label>
					
					<div class="col-sm-10">
						<div class="input-group">
							<input type="file" class="form-control" id="dynamicImg"
								name="dynamicImg"> <span class="input-group-btn">
								<button class="btn btn-success" type="button" id="uploadDynamicImg">上传图片</button>
							</span>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">背景图片</label>
					<div class="col-sm-10">
						<div class="col-sm-6">
							<img src="${entity.dynamicPicture }" alt="暂无图片，点击上传！" class="img-rounded"
								style="max-width: 100%; max-height: 300px;" id="dynamicFileimg">
						</div>
					</div>
				</div>
				<span id="dynamicTip" style="color: red;font-size: 14px;margin-left:20px; "></span>
				<%-- </c:if> --%>
				<!-- 动图上传结束---------------------------------->
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

    if('${entity}' != ''){
    	var  homeType =  '${entity.homeType}';
    	if(homeType != null  ||  homeType.trim() != ''){
    		if(homeType == 6){
               $("#dynamicImgDiv").css({display : "block"});
    		}else if(homeType == 9){
    			$('#iconLabelDiv').show();
            	$('#isProductDiv').show();
    		}else if(homeType == 5){
            	$('#isProductDiv').show();
    		}
    	}
    }else{
    	var  homeType = $("#homeType").val();
    	if(homeType == 6){
            $("#dynamicImgDiv").css({display : "block"});
 		}else{
    		$("#dynamicImgDiv").css({display : "none"});
 		}
    	 $('#iconLabelDiv').hide();
      	$('#isProductDiv').hide();
    	
    }



    $("select#homeType").change(function(){
        console.log("执行了change")
        var objs = document.getElementById("homeType");
        var homeType = objs.options[objs.selectedIndex].value;
        var toolType = document.getElementById("toolTypeDiv");
        var img = document.getElementById("img");
        $("#dynamicImgDiv").css({display : "none"});
        if(homeType == '5'){
        	img.setAttribute("style","display:none");
            toolType.setAttribute("style","display:block");
            $('#iconLabelDiv').hide();
            $('#isProductDiv').show();
        }else if(homeType=='8'){
        	toolType.setAttribute("style","display:none");
        	img.setAttribute("style","display:block");
        	 $('#iconLabelDiv').hide();
          	$('#isProductDiv').hide();
        }else if(homeType=='6'){
        	//动图显示
        	$("#dynamicImgDiv").css({display : "block"});
        	toolType.setAttribute("style","display:none");
        	img.setAttribute("style","display:none");
        	 $('#iconLabelDiv').hide();
         	$('#isProductDiv').hide();
        }
        else if(homeType=='9'){
        	$('#iconLabelDiv').show();
        	$('#isProductDiv').show();
        }
        
        
        else{
        	toolType.setAttribute("style","display:none");
        	img.setAttribute("style","display:none");
        }
	});

	
function check_fun(){
	$("#tip").html("");
	
	var state = $('input[name="state"]:checked').val();//状态
	var remark = $('input[name="remark"]').val();//备注
	var bannerFileInput = $('input[name="bannerFile_input"]').val();
	var zoneName = $("#zoneNames").val();//名称
	var linkUrlH = $("#linkUrlH").val();
	var zoneType = $("#zoneType").val();
	var versionCompareType = $("#versionCompareType").val();
	var actionVersion = $("#actionVersion").val();

	var homeType = $("#homeType").val();  //专区类型
	var labelLcolor = $("#labelLcolor").val();   //字体颜色
	var labelFontSize = $("#labelFontSize").val();   //字体大小
	var appversionFile = $('input[name="appversionFile"]').val(); // 图片链接
	//var iconlabel=$('#iconLabel').val();//新icon 标签
	var startTime=$('#banner_startTime').val();//有效开始时间
	var endTime=$('#banner_endTime').val();//有效结束时间
	if(state == undefined){
		$("#tip").html("请选择状态");
		return false;
	}

	if(versionCompareType != 0 && (actionVersion.length == 0 || actionVersion.length == undefined)){
        $("#tip").html("请输入版本号");
        return false;
	}else if(actionVersion.length != 0){
		var regEx = "^[0-9]+\.[0-9]+\.[0-9]+";
		var ismatch = actionVersion.match(regEx);
		console.log(ismatch);
		if(ismatch == null || ismatch == ''){
            $("#tip").html("版本号格式不正确");
            return false;
		}
	}
	
	if(zoneName.length==0){
		$("#tip").html("请输入icon名");
		return false;
	}
	
	if(linkUrlH.length==0){
		$("#tip").html("请输入链接");
		return false;
	}

	if(zoneType.length==0){
		$("#tip").html("请输入序号");
		return false;
	} 
	if(startTime.length==0){
		$("#tip").html("请输入有效开始时间");
		return false;
	} 
	if(endTime.length==0){
		$("#tip").html("请输入有效结束时间");
		return false;
	} 
	
	/* if(homeType==9&&iconlabel.length==0){
		$("#tip").html("请输入新工具标签");
		return false;
	} */
	
	if(homeType.length==0){
		$("#tip").html("请输入专区类型");
		return false;
	}	
	if('${entity}' == ''){
		var filepath = $("#bannerFile_input").val();
		
		if( filepath == null || filepath == ''){
			$("#tip").html("请上传图片");
			return false;
		}
		if(homeType==8){
			var filepathTwo = $("#backgroundPicture").val();
			if( filepathTwo == null || filepathTwo == ''){
				$("#twotip").html("请上传图片2");
				return false;
			}
		}
		
	}
	
	//格式校验
	
	/*if(title&&title.length>150){
		$("#tip").html("标题不能超过150个字");
		b = false;
	}**/
	
	
	return true;
}
	$(function() {
		$('#uploadDynamicImg').click(function() {
			var file = document.bannerForm.dynamicImg.value;
			if(file == ""){
				$("#dynamicTip").html("请选择图片");
				return false;
			}
			$.ajaxFileUpload({
				type : "post",
				dataType : "json",
				fileElementId : 'dynamicImg',
				url : "upload/dynamicImg.htm",
				data : {
					"id" : "${entity.zoneId }"
				},
				error : function(data) {
					alert("错误！");
				},
				success : function(data) {
					if (data.result = 'success') {
						console.info(data);
						alert("上传图片成功！");
						$('#dynamicPicture').val(data.imgUrl);
						$('#dynamicFileimg').attr("src", data.imgUrl);
						$("#dynamicTip").html("");
					} else {
						alert(data.msg);
					}
				}
			});
		});
		
		
		$('#uploadBanner').click(function() {
			var file = document.bannerForm.appversionFile.value;
			if(file == ""){
				$("#tip").html("请选择图片");
				return false;
			}
			$.ajaxFileUpload({
				type : "post",
				dataType : "json",
				fileElementId : 'appversionFile',
				url : "upload/appversion.htm",
				data : {
					"id" : "${entity.zoneId }"
				},
				error : function(data) {
					alert("错误！");
				},
				success : function(data) {
					if (data.result = 'success') {
						console.info(data);
						alert("上传图片成功！");
						$('#bannerFile_input').val(data.imgUrl);
						$('#bannerFile_img').attr("src", data.imgUrl);
						$("#tip").html("");
					} else {
						alert(data.msg);
					}
				}
			});
		});
		
		$('#uploadBannerTwo').click(function() {
			var file = document.bannerForm.backgroundimg.value;
			if(file == ""){
				$("#twotip").html("请选择图片");
				return false;
			}
			$.ajaxFileUpload({
				type : "post",
				dataType : "json",
				fileElementId : 'backgroundimg',
				url : "upload/backgroundimg.htm",
				data : {
					"id" : "${entity.zoneId }"
				},
				error : function(data) {
					alert("错误！");
				},
				success : function(data) {
					if (data.result = 'success') {
						console.info(data);
						alert("上传图片成功！");
						$('#backgroundPicture').val(data.imgUrl);
						$('#zoneFileimg').attr("src", data.imgUrl);
						$("#twotip").html("");
					} else {
						alert(data.msg);
					}
				}
			});
		});
		

	});
</script>