<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">分配用户角色</h3>
		</div>
		<div class="modal-body">
			<form method="post" id="sysUserForm" action="system/user/saveInsertAndUpdate.htm" role="form">
				<div class="row">
				  <div class="col-xs-5 col-md-5">
				  	<div class="form-group">
						<label>可选角色</label>
						<select multiple="multiple" class="form-control" id="role_dm" style="height:180px;">
							<c:forEach items="${uRoles.roles }" var="role" varStatus="status">
					  			<option value="${role.codes }">${role.names }</option>
							</c:forEach>
					  	</select>
					</div>
				  </div>
				  
				  <div class="col-xs-2 col-md-2">
				  		<div><input type="button" onClick="delOneOption()" value="<<" style="margin-top:30px; margin-left:16px;"></div>
				  		<input type="button" onClick="addOneOption()" value=">>" style="margin-top:20px; margin-left:16px;">    
				  </div>
				  
				  <div class="col-xs-5 col-md-5">
				  	<div class="form-group">
						<label>已选角色</label>
						<input type="hidden" name="account" value="${user.account }"/>
						<select multiple="multiple" class="form-control"  id="user_dm" name="role_code" style="height:180px;">
							<c:forEach items="${uRoles.selectRoles }" var="role" varStatus="status">
					  			<option value="${role.codes }">${role.names }</option>
							</c:forEach>
					  	</select>
					</div>
				  </div>
				</div>
			</form>
		</div>
		<div class="modal-footer">
			<button class="btn btn-default" data-dismiss="modal"
				aria-hidden="true">取消</button>
			<button class="btn btn-primary" data-dismiss="modal">保存</button>
		</div>
	</div>
</div>

<script type="text/javascript">
function check_fun(){
	return true;
}
	function delOneOption(){
		var role_code = $('#user_dm option:selected').val();
		var role_name = $('#user_dm option:selected').text();
		var selectObj=document.getElementById("role_dm"); 			
		selectObj.options[selectObj.length] = new Option(role_name, role_code);
		var index = $('#user_dm').get(0).selectedIndex;
		document.getElementById("user_dm").options.remove(index);
		$("#user_dm option").attr("selected", true);
	}
	function addOneOption(){
		var role_code = $('#role_dm option:selected').val();
		var role_name = $('#role_dm option:selected').text();
		var index = $('#role_dm').get(0).selectedIndex;
		document.getElementById("role_dm").options.remove(index);
		var selectObj=document.getElementById("user_dm"); 			
		selectObj.options[selectObj.length] = new Option(role_name, role_code);
		$("#user_dm option").attr("selected", true);
	}
	$(document).ready(function() {
		$("#user_dm option").attr("selected", true);
	})
</script>