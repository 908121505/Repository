<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>LoanManager Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="resources/font-awesome/css/font-awesome.css">
<link rel="stylesheet" type="text/css" href="resources/css/theme-blue.css">
<link rel="stylesheet" type="text/css" href="resources/message/css/messenger.css">
<link rel="stylesheet" type="text/css" href="resources/message/css/messenger-theme-future.css">

<script src="resources/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="resources/message/js/messenger.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
/*	$.globalMessenger().post({
		message: '用户名或者密码错误！',
		type: 'error',
		hideAfter: 2
	});  */
});
</script>
</head>
<div id="aa"></div>
<body class="theme-blue">
	<div class="navbar navbar-default" role="navigation">
		<div class="navbar-header">
			<a href="login.htm">
				<span class="navbar-brand">
					<span class="fa fa-paper-plane"></span> 网贷管家后台管理系统
				</span>
			</a>
		</div>
		<div class="navbar-collapse collapse" style="height: 1px;"></div>
	</div>

	<div class="dialog">
		<div class="panel panel-default">
			<!-- <p class="panel-heading no-collapse">Sign In</p> -->
			<div class="panel-body">
				<form action="login.htm" method="post" autocomplete="off">
					<div class="form-group">
						<label>账号</label>
						<input type="text" class="form-control span12" name="account">
					</div>
					<div class="form-group">
						<label>密码</label>
						<input type="password" class="form-control span12 form-control" name="password">
					</div>
					<label class="remember-me"><input type="checkbox"> 记住我</label>
					<button type="submit" class="btn btn-primary" style="float:right;"> 登录</button>
					<div class="clearfix"></div>
				</form>
			</div>
		</div>
		<!-- <p class="pull-right" style="">
			<a href="#" target="blank"
				style="font-size: .75em; margin-top: .25em;">注册</a>
		</p>
		<p>
			<a href="#">忘记密码?</a>
		</p> -->
	</div>
</body>
</html>
