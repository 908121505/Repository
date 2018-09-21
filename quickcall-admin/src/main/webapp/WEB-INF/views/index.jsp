<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	System.out.println(path + "||" + request.getScheme() + "|||" + basePath);
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>网贷管家后台管理系统</title>
<base href="<%=basePath%>" />
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="resources/datatables/css/jquery.dataTables.css">
<link rel="stylesheet" type="text/css" href="resources/css/theme-blue.css">
<link rel="stylesheet" type="text/css" href="resources/message/css/messenger.css">
<link rel="stylesheet" type="text/css" href="resources/message/css/messenger-theme-future.css">
<link rel="stylesheet" type="text/css" href="resources/treeview/css/zTreeStyle.css">
<link rel="stylesheet" type="text/css" href="resources/css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="resources/bootstrapvalidator/dist/css/bootstrapValidator.css" />
<link rel="stylesheet" type="text/css" href="resources/ueditor/themes/default/css/ueditor.css" />
<style>
.label-info {
	margin-right: 3px;
}

.collapse>li {
	border-bottom: 1px inset #E8E8E8;
}

a {
	text-decoration: none !important;
}

.left {
	width: 240px;
	position: fixed;
	top: 52px;
	z-index:999;
}

.rigth {
	padding-left: 250px;
	padding-top: 56px;
	z-index: 100;
}

.input_tree {
	z-index: 100;
	border: 1px solid #ddd;
	padding: 0;
	position: absolute;
	width: 100%;
	display: none;
}

.theme-blue .navbar {
	position: fixed;
	width: 100%;
	z-index: 1000;
	top: 0;
}

.btn_upload {
	color: #fff;
	background-color: #428bca;
	border-color: #357ebd;
}
</style>

<script src="resources/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="resources/bootstrap/js/bootstrap.js"></script>
<script src="resources/datatables/js/jquery.dataTables.js"></script>
<script src="resources/js/ajaxfileupload.js" type="text/javascript"></script>
<script src="resources/js/user.dataTables.js"></script>
<script src="resources/js/jquery.form.js"></script>
<script src="resources/message/js/messenger.min.js"	type="text/javascript"></script>
<script src="resources/js/jquery-ui.js" type="text/javascript"></script>
<script src="resources/js/jqueryUIcombobox.js" type="text/javascript"></script>
<script src="resources/treeview/js/jquery.ztree.core-3.5.js" type="text/javascript"></script>
<script src="resources/treeview/js/jquery.ztree.excheck-3.5.min.js"	type="text/javascript"></script>
<script src="resources/js/efunbox-combox-1.0.js" type="text/javascript"></script>
<script src="resources/js/ajaxfileupload.js" type="text/javascript"></script>
<script src="resources/js/highcharts.js" type="text/javascript"></script>
<script src="resources/bootstrapvalidator/dist/js/bootstrapValidator.js" type="text/javascript"></script>
<script src="resources/bootstrapvalidator/dist/js/language/zh_CN.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript" src="resources/js/ckeditor/ckeditor.js" mce_src="resources/js/ckeditor/ckeditor.js"></script>
<script language="javascript" type="text/javascript" src="resources/js/ckeditor/config.js" mce_src="resources/js/ckeditor/config.js"></script>
<script type="text/javascript" src="resources/js/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<body>
	<div>
		<jsp:include page="/WEB-INF/views/home/top.jsp" />
	</div>
	<div>
		<div class="left">
			<jsp:include page="/WEB-INF/views/home/left.jsp" />
		</div>
		<div class="rigth" id="content">
			<jsp:include page="/WEB-INF/views/home/welcome.jsp" />
		</div>
	</div>
</body>
</html>