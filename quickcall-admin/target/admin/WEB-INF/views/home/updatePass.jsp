<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">修改用户信息</h3>
		</div>
		<div class="modal-body">
			<form  class="form-horizontal" method="post" id="sysUserForm" action="system/user/modifyUserInfo.htm" role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">登录账号</label>
					<div class="col-sm-10">
						<input type="hidden" value="${entity.id }" name="id"/>
						<input type="text" class="form-control" id="inputEmail3" readonly="readonly" name="account" value="${entity.account }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">用户昵称</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="nick_name" name="nick_name" value="${entity.nick_name }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">真实姓名</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="user_name"  name="user_name" value="${entity.user_name }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">用户性别</label>
					<div class="col-sm-10">
						<label class="checkbox-inline">
						  <input type="radio" name="sex" value="1" ${entity.sex=='1'?'checked':'' }> 男
						</label>
						<label class="checkbox-inline">
						  <input type="radio" name="sex" value="0" ${entity.sex=='0'?'checked':'' }> 女
						</label>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">登录密码</label>
					<div class="col-sm-10">
						<input type="password"  value="" id="password" class="form-control" name="password">
						</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">确认密码</label>
					<div class="col-sm-10">
						<input type="password" id="confirmPassword" class="form-control" name="confirmPassword">
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
<script type="text/javascript">
 
$(function (){
	
});


function check_fun(){
	 
	 $("#tip").html("");
     var b = true;
     
     var nickName = $("#nick_name").val();
   	 var userName = $("#user_name").val();
 	 var password = $("#password").val();
	 var confirmPassword = $("#confirmPassword").val();
	
	 if (nickName.trim() == '' || nickName == undefined) {
         $("#tip").html("请输入用户昵称");
         b = false;
         return
     }
	 
	 if (userName.trim() == '' || userName == undefined) {
         $("#tip").html("请输入真实姓名");
         b = false;
         return
     }
	 
	 if (password.trim() == '' || password == undefined) {
       /*   $("#tip").html("请输入登录密码");
         b = false;
         return */
     }else{
    	 if (confirmPassword.trim() == '' || confirmPassword == undefined) {
             $("#tip").html("请输入确认密码");
             b = false;
             return
         }else{
        	  if (password!=confirmPassword) {
        	         $("#tip").html("两次密码输入不一致");
        	         b = false;
        	  }
         }
     }
   
     return b;
}
</script>