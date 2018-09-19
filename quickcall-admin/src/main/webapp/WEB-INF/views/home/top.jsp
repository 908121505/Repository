<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="theme-blue">
    <div class="navbar navbar-default" role="navigation">
        <div class="navbar-header">
          <a href="welcome.htm" target="content">
          	<span class="navbar-brand"><span class="fa fa-paper-plane"></span> 网贷管家 后台管理系统</span>
          </a>
		</div>
		
        <div class="navbar-collapse collapse" style="height: 1px;">
			<ul id="main-menu" class="nav navbar-nav navbar-right">
				<li class="dropdown hidden-xs">
					<a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">
						<span class="glyphicon glyphicon-user padding-right-small" style="position:relative;top: 3px;"></span> 
						${account }
					</a>
				</li>
				<li class="dropdown hidden-xs">
					<a  href='#' onclick='modifyUserInfo(${account })' class="dropdown-toggle" data-toggle="dropdown" target="_parent">
						<span class="padding-right-small" style="position:relative;top: 3px;"></span> 修改用户信息
					</a>
				</li>
				<li class="dropdown hidden-xs">
					<a onclick="window.location.href='logout.htm';" class="dropdown-toggle" data-toggle="dropdown" target="_parent">
						<span class="glyphicon glyphicon-off padding-right-small" style="position:relative;top: 3px;"></span> 退出
					</a>
				</li>
			</ul>
		</div>
    </div>
       <div class="modal fade" id="insertAndUpdate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	   </div>
</div>
<script type="text/javascript"> 

function modifyUserInfo(account){
		$('#insertAndUpdate').addAndUpdateRow("system/user/modifyPass.htm?account="+account);
}
</script>