<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">${empty entity?'新增':'修改' }产品信息</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" method="post" id="productForm" name="productForm"
				action="excellentProduct/save${empty entity?'Insert':'Update' }.htm"
				role="form">
				 <c:if test="${empty entity==true}">
					 <div class="form-group">
					 <label class="col-sm-4 control-label">产品名称<font color="red">&nbsp;*</font></label>
						 <select id="productId" name="productId">
							 <c:forEach items="${entitys}" var="entity">
								 <option value="${entity.productId}" >
										 ${entity.productName}
								 </option>
							 </c:forEach>
						 </select>
					 </div>
				</c:if>
				<c:if test="${empty entity==false}">
					<div class="form-group">
						<label class="col-sm-4 control-label">产品名称<font color="red">&nbsp;*</font></label>
						<div class="col-sm-8">
							<input type="text" readonly="readonly" class="form-control" name="productName"
								   value="${entity.productName}">
							<input type="hidden"  class="form-control" name="excellentProductId" value="${entity.excellentProductId}">
						</div>
					</div>
				</c:if>
				<c:if test="${empty entity==false}">
				<div class="form-group">
					<label class="col-sm-4 control-label">通过率上涨<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<input type="text" id="passingRate"  class="form-control" name="passingRate"
							   value="${entity.passingRate}">
					</div>
				</div>
				</c:if>
				<c:if test="${empty entity==true}">
					<div class="form-group">
						<label class="col-sm-4 control-label">通过率上涨<font color="red">&nbsp;*</font></label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="passingRate" value="例：100%" onfocus="if (value =='例：100%'){value =''}" onblur="if (value ==''){value='例：100%'}"/>
						</div>
					</div>
				</c:if>

				
				<div class="form-group">
					<label class="col-sm-4 control-label">小喵密报<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<textarea id="content" name="content" style="width:200px;height:80px;"  class="form-control"  >${entity.content}</textarea>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">开始时间<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<input type="date" id="startTime"  class="form-control" name="startTime"
							value="${entity.startTime}">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-4 control-label">结束时间<font color="red">&nbsp;*</font></label>
					<div class="col-sm-8">
						<input type="date" id="endTime"  class="form-control" name="endTime"
							   value="${entity.endTime}">
					</div>
				</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">状态<font color="red">&nbsp;*</font></label>
						<c:if test="${empty entity==false}">
							<c:if test="${entity.status==0}">
							<div class="col-sm-8">
								<label><input name="status" type="radio" value="0" checked="checked" />开启 </label>
								<label><input name="status" type="radio" value="1"  />关闭 </label>
							</div>
							</c:if>
							<c:if test="${entity.status==1}">
								<div class="col-sm-8">
								<label><input name="status" type="radio" value="0" />开启 </label>
								<label><input name="status" type="radio" value="1" checked="checked" />关闭 </label>
								</div>
							</c:if>
						</c:if>
						<c:if test="${empty entity==true}">
							<div class="col-sm-8">
							<label><input name="status" type="radio" value="0" />开启 </label>
							<label><input name="status" type="radio" value="1" />关闭  </label>
							</div>
						</c:if>
				</div>
				<span id="tip" style="color: red;font-size: 14px;margin-left:20px; "></span>
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

	function check_fun(){
		$("#tip").html("");
		var b = true;
        var productId=$('select[name="productId"]').val();
        var passingRate=$('input[name="passingRate"]').val();
        //var content=$('input[name="content"]').val();
        var content = document.getElementById("content").value;
        var startTime=$('input[name="startTime"]').val();
        var endTime=$('input[name="endTime"]').val();
        var status=$('input[name="status"]:checked').val();


		if(status == undefined){
            $("#tip").html("请选择状态");
            return false;
        }
        /*if(productId== undefined){
            $("#tip").html("请选择产品");
            return false;
        }*/
        if(content.length==0){
            $("#tip").html("请输入小喵密保");
            return false;
        }
        if(startTime.length==0){
            $("#tip").html("请选择开始时间");
            return false;
        }
        if(endTime.length==0){
            $("#tip").html("请选择结束时间");
            return false;
        }
        if(passingRate.length==0 || passingRate=='例：100%'){
            $("#tip").html("请输入通过率上涨");
            return false;
        }
		
		return b;
	}

</script>