<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
			<h3 id="myModalLabel">历史记录</h3>
		</div>
		<c:if test="${empty messages}">
			<span style="text-align: center">暂无记录</span>
		</c:if>
		<c:if test="${not empty messages}">
			<table id="example" class="table">
				<thead>
				<tr role="row">
					<th class="text-center sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">用户组</th>
					<th class="text-center sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">产品</th>
					<th class="text-center sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">启动/停用时间</th>
					<th class="text-center sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">备注</th>
				</tr>
				</thead>
				<tbody id=tbody-result>
				<c:forEach var="info" items="${messages}">
					<tr>
						<td>${info.title}</td>
						<td>${info.fromSource}</td>
						<td>${info.content}</td>
						<td>${info.remark}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</div>


<script type="text/javascript"
		src="resources/js/My97DatePicker/WdatePicker.js" charset="utf-8">
</script>