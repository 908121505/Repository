<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="articleReply:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">评论管理</h1>
			<ul class="breadcrumb">
				<li>贷款管理</li>
				<li class="active">评论管理</li>
				<spam>&nbsp;&nbsp;<font color="red">(*时间不填或只填一个默认查当天数据)</font></spam>
			</ul>
		</div>
		<div class="main-content">
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">用户ID</div>
							<input class="form-control" type="text" name="accountId" id="accountId">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">用户昵称</div>
							<input class="form-control" type="text" name="nickName" id="nickName">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">用户手机号</div>
							<input class="form-control" type="text" name="phoneNum" id="phoneNum">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">评论内容</div>
							<input class="form-control" type="text" name="replyContent" id="replyContent">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">帖子专区</div>
							  
							  <select class="form-control" id="zoneType">
								<option value="">--请选择--</option>
								
							</select>
				</div>
					</div>
				</div>					
				    <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">状态</div>
                            <select class="form-control" id="state">
                                <option value="">--请选择--</option>
                                <option value="1">正常</option>
                                <option value="2">已删除</option>
                                <option value="3">待审核</option>
                            </select>
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
				
				<div class="col-md-1">
					<shiro:hasPermission name="articlePost:add">
						<button type="button" class="btn btn-default btn-info btn-small"
							onclick="batchAudit('articleReply/batchDel.htm','')">
							<i class="glyphicon glyphicon-plus"></i> 批量删除
						</button>
					</shiro:hasPermission>
				</div>
				<div class="col-md-1">
					<button type="button" class="btn btn-default btn-info btn-small"
							onclick="batchPass('articleReply/batchPass.htm','')">
						<i class="glyphicon glyphicon-plus"></i> 批量通过
					</button>
				</div>
			</div>
			<table id="example" class="table"></table>
		</div>
		<script>
		
		function joinId(){
			var tempStr='';
			$("input[name='sun']").each(function(){
				            if(this.checked==true){
					            tempStr=tempStr+$(this).attr("id")+",";
				            }
					}		
				);
			return tempStr;
		}
		
		function batchPass(str,se) {
            var joIds = joinId();

            if(se==null||se==''){

                if(joIds.length<1){
                    alert("请选择记录");
                    return false;
                }
            }else{
                joIds = se;
            }
            if (confirm("确定要通过吗？")) {
                $.ajax({
                    dataType: "json",
                    url: str+"?data="+joIds,
                    type:"POST",
                    contentType: "application/json;charset=utf-8",
                    async: false,
                    success: function(data){
                        //刷新列表
                        alert("操作成功");
                        $('#example').dataTable().fnDraw();

                    },
                    error:function(data){
                        alert("操作失败");
                    }

                });
            }
        }
		
		function batchAudit(str,se){
		
		   var joIds = joinId();
			
		   if(se==null||se==''){
		   
		     if(joIds.length<1){
				alert("请选择记录");
				return false;
			 }
		  }else{
			  joIds = se;
		  }
		   if (confirm("确定要删除吗？")) {  
			$.ajax({
				dataType: "json", 
				url: str+"?data="+joIds,
				type:"POST",
				contentType: "application/json;charset=utf-8",
				async: false,
				success: function(data){
					//刷新列表
					alert("操作成功");
					$('#example').dataTable().fnDraw();
					
				},
				error:function(data){
					alert("操作失败");
				}
				
				});
		   }
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
				var table = $('#example').initTable({
					sAjaxSource:"articleReply/initTable.htm",
					aoColumns: [
						{ 
        					'sClass':"text-center",
        					"sTitle":"<input type='checkbox' onclick='allCheck()' id='all' >",
        					"mRender": function(data, type, full) { 
        				           return "<input type='checkbox'  name='sun' id='"+full.id+"' value='"+full.id+"'>";
        				     } 
        				},   
			            { 
			              "data": "accountId",
			              "sTitle":"用户ID",
			              'sClass':"text-center"
			            },
			            { 
			               "data": "nikeName",
			               "sTitle":"昵称",
			               'sClass':"text-center"
			            },
			            { 
				               "data": "phoneNum",
				               "sTitle":"手机号码",
				               'sClass':"text-center"
				            },
			            { 
			                "data": "content",
			                "sTitle":"内容",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "createTime",
			                "sTitle":"发布时间",
			                'sClass':"text-center",
			                "mRender": function(data, type, full) { 
					                  return	Format(new Date(data),"yyyy-MM-dd HH:mm:ss")
					        } 
			            },
			            { 
			                "data": "articlePostTitle",
			                "sTitle":"评论帖子",
			                'sClass':"text-center"
			               
			            } ,
			            { 
			                "data": "state",
			                "sTitle":"帖子状态",
			                'sClass':"text-center",
			                 "mRender": function(data, type, full) {
		                              if(data == 1){
		                                  return "<font>正常</font>";
		                              }else if(data == 2){
		                                    return "<font color='blue'>已删除</font>";
		                              }else if(data == 3){
		                                    return "<font color='blue'>待审核</font>";
		                              }else{
		                                  return "<font>未知</font>";
		                              }
		                          }
			            } 
			            <shiro:hasPermission name="articleReply:update or articleReply:delete">
			            ,{"data": "id","sTitle":"操作",'sClass':"text-center"}
			            </shiro:hasPermission>
			         ],
					 fnServerParams: function (aoData) {  //查询条件
						aoData.push({"name": "sDate", "value": $("#sTime").val()});
	                    aoData.push({"name": "eDate", "value": $("#eTime").val()});
		             	aoData.push({ "name": "accountId", "value": $("#accountId").val() } );
		             	aoData.push({ "name": "nickName", "value": $("#nickName").val() } );
					 	aoData.push({ "name": "phoneNum", "value": $("#phoneNum").val() } );
		             	aoData.push({ "name": "zoneType", "value": $("#zoneType").val() } );
		             	aoData.push({ "name": "replyContent", "value": $("#replyContent").val() } );
		             	aoData.push({ "name": "state", "value": $("#state").val() } );		             	
		             },
		             aoColumnDefs : [
						<shiro:hasPermission name="articleReply:update or articleReply:delete">
		             	{"aTargets" :8,"mRender" : function(data, type, row){
		             		var edit="",del="";
		             		<shiro:hasPermission name="articleReply:add">
		             			edit = "<a href='#' onclick='addAndUpdateRow(\""+data+"\",\""+row.id+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i>回复</a>";
		             		</shiro:hasPermission>
		             	    <shiro:hasPermission name="articleReply:delete">
                            del = "<a href='#' onclick='deleteRow(\"" + row.id + "\")' data-toggle='modal' class='label label label-danger'><i class='glyphicon glyphicon-trash'></i> 删除 </a>";
                            </shiro:hasPermission>
		             		
		             		 return edit+ "&nbsp;" + del;
		                }
		             }
		            	</shiro:hasPermission>
		             ]
				});
				
				 $('#query').click(function(){
					$('#example').dataTable().fnDraw();
				});
			});
			
			 function allCheck(){
					setTimeout(allSun, 2500);
				} 
				
				function allSun(){
					var ischecked = $("#all").is(':checked');
					if(ischecked){
						$("input[name=sun]").each(
							function(){
							   $(this).attr("checked",true);	
							}		
						);
						
					}else{
						$("input[name=sun]").each(
								function(){
								   $(this).removeAttr("checked");	
								}		
							);
					}
					
				}
			
			fn(); 
		   function fn(){
			   
				$.ajax({
					dataType: "json", 
					url: "articleReply/querySelectData.htm",
					type:"GET",
					contentType: "application/json;charset=utf-8",
					async: false,
					success: function(data){
						//刷新列表
						 $("#zoneType").html(data);
					},
					error:function(data){
						//刷新列表
						 $("#zoneType").html(data);
					}
					});
			   
		   }
		   
		  
			
			//删除受影响的行数
			function deleteRow(id){
				console.log(id);
				$('#myModal').deleteRow('articleReply/del.htm?id='+id);
			}
			//增加或者修改受影响的行数
			function addAndUpdateRow(id,parentId){
				$('#insertAndUpdate').addAndUpdateRow("articleReply/toAddReply.htm?id="+id+"&parentId="+parentId);
			}
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