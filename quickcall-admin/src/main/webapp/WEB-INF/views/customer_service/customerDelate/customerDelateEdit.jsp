<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">编辑</h3>
		</div>
		<div class="modal-body" style="max-height: 700px; overflow-y: auto;"  id = "editBody">
			<form class="form-horizontal" method="post" id="customerDelateForm" name="customerDelateForm" role="form"
				action="customerDelate/saveUpdate.htm?id=${entity.id}">
				<div class="form-group">
					<label class="col-sm-3 control-label">举报名称<font color="red"> </font></label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="delateName" value="${entity.delateName}" disabled='disabled'/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">举报内容<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<textarea id="delateContent" name="delateContent" rows="6" cols="20"class="form-control" disabled='disabled'>${entity.delateContent}</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">状态<font color="red">&nbsp;*</font></label>
					<div class="col-sm-6">
						<label class="checkbox-inline">
							<input name="handleStatus" type="radio" value="0" ${entity.handleStatus=='0'?'checked':'' }/>待处理
						</label>
						<label class="checkbox-inline">
							<input name="handleStatus" type="radio" value="1" ${entity.handleStatus=='1'?'checked':'' }/>已处理
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">联系方式<font color="red"> </font></label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="phone" value="${entity.phone}" disabled='disabled'/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">处理结果<font color="red"> </font></label>
					<div class="col-sm-6">
						<textarea id="handleResult" name="handleResult" rows="6" cols="20"class="form-control" >${entity.handleResult}</textarea>
					</div>
				</div>
				<span id="tip"
                      style="color: red; font-size: 14px; margin-left: 20px;"></span>
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
	$(function (){
		
	});
	function check_fun(){
		return true;
	}

</script>