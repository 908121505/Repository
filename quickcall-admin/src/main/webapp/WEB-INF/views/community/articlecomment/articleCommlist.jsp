<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<shiro:hasPermission name="articleComm:select">
    <div class="content1">
        <div class="header">
            <h1 class="page-title">文章评论列表</h1>
            <ul class="breadcrumb">
                <li>文章列表</li>
                <li class="active">文章评论列表</li>
            </ul>
        </div>
        <div class="main-content">
            <div class="row">
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">文章标题</div>
                            <input class="form-control" type="text" id="articleTitle">
                        </div>
                    </div>
                </div>
             
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">主题分类</div>
                            <select class="form-control" id="classification">
                            </select>
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
							onclick="batchAudit('article/comment/batchDel.htm','')" data-toggle='modal'>					
							<i class="glyphicon glyphicon-plus">
							</i> 批量删除
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
            //表格的初始化
            $(document).ready(function () {
                var table = $('#example').initTable({
                    sAjaxSource: "article/comment/initTable.htm",
                    aoColumns: [
        				{ 
        					'sClass':"text-center",
        					"sTitle":"<input type='checkbox' onclick='allCheck()' id='all' >",
        					"mRender": function(data, type, full) { 
        				           return "<input type='checkbox'  name='sun' id='"+full.articleCommentId+"' value='"+full.articleCommentId+"'>";
        				     } 
        				},  
                        {
                            "data": "articleTitle",
                            "sTitle": "文章标题",
                            'sClass': "text-center"
                        },
                        {
                            "data": "content",
                            "sTitle": "评论内容",
                            'sClass': "text-center"
                        },
                        {
                            "data": "readNum",
                            "sTitle": "阅读数",
                            'sClass': "text-center"
                        },
                        {
                            "data": "praiseNum",
                            "sTitle": "点赞数量",
                            'sClass': "text-center"
                        },
                        {
                            "data": "createTime",
                            "sTitle": "创建时间",
                            'sClass': "text-center"
                        },
                        {
                            "data": "articleCommentId",
                            "sTitle": "操作",
                            'sClass': "text-center"
                        }
                       
                    ],
                    fnServerParams: function (aoData) {  //查询条件
                        aoData.push({"name": "articleCommentId", "value": $("#articleCommentId").val()});
                        aoData.push({"name": "articleTitle", "value": $("#articleTitle").val()});
                        aoData.push({"name": "classification", "value": $("#classification").val()});
                    }, aoColumnDefs: [
                    {
                            "aTargets":6, "mRender": function (data, type, row) {
                            var edit = "", del = "", comment = "", detail = "";
                            <shiro:hasPermission name="article:delete">
                            del = "<a href='#' onclick='deleteRow(\"" + data + "\")' data-toggle='modal' class='label label label-danger'><i class='glyphicon glyphicon-trash'></i> 删除 </a>";
                            </shiro:hasPermission>
                            return edit + "&nbsp;" + del + "&nbsp;" + comment;
                        }
                    }
                ]
                    
                });

                $('#query').click(function () {
                    $('#example').dataTable().fnDraw();
                });

                $('#a_cancel').click(function () {
                    $("#a_content").hide();
                });

            });
            
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
			
            //删除受影响的行数
            function deleteRow(id) {
                $('#myModal').deleteRow('article/comment/del.htm?id=' + id);
            }
            
           //查询评论分类
           comment();
		   function comment(){
			   
				$.ajax({
					dataType: "json", 
					url: "article/comment/queryClassArticle.htm",
					type:"GET",
					contentType: "application/json;charset=utf-8",
					async: false,
					success: function(data){
						//刷新列表
						 $("#classification").html(data);
					},
					error:function(data){
						//刷新列表
						 $("#classification").html(data);
					}
					});
		   }
			
  
        </script>
        <!---dialog选项-->
        <div>
            <jsp:include page="/WEB-INF/views/common/delete_dialog.jsp"/>
            <jsp:include page="/WEB-INF/views/common/addupdate_dialog.jsp"/>

            <div class="modal fade" id="insertAndUpdate" tabindex="-1"
                 role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            </div>

           
        </div>
    </div>
</shiro:hasPermission>