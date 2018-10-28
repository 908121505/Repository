<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<link type="text/css"  href="resources/bootstrap/css/bootstrap-datetimepicker.min.css"  rel="stylesheet" />
<script type="text/javascript" language="javascript"	src="resources/bootstrap/js/bootstrap-datetimepicker.min.js"  charset="utf-8"></script>


<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">管理页面</h3>
		</div>
		<div class="modal-body" style="max-height: 700px; overflow-y: auto;"  id = "editBody">
			<form class="form-horizontal" method="post" id="skillForm"
				name="skillForm"
				action="customer/saveUpdate.htm"
				role="form">

				 
				 
				<div class="form-group">
					<label class="col-sm-4 control-label">用户昵称<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<input type="text" id="skillItemName" class="form-control"  name="nickName" value="${entity.nickName}"  maxlength="50" readonly="readonly">
						<input type="hidden" id="id" class="form-control"  name="customerId" value="${entity.customerId}" >
					</div>
				</div>

				<div class="form-group"  >
					<label class="col-sm-4 control-label">用户UID<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<input type="text" id="appId" class="form-control"
							   name="appId" value="${entity.appId}" readonly="readonly">
					</div>
				</div>

				<div class="form-group"  >
					<label class="col-sm-4 control-label">用户类型<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<input type="text" id="type" class="form-control"
							   name="type" value="${entity.vStatus == 2?'声优用户':'普通用户'}" readonly="readonly" disabled="disabled">
					</div>
				</div>
				<%-- 4=已封禁-无法接单,6=已封禁-无法接指定技能,8=已封禁-账户登录权限,10=已封禁-设备登录权限--%>
				<div class="form-group"  >
					<label class="col-sm-4 control-label">用户类型<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<input type="text" id="custStatusDispaly" class="form-control"
							   name="custStatusDispaly" value="${entity.custStatus == 1?'正常': entity.custStatus == 4 ?
							    '已封禁-无法接单':entity.custStatus==6?'已封禁-无法接指定技能':
							    entity.custStatus == 8 ?'已封禁-账户登录权限': entity.custStatus == 10?'已封禁-设备登录权限':'未知状态'}"
							   readonly="readonly">

					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">操作<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<select class="form-control" id="operate" name="operate" onchange="onOperateChange()">
							<c:if test="${entity.custStatus == 1}">
								<option value="1">封禁</option>
							</c:if>
							<c:if test="${entity.custStatus != 1}">
								<option value="0">解禁</option>
								<option value="1">封禁</option>
							</c:if>
						</select>
					</div>
				</div>
				<%-- 4=已封禁-无法接单,6=已封禁-无法接指定技能,8=已封禁-账户登录权限,10=已封禁-设备登录权限--%>
				<div class="form-group" id="allStatus">
					<label class="col-sm-2 control-label">操作类型<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<select class="form-control" id="custStatus" name="custStatus" onchange="onCustStatusChange()">
							<option value="4">已封禁-无法接单</option>

							<c:if test="${entity.customerSkills != null && fn:length(entity.customerSkills) > 0}">
								<option value="6">已封禁-无法接指定技能</option>
							</c:if>
							<option value="8">已封禁-账户登录权限</option>
							<option value="10">已封禁-设备登录权限</option>
						</select>
					</div>

					<div class="col-sm-10" >
						<select class="form-control" id="customerSkills" name="customerSkills" multiple="multiple">
								<c:forEach items="${entity.customerSkills}" var="skill" >
										<option>${skill}</option>
								</c:forEach>
						</select>
					</div>

				</div>


				<div class="form-group" id="allTime">
					<label class="col-sm-2 control-label">时间<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<select class="form-control" id="closureDate" name="closureDate">
							<option value="1">1</option>
							<option value="3">3</option>
							<option value="7">7</option>
							<option value="30">30</option>
							<option value="-1">永久</option>
						</select>
					</div>
				</div>

		<%--		<div class="form-group"  >
					<label class="col-sm-4 control-label">描述<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<input type="text" id="skillDescribe" class="form-control"
							name="skillDescribe" value="${entity.skillDescribe}">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">状态<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<label class="checkbox-inline">
							<input type="radio" name="skillStatus" value="1" ${empty entity or entity.skillStatus=='1'?'checked':'' }>
							不可用
						</label>
						<label class="checkbox-inline">
							<input type="radio" name="skillStatus" value="0" ${entity.skillStatus=='0'?'checked':'' }>
							可用
						</label>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">技能类型<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<label class="checkbox-inline">
							<input type="radio" name="skillType" value="1" ${empty entity or entity.skillType=='1'?'checked':'' }>
							不可重复接单类型
						</label>
						<label class="checkbox-inline">
							<input type="radio" name="skillType" value="2" ${entity.skillType=='2'?'checked':'' }>
							可重复接单类型
						</label>
					</div>
				</div>

				<div class="form-group"  >
					<label class="col-sm-4 control-label">备注<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<input type="text" id="remark" class="form-control"
							   name="remark" value="${entity.remark}">
					</div>
				</div>


			&lt;%&ndash;<div class="form-group" >
					<label class="col-sm-4 control-label">最高价格<!-- <font color="red">&nbsp;*</font> --></label>
					<div class="col-sm-8">
						<input type="text" id="maxPrice" class="form-control" name="maxPrice" value="${entity.maxPrice}">
					</div>
				</div>
				<div class="form-group" >
					<label class="col-sm-4 control-label">价格步长<!-- <font color="red">&nbsp;*</font> --></label>
					<div class="col-sm-8">
						<input type="text" id="priceStep" class="form-control" name="priceStep" value="${entity.priceStep}">
					</div>
				</div>&ndash;%&gt;
				<div class="form-group" >
					<label class="col-sm-4 control-label">排序<!-- <font color="red">&nbsp;*</font> --></label>
					<div class="col-sm-8">
						<input type="text" id="sort" class="form-control" name="sort" value="${entity.sort}" >
					</div>
				</div>

				<div class="form-group" >
					<label class="col-sm-4 control-label">背景颜色<!-- <font color="red">&nbsp;*</font> --></label>
					<div class="col-sm-8">
						<input type="text" id="backColor" class="form-control" name="backColor" value="${entity.backColor}" >
					</div>
				</div>

				<div class="form-group" >
					<label class="col-sm-4 control-label">字体颜色<!-- <font color="red">&nbsp;*</font> --></label>
					<div class="col-sm-8">
						<input type="text" id="fontColor" class="form-control" name="fontColor" value="${entity.fontColor}" >
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">未解锁图片<c:if test="${entity eq null }"><font color="red">&nbsp;*</font></c:if>
					</label>
					<div class="col-sm-8">
						<div class="input-group">
							<input type="file" class="form-control" id="imageFile"  name="imageFile">
							<span class="input-group-btn">
								<button class="btn btn-success" type="button" id="uploadImage">上传图片</button>
							</span>
						</div>
					</div>
				</div>
		      <div class="form-group">
					<label for="skillFile_img" class="col-sm-4 control-label">图片</label>
					<div class="col-sm-8">
						<div class="col-sm-6">
							<img src="${entity.lockIcon }" alt="暂无图片，点击上传！" class="img-rounded" style="max-width: 100%; max-height: 300px;" id="skillFile_img">
						</div>
					</div>
				</div>
				
				
				
				<div class="form-group">
					<label class="col-sm-4 control-label">解锁图片上传<c:if test="${entity eq null }"><font color="red">&nbsp;*</font></c:if>
					</label>
					<div class="col-sm-8">
						<div class="input-group">
							<input type="file" class="form-control" id="imageFile1"
								name="imageFile1"> <span class="input-group-btn">
								<button class="btn btn-success" type="button" id="uploadImage1">上传图片</button>
							</span>
						</div>
					</div>
				</div>
		      <div class="form-group">
					<label for="skillFile_img1" class="col-sm-4 control-label">图片</label>
					<div class="col-sm-8">
						<div class="col-sm-6">
							<img src="${entity.unlockIcon }" alt="暂无图片，点击上传！" class="img-rounded" style="max-width: 100%; max-height: 300px;" id="skillFile_img1">
						</div>
					</div>
				</div>--%>
				
				
			</form>
		</div>
		<div class="modal-footer">
		<span id="tip" style="color: red; font-size: 16px; margin-left: 20px;float:left;"></span>
			<button class="btn btn-default" data-dismiss="modal" aria-hidden="true" >取消</button>
			<button class="btn btn-primary" data-dismiss="modal" >保存</button>
		</div>
	</div>
</div>

<script type="text/javascript">

    $(function() {// 初始化内容
        var status = $('#operate').val();
        if(status == 0){
            $('#custStatus').attr("disabled",true);
            $('#allStatus').css({ "display": "none" });
            $('#closureDate').attr("disabled",true);
            $('#allTime').css({ "display": "none" });
        }else{
            $('#allTime').css({ "display": "inline" });
            $('#custStatus').attr("disabled",false);
            $('#allStatus').css({ "display": "inline" });
            $('#closureDate').attr("disabled",false);

        }

        var status1 = $('#custStatus').val();
        if(status1 == 6){
            $('#customerSkills').css({ "display": "inline" });
            $('#customerSkills').attr("disabled",false);
        } else{
            $('#customerSkills').css({ "display": "none" });
            $('#customerSkills').attr("disabled",true);
        }


    });


    Date.prototype.Format = function (fmt) { //author: meizz
	    var o = {  
	        "M+": this.getMonth() + 1, //月份   
	        "d+": this.getDate(), //日   
	        "H+": this.getHours(), //小时   
	        "m+": this.getMinutes(), //分   
	        "s+": this.getSeconds(), //秒   
	        "q+": Math.floor((this.getMonth() + 3) / 3),
	        "S": this.getMilliseconds()
	    };  
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
	    for (var k in o)  
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));  
	    return fmt;  
	};
	
	function onOperateChange() {
        var status = $('#operate').val();
        if(status == 0){
            $('#custStatus').attr("disabled",true);
            $('#allStatus').css({ "display": "none" });
            $('#closureDate').attr("disabled",true);
            $('#allTime').css({ "display": "none" });
		}else{
            $('#allTime').css({ "display": "inline" });
            $('#custStatus').attr("disabled",false);
            $('#allStatus').css({ "display": "inline" });
            $('#closureDate').attr("disabled",false);

		}

    }
	
    function onCustStatusChange() {
	    var status = $('#custStatus').val();
	    if(status == 6){
            $('#customerSkills').css({ "display": "inline" });
            $('#customerSkills').attr("disabled",false);
		} else{
            $('#customerSkills').css({ "display": "none" });
            $('#customerSkills').attr("disabled",true);
		}
		
    }
	

	
	

	  //字符串转日期格式，strDate要转为日期格式的字符串
    function getDate(strDate) {
        var st = strDate;
        var a = st.split(" ");
        var b = a[0].split("-");
        var c = a[1].split(":");
        var date = new Date(b[0], (b[1]-1), b[2], c[0], c[1], c[2]);
        return date;
    }
	
	

	

	


	function check_fun() {
		
		var b = true;
      
		var name =  $("#skillItemName").val();
		if(name == null  || name.trim() == ''){
			$("#tip").text("请输入技能名称");
			return  false;
		}
		/*var minPrice =  $("#minPrice").val();
		if(minPrice == null  || minPrice.trim() == ''){
			$("#tip").text("请输入最低价格");
			return  false;
		}*/
		/*var skillBackGroungImg =  $("#skillBackGroungImg").val();
		if(skillBackGroungImg == null  || skillBackGroungImg.trim() == ''){
			$("#tip").text("请上传项目背景图片");
			return  false;
		}*/
		/*var skillTitleImg =  $("#skillTitleImg").val();
		if(skillTitleImg == null  || skillTitleImg.trim() == ''){
			$("#tip").text("请上传项目标题图片");
			return  false;
		}*/
		
	
		return b;
	}
	
	
	
	

	$(function() {
		/* $('#shangxianTime').datetimepicker({
			format : 'yyyy-mm-dd hh:ii:ss'
		}); */
		$('#uploadImage').click(function() {
			var file = document.skillForm.imageFile.value;
			if(file == ""){
				$("#tip").html("请选择图片");
				return false;
			}
			$.ajaxFileUpload({
				type : "post",
				dataType : "json",
				fileElementId : 'imageFile',
				url : "upload/skillImageupload.htm",
				data : {
					"id" : ""
				},
				error : function(data) {
					alert("错误！");
				},
				success : function(data) {
					if (data.result = 'success') {
						console.info(data);
						alert("上传图片成功！");
						$('#skillBackGroungImg').val(data.imgUrl);
						$('#skillFile_img').attr("src", data.imgUrl);
						$("#tip").html("");
					} else {
						alert(data.msg);
					}
				}
			});
		});
		$('#uploadImage1').click(function() {
			var file = document.skillForm.imageFile1.value;
			if(file == ""){
				$("#tip").html("请选择图片");
				return false;
			}
			$.ajaxFileUpload({
				type : "post",
				dataType : "json",
				fileElementId : 'imageFile1',
				url : "upload/skillImageupload1.htm",
				data : {
					"id" : ""
				},
				error : function(data) {
					alert("错误！");
				},
				success : function(data) {
					if (data.result = 'success') {
						console.info(data);
						alert("上传图片成功！");
						$('#skillTitleImg').val(data.imgUrl);
						$('#skillFile_img1').attr("src", data.imgUrl);
						$("#tip").html("");
					} else {
						alert(data.msg);
					}
				}
			});
		});

		




	});
</script>