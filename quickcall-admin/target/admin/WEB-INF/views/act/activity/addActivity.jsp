<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }活动列表信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sysUserForm"
				name="activityForm"
				action="managerActivity/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">活动ID<font
						color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" readonly="readonly" value="${entity.activityId }"
							name="activityId" />
							
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">活动标题<font
						color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
							 <input type="text" class="form-control"
							id="activity_title" name="title" value="${entity.title }">
							
							
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
					<label class="col-sm-2 control-label">是否推荐<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<label class="checkbox-inline"> <input type="radio"
							name="ifHot" value="1"
							${entity.ifHot=='1'?'checked':'' }> 推荐
						</label> <label class="checkbox-inline"> <input type="radio"
							name="ifHot" value="0"
							${entity.ifHot=='0'?'checked':'' }> 不推荐
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">排序值<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="number" class="form-control" name="useId"
							   id="activity_sort" value="${entity.useId }">
					</div>
				</div>
			     <div class="form-group">
					<label class="col-sm-2 control-label">活动内容<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="content"
							id="activity_content" value="${entity.content }">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">热门链接<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="linkAddress"
							id="activity_linkAddress" value="${entity.linkAddress }">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">开始时间<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" id="banner_startTime" class="form-control"
							onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'1950-01-01',maxDate:'#F{$dp.$D(\'banner_endTime\')}'})"
							value="${entity.beginTime }" name="beginTime" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">结束时间<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" id="banner_endTime" class="form-control"
							onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'banner_startTime\')}',maxDate:'2050-12-31'})"
							value="${entity.endTime }" name="endTime" />
					</div>
				</div>
				
				
			
				<div class="form-group">
					<label class="col-sm-2 control-label">备注</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="remark"
							id="activity_remark" value="${entity.remark }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">图片上传</label>
					<div class="col-sm-10">
						<div class="input-group">
						   <input type="file" class="form-control" id="picOneFile"
							name="picOneFile"> <span class="input-group-btn">
							<button class="btn btn-success" type="button"
								id="uploadpicOneFileImg">上传图片</button>
						</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">图片地址1</label>
						<div class="col-sm-10">
							<img src="${entity.picOne }" alt="暂无图片，点击上传！"
								class="img-rounded" style="max-width: 100%; max-height: 300px;"
								id="picOneImg"/>
							<input type="hidden" class="form-control" name="picOne"
							id="picOne" value="${entity.picOne }">
							<button class="btn btn-success" type="button"
									id="delImg1" onclick="delsmallLaelImg('picOne')">清空图片</button>
						</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">图片上传</label>
					<div class="col-sm-10">
					   <div class="input-group">
						   <input type="file" class="form-control" id="picTwoFile"
							name="picTwoFile"> <span class="input-group-btn">
							<button class="btn btn-success" type="button"
								id="uploadpicTwoFileImg">上传图片</button>
						</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">图片地址2</label>
						<div class="col-sm-10">
							<img src="${entity.picTwo }" alt="暂无图片，点击上传！"
								class="img-rounded" style="max-width: 100%; max-height: 300px;"
								id="picTwoImg"/>
							<input type="hidden" class="form-control"  name="picTwo"
							id="picTwo" value="${entity.picTwo }">
							<button class="btn btn-success" type="button"
									id="delImg2" onclick="delsmallLaelImg('picTwo')">清空图片</button>
						</div>
				</div>
				
				
				
				<span id="tip"
					style="color: red; font-size: 14px; margin-left: 20px;"></span>
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
	function check_fun() {
		$("#tip").html("");
		var b = true;
		var ifHot = $('input[name="ifHot"]:checked').val();
		var state = $('input[name="state"]:checked').val();
		var title = $("#activity_title").val();
		var activityContent = $("#activity_content").val();
		var activityLinkAddress = $("#activity_linkAddress").val();
		var bannerStartTime = $("#banner_startTime").val();
		var bannerEndTime = $("#banner_endTime").val();
		var useId = $("#activity_sort").val();;
		if (ifHot == undefined) {
			$("#tip").html("请选择是否推荐");
			b = false;
		}
		if (state == undefined) {
			$("#tip").html("请选择状态");
			b = false;
		}
		
		if (title == null || title.trim() == '') {
			$("#tip").html("请输入标题");
			b = false;
		}
	
		if (activityContent == null || activityContent.trim() == '') {
			$("#tip").html("请输入活动内容");
			b = false;
		}
		
		
		if (activityLinkAddress == null || activityLinkAddress.trim() == '') {
			$("#tip").html("请输入热链接");
			b = false;
		}
		
		
		if (bannerStartTime == null || bannerStartTime.trim() == '') {
			$("#tip").html("请输入开始时间");
			b = false;
		}
		
		
		if (bannerEndTime == null || bannerEndTime.trim() == '') {
			$("#tip").html("请输入结束时间");
			b = false;
		}

		if (useId == null || useId.trim() == '') {
			$("#tip").html("请输入排序值");
			b = false;
		}
		
		return b;
	}
	
	function delsmallLaelImg(pic){
		$("#"+pic+"Img").attr("src","");
		$("#"+pic).val("");
	}
	
	$(function() {
		$('#uploadpicOneFileImg').click(function() {
			var file = $('#picOneFile').val();
			if(file == ""){
				$("#tip").html("请选择图片");
				return false;
			}
			$.ajaxFileUpload({
				type : "post",
				dataType : "json",
				fileElementId : 'picOneFile',
				url : "upload/activitypicOneFile.htm",
				data : {
					"id" : "${entity.activityId }"
				},
				error : function(data) {
					alert("错误！");
				},
				success : function(data) {
					if (data.result = 'success') {
						console.info(data);
						alert("上传图片成功！");
						$('#picOne').val(data.imgUrl);
						$('#picOneImg').attr("src", data.imgUrl);
						$("#tip").html("");
					} else {
						alert(data.msg);
					}
				}
			});
		});
		
		$('#uploadpicTwoFileImg').click(function() {
			var file = $('#picTwoFile').val();
			if(file == ""){
				$("#tip").html("请选择图片");
				return false;
			}
			$.ajaxFileUpload({
				type : "post",
				dataType : "json",
				fileElementId : 'picTwoFile',
				url : "upload/activitypicTwoFile.htm",
				data : {
					"id" : "${entity.activityId }"
				},
				error : function(data) {
					alert("错误！");
				},
				success : function(data) {
					if (data.result = 'success') {
						console.info(data);
						alert("上传图片成功！");
						$('#picTwo').val(data.imgUrl);
						$('#picTwoImg').attr("src", data.imgUrl);
						$("#tip").html("");
					} else {
						alert(data.msg);
					}
				}
			});
		});
		
	});	
	
	
</script>