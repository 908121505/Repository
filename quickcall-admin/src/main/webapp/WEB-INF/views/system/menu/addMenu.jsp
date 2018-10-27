<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }菜单信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="sysUserForm" action="system/menu/save${empty entity?'Insert':'Update' }.htm" role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">菜单名称</label>
					<div class="col-sm-10">
						<input type="hidden" value="${entity.id }" name="id"/>
						<input type="text" class="form-control" name="names" value="${entity.names }">
					</div>
				</div>
				
				<c:if test="${empty entity.menu_type or entity.menu_type=='1' }">
					<div class="form-group">
						<label class="col-sm-2 control-label">访问地址</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="url" value="${entity.url }">
						</div>
					</div>
				
					<div class="form-group">
						<label class="col-sm-2 control-label">菜单ICON</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="img_icon" value="${entity.img_icon }">
						</div>
					</div>		
				</c:if>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">权限编码</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="author" value="${entity.author }">
					</div>
				</div>
					
				<div class="form-group">
					<label class="col-sm-2 control-label">菜单类别</label>
					<div class="col-sm-10">
						<label class="checkbox-inline">
						  <input type="radio" name="menu_type" value="1" ${entity.menu_type=='1'?'checked':'' }> 菜单
						</label>
						<label class="checkbox-inline">
						  <input type="radio" name="menu_type" value="2" ${entity.menu_type=='2'?'checked':'' }> 权限
						</label>
					</div>
				</div>
					
				<div class="form-group">
					<label class="col-sm-2 control-label">菜单状态</label>
					<div class="col-sm-10">
						<label class="checkbox-inline">
						  <input type="radio" name="menuState" value="1" ${entity.menuState=='1'?'checked':'' }> 显示
						</label>
						<label class="checkbox-inline">
						  <input type="radio" name="menuState" value="2" ${entity.menuState=='2'?'checked':'' }> 隐藏
						</label>
					</div>
				</div>		
				
				<div class="form-group">
					<label class="col-sm-2 control-label">显示顺序</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="orders" value="${entity.orders }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">父级菜单</label>
					<div class="col-sm-10">
						<%-- <select class="form-control" name="parent_id" value="${entity.parent_id }">
							<option value="0">--请选择菜单--</option>
							<c:forEach items="${menus }" var="menu">
								<c:choose>
									<c:when test="${entity.parent_id==menu.id}"><option value="${menu.id }" selected="true">${menu.names }</option></c:when>
									<c:otherwise><option value="${menu.id }">${menu.names }</option></c:otherwise>
								</c:choose>
							</c:forEach>
						</select> --%>
						<input type="text" class="form-control" name="parent_id" value="${entity.parent_id }">
					</div>
				</div>
				
				
				
				<c:if test="${!empty entity}">
				<div class="form-group">
					<label class="col-sm-3 control-label">选择权限</label>
					<div class="col-sm-9">
						<div class="row">
							<div class="col-md-4">
					        	<input type="checkbox" id="selectMenu" name="menu_authority" value="select"> 查询
					      	</div>
							<div class="col-md-4">
					        	<input type="checkbox" id="addMenu" name="menu_authority" value="add"> 添加
					      	</div>
							<div class="col-md-4">
					        	<input type="checkbox" id="updateMenu" name="menu_authority" value="update"> 修改
					      	</div>
							<div class="col-md-4">
					        	<input type="checkbox" id="deleteMenu" name="menu_authority" value="delete"> 删除
					      	</div>
							<div class="col-md-4">
					        	<input type="checkbox" id="uploadMenu" name="menu_authority" value="upload"> 上传
					      	</div>
							<div class="col-md-4">
					        	<input type="checkbox" id="downloadMenu" name="menu_authority" value="download"> 下载
					      	</div>
					</div>
				</div>
				</div>
			</c:if>

			<c:forEach items="${menu_authority }" var="item" varStatus="var">
					<div class="col-md-4 quanxian">
			        	<input type="checkbox" checked="checked" value="${item.names }" id="quanxian" readonly="readonly" hidden="hidden">
			      	</div>
				</c:forEach>
				
			</form>
		</div>
		<div class="modal-footer">
			<button class="btn btn-default" data-dismiss="modal"
				aria-hidden="true">取消</button>
			<button class="btn btn-primary" data-dismiss="modal">保存</button>
		</div>
	</div>
</div>
</body>

<script type="text/javascript">
		function check_fun(){
			return true;
		}
	var files = $(".quanxian").find("input[type='checkbox']");
	files.each(function(){
		if($(this).val() == "查询"){
			$("#selectMenu").attr({checked:true,disabled:true});
			$("#selectMenu").val("");
		}else if($(this).val() == "添加"){
			$("#addMenu").attr({checked:true,disabled:true});
			$("#addMenu").val("");
		}else if($(this).val() == "修改"){
			$("#updateMenu").attr({checked:true,disabled:true});
			$("#updateMenu").val("");
		}else if($(this).val() == "删除"){
			$("#deleteMenu").attr({checked:true,disabled:true});
			$("#deleteMenu").val("");
		}else if($(this).val() == "上传"){
			$("#uploadMenu").attr({checked:true,disabled:true});
			$("#uploadMenu").val("");
		}else if($(this).val() == "下载"){
			$("#downloadMenu").attr({checked:true,disabled:true});
			$("#downloadMenu").val("");
		}
	});

</script>
</html>