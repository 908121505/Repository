<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
    /*input css*/
    .iInput{
        position: absolute;
        width: 99px;
        height: 22px;
        left: 1px;
        top: 2px;
        border-bottom: 0px;
        border-right: 0px;
        border-left: 0px;
        border-top: 0px;
    }
</style>
<shiro:hasPermission name="productreviews:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">商户评论管理</h1>
			<ul class="breadcrumb">
				<li>社区管理</li>
				<li class="active">商户评论管理</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
				 

                <div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">商户名称</div>
                            <div style="position:relative;">
                                <select style="width:120px;" onclick="setInputValue()" id = "productSelect">
                                </select>
                                <input id="input" name="input" class="iInput" onkeyup="getProductValue(this.value)" value = "--全部--">
                                <input id="inputProductId" name="inputProductId" type="hidden" value = "">
                            </div>
							<%--<select class="form-control" id="productId">--%>
								<%--<option value=" " selected="selected">--全部--</option>--%>
								<%--<c:forEach var="info" items="${products }">--%>
									<%--<option value="${info.productId }">${info.productName }</option>--%>
								<%--</c:forEach>--%>
							<%--</select>--%>
						</div>
					</div>
				</div>
				
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">手机号</div>
							<input class="form-control" id="phoneNum"/>
						</div>
					</div>
				</div> 
				
				
				<div class="col-md-4">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">时间</div>
							<input class="form-control" type="date" id="sTime">
							<div class="input-group-addon">到</div>
							<input class="form-control" type="date" id="eTime">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<button type="button" class="btn btn-primary btn-small btn-block"
						id="query">
						<i class="glyphicon glyphicon-search"></i> 查询
					</button>
				</div>
				<div class="col-md-2">
					<shiro:hasPermission name="productreviews:add">
						<button type="button" class="btn btn-info btn-small btn-block"
							onclick="addAndUpdateRow(0)">
							<i class="glyphicon glyphicon-plus"></i> 增加
						</button>
					</shiro:hasPermission>
				</div>
				<div class="col-md-2">
					<shiro:hasPermission name="productreviews:delete">
						<button type="button" class="btn btn-info btn-small btn-block"
								onclick="batchDelete(0)">
							<i class="glyphicon glyphicon-trash"></i> 批量删除
						</button>
					</shiro:hasPermission>
				</div>
			</div>
			<table id="example" class="table"></table>
		</div>
		<script>
		
		function setInputValue(){
        	$("#productSelect").attr("onclick","showValue()");
		}
		function showValue(){
		    var index = document.getElementById("productSelect").selectedIndex;
		    var text = document.getElementById("productSelect").options[index].text;
		    $("#input").val(text);
            $("#inputProductId").val("");
            // 刷新下拉列表
            if (text == '--全部--') {
                $("#productSelect").empty();
                var selectStrNew = "<option value=\" \">--全部--</option>";
                <c:forEach var="info" items="${products }">
                var proIdNew  = "${info.productId }";
                var proNameNew = "${info.productName }"
                selectStrNew = selectStrNew + "<option value=\""+proIdNew+"\">"+proNameNew+"</option>";
                </c:forEach>
                $("#productSelect").html(selectStrNew);
            }
        }
		function getProductValue(data){
            $("#productSelect").empty();
            var str = "<option value=\" \">--全部--</option>";
            <c:forEach var="info" items="${products }">
                var proIdNew  = "${info.productId }";
                var proNameNew = "${info.productName }"
                // 将包含输入文字的下拉框填充进下拉列表
                if (proNameNew.indexOf(data) != -1){
                    str = str + "<option value=\""+proIdNew+"\">"+proNameNew+"</option>";
                }
                // 如果输入文字后没有出发下拉框的选择事件，则会使用到此处的商户ID
                if (data == proNameNew) {
                    document.getElementById("inputProductId").value = proIdNew;
                }
                // 输入空的时候查询所有
                if (data == ""){
                    $("#inputProductId").val("");
                }
            </c:forEach>
            $("#productSelect").html(str);
            $("#productSelect").attr("onclick","setInputValue()");
        }
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
		
			//表格的初始化
			$(document).ready(function() {
				$("#productSelect").empty();
	            var selectStr = "<option value=\" \">--全部--</option>";
	            <c:forEach var="info" items="${products }">
	            var proId  = "${info.productId }";
	            var proName = "${info.productName }"
	            selectStr = selectStr + "<option value=\""+proId+"\">"+proName+"</option>";
	            </c:forEach>
	            $("#productSelect").html(selectStr);
				
				
				var table = $('#example').initTable({
					sAjaxSource:"productreviews/initTable.htm",
					aoColumns: [
                        {"data": "opinionFeedbackId","sTitle":"批量操作",'sClass':"text-center"},
						{ 
						    "data": "account.nickname",
						    "sTitle":"昵称",
						    'sClass':"text-center" 
						  },
							  { 
					                "data": "account.userAvatar",
					                "sTitle":"头像图片",
					                'sClass':"text-center",
					                "mRender": function(data, type, full) { 
		    				            	return "<img src='" + data + "' height='50px;'/>"; 
		    			             } 
					            },
					            { 
								    "data": "product.productName",
								    "sTitle":"商户",
								    'sClass':"text-center" 
								  },
			            { 
			                "data": "feedbackReason",
			                "sTitle":"评论内容",
			                'sClass':"text-center"
			               
			            },
			            { 
			                "data": "account.phoneNum",
			                "sTitle":"用户手机号",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "createTime",
			                "sTitle":"评论时间",
			                'sClass':"text-center"
			               
			            },
			            { 
			                "data": "grade",
			                "sTitle":"评论星级",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "feedbackLabel",
			                "sTitle":"所选标签",
			                'sClass':"text-center",
			                
			            }
			           
			            ,{"data": "opinionFeedbackId","sTitle":"操作",'sClass':"text-center"}
			         ],
					 fnServerParams: function (aoData) {  //查询条件
                         if ($("#inputProductId").val() != '') {
                             aoData.push({ "name": "productId", "value": $("#inputProductId").val() } );
                         } else {
                             aoData.push({ "name": "productId", "value": $("#productSelect").val() } );
                         }
		             	if ($("#sTime").val() != '') {
                            aoData.push({"name": "startTime", "value": $("#sTime").val()+" 00:00:00"});
                        }
                        if ($("#eTime").val() != '') {

                            aoData.push({"name": "endTime", "value": $("#eTime").val()+" 23:59:59"});
                        }
                        aoData.push({"name": "phoneNum", "value": $("#phoneNum").val()});
		             },
		             aoColumnDefs : [
		             	{"aTargets" :9,"mRender" : function(data, type, row){
		             	   
		             		var edit="",del="",detail="";
		             		<shiro:hasPermission name="productreviews:delete">
		             			del ="<a href='#' onclick='deleteRow(\""+data+"\")' data-toggle='modal' class='label label label-danger'><i class='glyphicon glyphicon-trash'></i> 删除 </a>";
		             		</shiro:hasPermission>
		             			return edit+"&nbsp;"+del;
		                }
		             },
                         {"aTargets" :0,"mRender" : function(data, type, row){

                             var batchDelete="";
                             <shiro:hasPermission name="productreviews:delete">
                             batchDelete ="<input type = 'checkbox' name='batchDelete' style='width: 15px;height: 15px; ' value='"+data+"'/>";
                             </shiro:hasPermission>
                             return batchDelete;
                         }
                         }

		             ]
				});
				
				 $('#query').click(function(){
					$('#example').dataTable().fnDraw();
				});
			
			});
			//删除受影响的行数
			function deleteRow(id){
				$('#myModal').deleteRow('productreviews/del.htm?id='+id);
			}
			//增加或者修改受影响的行数
			function addAndUpdateRow(id){
				$('#insertAndUpdate').addAndUpdateRow("productreviews/addAndUpdateHome.htm?id="+id);
			}
			function batchDelete(id){
				var checkValue =[];
				$('input[name="batchDelete"]:checked').each(function(){
                    checkValue.push($(this).val());
				});
				if	(checkValue.length == 0) {
					alert ('你还没有选择任何内容！');
                } else {
                    $('#myModal').deleteRow('productreviews/delNew.htm?id='+checkValue.toString());
                }
			}
//        	$("#名称").attr("属性名","属性值");
		</script>
		<!---dialog选项-->
		<div>
			<jsp:include page="/WEB-INF/views/common/delete_dialog.jsp" />
			<jsp:include page="/WEB-INF/views/common/addupdate_dialog.jsp" />

			<div class="modal fade" id="insertAndUpdate" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			</div>
		</div>
	</div>
</shiro:hasPermission>