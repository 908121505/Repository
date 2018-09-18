<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<shiro:hasPermission name="dailyRevenue:select">
    <div class="content1">
        <div class="header">
            <h1 class="page-title">每日对账明细</h1>
            <ul class="breadcrumb">
                <li>商务后台</li>
                <li class="active">每日对账明细</li>
            </ul>
        </div>
        <shiro:hasPermission name="dailyRevenue:upload">
        <input type="hidden" value="1" id="isAdmin"/>
        </shiro:hasPermission>
        <div class="main-content">
            <div class="row">
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">商户名称</div>
                            <input class="form-control" type="text" id="productName1">
                            <input class="form-control" type="hidden" id="productIdStr"  value ="${productIdStr}">
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">录入人</div>
                            <input class="form-control" type="text" id="tallyer1">
                        </div>
                    </div>
                </div>
                
                  <div class="col-md-4">
                    <div class="form-group">
                        <div class="input-group">
                            
                            <div class="input-group-addon">对账日期</div>
                            <input class="form-control" type="date" id="stallyDate">
                            <div class="input-group-addon">到</div>
                            <input class="form-control" type="date" id="etallyDate">
                        </div>
                    </div>
                </div> 
            </div>
            <div class="row">  
                <div class="col-md-1">
                    <button type="button" class="btn btn-primary btn-small btn-block"
                            id="query">
                        <i class="glyphicon glyphicon-search"></i> 查询
                    </button>
                </div>
                <shiro:hasPermission name="dailyRevenue:download">
              
                
                
                
                
                
                 <div class="col-md-1">
                    <div class="form-group">
                        <a class="btn btn-primary btn-small btn-block" id="user_export" onclick="exportUserInfo();">导出</a>
                    </div>
                </div> 
                </shiro:hasPermission>
                
                
                <div class="col-md-1">
                    <button type="button" class="btn btn-primary btn-small btn-block"  onclick="add()" >
                        <i class="glyphicon glyphicon-search"></i> 新增
                    </button>
                </div>
                <div class="col-md-3">
                    <input type="file" class="form-control" id="importDataFile"  name="importDataFile" style="width: 60%;float: left;"> 
							<button class="btn btn-success" type="button" id="uploadDataFile" style="width: 30%;float: left;">Excel导入</button>
                </div> 
                <div class="col-md-6">
                </div> 
            </div>
            <table id="example" class="table"></table>
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
		
            //表格的初始化
            $(document).ready(function() {
                var table = $('#example').initTable({
                    sAjaxSource:"dailyRevenue/initTable.htm",
                    aoColumns: [
                    	{
                            sTitle: '序号',
                            data: "no",
                            className: 'text-center whiteSpace',
                            render:function(data,type,row,meta) {
                            	if(data==null||data==''){
                                return meta.row + 1 ;
                            	}else{
                            		return data;
                            	}
                            }
                        },
                        {
                            "data": "companyName",
                            "sTitle":"公司名称",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                            	if(data==''||data==null){
                                return	"--";
                            	}else{
                            		if(data.length > 6){
										var head =  data.substring(0,6);
                                        return "<font>"+head +"......</font>";
									}else{
										return "<font>"+data+"</font>";
									}
                            	}
                            }
                        },
                        {
                            "data": "productName",
                            "sTitle":"商户名称",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                            	if(data==''||data==null){
                                return	"--";
                            	}else{
                            		return data;
                            	}
                            }
                        },
                        {
                            "data": "tallyTime",
                            "sTitle":"对账日期",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                            	if(data==''||data==null){
                                return	"--";
                            	}else{
                            		return data;
                            	}
                            }
                        },
                    /*     {
                            "data": "sort",
                            "sTitle":"权重",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                            	if(data==''||data==null){
                                return	"--";
                            	}else{
                            		return data;
                            	}
                            }
                        },
                        {
                            "data": "isRecommend",
                            "sTitle":"是否推荐",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                            	if(data==''||data==null){
                                return	"--";
                            	}else{
                            		if(data==1){
                            			return '是';
                            		}else{
                            			return '否';
                            		}
                            		return data;
                            	}
                            }
                        }, */
                        
                        {
                            "data": "cpa",
                            "sTitle":"CPA/元",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                            	if(data==''||data==null){
                                return	"--";
                            	}else{
                            		return data.toFixed(2);
                            	}
                            }
                        },
                        {
                            "data": "registCount",
                            "sTitle":"注册个数/个",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                            	if(data==''||data==null){
                                return	"--";
                            	}else{
                            		return data;
                            	}
                            }
                        },
                        {
                            "data": "expectCount",
                            "sTitle":"期望注册个数/个",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                            	if(data==''||data==null){
                                return	"--";
                            	}else{
                            		return data;
                            	}
                            }
                        },
                        {
                            "data": "uv",
                            "sTitle":"UV",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                            	if(data==''||data==null){
                                return	"--";
                            	}else{
                            		return data;
                            	}
                            }
                        },
                        {
                            "data": "percent",
                            "sTitle":"转化率",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                            	if(data==''||data==null){
                                return	"--";
                            	}else{
                            		return data.toFixed(2)+"%";
                            	}
                            }
                        },
                        {
                            "data": "tallyAmount",
                            "sTitle":"当日营收/元",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                            	if(data==''||data==null){
                                return	"--";
                            	}else{
                            		return data;
                            	}
                            }
                        },
                        {
                            "data": "manzuStr",
                            "sTitle":"满足率",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                            	if(data==''||data==null){
                                return	"--";
                            	}else{
                            		return data;
                            	}
                            }
                        },
                        {
                            "data": "tallyer",
                            "sTitle":"录入人",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                            	if(data==''||data==null){
                               		 return	"--";
                            	}else{
                            		return data;
                            	}
                            }
                        },
                        {
                            "data": "createTime",
                            "sTitle":"对账录入时间",
                            'sClass':"text-center",
                            "mRender": function(data, type, full) {
                            	if(data==''||data==null){
                                	return	"--";
                            	}else{
                            		return	Format(new Date(data),"yyyy-MM-dd HH:mm:ss");
                            	}
                            }
                        }
                        
                        <shiro:hasPermission name="dailyRevenue:delete or dailyRevenue:update">
                        	,{"data": "id","sTitle":"操作",'sClass':"text-center"}
                        </shiro:hasPermission> 
                    ],
                    fnServerParams: function (aoData) {  //查询条件
                        aoData.push({ "name": "productName", "value": $("#productName1").val().replace(new RegExp(" ","g"),"") } );
                        aoData.push({ "name": "productId", "value": $("#productIdStr").val() } );
                        aoData.push({ "name": "tallyer", "value": $("#tallyer1").val().replace(new RegExp(" ","g"),"") } );
                        aoData.push({ "name": "stallyDate", "value": $("#stallyDate").val()});
                        aoData.push({ "name": "etallyDate", "value": $("#etallyDate").val() } );
                        aoData.push({ "name": "isAdmin", "value": $("#isAdmin").val() } );
                    }
                ,
                aoColumnDefs: [
                    {"aTargets" :13,"mRender" : function(data, type, row){
						var edit="",del="";
					<shiro:hasPermission name="dailyRevenue:update">
						edit ="<a href='#' onclick='addAndUpdateRow(\""+row.id+"\")' data-toggle='modal' class='padding-right-small label label-success'><i class='glyphicon glyphicon-edit'></i> 编辑 </a>";
					</shiro:hasPermission>
					<shiro:hasPermission name="dailyRevenue:delete">
						del="<a href='#' onclick='deleteRow(\""+row.id+"\")' data-toggle='modal' class='label label label-danger'><i class='glyphicon glyphicon-trash'></i> 删除 </a>";
					</shiro:hasPermission>
					if(row.id==null||row.id==''){
						return '--';
					}else{
					
				    	return edit+"&nbsp;"+del;
                    }
				}
				}
                   
                ]
                });
                
                
              //手机号码文件上传
        		$('#uploadDataFile').click(function() {
        			// var file = document.sendMessageForm.importDataFile.value;
        			var file = $("#importDataFile").val();
        			if(file == ""){
        				// $("#tip").html("请选择文件");
        				alert("请选择文件");
        				return false;
        			}
        			
        			if(!/\.(XLSX)$/.test(file.toUpperCase())){
        				$("#tip").html("文件格式错误，请选择Excel2007或者以上版本文件");
        				return false;
        			}
        			
        			
        			
        			$.ajaxFileUpload({
        				type : "post",
        				dataType : "json",
        				fileElementId : 'importDataFile',
        				url : "dailyRevenue/tally.htm",
        				data : {
        					"id" : ""
        				},
        				error : function(data) {
        					alert("错误！");
        				},
        				success : function(data) {
        					if (data.result = 'success') {
        						alert(data.msg);
        						$("#importDataFile").val("");
        					} else {
        						alert(data.msg);
        						$("#importDataFile").val("");
        					}
        				}
        			});
        		});

                $('#query').click(function(){
                    $('#example').dataTable().fnDraw();
                });
                

				var  productIdStr =  "${productIdStr}";
				if(productIdStr.length > 0){
					$('#query').trigger("click");
					$("#productIdStr").val("");
				}

                
                
                

            });
            function exportUserInfo(){
                var href="export/dailyRevenue.htm"
                        +"?productName="+encodeURI($("#productName1").val())
                        +"&tallyer="+encodeURI($("#tallyer1").val())
                        +"&stallyDate="+$("#stallyDate").val()
                        +"&etallyDate="+$("#etallyDate").val();
                        if($("#isAdmin").val()!=undefined){
                        	href+="&isAdmin="+$("#isAdmin").val();
                        }
                $('#user_export').attr("href",href);
            }
            
             //删除受影响的行数
            function deleteRow(id) {
                console.log(id);
                $('#myModal').deleteRow('dailyRevenue/del.htm?id=' + id);
            }
            function addAndUpdateRow(id) {
                $('#insertAndUpdate').addAndUpdateRow("dailyRevenue/addAndUpdateHome.htm?id=" + id);
            }
            function add() {
                $('#insertAndUpdate').addAndUpdateRow("dailyRevenue/add.htm?isAdmin=" + $("#isAdmin").val());
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