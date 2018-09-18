<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }添加文章</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="articleForm"
				  name="articleForm"
				  action="article/save${empty entity?'Insert':'Update' }.htm"
				  role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label">文章分类<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<select class="form-control" id="article_class_id" name="articleClassId">
							<c:forEach var="info" items="${articleClass}">
								<option value="${info.articleClassId }" ${fn:contains(entity.articleClassId,info.articleClassId)?'selected':'' }>${info.articleClassTitle}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">文章标题<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="hidden" name="articleId" value="${entity.articleId }">
						<input type="text" class="form-control" name="articleTitle" value="${entity.articleTitle }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">文章内容<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<textarea class="form-control" id="articleContent" name="articleContent">${entity.articleContent }</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">阅读数量<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="readNum" value="${entity.readNum }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">排序<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="sort" value="${entity.sort }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">是否推荐<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<label class="checkbox-inline"> <input type="radio"
															   name="isPush" value="1" ${entity.isPush=='1'?'checked':'' }>
							开启
						</label>
						<label class="checkbox-inline">
							<input type="radio" name="isPush" value="2" ${entity.isPush=='2'?'checked':'' }>
							关闭
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">状态<font color="red">&nbsp;*</font></label>
					<div class="col-sm-10">
						<label class="checkbox-inline"> <input type="radio"
															   name="state" value="1" ${entity.state=='1'?'checked':'' }>
							开启
						</label>
						<label class="checkbox-inline">
							<input type="radio" name="state" value="2" ${entity.state=='2'?'checked':'' }>
							关闭
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">主题类型</label>
					<div class="col-sm-10">
						<select class="form-control" id="title_type" name="titleType">
							<c:forEach var="info" items="${articleClass}">
								<option value="${info.articleClassId }" ${fn:contains(entity.articleClassId,info.articleClassId )?'selected':'' }>${info.articleClassTitle}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">点赞数量</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="praiseNum" value="${entity.praiseNum }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">描述</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="description" value="${entity.description }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">文件上传<c:if test="${entity eq null }"><font color="red">&nbsp;*</font></c:if></label>
					<div class="col-sm-10">
						<div class="input-group">
							<input type="file" class="form-control" id="articleFile"
								   name="articleFile"> <span class="input-group-btn">
								<button class="btn btn-success" type="button" id="uploadarticle">上传文件</button>
							</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">文件内容</label>
					<div class="col-sm-10">
						<div class="col-sm-6">
							<input id="articleFile_input" type="hidden" name="articleImg" value="">
							<img src="${entity.articleImg }" alt="暂无图片，点击上传！"
								 class="img-rounded" style="max-width: 100%; max-height: 300px;"
								 id="articleFile_img">
						</div>
					</div>
				</div>
				<span id="tip"
					  style="color: red; font-size: 14px; margin-left: 20px;"></span>
			</form>
		</div>
		<div class="modal-footer">
			<button class="btn btn-default" data-dismiss="modal"
					aria-hidden="true">取消</button>
			<button class="btn btn-danger" id="emptyImg">清空图片</button>
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
		var articleClassId = $("#article_class_id option:selected").val();
		var articleTitle = $('input[name="articleTitle"]').val();
		var articleContent = $('#articleContent').val();
		var readNum = $('input[name="readNum"]').val();
		var sort = $('input[name="sort"]').val();
		var isPush = $('input[name="isPush"]:checked').val();
		var state = $('input[name="state"]:checked').val();
		var titleType = $("#title_type").val();
		var praiseNum = $('input[name="praiseNum"]').val();
		var description = $('input[name="description"]').val();
		var articleImg = $('#articleFile_img').attr("src");
		/*		if('${entity}' == ''){
		 var file = document.articleForm.articleFile.value;
		 var filepath = $("#appversionFile_input").val();
		 if(file == ""){
		 $("#tip").html("请上传文件");
		 b = false;
		 }
		 }*/
		if (isPush == undefined) {
			$("#tip").html("请选择是否推荐");
			b = false;
		}
		if (articleClassId == null || articleClassId.trim() == '') {
			$("#tip").html("请选择文章分类");
			b = false;
		}
		if (articleTitle == null || articleTitle.trim() == '') {
			$("#tip").html("请输入文章标题");
			b = false;
		}
		if (articleContent == null || articleContent.trim() == '') {
			$("#tip").html("请输入文章内容");
			b = false;
		}
		if (readNum == null || readNum.trim() == '') {
			$("#tip").html("请输入阅读数量");
			b = false;
		}
		if (sort == null || sort.trim() == '') {
			$("#tip").html("请输入排序");
			b = false;
		}
		if (titleType == null || titleType.trim() == '') {
			$("#tip").html("请输入文章类型");
			b = false;
		}
		if (praiseNum == null || praiseNum.trim() == '') {
			$("#tip").html("请输入点赞数量");
			b = false;
		}
		if (description == null || description.trim() == '') {
			$("#tip").html("请输入描述");
			b = false;
		}
		/*		if (articleImg == null || articleImg.trim() == '') {
		 $("#tip").html("图片不能为空");
		 b = false;
		 }*/
		if (state == undefined) {
			$("#tip").html("请选择状态");
			b = false;
		}
		return b;
	}



	$(function(){


		$('#emptyImg').click(function() {
			$('#articleFile_img').attr("src", "");
		});
		$('#uploadarticle').click(function() {
			var file = document.articleForm.articleFile.value;
			if(file == ""){
				$("#tip").html("请选择文件");
				return false;
			}
			$.ajaxFileUpload({
				type : "post",
				dataType : "json",
				fileElementId : 'articleFile',
				url : "upload/article.htm",
				data : {
				},
				error : function(data) {
					alert("错误！");
				},
				success : function(data) {
					if (data.result = 'success') {
						console.info(data);
						alert("上传图片成功！");
						$('#articleFile_input').val(data.imgUrl);
						$('#articleFile_img').attr("src", data.imgUrl);
						$("#tip").html("");
					} else {
						alert(data.msg);
					}
				}
			});
		});
	});
</script>
