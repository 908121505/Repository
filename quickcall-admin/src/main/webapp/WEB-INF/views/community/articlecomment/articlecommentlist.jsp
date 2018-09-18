<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
			<h3 id="myModalLabel">查看评论</h3>
		</div>
		<c:if test="${empty articleClass}">
			<span style="text-align: center">暂无评论</span>
		</c:if>
		<c:if test="${not empty articleClass}">
			<table id="example1" class="table">
				<thead>
				<tr role="row">
					<th class="text-center sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">评论内容</th>
					<th class="text-center sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">阅读数</th>
					<th class="text-center sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">点赞数量</th>
					<th class="text-center sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">删除</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="info" items="${articleClass}">
					<tr>
						<td>${info.content}</td>
						<td>${info.state}</td>
						<td>${info.praiseNum}</td>
						<td><a data-id="${info.articleCommentId}" onclick="del(this)" data-toggle='modal'
							   class='label label label-danger'>
							<i class='glyphicon glyphicon-trash'></i> 删除 </a></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</div>

<script>
	function del(obj){
		var id = $(obj).attr("data-id");
		$.ajax({
			url: "article/comment/del.htm?id=" + id,
			type:"POST",
			success: function(data){
				if(data > 0){
					$.globalMessenger().post({
						message: '删除成功！',
						type: 'success',
						id: "Only-one-message",
						hideAfter: 2,
						hideOnNavigate: true
					});
				}else {
					$.globalMessenger().post({
						message: '删除失败：'+data,
						type: 'error',
						id: "Only-one-message",
						hideAfter: 2,
						hideOnNavigate: true
					});
				}
			},
			error:function(data){
				$.globalMessenger().post({
					message: '系统错误',
					type: 'error',
					id: "Only-one-message",
					hideAfter: 2,
					hideOnNavigate: true
				});
			}
		});
	}
</script>

<script type="text/javascript"
		src="resources/js/My97DatePicker/WdatePicker.js" charset="utf-8">
</script>