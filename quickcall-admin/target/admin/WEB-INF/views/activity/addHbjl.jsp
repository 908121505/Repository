<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<div class="content1">
		<div class="header">
			<h1 class="page-title">红包概率设置</h1>
			<ul class="breadcrumb">
				<li>运营活动管理</li>
				<li class="active">红包概率设置<</li>
			</ul>
		</div>
		<div class="main-content">
		
		<div class="modal-body">
		<table>
				<div class="form-group">
						<label class="col-sm-4 control-label">随机红包<font color="red">（单位%）</font></label>
						<div class="col-sm-8">
						</div>
					</div>
					</br>
					<div class="form-group">
						<label class="col-sm-4 control-label">10元红白概率<font color="red">&nbsp;*</font></label>
						<div class="col-sm-8">
							<input type="text"   style="width:120px;" class="form-control" id="sj10hbgl" name="sj10hbgl" value="${entity.sj10hbgl}">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-4 control-label">20元红白概率<font color="red">&nbsp;*</font></label>
						<div class="col-sm-8">
							<input type="text"  style="width:120px;"  class="form-control" id="sj20hbgl" name="sj20hbgl" value="${entity.sj20hbgl}">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-4 control-label">100元红包概率<font color="red">&nbsp;*</font></label>
						<div class="col-sm-8">
							<input type="text"  style="width:120px;"  class="form-control" id="sj100hbgl" name="sj100hbgl" value="${entity.sj100hbgl}">
						</div>
					</div>
				
			</table>
				<br/>
				<br/>
				<table>
				<hr style="height:1px;width:98%;border:none;border-top:1px dashed #555555;" />
				</table>
				<table>
				<div class="form-group">
						<label class="col-sm-4 control-label">必得红包<font color="red">（单位%）</font></label>
						<div class="col-sm-8">
						</div>
					</div>
					</br>
					<div class="form-group">
						<label class="col-sm-4 control-label">10元红白概率<font color="red">&nbsp;*</font></label>
						<div class="col-sm-8">
							<input type="text"   style="width:120px;" class="form-control" id="bd10hbgl" name="bd10hbgl" value="${entity.bd10hbgl}">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-4 control-label">20元红白概率<font color="red">&nbsp;*</font></label>
						<div class="col-sm-8">
							<input type="text"  style="width:120px;"  class="form-control" id="bd20hbgl" name="bd20hbgl" value="${entity.bd20hbgl}">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-4 control-label">100元红包概率<font color="red">&nbsp;*</font></label>
						<div class="col-sm-8">
							<input type="text"  style="width:120px;"  class="form-control" id="bd100hbgs" name="bd100hbgs" value="${entity.bd100hbgs}">
						</div>
					</div>
				
			</table>
			
			
			<br/>
			<br/>
			<table>
					<div class="form-group">
						<label class="col-sm-4 control-label">开启/关闭活动<font color="red">&nbsp;*</font></label>
						<div class="col-sm-8">
							<input type="checkbox" id="switch"  value="">
						</div>
					</div>
					
					
			</table>
			
			<table>
					<div class="form-group">
						<label class="col-sm-4 control-label">商家配置<font color="red">&nbsp;*</font></label>
						<div class="col-sm-8">
							<textarea id="merchantInfo"  value="">${entity.merchantInfo}</textarea>
						</div>
					</div>
		</table>
			
		</div>
		<div class="modal-footer" style="padding-right:650px;">
			<button class="btn btn-default" data-dismiss="modal"
				aria-hidden="true">取消</button>
			<button class="btn btn-primary" id="save" >保存</button>
		</div>
		
		</div>
	
		<div>
			<jsp:include page="/WEB-INF/views/common/delete_dialog.jsp" />
			<jsp:include page="/WEB-INF/views/common/addupdate_dialog.jsp" />

			<div class="modal fade" id="insertAndUpdate" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			</div>
		</div>
	</div>
	
	
<script type="text/javascript">
	$(function (){
		
		var of= '${entity.swc}'
		if(of=="on"){
			$("#switch").attr("checked",'true');
		}else{
			$("#switch").removeAttr("checked");
		}
		
		$("#save").click(function (){
			
			var obj ={};
			var swc="";
			var switchs= $("#switch").is(':checked');
			
			if(switchs===false){
				swc="off"
			}else{
				swc="on"
			}
			
			var merchantInfo = $("#merchantInfo").val();
			
			
			var sj10hbgl = $("#sj10hbgl").val();
			var sj20hbgl = $("#sj20hbgl").val();
			var sj100hbgl = $("#sj100hbgl").val();
			
			
			var bd10hbgl = $("#bd10hbgl").val();
			var bd20hbgl = $("#bd20hbgl").val();
			var bd100hbgs = $("#bd100hbgs").val();
			
			
			obj.sj10hbgl=sj10hbgl;
			obj.sj20hbgl=sj20hbgl;
			obj.sj100hbgl=sj100hbgl;
			
			obj.bd10hbgl=bd10hbgl;
			obj.bd20hbgl=bd20hbgl;
			obj.bd100hbgs=bd100hbgs;
			
			obj.swc=swc;
			obj.merchantInfo=merchantInfo;
			
			$.ajax({
				url :"hbgl/update.htm",
				method: "post",
				dataType:"json",
				data: {
					str:JSON.stringify(obj)
				},
				success: function(data){
						if(data.flag===true){
							alert("保存成功");
							location.reload();
						}
				},
				error: function(){
					
				}
			});	
			
			
		});
		
		
		
	});
</script>