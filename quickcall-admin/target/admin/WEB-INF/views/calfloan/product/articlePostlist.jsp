<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<shiro:hasPermission name="articlePost:select">
	<div class="content1">
		<div class="header">
			<h1 class="page-title">社区发帖列表</h1>
			<ul class="breadcrumb">
				<li>社区发帖管理</li>
				<li class="active">社区发帖列表</li>
			</ul>
		</div>
		<div class="main-content">
			<div class="row" >
				<div class="col-md-2" style="width: 190px;">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">标题</div>
							<input class="form-control" type="text" id="articlePostTitle">
						</div>
					</div>
				</div>
				<div class="col-md-2" style="width: 210px;">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">手机号</div>
							<input class="form-control" type="text" id="phoneNum">
						</div>
					</div>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">帖子内容</div>
							<input class="form-control" type="text" id="articlePostContent">
						</div>
					</div>
				</div>
				<div class="col-md-2" style="width: 210px;">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">发帖人昵称</div>
							<input class="form-control" type="text" id=nickname>
						</div>
					</div>
				</div>
				<div class="col-md-3" style="width: 210px;">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">发帖标签</div>
							<select  class="form-control" name="postLable" id="postLable">
								<option value="">全部</option>
							</select>
						</div>
					</div>
				</div>
				<div class="col-md-2" style="width: 210px;">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">帖子位置</div>
							<input class="form-control" style="width: 40px" type="text" id="startPosition" name = "startPosition" >
							<div class="input-group-addon">-</div>
							<input class="form-control" style="width: 40px" type="text" id="endPosition" name = "endPosition">
						</div>
					</div>
				</div>
			</div>
			<div class="row" >
				<div class="col-md-2" style="width: 190px;">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">状态</div>
							<select class="form-control" id="state">
								<option value="">--全部--</option>
								<option value="1">已通过</option>
								<option value="2">无效</option>
								<option value="3">待审核</option>
								<option value="4">已驳回</option>
								<option value="5">违禁</option>
							</select>
						</div>
					</div>
				</div>
				<div class="col-md-2" style="width: 250px;">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">帖子类型</div>
							<select class="form-control" id="isEssence">
								<option value="">--全部--</option>
								<option value="1">普通</option>
								<option value="2">精华</option>
								<option value="3">热门</option>
								<option value="4">IOS审核专区</option>
								<option value="5">置顶</option>
							</select>
						</div>
					</div>
				</div>
				<div class="col-md-1">
					<button type="button" class="btn btn-primary btn-small btn-block"
							id="query">
						<i class="glyphicon glyphicon-search"></i> 查询
					</button>
				</div>
				<div class="col-md-1">
					<shiro:hasPermission name="articlePost:add">
						<button type="button" class="btn btn-info btn-small btn-block"
								onclick="addAndUpdateRow('')">
							<i class="glyphicon glyphicon-plus"></i> 增加
						</button>
					</shiro:hasPermission>
				</div>

				<div class="col-md-1">
					<shiro:hasPermission name="articlePost:add">
						<button type="button" class="btn btn-info btn-small btn-block"
								onclick="batchAudit('articlePost/batchPass.htm','')">
							<i class="glyphicon glyphicon-plus"></i> 批量通过
						</button>
					</shiro:hasPermission>
				</div>

				<div class="col-md-1">
					<shiro:hasPermission name="articlePost:add">
						<button type="button" class="btn btn-info btn-small btn-block"
								onclick="batchAudit('articlePost/batchRejected.htm','')">
							<i class="glyphicon glyphicon-plus"></i> 批量驳回
						</button>
					</shiro:hasPermission>
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
			
			$.ajax({
				dataType: "json", 
				url: str+"?data="+joIds,
				type:"POST",
				contentType: "application/json;charset=utf-8",
				async: false,
				success: function(data){
					//刷新列表
					alert("操作成功");
					initTableData();
				},
				error:function(data){
					alert("操作失败");
				}
				
				});
			
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
		function loadLable() {
            $.ajax({
                type: "post",
                dataType: "json",
                url: "articlePost/loadLable",
                data: {
                    "falg": "1"
                },
                error: function () {
                    alert("错误！");
                },
                success: function (data) {
					var str = "<option value=\"\">全部</option>";
                    if (data !== null && data !== '' && data.list !== undefined) {
                        var list = data.list;
                        for (var i = 0;i<list.length;i++) {
							str += "<option value=\""+list[i].id+"\">"+list[i].lableName+"</option>"
                        }
                    }
					$("#postLable").html(str);
                }
            });
        }
			//表格的初始化
			$(document).ready(function() {
				initTableData();
                loadLable();
			});
			
			function initTableData(){
				var table = $('#example').initTable({
					sAjaxSource:"articlePost/initTable.htm",
					aoColumns: [
			           
						{ 
							'sClass':"text-center",
						    "sTitle":"<input type='checkbox' onclick='allCheck()' id='all' >",
						    "mRender": function(data, type, full) { 
				                  return "<input type='checkbox'  name='sun' id='"+full.articlePostId+"' value='"+full.articlePostId+"'>";
				                } 
						  },
					    { 
			              "data": "articlePostTitle",
			              "sTitle":"标题",
			              'sClass':"text-center"
			            },
			            { 
				              "data": "articlePostContent",
				              "sTitle":"帖子内容",
				              'sClass':"text-center",
				              "mRender": function(data, type, full) { 
					                  if(full.articlePostContent!=null && full.articlePostContent.length >20)	{
					                	  return data.substr(0,20)+"..."
					                  }else{
					                	  return data;
					                  }
					                } 
				         },

                        {
                            "data": "articlePostLableName",
                            "sTitle":"帖子标签",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if (data != null) {
                                    return data;
                                } else {
                                    return "--";
                                }
                            }
                        },
                        {
                            "data": "articlePosition",
                            "sTitle":"帖子位置",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                                if (data != null) {
									return data;
                                } else {
                                    return "--";
								}
                            }
                        },
			            { 
			                "data": "createTime",
			                "sTitle":"创建时间",
			                'sClass':"text-center",
			                "mRender": function(data, type, full) { 
			                  return	Format(new Date(data),"yyyy-MM-dd HH:mm:ss")
			                } 
			            },
			            { 
			                "data": "phoneNum",
			                "sTitle":"手机号码",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "accountNickName",
			                "sTitle":"昵称",
			                'sClass':"text-center"
			            }, 
			            { 
			                "data": "modifyTime",
			                "sTitle":"更新时间",
			                'sClass':"text-center",
			                "mRender": function(data, type, full) { 
				                  return	Format(new Date(data),"yyyy-MM-dd HH:mm:ss")
				                } 
			            },
			            { 
			                "data": "modifyMan",
			                "sTitle":"更新人",
			                'sClass':"text-center"
			            },
			            { 
			                "data": "state",
			                "sTitle":"状态",
			                'sClass':"text-center",
			                "mRender": function(data, type, full) { 
			                	if(data == 1){
									return "<font color='green'>已通过</font>";
								}else if(data == 2){
									return "<font color='red'>无效</font>";
								}else if(data == 3){
									return "<font color='blue'>待审核</font>";
								}else if(data == 4){
                                    return "<font color='blue'>已驳回</font>";
                                }else if(data == 5){
                                    return "<font color='red'>违禁</font>";
                                }else{
									return "<font color='gray'>未知</font>";
								}
			                	
			                } 
			            },
			            
			            { 
			                "data": "isEssence",
			                "sTitle":"类型",
			                'sClass':"text-center",
			                "mRender": function(data, type, full) { 
			                	if(data == 2){
									return "<font color='red'>精华</font>";
								}else if(data == 3){
									return "<font color='blue'>热门</font>";
								}else if(data == 4){
									return "<font color='blue'>IOS审核专区</font>";
								}else if(data == 5){
									return "<font color='blue'>置顶</font>";
								}
								else{
									return "<font color='black'>普通</font>";
								}
			                } 
			            },
			            
			            { 
				              "data": "readNum",
				              "sTitle":"阅读数",
				              'sClass':"text-center"
				         },
				         { 
				              "data": "numberComment",
				              "sTitle":"评论数",
				              'sClass':"text-center"
				         },
				         { 
				                "data": "prefectureId",
				                "sTitle":"帖子专区",
				                'sClass':"text-center",
				                "mRender": function(data, type, full) { 
				                	if(data == 'a883bec3a23f4518a7ec93466f5asfa'){
										return "贷款专区";
									}else if(data == 'a883bec3a23f4518a7ec93466f5ecc'){
										return "信用专区";
									}else if(data == 'a883bec3a23f4518a7ec93466f5ecsdf'){
										return "生活理财";
									}else{
										return "其他";
									}
				                } 
				            },
				            { 
				                "data": "productNames",
				                "sTitle":"相关产品",
				                'sClass':"text-center"
				              
				            }
			            <shiro:hasPermission name="articlePost:update">
			            ,{"data": "articlePostId","sTitle":"操作",'sClass':"text-center"}
			            </shiro:hasPermission>
			         ],
					 fnServerParams: function (aoData) {  //查询条件
		             	aoData.push({ "name": "articlePostTitle", "value": $("#articlePostTitle").val() } );
		             	aoData.push({ "name": "phoneNum", "value": $("#phoneNum").val() } );
		             	aoData.push({ "name": "articlePostContent", "value": $("#articlePostContent").val() } );
		            	aoData.push({ "name": "nickname", "value": $("#nickname").val() } );
		            	aoData.push({ "name": "state", "value": $("#state").val() } ); 
		            	aoData.push({ "name": "isEssence", "value": $("#isEssence").val() } );
                        aoData.push({ "name": "startPosition", "value": $("#startPosition").val() } );
                        aoData.push({ "name": "endPosition", "value": $("#endPosition").val() } );
                        aoData.push({ "name": "postLable", "value": $("#postLable").val() } );
			         },
		             aoColumnDefs : [
		     						<shiro:hasPermission name="articlePost:update">
		     		             	{"aTargets" :16,"mRender" : function(data, type, row){
		     		             		var edit="",del="",comment;
		    		             		var pass='',rejected='';
		     		             		<shiro:hasPermission name="articlePost:update">
		    		             			edit = "<a href='#' onclick='addAndUpdateRow(\""+row.articlePostId+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑</a>";
		    		             			</shiro:hasPermission>
		    		             		
		    		             	 	<shiro:hasPermission name="articlePost:rejected">
		    		             	 	rejected = "<a href='#' onclick='fcReject(\""+row.articlePostId+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i>驳回</a>";
	    		             		</shiro:hasPermission>
	    		             		
	    		             		<shiro:hasPermission name="articlePost:pass">
	    		             		  pass = "<a href='#' onclick='fcPass(\""+row.articlePostId+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i>通过</a>";
    		             		    </shiro:hasPermission>
		    		             		
		    		             		<shiro:hasPermission name="articlePost:delete">
		    		             			del ="<a href='#' onclick='deleteRow(\""+row.articlePostId+"\")' data-toggle='modal' class='label label label-danger'><i class='glyphicon glyphicon-trash'></i> 删除 </a>";
		    		             		</shiro:hasPermission>
		    		             		
		    		             		 comment = "<a href='#' onclick='commentRow(\"" + row.articlePostId +
	                                        "\")' data-toggle='modal' class='label label label-success'><i class='glyphicon glyphicon-trash'></i> 查看评论 </a>";
		    		                  
	                                        if(row.state==1){ //已通过
	                                        	return edit+"&nbsp;"+rejected+"&nbsp;"+del+ "&nbsp;" +comment ;
	                                        }else if(row.state==4){  //未通过
	                                        	return edit+"&nbsp;"+pass+"&nbsp;"+del+ "&nbsp;" +comment ;
	                                        }else{
	                                            return edit+"&nbsp;"+rejected+"&nbsp;"+pass+"&nbsp;"+del+ "&nbsp;"+comment ;
	                                        }
		     		                }
		     		             }
		     		            	</shiro:hasPermission>
		     		             ]
				});
				
				 $('#query').click(function(){
					$('#example').dataTable().fnDraw();
				});
				 
			}
			
		   function fcPass(str){
			   var batchPass = 'articlePost/batchPass.htm';
			   batchAudit(batchPass,str);
			   
		   }
		   
		   function fcReject(str){
			   var batchRejected = 'articlePost/batchRejected.htm';
			   batchAudit(batchRejected,str);
		   }
			
			
			function allCheck(){
				setTimeout(allSun, 150);
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
			
			//查看评论
            function commentRow(id) {
                $('#insertAndUpdate').addAndUpdateRow("articlePost/detail.htm?postId=" + id);
            }
			
			//更新受影响的行数
			function deleteRow(id){
				console.log(id);
				$('#myModal').deleteRow('articlePost/del.htm?id='+id);
			}
			//增加或者修改受影响的行数
			function addAndUpdateRow(id){
				$('#insertAndUpdate').addAndUpdateRow("articlePost/addAndUpdateHome.htm?id="+id);
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