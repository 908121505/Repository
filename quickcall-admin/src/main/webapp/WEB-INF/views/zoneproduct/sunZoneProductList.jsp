<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<%
//int serverPort = request.getServerPort();
	String path = request.getContextPath();
	String basePath = "https://" + request.getServerName() + path + "/";
%>

    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/bootstrap/js/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/bootstrap/js/icon.css">
	<script type="text/javascript" src="<%=basePath%>resources/bootstrap/js/jquery.easyui.min.js"></script>


<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
		 
			<div style="left:50px;">
				<input class="easyui-combobox" id="selectProduct"
			name="language"   editable='false'
			data-options="
					url:'product/selectSunProduct?zoneId=${zoneId}',
					method:'get',
					valueField:'productId',
					textField:'productName',
					multiple:true,
					panelHeight:'300'
			"> <input type="button" id="tjc" value="添加到此专区" onclick="addPre()">
			</div>
			
			
		
			<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
			<h3 id="myModalLabel"><span id="my"></span>专区下所有产品</h3>
			
			
			<input type="hidden" id="zoneId" value="${zoneId}"  />
			
			
		</div>
		<c:if test="${empty dtList}">
			<span style="text-align: center">暂无产品</span>
		</c:if>
		<c:if test="${not empty dtList}">
			 <table id="example1" class="table">
				 <thead>
				<tr role="row">
					<th class="text-center sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">产品名称</th>
					<th class="text-center sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">创建人</th>
					<th class="text-center sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">创建时间</th>
					<th class="text-center sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">专区权重值</th>
					<th class="text-center sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1">删除</th>
				</tr>
				</thead>
				<tbody id=tbody-result>
				<c:forEach var="info" items="${dtList}">
					<tr>
						<td>${info.productName}</td>
						<td>${info.createMan}</td>
						<td><fmt:formatDate value="${info.createTime}"  type="both"/></td>
						<td  id="tddb" ondblclick="ShowElement(this,'${zoneId}','${info.productId}' ,${info.zoneProductsort} )">${info.zoneProductsort}</td>
						<td><a onclick="del('${info.productId}','${zoneId}')" data-toggle="modal"
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
//更新受影响的行数

  $(function(){ 
	  $("#my").html($("#zoneName").val());
	  $("#tjc").val("添加到"+$("#zoneName").val()+"专区");
	  
}); 

function addPre(){
	
	if($("#selectProduct").val()==null||$("#selectProduct").val()==''){
		alert("请选择产品");
		return false;
	}
   
	var zoneId = "";
	
	$.ajax({
		dataType: "json", 
		url: "product/updateProductZone?zoneId=" +$("#zoneId").val()+"&productId="+$("#selectProduct").val(),
		type:"GET",
		async: false,
		success: function(data){
			alert("添加成功");
			  var datas = data.list;  
			  var str = "";  
					
	                for (var i in datas) {  
	                    str += "<tr>" +  
	                    "<td>" + datas[i].productName + "</td>" +  
	                    "<td>" + datas[i].createMan + "</td>" +  
	                    "<td>"+datas[i].createTime+ "</td>" + 
	                    "<td><a onclick='del(\""+datas[i].productId+"\",\""+data.zoneId+"\")' data-toggle='modal'class='label label label-danger'><i class='glyphicon glyphicon-trash'></i> 删除 </a></td>"  +
	                    "</tr>";  
	                    
	                   
	                    
	                }  
	                $("#tbody-result").html(str); 
	                
	                zoneId = data.zoneId;
	       	
		},
		error:function(data){
			alert("添加失败");
		}
		
		});
	
	 
	 $("#"+zoneId).click();
	
}

function del(id,postId){
	
	var zoneId = "";
	$.ajax({
		 dataType: "json", 
		url: "product/delZongId?id=" + id+"&zoneId=" + postId,
		type:"POST",
		async: false,
		success: function(data){

		if(data.resultFlag > 0){
		
				    var datas = data.list;  
					 var str = "";  
	                for (var i in datas) {  
	                    str += "<tr>" +  
	                    "<td>" + datas[i].productName + "</td>" +  
	                    "<td>" + datas[i].createMan + "</td>" +  
	                    "<td>"+datas[i].createTime+ "</td>" + 
	                    "<td><a onclick='del('"+datas[i].productId+"','"+data.zoneId+"')' data-toggle='modal'class='label label label-danger'><i class='glyphicon glyphicon-trash'></i> 删除 </a></td>"  +
	                    "</tr>";  
	                }  
	                $("#tbody-result").html(str); 
	                
	                $.globalMessenger().post({
						message: '操作成功！',
						type: 'success',
						id: "Only-one-message",
						hideAfter: 2,
						hideOnNavigate: true
					});
		
	                zoneId = data.zoneId;
			
			}else {
				$.globalMessenger().post({
					message: '操作失败',
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
	
	
	 $("#"+zoneId).click();
	
}


function ShowElement(element,zoneId,productId,zoneProductsort){
    var me = element;
    var oldhtml = element.innerHTML;
    var newobj = document.createElement('input');
    newobj.type = 'text';//修改新创建的input的类型
    element.innerHTML = '';//清空td中的内容用于存放新创建input;
    $(newobj).attr({ value:zoneProductsort});
    element.appendChild(newobj);//把input放入td
	//给input对象绑定鼠标移开事件
    newobj.onblur = function(){
	// 判断是否满足发送ajax的条件
        if(this.value != zoneProductsort){
            element.innerHTML = this.value;
            var value = this.value;
			// alert(value);
			//发送ajax请求
            var id = $(me).parents('.gradeA').find('.sid').html();
            var url = "zoneProduct/updateSunZongDetail";
            var btn = $(this);
            $.ajax({
                url:url,
                data:{id:id,productId:productId,value:value,zoneId:zoneId},
                type:'post',
                success:function(data){
                    console.log(data);
                    if(data == 1){
                        alert('修改成功')
                    }else{
                        alert('修改失败');
                    }
                }
            })
        }else{
            element.innerHTML = zoneProductsort;//放入原来的内容
		// return false;
        }
    }
    newobj.focus();
// return false;
}


</script>

<script type="text/javascript"
		src="resources/js/My97DatePicker/WdatePicker.js" charset="utf-8">
</script>