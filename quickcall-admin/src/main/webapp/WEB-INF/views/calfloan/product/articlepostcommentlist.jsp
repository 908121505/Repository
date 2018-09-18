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
			<h3 id="myModalLabel">查看评论</h3>
		</div>
		<c:if test="${empty articlepostcommentlist}">
			<span style="text-align: center">暂无评论</span>
		</c:if>
		<c:if test="${not empty articlepostcommentlist}">
			<table id="example1" class="table">
				<thead>
				<tr role="row">
					<th class="text-center sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">评论内容</th>
					<th class="text-center sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">评论人</th>
					<th class="text-center sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">手机号码</th>
					<th class="text-center sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">评论时间</th>
					<th class="text-center sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">删除</th>
				</tr>
				</thead>
				<tbody id=tbody-result>
				<c:forEach var="info" items="${articlepostcommentlist}">
					<tr>
						<td>${info.content}</td>
						<td>${info.nikeName}</td>
						<td>${info.phoneNum}</td>
						<td><fmt:formatDate value="${info.createTime}"  type="both"/></td>
						<td><a onclick="del('${info.id}','${info.postId}')" data-toggle="modal"
							   class="label label label-danger">
							<i class="glyphicon glyphicon-trash"></i> 删除 </a></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</div>

<script>
function Format(now,mask)
{
    var d = now;
    var zeroize = function (value, length)
    {
        if (!length) length = 2;
        value = String(value);
        for (var i = 0, zeros = ''; i < (length - value.length); i++)
        {
            zeros += '0';
        }
        return zeros + value;
    };
 
    return mask.replace(/"[^"]*"|'[^']*'|\b(?:d{1,4}|m{1,4}|yy(?:yy)?|([hHMstT])\1?|[lLZ])\b/g, function ($0)
    {
        switch ($0)
        {
            case 'd': return d.getDate();
            case 'dd': return zeroize(d.getDate());
            case 'ddd': return ['Sun', 'Mon', 'Tue', 'Wed', 'Thr', 'Fri', 'Sat'][d.getDay()];
            case 'dddd': return ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'][d.getDay()];
            case 'M': return d.getMonth() + 1;
            case 'MM': return zeroize(d.getMonth() + 1);
            case 'MMM': return ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'][d.getMonth()];
            case 'MMMM': return ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'][d.getMonth()];
            case 'yy': return String(d.getFullYear()).substr(2);
            case 'yyyy': return d.getFullYear();
            case 'h': return d.getHours() % 12 || 12;
            case 'hh': return zeroize(d.getHours() % 12 || 12);
            case 'H': return d.getHours();
            case 'HH': return zeroize(d.getHours());
            case 'm': return d.getMinutes();
            case 'mm': return zeroize(d.getMinutes());
            case 's': return d.getSeconds();
            case 'ss': return zeroize(d.getSeconds());
            case 'l': return zeroize(d.getMilliseconds(), 3);
            case 'L': var m = d.getMilliseconds();
                if (m > 99) m = Math.round(m / 10);
                return zeroize(m);
            case 'tt': return d.getHours() < 12 ? 'am' : 'pm';
            case 'TT': return d.getHours() < 12 ? 'AM' : 'PM';
            case 'Z': return d.toUTCString().match(/[A-Z]+$/);
            // Return quoted strings with the surrounding quotes removed
            default: return $0.substr(1, $0.length - 2);
        }
    });
};
//更新受影响的行数
function del(id,postId){
	$.ajax({
		 dataType: "json", 
		url: "articlePost/delpl.htm?id=" + id+"&postId=" + postId,
		type:"POST",
		success: function(data){
			if(data.resultFlag > 0){
				 var datas = data.list;  
				 var str = "";  
                 for (var i in datas) {  
                     str += "<tr>" +  
                     "<td>" + datas[i].content + "</td>" +  
                     "<td>" + datas[i].nikeName + "</td>" + 
                     "<td>" + datas[i].phoneNum + "</td>" + 
                     "<td>"+Format(new Date(datas[i].createTime),'yyyy-MM-dd HH:mm:ss')+"</td>" +  
                     "<td><a onclick=del('"+datas[i].id+"','"+datas[i].postId+"') data-toggle='modal'class='label label label-danger'><i class='glyphicon glyphicon-trash'></i> 删除 </a></td>"+ 
                     "</tr>";  
                 }  
                 $("#tbody-result").html(str); 
			}else {
				$.globalMessenger().post({
					message: '删除失败',
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